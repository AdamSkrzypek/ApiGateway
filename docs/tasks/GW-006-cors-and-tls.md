# GW-006 — CORS i TLS na brzegu

**Status:** Proponowane
**Obszar:** API Gateway
**Zależność:** GW-002; originy od klientów

## Zakres

- Ustalić dozwolone originy, metody, nagłówki i obsługę credentials dla każdego klienta.
- Skonfigurować CORS centralnie i usunąć konflikty z konfiguracją usług.
- Dla wdrożenia poza lokalnym środowiskiem ustalić terminację TLS, zarządzanie certyfikatami i ochronę ruchu Gateway→usługa.

## Kryteria akceptacji

1. Dozwolony preflight i request przechodzą, a niedozwolony origin nie otrzymuje uprawnień CORS.
2. Nie ma konfiguracji wildcard origin razem z credentials.
3. W środowisku docelowym ruch publiczny jest szyfrowany, a porty downstream nie są dostępne publicznie.
