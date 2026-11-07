# GW-001 — Uruchamianie i debugowanie usług w Dockerze

**Status:** Do weryfikacji
**Obszar:** API Gateway, Order Service, lokalne środowisko
**Zależności:** brak

## Cel i wartość

Uruchamiać Gateway i Order Service jako kontenery podczas codziennej pracy. Po zmianie kodu i restarcie aplikacja ma korzystać z aktualnych plików źródłowych, bez ręcznego odtwarzania obrazu aplikacyjnego.

## Zakres i reguły

- Każdy serwis ma własny obraz developerski i konfigurację uruchomieniową, którą można odpalić niezależnie.
- Kod źródłowy i katalog wynikowy Maven są dostępne w kontenerze; kompilacja/uruchomienie odbywa się w kontenerze z obsługą restartu po zmianie.
- Zależności infrastrukturalne i Eureka są osiągalne pod nazwami DNS sieci kontenerowej, a nie `localhost` z perspektywy kontenera.
- Sekrety i adresy zależności są przekazywane przez zmienne środowiskowe.
- Istnieje też powtarzalny sposób zbudowania samodzielnego obrazu aplikacyjnego do uruchomienia bez montowania kodu.

## Kryteria akceptacji

1. Gateway i Order Service można zbudować jako obrazy i uruchomić jako kontenery.
2. Po zmianie kodu i restarcie odpowiedniego kontenera uruchamia się świeża kompilacja.
3. Gateway rejestruje się/komunikuje z Eureka oraz przekazuje żądanie do Order Service po jego identyfikatorze usługi.
4. Order Service łączy się z kontenerami PostgreSQL i Kafka po nazwach usług.
5. Instrukcja opisuje komendy startu, restartu, logów, debugowania i budowy samodzielnego obrazu.

## Zależności i uwagi

- Sprawdzić działanie DevTools przy bind mountach i konfiguracji IntelliJ; nie zakładać automatycznego restartu JVM bez potwierdzenia.
- Uzgodnić port debuggera i sposób jego włączenia wyłącznie w profilu developerskim.
- Nie publikować portów PostgreSQL/Kafka poza hosta, jeśli nie są potrzebne do pracy lokalnej.

## Uruchamianie lokalne

Z katalogu `Shop`:

```shell
docker compose up --build -d
docker compose logs -f api-gateway order-service-1
docker compose restart api-gateway
docker compose restart order-service-1
```

Kod jest montowany z hosta, a Maven uruchamia aplikację z tego katalogu. Restart kontenera ponownie wykonuje `mvn spring-boot:run`, więc kompiluje aktualne źródła. Porty debuggera: Gateway `localhost:5005`, Order Service 1 `localhost:5006`, Order Service 2 `localhost:5007`.

Samodzielne obrazy aplikacyjne można zbudować z katalogu każdego projektu przez `mvn spring-boot:build-image`; nazwy domyślne to `api-gateway:local` oraz `order:local`.
