# Czysta Architektura: Struktura i design oprogramowania — Podsumowanie Rozdziałów

Podręcznik *„Czysta architektura. Struktura i design oprogramowania. Przewodnik dla profesjonalistów”* (*Clean Architecture: A Craftsman's Guide to Software Structure and Design*) autorstwa Roberta C. Martina stanowi kompendium wiedzy na temat projektowania trwałych, elastycznych i łatwych w utrzymaniu systemów oprogramowania. Główną tezą książki jest wykazanie, że reguły architektury oprogramowania są niezależne od stosowanych technologii, frameworków czy narzędzi, a celem dobrej architektury jest zminimalizowanie nakładów pracy potrzebnych do zbudowania i utrzymania systemu.

Poniższy dokument zawiera wyczerpujące, bezosobowe i precyzyjne podsumowanie wszystkich 34 rozdziałów książki oraz dodatku historycznego, ukierunkowane na uniwersalne zasady inżynierii oprogramowania.

---

## Część I. Wprowadzenie

### 1. Rozdział: Czym są projekt i architektura?

Projekt (*design*) oraz architektura (*architecture*) stanowią jedno kontinuum decyzji i nie istnieje między nimi żadna sztuczna linia podziału. Decyzje architektoniczne dotyczą najwyższych poziomów struktury, podczas gdy decyzje projektowe dotyczą szczegółów niskopoziomowych — oba poziomy wspólnie tworzą spójną całość.

#### Cel architektury
Jedynym miarodajnym celem architektury oprogramowania jest zminimalizowanie zasobów ludzkich i nakładów pracy wymaganych do zbudowania, rozwijania i utrzymania danego systemu w całym okresie jego eksploatacji.

#### Studium przypadku i koszt bałaganu
* Brak dbałości o jakość architektury powoduje, że z każdym kolejnym wydaniem produktu produktywność zespołu spada, dążąc asymptotycznie do zera.
* Zwiększanie zatrudnienia w odpowiedzi na spadek wydajności pogłębia problem, generując wyższe koszty przy zminimalizowanym przyroście nowej funkcjonalności.
* Postawa zakładająca, że „kod można posprzątać później”, jest iluzją — bałagan tworzony w celu szybkiego dowiezienia funkcji natychmiast spowalnia dalszy rozwój.

---

### 2. Rozdział: Opowieść o dwóch wartościach

Każdy system oprogramowania dostarcza interesariuszom dwie wartości: **zachowanie** (*behavior*) oraz **strukturę** (*architecture*).

#### Zachowanie vs Architektura
* **Zachowanie**: Realizacja wymagań funkcjonalnych (sprawienie, aby program działał i przynosił zysk lub oszczędności). Jest to wartość pilna, ale nie zawsze najważniejsza.
* **Architektura**: Elastyczność i miękkość oprogramowania (*software*). Oprogramowanie stworzono po to, aby można było łatwo zmieniać jego zachowanie. Jeżeli wprowadzenie zmiany staje się zbyt kosztowne, system traci swoją podstawową wartość.

#### Macierz Eisenhowera
* Priorytetyzacja zadań wskazuje, że sprawy ważne i niepilne (architektura) mają wyższy priorytet niż sprawy pilne, ale nieważne (drobne poprawki zachowania).
* Zepchnięcie architektury na dalszy plan prowadzi do paraliżu rozwoju systemu. Dział inżynierii ma obowiązek walczyć o jakość architektury z tą samą determinacją, z jaką działy biznesowe walczą o funkcjonalności.

---

## Część II. Paradygmaty oprogramowania

### 3. Rozdział: Przegląd paradygmatów

Trzy główne paradygmaty oprogramowania, powstałe w latach 1958–1968, nie dodają nowych możliwości, lecz **odbierają** określone swobody programistom.

1. **Programowanie strukturalne**: Odbiera swobodę bezpośredniego transferu sterowania (`goto`).
2. **Programowanie obiektowe**: Odbiera swobodę pośredniego transferu sterowania (poprzez wskaźniki do funkcji).
3. **Programowanie funkcyjne**: Odbiera swobodę modyfikacji stanu zmiennych (wprowadza niezmienność).

Każdy paradygmat odpowiada za inny fundament architektury: programowanie strukturalne daje algorytmiczną dekompozycję, obiektowe umożliwia bezpieczny polimorfizm i odwrócenie zależności, a funkcyjne dyscyplinuje dostęp do danych.

---

### 4. Rozdział: Programowanie strukturalne

Wprowadzone przez Edsgera Dijkstrę programowanie strukturalne opiera się na zastąpieniu samowolnych skoków `goto` trójką podstawowych struktur sterujących: **sekwencją**, **wyborem** (`if/else`) oraz **pętlą** (`while`).

#### Dekompozycja funkcyjna i metoda naukowa
* Programowanie strukturalne umożliwia rekursywną dekompozycję złożonych modułów na mniejsze podprogramy.
* Formalne matematyczne dowodzenie poprawności oprogramowania okazało się niepraktyczne w skali przemysłowej. Zamiast tego oprogramowanie rozwija się metodą naukową poprzez **falsyfikację**.
* Testy jednostkowe nie dowodzą poprawności kodu — dowodzą jedynie obecności błędów lub ich braku w przetestowanych przypadkach. Architektura musi wspierać łatwą falsyfikowalność (testowalność) modułów.

---

### 5. Rozdział: Programowanie obiektowe

Istotą programowania obiektowego (OO) nie jest sama hermetyzacja czy dziedziczenie (mechanizmy te istniały już w językach C czy SIMULA), lecz **bezpieczny, językowo wspierany polimorfizm**.

#### Polimorfizm i Odwrócenie Zależności (DIP)
* Przed powstaniem OO polimorfizm realizowano za pomocą niebezpiecznych wskaźników do funkcji.
* Zastosowanie polimorfizmu pozwala na całkowite odwrócenie kierunku zależności w kodzie źródłowym względem przepływu sterowania.
* **Architektura wtyczkowa (*Plugin Architecture*)**: Dzięki odwróceniu zależności moduły wysokiego poziomu (reguły biznesowe) nie zależą od modułów niskiego poziomu (UI, baza danych). Moduły niskopoziomowe stają się wymiennymi wtyczkami, co umożliwia niezależne rozwijanie (*independent developability*) i niezależne wdrażanie (*independent deployability*).

---

### 6. Rozdział: Programowanie funkcyjne

Programowanie funkcyjne opiera się na rachunku lambda Alonsa Churcha i charakteryzuje się brakiem modyfikacji stanu zmiennych (*immutability*).

#### Podział zmienności i Niezmienność
* Wszystkie problemy ze współbieżnością, warunkami wyścigu (*race conditions*), zakleszczeniami (*deadlocks*) i spójnością pamięci wynikają wyłącznie z możliwości modyfikacji stanu zmiennych.
* Architektura powinna dążyć do wydzielenia jak największej części logiki do komponentów czysto funkcyjnych (niemodyfikowalnych), izolując sekcje modyfikujące stan w wyznaczonych, bezpiecznych kontenerach.
* **Pozyskiwanie zdarzeń (*Event Sourcing*)**: Przechowywanie jedynie niepodważalnej historii zdarzeń (transakcji) bez operacji edycji czy usuwania (CR zamiast CRUD) eliminuje problemy ze współbieżnością przy odpowiednim zasobie pamięci.

---

## Część III. Reguły projektowe (SOLID)

### 7. Rozdział: SRP — Reguła jednej odpowiedzialności

*Zasada Single Responsibility Principle na poziomie architektury*: **Moduł powinien mieć jeden i tylko jeden powód do zmiany**.

#### Definicja powody zmiany
* Powód do zmiany jest utożsamiany z jedną grupą osób lub interesariuszy (określaną jako **aktor**).
* Naruszenie SRP prowadzi do dwóch głównych symptomów:
  1. **Przypadkowa duplikacja**: Modyfikacja kodu na potrzeby jednego aktora (np. księgowości) uszkadza logikę wykorzystywaną przez innego aktora (np. kadry).
  2. **Złączenia i konflikty scalania**: Równoległa praca nad jednym plikiem przez różne zespoły.
* **Rozwiązanie**: Rozdzielanie klas operujących na danych od logiki biznesowej poszczególnych aktorów oraz stosowanie wzorca **Fasada** do koordynacji wywołań.

---

### 8. Rozdział: OCP — Reguła otwarte-zamknięte

*Zasada Open/Closed Principle*: **Element oprogramowania powinien być otwarty na rozbudowę, ale zamknięty na modyfikacje**.

#### Hierarchia ochrony architektonicznej
* Celem OCP na poziomie architektury jest uodpornienie istniejącego kodu na zmiany wymagań poprzez dodawanie nowego kodu zamiast modyfikacji starego.
* Osiąga się to poprzez podział systemu na komponenty o jednokierunkowych zależnościach.
* Komponenty wysokiego poziomu (interaktory i reguły biznesowe) znajdują się na szczycie hierarchii i są maksymalnie chronione przed zmianami w komponentach niższego poziomu (kontrolery, prezentery, widoki, bazy danych).

---

### 9. Rozdział: LSP — Zasada podstawień Barbary Liskov

*Zasada Liskov Substitution Principle*: **Klasy pochodne muszą być w pełni podstawialne za swoje klasy bazowe bez zmiany poprawności działania systemu**.

#### Implikacje architektoniczne
* Klasycznym przykładem naruszenia LSP jest problem kwadratu dziedziczącego po prostokącie.
* Na poziomie architektury naruszenie LSP (np. zmiana specyfikacji interfejsu REST lub formatu danych przez jednego z dostawców usług) wymusza dodawanie skomplikowanych mechanizmów obronnych, instrukcji warunkowych i przejściowych tabel masek, co niszczy czystość architektury.

---

### 10. Rozdział: ISP — Zasada rozdzielania interfejsów

*Zasada Interface Segregation Principle*: **Moduły nie powinny zależeć od interfejsów i metod, których nie używają**.

#### Wpływ na kompilację i architekturę
* Zależność od zbyt szerokiego interfejsu powoduje, że zmiana w metodzie używanej przez jeden moduł wymusza ponowną kompilację, przebudowę i wdrożenie pozostałych, niezaangażowanych modułów.
* Na poziomie architektury niepotrzebne zależności miedzy komponentami powodują przesyłanie zbędnych informacji i niepotrzebne przewdrożenia.

---

### 11. Rozdział: DIP — Zasada odwrócenia zależności

*Zasada Dependency Inversion Principle*: **Moduły wysokiego poziomu nie powinny zależeć od modułów niskiego poziomu — oba powinny zależeć od abstrakcji**.

#### Zasady stabilnych abstrakcji
1. Nie tworzyć zmiennych wskazujących na konkretne klasy nieulotne (używać interfejsów lub klas abstrakcyjnych).
2. Nie dziedziczyć po konkretnych klasach nieulotnych.
3. Nie nadpisywać konkretnych funkcji.
4. Granica architektoniczna przebiega w miejscu, gdzie konkretne fabryki (`Abstract Factory`) izolują tworzenie obiektów szczegółowych od logiki biznesowej.

---

## Część IV. Zasady komponentów

### 12. Rozdział: Komponenty

Komponenty są najmniejszymi jednostkami wdrożeniowymi w systemie (np. pliki `.jar`, `.dll`, `.gem`).

* Historia rozwoju komponentów to ewolucja od kart perforowanych, przez biblioteki relokowalne i programy łącząco-ładujące (*linking loaders*), aż po nowoczesne, dynamicznie łączone biblioteki binarne.
* Dobrze zaprojektowane komponenty zachowują możliwość niezależnego wdrożenia i niezależnego rozwoju.

---

### 13. Rozdział: Spójność komponentów

Spójność komponentów określa, które klasy i moduły powinny trafić do jednego pliku wdrożeniowego.

#### Zasadnicze reguły
1. **REP (*Release Equivalence Principle*)**: Zasada istotności numeru wydania. Klasy w komponencie muszą być wydawane razem, mieć wspólny numer wersji i dokumentację zmian.
2. **CCP (*Common Closure Principle*)**: Zasada wspólnego domknięcia. Grupuj w jednym komponencie klasy, które zmieniają się z tych samych powodów i w tym samym czasie (odpowiednik SRP dla komponentów).
3. **CRP (*Common Reuse Principle*)**: Zasada wspólnego użycia. Nie zmuszaj użytkowników komponentu do zależności od klas, których nie potrzebują (odpowiednik ISP dla komponentów).

#### Diagram napięć spójności
* REP i CCP dążą do powiększania komponentów (ułatwienie rozwoju i konserwacji).
* CRP dąży do zmniejszania komponentów (unikanie niepotrzebnych re-wydaności).
* Architekt balansuje na diagramie napięć w zależności od fazy dojrzałości projektu.

---

### 14. Rozdział: Łączenie komponentów

#### ADP — Zasada zależności niecyklicznych (*Acyclic Dependencies Principle*)
W grafie zależności komponentów nie mogą występować cykle. Cykle wywołują „syndrom dnia następnego” (zmiany w jednym module psują inny). Cykle łamie się stosując odwrócenie zależności (DIP) lub tworząc nowy komponent łączący.

#### SDP — Zasada stabilnych zależności (*Stable Dependencies Principle*)
Zależności muszą kierować się w stronę stabilności. Komponenty niestabilne (łatwe do zmiany) powinny zależeć od komponentów stabilnych (trudnych do zmiany).
* **Miernik niestabilności**: $I = Fan-out / (Fan-in + Fan-out)$, gdzie $I = 0$ oznacza maksymalną stabilność, a $I = 1$ maksymalną niestabilność.

#### SAP — Zasada stabilnych abstrakcji (*Stable Abstractions Principle*)
Komponent powinien być tak abstrakcyjny, jak jest stabilny.
* **Miernik abstrakcyjności**: $A = Na / Nc$ (stosunek klas abstrakcyjnych/interfejsów do wszystkich klas).

#### Ciąg Główny (*Main Sequence*)
Projekt idealny balansuje na linii $A + I = 1$. Unika się **strefy bólu** ($A=0, I=0$ — kod bardzo konkretny i stabilny, np. schema bazy) oraz **strefy bezużyteczności** ($A=1, I=1$ — kod abstrakcyjny, bez zależności). Miarą odchylenia jest $D = |A + I - 1|$.

---

## Część V. Architektura

### 15. Rozdział: Czym jest architektura?

Architektura oprogramowania to kształt nadany systemowi przez podział na komponenty, ich rozmieszczenie oraz komunikację między nimi.

#### Zachowywanie otwartych opcji
* Celem architektury jest zachowanie jak największej liczby otwartych opcji przez jak najdłuższy czas.
* System składa się z dwóch elementów: **reguł biznesowych** (rdzeń wartości) oraz **szczegółów** (UI, bazy danych, frameworki, protokoły).
* Dobra architektura sprawia, że szczegóły są nieistotne dla reguł biznesowych, co pozwala na opóźnienie decyzji technologicznych do optymalnego momentu.

---

### 16. Rozdział: Niezależność

Dobra architektura zapewnia wsparcie dla czterech wymiarów:
1. **Przypadki użycia**: Struktura systemu wyraźnie komunikuje jego intencje biznesowe.
2. **Działanie**: Umożliwia uzyskanie wymaganej przepustowości i czasu reakcji poprzez wydzielenie odpowiednich trybów rozdzielania.
3. **Rozwój**: Ułatwia pracę wielu zespołom zgodnie z prawem Conwaya.
4. **Wdrożenie**: Dąży do natychmiastowego wdrożenia (*one-click deployment*).

#### Oddzielanie warstw i przypadków użycia
System dzieli się poziomymi warstwami (UI, logika, baza danych) oraz pionowymi plastrami (poszczególne przypadki użycia).

#### Tryby rozdzielania
* Poziom kodu źródłowego (monolit).
* Poziom instalacji (komponenty binarne / pliki `.jar`).
* Poziom usług (mikroserwisy).
Dobra architektura pozwala na zmianę trybu rozdzielania w miarę rozwoju systemu.

---

### 17. Rozdział: Granice. Wyznaczanie linii

Granice w architekturze to linie oddzielające elementy o wysokim poziomie abstrakcji od szczegółów niskopoziomowych.

* Granice zapobiegają przenikaniu szczegółów technicznych (np. wyboru bazy danych czy frameworka WWW) do rdzenia logiki biznesowej.
* **Architektura wtyczek (*Plugin Architecture*)**: Reguły biznesowe stanowią rdzeń aplikacji, natomiast UI, baza danych i frameworki są wtyczkami podłączanymi przez interfejsy.

---

### 18. Rozdział: Anatomia granic

Granice przybierają różne formy fizyczne:
* **Monolit**: Granica na poziomie kodu źródłowego, przekraczana prostymi wywołaniami funkcji, zarządzana dynamicznym polimorfizmem.
* **Komponenty binarne**: Dynamicznie dołączane biblioteki (`.dll`, `.jar`), komunikacja w jednej przestrzeni adresowej.
* **Procesy lokalne**: Wykonanie w osobnych przestrzeniach adresowych na jednym procesorze, komunikacja przez gniazda lub pamięć dzieloną.
* **Usługi / Mikroserwisy**: Komunikacja sieciowa, pełna niezależność wdrożeniowa, najwyższy koszt komunikacyjny.

---

### 19. Rozdział: Zasady i poziomy

Poziom w architekturze definiowany jest przez dystans od wejść i wyjść systemu.

* **Wysoki poziom**: Moduły położone najdalej od fizycznych urządzeń wejścia/wyjścia (pure business logic).
* **Niski poziom**: Moduły położone blisko urządzeń wejścia/wyjścia (I/O, formatowanie, sterowniki).
* **Reguła poziomów**: Zależności w kodzie źródłowym muszą zawsze wskazywać od komponentów niskiego poziomu do komponentów wysokiego poziomu, niezależnie od kierunku przepływu danych.

---

### 20. Rozdział: Reguły biznesowe

Reguły biznesowe stanowią rdzeń aplikacji i dzielą się na dwa typy:

1. **Kluczowe Reguły Biznesowe i Encje**: Zasady przynoszące zysk lub oszczędności niezależnie od istnienia systemu komputerowego. Encje grupują kluczowe reguły i związane z nimi dane.
2. **Aplikacyjne Reguły Biznesowe i Przypadki Użycia (*Use Cases*)**: Zasady definiujące sposób automatyzacji biznesu w konkretnej aplikacji. Sterują przepływem danych do i z Encji.

#### Modele żądania i odpowiedzi
Przypadki użycia komunikują się ze światem zewnętrznym za pomocą prostych struktur danych (*Request/Response DTO*), nie ujawniając obiektów Encji.

---

### 21. Rozdział: Krzycząca architektura

Struktura katalogów i architektura aplikacji powinny od pierwszego spojrzenia wykrzykiwać jej domenę biznesową (np. „System Służby Zdrowia”), a nie zastosowane narzędzia czy frameworki (np. „Rails”, „Spring”, „ASP”).

* Frameworki to tylko narzędzia dostarczające mechanizmy wyjścia/wejścia, a nie motyw przewodni projektu.
* Architektura odseparowana od frameworków umożliwia testowanie wszystkich przypadków użycia w całkowitej izolacji od serwerów WWW i baz danych.

---

### 22. Rozdział: Czysta architektura

Czysta architektura stanowi syntezę koncepcji architektury heksagonalnej (Porty i Adaptery), DCI oraz BCE.

```
+-------------------------------------------------------+
|  Frameworki i Sterowniki (UI, DB, Web, Devices)       |
|   +-------------------------------------------------+ |
|   |  Adaptery Interfejsów (Controllers, Presenters) | |
|   |   +-------------------------------------------+ | |
|   |   |  Przypadki Użycia (Use Cases)             | | |
|   |   |   +-------------------------------------+ | | |
|   |   |   |  Encje (Entities)                   | | | |
|   |   |   +-------------------------------------+ | | |
|   |   +-------------------------------------------+ | |
|   +-------------------------------------------------+ |
+-------------------------------------------------------+
```

#### Zasada Zależności (*The Dependency Rule*)
**Wszystkie zależności w kodzie źródłowym muszą być skierowane wyłącznie do wewnątrz, w stronę klas wyższego poziomu (Encji i Przypadków Użycia).**

* Żaden element z wewnętrznego kręgu nie może wiedzieć nic o elementach z kręgu zewnętrznego.
* Przekraczanie granic odbywa się poprzez wzorzec odwrócenia zależności (DIP) z wykorzystaniem portów i interfejsów wyjściowych.

---

### 23. Rozdział: Prezentery i skromne obiekty

#### Wzorzec Skromny Obiekt (*Humble Object Pattern*)
Wzorzec dzielący kod na dwa moduły przy granicach architektonicznych: jeden skromny (trudny do przetestowania, zawierający wyłącznie szczegóły techniczne) oraz drugi zawierający pełną logikę (łatwy do przetestowania).

* **Prezenter i Widok**: Widok jest skromnym obiektem (tylko wyświetla dane). Prezenter przygotowuje dane w postaci gotowych ciągów tekstowych i znaczników w obiekcie `ViewModel`.
* **Bramy Bazy Danych (*Database Gateways*)**: Interaktywny kod SQL jest zamknięty w skromnej bramie, podczas gdy logika biznesowa operuje na abstrakcyjnych interfejsach dostępu.

---

### 24. Rozdział: Granice częściowe

Kiedy pełna granica architektoniczna jest zbyt kosztowna, stosuje się granice częściowe:
1. **Pominięcie ostatniego kroku**: Podział na komponenty i interfejsy przygotowany w kodzie, ale zaimplementowany i skompilowany jako jeden monolit.
2. **Granice jednowymiarowe**: Odwrócenie zależności za pomocą interfejsu (wzorzec Strategia), zachowujące elastyczność na przyszłość.
3. **Fasada**: Proste odseparowanie logiki klasą wywołań bez odwrócenia zależności.

---

### 25. Rozdział: Warstwy i granice

Architektura nie sprowadza się do prostej, trzywarstwowej struktury (UI, Logika, DB). W rzeczywistych systemach występuje wiele ukrytych granic architektonicznych.

* Przykłady gier (np. *Hunt the Wumpus*) pokazują rozdzielenie języków, interfejsów tekstowych, zachowania gracza i komunikacji sieciowej.
* Zadaniem architekta jest ciągłe balansowanie między kosztem nadmiernej abstrakcji (złamanie YAGNI) a ryzykiem i kosztem braku granicy w przyszłości.

---

### 26. Rozdział: Komponent Main

Komponent `Main` jest najbardziej zewnętrznym, brudnym modułem w czystej architekturze.

* Odpowiada za uruchomienie aplikacji, utworzenie fabryk, wstrzyknięcie zależności (DI) oraz przekazanie sterowania do przypadków użycia.
* `Main` należy traktować jako wtyczkę do aplikacji — można posiadać osobne moduły `Main` dla środowisk deweloperskich, testowych, produkcyjnych czy różnych jurysdykcji.

---

### 27. Rozdział: Serwisy, duże i małe

Architektura zorientowana na serwisy (SOA/Mikroserwisy) sama w sobie nie jest architekturą, lecz jedynie mechanizmem wdrożenia.

* Serwisy nie zapewniają automatycznie pełnej niezależności, jeśli są ze sobą połączone na poziomie struktur danych lub reguł biznesowych (np. problem dodania nowej usługi wymagającej zmian we wszystkich serwisach).
* Zasady SOLID i czysta architektura muszą być stosowane wewnątrz samych serwisów, dzieląc je na komponenty i otaczając wewnętrznymi granicami.

---

### 28. Rozdział: Granice testów

Testy stanowią integralny komponent systemu i wchodzą w skład jego architektury.

* **Problem kruchych testów (*Fragile Tests Problem*)**: Ścisłe powiązanie struktury testów ze strukturą kodu produkcyjnego sprawia, że drobna zmiana w kodzie psuje setki testów.
* **API Testujące**: Testy powinny komunikować się z aplikacją poprzez specjalne API testowe zbijające wywołania do poziomu przypadków użycia, z pominięciem interfejsu użytkownika.

---

### 29. Rozdział: Czysta architektura osadzona

Oprogramowanie osadzone (*embedded*) cierpi z powodu nadmiernego powiązania z firmwarem i konkretnym sprzętem.

#### Zasady osadzonej czystej architektury
* **Test n-App-stawienia**: Oprogramowanie musi dać się uruchomić i przetestować poza docelowym sprzętem (na maszynie deweloperskiej).
* **HAL (*Hardware Abstraction Layer*)**: Warstwa abstrakcji sprzętu ukrywająca rejestry i mikrokontrolery.
* **OSAL (*Operating System Abstraction Layer*)**: Warstwa abstrakcji systemu operacyjnego odizolowująca aplikację od konkretnego RTOS.

---

## Część VI. Szczegóły

### 30. Rozdział: Baza danych jest szczegółem

Z punktu widzenia architektury baza danych nie jest modelem danych, lecz **mechanizmem dostępu i przechowywania informacji na nośniku**.

* Relacyjne bazy danych (RDBMS) są narzędziem niskiego poziomu wynikającym z fizycznych ograniczeń tradycyjnych dysków twardych.
* Reguły biznesowe aplikacji nie powinny wiedzieć, w jaki sposób dane są serializowane czy indeksowane. Tabela, wiersze i SQL są szczegółami technicznymi i powinny być odizolowane za bramami dostępu.

---

### 31. Rozdział: Sieć WWW jest szczegółem

Sieć WWW jest jedynie kolejnym urządzeniem wejścia/wyjścia (I/O) i mechanizmem prezentacji.

* Architektura aplikacji powinna traktować przeglądarkę i protokół HTTP jako szczegół interfejsu użytkownika.
* Logika aplikacji musi dać się uruchomić w postaci konsolowej, usługi sieciowej czy interfejsu graficznego bez wprowadzania jakichkolwiek zmian w rdzeniu biznesowym.

---

### 32. Rozdział: Frameworki są szczegółem

Frameworki dostarczają potężnych narzędzi, ale tworzą **asymetryczne małżeństwo** — autor frameworka nie zna aplikacji, ale wymusza dziedziczenie po swoich klasach i wniknięcie w struktury domeny.

* Nie należy poślubiać frameworka. Framework musi znajdować się w najbardziej zewnętrznym kręgu czystej architektury jako wtyczka.
* Odizolowanie frameworka klasami adapterów zabezpiecza system przed kosztowną migracją w przypadku zmiany technologii.

---

### 33. Rozdział: Studium przypadku. Sprzedaż filmów

Praktyczny przykład projektowania systemu sprzedaży wideo:
1. Identyfikacja aktorów zgodnie z SRP (Autor, Administrator, Kupujący, Widz).
2. Wyznaczenie abstrakcyjnych i konkretnych przypadków użycia.
3. Podział na komponenty widoków, prezenterów, interaktorów i kontrolerów wyznaczony Zasadą Zależności.

---

### 34. Rozdział: Zaginiony rozdział

Ostatni krok architektury dotyczy sposobu organizacji i pakowania kodu w językach programowania.

#### Cztery strategie pakowania
1. **Pakowanie w warstwy (*Package by Layer*)**: Podział poziomy (web, service, data). Słaba hermetyzacja, wysokie ryzyko niepożądanych zależności.
2. **Pakowanie według funkcji (*Package by Feature*)**: Podział pionowy domenowy. Lepsza lokalizacja zmian, ale nadal słabe ukrywanie szczegółów.
3. **Porty i adaptery (*Hexagonal*)**: Dzielenie na domenę wewnętrzną i infrastrukturę zewnętrzną.
4. **Pakowanie według komponentów (*Package by Component*)**: Łączenie logiki biznesowej i dostępu do danych w spójne komponenty z jednym publicznym interfejsem. Ścisłe wykorzystanie modyfikatorów dostępu (`package-private` / `internal`) w celu wymuszenia architektury przez kompilator.

---

## Dodatki

### Dodatek A: Archeologia architektury

Przegląd projektów i historyczna ewolucja autorstwa Roberta C. Martina od 1970 roku:
* Od systemów taśmowych i kart perforowanych, przez programowanie w asemblerze, rozwój oprogramowania na układy EPROM i rejestry, aż po systemy wielowątkowe, języki C/C++ oraz powstanie notacji Boocha i zasad SOLID.
* Lekcja płynąca z półwiecza historii: technologie i sprzęt drastycznie się zmieniają, ale **fundamentalne zasady architektury oprogramowania pozostają w pełni niezmienne**.

---

## Macierz Zasad Architektonicznych

| Obszar | Kluczowa Zasada / Pojęcie | Główny Cel Architektoniczny |
| :--- | :--- | :--- |
| **Paradygmaty** | Ograniczenie swobód (goto, wskaźniki, modyfikacje) | Zapewnienie struktury, polimorfizmu i niezmienności danych. |
| **SOLID** | SRP, OCP, LSP, ISP, DIP | Tworzenie stabilnych, otwartych na rozszerzanie i luźno powiązanych modułów. |
| **Spójność Komponentów** | REP, CCP, CRP | Zrównoważenie priorytetów wydań, rozwoju i ponownego użycia kodu. |
| **Łączenie Komponentów** | ADP, SDP, SAP & Ciąg Główny | Eliminacja cykli, kierowanie zależności do komponentów stabilnych i abstrakcyjnych. |
| **Czysta Architektura** | Zasada Zależności (*Dependency Rule*) | Skierowanie wszystkich zależności do wewnątrz — ku Encjom i Przypadkom Użycia. |
| **Szczegóły Techniczne** | UI, DB, Web, Frameworki jako wtyczki | Odseparowanie rdzenia biznesowego od nietrwałych technologii zewnętrznych. |
