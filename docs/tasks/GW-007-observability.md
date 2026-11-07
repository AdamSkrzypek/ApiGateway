# GW-007 — Metryki, tracing i korelacja żądań

**Status:** Proponowane
**Obszar:** API Gateway
**Zależność:** GW-002

## Zakres

- Sprawdzić metryki Spring Boot Actuator/Micrometer dostępne dla Gateway Server MVC.
- Udostępnić request count, czas odpowiedzi i status per route, z niską kardynalnością.
- Dodać Micrometer Tracing lub correlation ID i propagować kontekst do usług.
- Nie rejestrować tokenów, danych osobowych ani treści requestów.
- Dodać własny `OncePerRequestFilter` tylko wtedy, gdy standardowa instrumentacja nie zapewnia potrzebnego wymiaru.
- Wskazać backend metryk/tracingu i zabezpieczyć endpoint scrape.

## Kryteria akceptacji

1. Żądanie można prześledzić przez Gateway i usługę po wspólnym trace/correlation ID.
2. Metryki pokazują liczbę, opóźnienie i status per route bez surowych ID w tagach.
3. Błędy downstream i timeouty są widoczne i nie są liczone jako sukces.
