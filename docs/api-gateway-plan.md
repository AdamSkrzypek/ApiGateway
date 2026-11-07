# API Gateway — plan rozwoju

Plan łączy bieżący stan projektu z kierunkiem prac. Gateway ma być jedynym publicznym wejściem do API usług Shop, warstwą egzekwowania polityk brzegowych oraz miejscem wspólnej obserwowalności. Eureka pozostaje na razie w tym samym projekcie i procesie z powodów organizacyjnych oraz edukacyjnych.

## Cel i zakres

- Klienci zewnętrzni wywołują API usług przez Gateway; ruch klienta nie omija bramki.
- Gateway mapuje publiczne ścieżki i metody na endpointy usług, korzystając z Eureka i Spring Cloud LoadBalancer.
- Gateway weryfikuje uwierzytelnienie i egzekwuje reguły dostępu, ale usługi nadal chronią swoje zasoby i nie ufają bezwarunkowo nagłówkom tożsamości.
- Wspólne mechanizmy brzegowe obejmują CORS, correlation/trace ID, limity ruchu oraz metryki i health checks.
- Gateway nie zawiera logiki domenowej, nie agreguje odpowiedzi biznesowych bez osobnego uzasadnienia i nie zastępuje komunikacji serwis-serwis.

## Stan obecny (sprawdzony w kodzie)

- Projekt używa Spring Cloud Gateway Server MVC, czyli servlet/MVC, a nie WebFlux.
- Eureka Server działa w tej samej aplikacji co Gateway (`@EnableEurekaServer`). Klient nie rejestruje tej instancji jako klienta Eureki, ale pobiera registry z `http://localhost:8080/eureka/`.
- Routing Order Service korzysta z `lb://order-service`. Konfiguracja obejmuje osiem operacji z `OrderController` oraz dodatkowy passthrough `/v2/**`, który przepisuje ścieżkę na `/v1/**`.
- Actuator jest zależnością, a publicznie wystawiony jest wyłącznie endpoint `health`. Nie ma jeszcze konfiguracji Spring Security, pomiaru requestów ani reguł CORS.
- Obecny routing ma własne `ConfigurationApiProperties` i `RouterFunction`; kolejne usługi powinny być dodawane w sposób konsekwentny, bez tworzenia niekontrolowanego passthrough dla dowolnych ścieżek.

## Decyzje architektoniczne i warunki

### MVC pozostaje aktualnym modelem

Kod używa Gateway Server MVC, więc filtry, security i pomiar czasu należy projektować dla servlet stacku. `OncePerRequestFilter` jest możliwym narzędziem do własnego zachowania servletowego, ale nie powinien dublować standardowych pomiarów Micrometera. Poprzedni szkic WebFlux w tym dokumencie był niezgodny z implementacją i nie jest aktualną decyzją.

### Gateway jako jedyne wejście zewnętrzne

Samo dodanie routingu nie gwarantuje, że ruch przechodzi przez Gateway. W środowisku uruchomieniowym porty usług downstream powinny być niedostępne dla klientów zewnętrznych (sieć Docker/Kubernetes, reguły ingress/firewall). Bez tej granicy klient mógłby ominąć uwierzytelnianie i limity ustawione na Gateway.

### Uwierzytelnianie i autoryzacja

Rekomendowany kierunek to OAuth2 Resource Server na Gateway, walidujący JWT wystawiane przez Auth Service. Publiczne endpointy (np. logowanie i health check wymagany przez platformę) muszą być jawnie określone; pozostałe trasy domyślnie wymagają uwierzytelnienia. Uprawnienia do operacji powinny być opisane jako reguły per trasa/zasób, a nie jako ogólne „użytkownik zalogowany”.

Nie przyjmować wariantu, w którym usługi ufają `X-User-Id`/`X-Roles` bezwarunkowo, jeśli mogą być osiągalne z pominięciem Gateway. Docelowo usługi powinny samodzielnie weryfikować JWT albo komunikacja i tożsamość Gateway→usługa muszą być zabezpieczone równoważnym, wymuszonym mechanizmem wewnętrznym. Propagowane nagłówki to dane kontekstowe, nie samodzielny dowód tożsamości. Auth Service, kontrakt tokenów (issuer, audience, role/scope, TTL, JWKS) i reguły dostępu wymagają osobnego uzgodnienia.

### Eureka w tym samym projekcie

Akceptujemy na tym etapie wspólne repozytorium i aplikację Gateway + Eureka. To upraszcza lokalny ekosystem i odpowiada obecnemu celowi. Kosztem jest wspólny cykl wdrażania, konfiguracji i awarii: restart lub przeciążenie aplikacji dotyka jednocześnie bramki i registry, a skala/redundancja tych ról nie jest niezależna. Przy wdrożeniu wieloinstancyjnym trzeba osobno zaplanować dostępność Eureki i jej konfigurację klastrową; obecne ustawienie localhost jest lokalne. Rozdzielenie na osobny serwis pozostaje możliwą późniejszą zmianą, nie warunkiem rozpoczęcia prac.

## Kolejność prac

### 1. Domknięcie routingu i kontraktu wejściowego

