# GW-003 — Kontrakt uwierzytelniania i uprawnień

**Status:** Proponowane
**Obszar:** API Gateway / Auth Service
**Zależność:** GW-002

## Cel i wartość

Ustalić, kto może korzystać z API i jak usługi rozpoznają użytkownika lub klienta systemowego.

## Zakres

- Zdefiniować issuer, audience, algorytm, JWKS, claims ról/scope, TTL i odwoływanie/rotację kluczy JWT.
- Wypisać endpointy publiczne, role/scope wymagane przez operacje oraz typy klientów.
- Rozstrzygnąć walidację tokenu w Gateway i usługach. Nie opierać bezpieczeństwa na nagłówkach tożsamości, które klient może podrobić lub wysłać bezpośrednio do usługi.
- Określić zachowanie przy błędzie lub niedostępności JWKS.

## Kryteria akceptacji

1. Istnieje zatwierdzona tabela endpoint → wymagane uwierzytelnienie/uprawnienie.
2. Kontrakt tokenu jest współdzielony i możliwy do zweryfikowania przez Gateway oraz usługi.
3. Ustalono granicę sieciową uniemożliwiającą ominięcie Gateway albo każda usługa samodzielnie weryfikuje token.

## Otwarte decyzje

- Czy Auth Service już istnieje i jaki kontrakt wystawia?
- Czy API obsługuje machine-to-machine, użytkowników, czy oba przypadki?
