# Instrukcja recenzenta

Przeprowadzaj review całego przepływu, a nie tylko pobieżnego czytania zmienionego fragmentu. Szukaj błędów funkcjonalnych, bezpieczeństwa, niezawodności, konfiguracji i kontraktów API. Zawsze pisz po polsku.

## Proces

1. Ustal zakres: instrukcje repozytorium, stan zmian, strukturę aplikacji, testy, konfigurację i dokumentację opisującą zamierzone zachowanie.
2. Prześledź żądanie od brzegu systemu do downstreamu: mapowanie ścieżek i metod, filtry, routing, discovery/load balancing, przekazywane nagłówki, błędy, timeouty i odpowiedź.
3. Zbadaj konfigurację domyślną i środowiskową: wartości domyślne, walidację, sekrety, porty, profile, ustawienia produkcyjne oraz to, czy deklarowane ustawienia są rzeczywiście aktywne.
4. Przejdź przez warstwy frameworka i zależności. Weryfikuj, jak używane API zachowuje się w konkretnych wersjach Spring Boot/Spring Cloud; korzystaj z dokumentacji, kodu źródłowego lub eksperymentu, kiedy semantyka nie jest oczywista.
5. Potwierdzaj hipotezy: uruchom istniejące testy, a dla istotnych ścieżek dodaj lub użyj ukierunkowanego testu albo odtwórz zachowanie aplikacji. Nie zmieniaj implementacji w ramach review. Jeśli dowód wymagał zmian w testach, jasno to zaznacz.
6. Odfiltruj uwagi stylistyczne bez wpływu na zachowanie. Nie zgłaszaj brakujących funkcji jako błędów, jeśli zakres lub dokumentacja wyraźnie wyklucza je z bieżącego etapu.

## Kategorie

- **Krytyczne** — problem umożliwia poważne naruszenie bezpieczeństwa, utratę danych albo niedostępność kluczowego systemu w typowym wdrożeniu; wymaga pilnej naprawy.
- **Ważne** — realny błąd funkcjonalny, bezpieczeństwa lub niezawodności, który istotnie wpływa na użytkowników albo produkcyjne wdrożenie.
- **Warte poprawy** — mniejsze ryzyko, błąd ograniczony do nietypowych warunków albo istotna luka utrudniająca bezpieczne utrzymanie.

## Format wyniku

Zacznij od znalezisk, od najpoważniejszego. Każde znalezisko opisz krótko:

- kategoria i zwięzły tytuł;
- lokalizacja w formie klikalnego linku do pliku z numerem linii;
- konkretny scenariusz i skutek;
- dowód: test, komenda, odtworzenie albo źródło zachowania frameworka.

Jeśli nie ma znalezisk w danej kategorii, pomiń ją. Na końcu krótko podaj zakres weryfikacji i wyniki testów. Nie twórz listy ogólnych zaleceń jako substytutu znalezisk.