- Spisać publiczne endpointy wszystkich obecnych usług: metoda, ścieżka zewnętrzna, ścieżka downstream, wymagane role/scope i ewentualne wyjątki.
- Dodać brakujące trasy do usług i ustalić konwencję wersjonowania. Utrzymać jawne mapowanie i nie wystawiać przypadkowo wewnętrznych endpointów, konsoli administracyjnych ani całego API przez szeroki wildcard.
- Zweryfikować zachowanie metod, ścieżek, query stringów, nagłówków, payloadów i odpowiedzi błędów przy proxy. Ustalić, czy obecne `/v2/**` dla Order Service jest celową kompatybilnością i opisać jej mapowanie.
- Ustalić, które porty downstream są dostępne wyłącznie wewnątrz sieci usług.

### 2. Polityka bezpieczeństwa

- Uzgodnić kontrakt JWT z Auth Service: issuer, audience, algorytm podpisu, JWKS, claims ról/scope, wygaśnięcie i zachowanie przy niedostępnym JWKS.
- Włączyć Resource Server na Gateway i reguły dostępu domyślnie odmawiające dostępu; jawnie dopuścić tylko uzgodnione trasy publiczne.
- Określić autoryzację per operacja. Gateway może egzekwować politykę brzegową, ale reguły domenowe (np. czy użytkownik może zobaczyć konkretny order) pozostają w usłudze posiadającej dane.
- Usunąć lub nadpisać przychodzące od klienta nagłówki tożsamości przed propagacją kontekstu. Usługi nie mogą ufać tym nagłówkom przy bezpośrednio dostępnym porcie.
- Skonfigurować CORS zgodnie z originami i metodami klientów; nie używać wildcard credentials.

### 3. Obserwowalność i health

- Najpierw wykorzystać Actuator/Micrometer do liczby requestów, czasu odpowiedzi i statusów; sprawdzić, jakie metryki Gateway Server MVC i Spring Boot już rejestrują w obecnym stosie.
- Ustalić backend eksportu metryk i granice kardynalności tagów. Nie używać surowej ścieżki zawierającej identyfikatory jako etykiety metryki; preferować identyfikator trasy.
- Dodać correlation ID, jeśli tracing nie dostarcza już trace ID. Akceptować zewnętrzny identyfikator tylko po walidacji formatu albo generować nowy; propagować go do downstreamów i logów.
- Własny filtr pomiarowy (`OncePerRequestFilter`) dodawać tylko wtedy, gdy Micrometer nie daje potrzebnego wymiaru. Mierzyć pełny cykl proxy, w tym błąd downstream/timeout, i nie logować tokenów, danych osobowych ani treści requestu.
- Pozostawić `/actuator/health` jako wymagany probe i ograniczyć ekspozycję oraz dostęp do pozostałych endpointów Actuatora. Rozważyć osobne liveness/readiness i health zależności, gdy wymaga tego sposób wdrożenia.

### 4. Odporność i kontrola nadużyć

- Dla każdej trasy określić timeouty, zachowanie przy niedostępnej usłudze i spójny format błędu Gateway.
- Dodać Circuit Breaker dla wybranych tras, z odpowiedzią awaryjną tylko tam, gdzie ma sens biznesowy.
- Retry stosować wyłącznie dla bezpiecznych/idempotentnych operacji lub przy wiarygodnym kluczu idempotencji; nie ponawiać automatycznie tworzenia/zamówień bez takiej gwarancji.
- Rate limiting z Redisem wprowadzić po określeniu tożsamości limitowanego klienta, progów, odpowiedzi 429 i zachowania przy awarii Redis. Rozróżnić limity per IP, użytkownik i kosztowne endpointy.

### 5. Uruchamianie i operacje

- Ujednolicić konfigurację adresu Eureki i usług przez profile/zmienne środowiskowe; usunąć założenie `localhost` z konfiguracji wdrożeniowej.
- Dodać lokalny sposób uruchamiania Gateway, Eureki i pozostałych usług w jednej sieci, z health checks.
- Przed środowiskiem produkcyjnym określić TLS na brzegu, sekrety, limity zasobów, logowanie, eksport metryk i skalowanie osobno Gateway/Eureka.

## Kryterium „ruch idzie przez Gateway”

Klient korzysta z publicznego adresu Gateway; trasy publiczne są jawnie zarejestrowane; usługi nie są osiągalne z publicznej sieci; endpointy operacyjne mają kontrolowany dostęp; test ścieżki potwierdza, że żądanie przechodzi przez politykę bezpieczeństwa i routing Gateway.

## Otwarte decyzje produktowe / analityczne

- Jakie klienty i aplikacje będą korzystać z API oraz jakie są dozwolone originy CORS?
- Które endpointy są publiczne, a które wymagają jakich ról/scope? Jak wygląda model użytkowników i uprawnień?
- Czy API ma obsługiwać wyłącznie użytkowników, czy również klientów systemowych?
- Jakie są oczekiwane progi ruchu, limity, wymagania dostępności i docelowy backend metryk?
- Czy `/v2/**` jest trwałym publicznym kontraktem, czy tymczasową trasą kompatybilności?

## Odniesienie do wcześniejszych notatek

Poprzednia wersja planu rekomendowała WebFlux i odkładanie Eureki oraz opisywała walidację JWT wyłącznie na Gateway. Są to historyczne propozycje, nie stan obecny ani zatwierdzone wymagania. Repozytorium używa MVC i Eureki, a bezpieczny podział walidacji JWT wymaga opisanych wyżej warunków sieciowych i kontraktu z Auth Service. Konkretne wersje zależności z wcześniejszej notatki traktować jako historyczne; przed zmianą zależności weryfikować zgodność z aktualnym BOM-em projektu.
