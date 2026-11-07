# GW-005 — Zabezpieczenie usług downstream

**Status:** Proponowane
**Obszar:** Order Service i kolejne usługi
**Zależność:** GW-003

## Cel i wartość

Chronić dane również w razie błędnej konfiguracji sieci, bezpośredniego wywołania usługi lub kompromitacji Gateway.

## Zakres i kryteria akceptacji

- Każda usługa weryfikuje JWT i własne reguły dostępu albo przyjmuje wyłącznie uwierzytelnioną komunikację serwisową, której nie da się podrobić z zewnątrz.
- Reguły domenowe, takie jak dostęp użytkownika do konkretnego zamówienia, pozostają w usłudze posiadającej dane.
- Porty usług nie są publicznie wystawione w lokalnym ani docelowym deployment.
- Próby obejścia Gateway nie pozwalają na nieuprawniony odczyt lub zmianę danych.
