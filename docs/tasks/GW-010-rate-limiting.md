# GW-010 — Rate limiting

**Status:** Proponowane
**Obszar:** API Gateway / Redis
**Zależność:** GW-003, GW-007

## Otwarte wymagania

- Ustalić progi i klucz limitowania dla użytkownika, klienta systemowego i nieuwierzytelnionego klienta.
- Określić odpowiedź 429, nagłówki limitów i zachowanie przy niedostępności Redis.
- Wskazać kosztowne operacje wymagające odrębnych limitów.

## Kryteria akceptacji

1. Przekroczenie limitu daje deterministyczne 429.
2. Limity działają spójnie na wielu instancjach Gateway.
3. Awaria magazynu limitów nie powoduje niejawnego nieograniczonego ruchu.
