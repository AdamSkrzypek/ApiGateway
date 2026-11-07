# GW-002 — Kontrakt publicznego API i routing usług

**Status:** Proponowane
**Obszar:** API Gateway
**Zależność:** GW-001

## Cel i wartość

Klient ma jeden stabilny adres wejściowy, a zespół zna publiczne ścieżki oraz ich mapowanie na API usług.

## Zakres

- Spisać metody i ścieżki wszystkich endpointów OrderController oraz kolejnych usług.
- Dodać jawne trasy dla każdej operacji, zachowując query string, body i wymagane nagłówki.
- Opisać mapowanie `/v2/**` na `/v1/**` i podjąć decyzję, czy jest utrzymywane.
- Nie udostępniać szerokim wildcardem endpointów administracyjnych ani wewnętrznych.
- Skonfigurować routing przez Eureka i Spring Cloud LoadBalancer; statyczne URI pozostawić wyłącznie jako świadomy wariant lokalny/testowy.

## Kryteria akceptacji

1. Każdy zatwierdzony endpoint usługi ma jednoznaczną trasę publiczną.
2. Nieznana ścieżka nie jest przekazywana przypadkowo do usługi.
3. Parametry, walidacja, kody odpowiedzi i błędy zachowują kontrakt usługi.
4. Dwie instancje usługi zarejestrowane pod tym samym ID mogą obsłużyć ruch przez load balancer.

## Otwarte decyzje

- Czy wersja publiczna ścieżki pokrywa się z wersją API downstream?
- Czy `/v2/**` jest trwałą kompatybilnością, czy należy je usunąć?
