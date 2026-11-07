# GW-011 — Operacyjne przygotowanie Eureki

**Status:** Proponowane
**Obszar:** API Gateway / Eureka
**Zależność:** GW-001

## Zakres i kryteria akceptacji

- Parametry registry, porty i adresy instancji pochodzą z konfiguracji środowiskowej; `localhost` służy tylko lokalnemu uruchomieniu poza Dockerem.
- Określić wpływ wspólnego procesu Gateway/Eureka na restart, skalowanie, health i wdrożenie.
- Ustalić konfigurację Eureka dla wielu instancji i obsługę restartu/utraty registry.
- Zachować możliwość późniejszego wydzielenia registry bez zmiany publicznego kontraktu Gateway.
