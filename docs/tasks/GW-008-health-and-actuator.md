# GW-008 — Health checks i bezpieczny Actuator

**Status:** Proponowane
**Obszar:** API Gateway / Eureka
**Zależność:** GW-001

## Zakres i kryteria akceptacji

- Utrzymać health check dla Gateway i określić liveness/readiness w kontekście współlokowanej Eureki.
- Wystawiać wyłącznie potrzebne endpointy Actuatora i kontrolować ich dostęp.
- Skonfigurować healthcheck kontenera i test gotowości zależności bez uzależniania liveness od chwilowej awarii downstream.
- Udokumentować operacyjne skutki uruchamiania Eureka i Gateway w jednym procesie oraz sposób konfiguracji środowiskowej.
