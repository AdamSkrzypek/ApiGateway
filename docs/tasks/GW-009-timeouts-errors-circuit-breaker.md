# GW-009 — Timeouty, błędy i Circuit Breaker

**Status:** Proponowane
**Obszar:** API Gateway
**Zależność:** GW-002, GW-007

## Zakres

- Ustalić timeouty per typ trasy i spójny format błędów Gateway.
- Włączyć Circuit Breaker dla wybranych downstreamów i zdefiniować progi oraz recovery.
- Retry rozważać wyłącznie dla operacji bezpiecznych/idempotentnych albo chronionych kluczem idempotencji.
- Nie maskować awarii odpowiedzią fallback, która sugeruje fałszywy sukces.

## Kryteria akceptacji

1. Wolny downstream kończy się w uzgodnionym czasie.
2. Awaria serwisu nie wyczerpuje wątków Gateway i jest widoczna w metrykach.
3. Retry nie duplikuje tworzenia ani modyfikacji zamówienia.
