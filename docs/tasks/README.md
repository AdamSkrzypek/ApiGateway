# Zadania API Gateway

Zadania są uporządkowane według zależności, a nie numeru do natychmiastowej implementacji. Najpierw należy doprecyzować zależności wskazane w nagłówku zadania.

1. **GW-001** — kontenery developerskie dla Gateway i Order Service
2. **GW-002** — publiczny kontrakt i routing; zależy od GW-001
3. **GW-003** — kontrakt uwierzytelniania; zależy od GW-002
4. **GW-004** — ochrona Gateway; zależy od GW-003
5. **GW-005** — ochrona downstream; zależy od GW-003
6. **GW-006** — CORS i TLS; CORS zależy od ustalenia klientów, TLS od środowiska wdrożenia
7. **GW-007** — metryki i tracing; podstawowe pomiary można zacząć po GW-002
8. **GW-008** — health i Actuator; zależy od sposobu uruchamiania z GW-001
9. **GW-009** — odporność; zależy od routingu i obserwowalności
10. **GW-010** — rate limiting; zależy od tożsamości klientów i wymagań ruchu
11. **GW-011** — operacje Eureki; zależy od środowiska uruchomieniowego
12. **GW-012** — wersjonowanie; decyzje kontraktowe mogą zacząć się przy GW-002

Każde zadanie opisuje oczekiwany rezultat i kryteria akceptacji; zakres można rozbić lub odrzucić po uzgodnieniu wymagań.
