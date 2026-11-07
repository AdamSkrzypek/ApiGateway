# GW-004 — Uwierzytelnianie i autoryzacja na Gateway

**Status:** Proponowane
**Obszar:** API Gateway
**Zależność:** GW-003

## Zakres

- Skonfigurować OAuth2 Resource Server i walidację JWT zgodną z uzgodnionym issuer/audience/JWKS.
- Stosować zasadę domyślnej odmowy; dopuścić jawnie tylko uzgodnione trasy publiczne i health check wymagany przez platformę.
- Egzekwować wymagane role/scope per operacja jako politykę brzegową.
- Odrzucać lub nadpisywać zewnętrzne nagłówki kontekstu użytkownika przed przekazaniem żądania dalej.
- Ograniczyć dostęp do endpointów administracyjnych i Actuatora.

## Kryteria akceptacji

1. Brak tokenu, token błędny, wygasły lub o niewłaściwym audience otrzymuje 401.
2. Poprawny token bez wymaganego uprawnienia otrzymuje 403.
3. Poprawny token z wymaganym uprawnieniem przechodzi do właściwej usługi.
4. Klient nie może wymusić tożsamości przez własne nagłówki.
