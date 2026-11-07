# Czysty kod. Podręcznik dobrego programisty — Ogólne Zasady i Heurystyki

Podręcznik *„Czysty kod. Podręcznik dobrego programisty”* (*Clean Code: A Handbook of Agile Software Craftsmanship*) stawia tezę, że samo napisanie działającego programu to zaledwie początek procesu — o jakości oprogramowania, jego żywotności oraz ekonomicznej opłacalności decyduje czytelność, łatwość utrzymania i modularność struktury kodu źródłowego.

Poniższy dokument przedstawia uniwersalne zasady, wzorce oraz heurystyki inżynierii oprogramowania zawarte we wszystkich 17 rozdziałach książki oraz dodatku architektonicznym, ujęte w sposób ogólny i wolny od wąskich szczegółów konkretnych klas przykładowych.

---

## 1. Rozdział: Czysty kod (Clean Code)

Diagnoza stanu rzemiosła programistycznego oraz definicja czystości kodu jako kluczowego czynnika sukcesu projektu.

### Całkowity koszt bałaganu
Mechanizm degradacji projektów oprogramowania wynika z presji czasu oraz braku profesjonalizmu, co skłania do tworzenia tymczasowych, nieprzemyślanych rozwiązań. Wraz ze wzrostem bałaganu wydajność zespołu stale spada, dążąc asymptotycznie do zera. Dodawanie nowych członków do zespołu nie rozwiązuje problemu, lecz go pogłębia, ponieważ nowe osoby nie znają architektury i tworzą kolejne warstwy długu technologicznego. Jedynym sposobem na utrzymanie wysokiej prędkości tworzenia oprogramowania jest zachowanie ciągłej czystości kodu.

### Zasada skautów (Boy Scout Rule)
Nie wystarczy dobrze pisać kod — należy go stale utrzymywać w czystości. Zasada skautów brzmi: *„Pozostaw obozowisko czyściejszym, niż je zastałeś”*. Przy każdej modyfikacji modułu należy wprowadzić drobną poprawkę: zmienić nazwę zmiennej na lepszą, podzielić zbyt dużą funkcję czy wyeliminować drobne powtórzenie.

---

## 2. Rozdział: Znaczące nazwy (Meaningful Names)

Nazwy są podstawowym narzędziem komunikacji w kodzie źródłowym. Tworzenie dobrych nazw wymaga precyzji opisu i zrozumienia domeny.

### Kluczowe zasady tworzenia nazw
1. **Przedstawianie intencji**: Nazwa zmiennej, funkcji lub klasy musi odpowiadać na pytania: dlaczego dany element istnieje, co robi i jak jest używany. Jeżeli nazwa wymaga komentarza wyjaśniającego, to nie przedstawia intencji.
2. **Unikanie dezinformacji**: Nie należy stosować słów mających specyficzne znaczenie techniczne, jeśli dany element go nie spełnia. Unikać nazw różniących się w sposób subtelny oraz znaków łatwych do pomylenia (np. `l` oraz `O`).
3. **Tworzenie wyraźnych różnic**: Unikać bezinformacyjnych nazw (np. `a1`, `a2`) oraz nadmiarowych przyrostków i przedrostków (`Data`, `Info`, `Object`), które nie niosą różnic pojęciowych.
4. **Wymowność i łatwość wymowy**: Kod jest tworzywem społecznym. Nazwy zmiennych powinny dawać się łatwo wymówić podczas dyskusji w zespole.
5. **Łatwość wyszukiwania**: Długość nazwy powinna odpowiadać rozmiarowi jej zakresu widoczności. Zmienne jednoliterowe mogą być stosowane wyłącznie jako zmienne lokalne wewnątrz bardzo krótkich pętli. Stałe numeryczne i tekstowe muszą być zastępowane stałymi nazwanymi.
6. **Brak zbędnego kodowania**: Kodowanie typów (notacja węgierska) czy przedrostki składowych są szumem. Przy interfejsach i implementacjach lepiej pozostawić interfejs bez dekoracji, a zakodować implementację, zamiast dodawać prefiksy.
7. **Nazwy klas i metod**:
   * **Klasy i obiekty**: Powinny być rzeczownikami lub wyrażeniami rzeczownikowymi (np. `Customer`, `Account`, `AddressParser`). Unikać ogólników typu `Manager`, `Processor`, `Data`.
   * **Metody**: Powinny być czasownikami lub wyrażeniami czasownikowymi (np. `save`, `deletePage`, `postPayment`). Predykaty i akcesory należy nazywać z przedrostkami `is`, `get`, `set`.
   * **Konstruktory**: Przeciążone konstruktory warto zastępować statycznymi metodami fabrykującymi o nazwach opisujących przekazywane argumenty.
8. **Jedno słowo na pojęcie**: Ustalenie jednego słowa dla danej operacji w całym projekcie (np. konsekwentne stosowanie `fetch` albo `get`, bez mieszania ich). Unikać stosowania tego samego słowa do dwóch różnych celów.
9. **Nazwy z domeny rozwiązania i problemu**: Używać pojęć technicznych i wzorców tam, gdzie to możliwe (np. `Visitor`, `JobQueue`), a w pozostałych miejscach stosować nazwy z domeny biznesowej.

---

## 3. Rozdział: Funkcje (Functions)

Funkcje stanowią pierwszą linię organizacji każdego programu.

### Podstawowe reguły konstruowania funkcji
1. **Zmniejszanie rozmiaru funkcji**: Funkcje powinny być wyraziste i krótkie — idealnie powinny liczyć od kilku do kilkunastu wierszy.
2. **Jednopoziomowe wcięcia**: Bloki wewnątrz instrukcji warunkowych i pętli powinny zawierać wywołania funkcji o sugestywnych nazwach. Poziom zagnieżdżenia wewnątrz funkcji nie powinien przekraczać jednego lub dwóch.
3. **Zasada jednej operacji (Do One Thing)**: Funkcja powinna wykonywać tylko jedną operację, robić to dobrze i nie robić nic oprócz tego. Wszystkie instrukcje wewnątrz funkcji powinny znajdować się na jednym poziomie abstrakcji.
4. **Zasada zstępująca (Stepdown Rule)**: Kod powinien dać się czytać od góry do dołu jak tekst narracyjny. Po każdej funkcji powinna następować funkcja na niższym poziomie abstrakcji.
5. **Polimorfizm zamiast instrukcji warunkowych**: Złożone instrukcje `switch` lub `if-else` sprawdzające typy naruszają zasady SRP i OCP. Należy ukrywać je w fabrykach i zastępować polimorfizmem.
6. **Zarządzanie argumentami**:
   * **Liczba argumentów**: Najlepsza funkcja to funkcja bezargumentowa (0) lub jednoargumentowa (1). Liczba argumentów powyżej 2 wymaga wyraźnego uzasadnienia.
   * **Brak argumentów flagowych**: Przekazywanie wartości logicznej (`boolean`) świadczy o tym, że funkcja robi dwie różne rzeczy w zależności od flagi. Taki kod należy podzielić na dwie osobne funkcje.
   * **Obiekty argumentów**: Gdy funkcja wymaga kilku powiązanych ze sobą wartości, należy spakować je w osobny obiekt.
7. **Eliminacja efektów ubocznych**: Funkcja nie może po cichu modyfikować stanu globalnego, obiektów zewnętrznych ani argumentów wejściowych.
8. **Rozdzielanie poleceń i zapytań (CQS)**: Funkcja powinna albo zmieniać stan obiektu, albo zwracać informacje, ale nigdy oba jednocześnie.
9. **Obsługa błędów jako osobna operacja**: Stosowanie wyjątków zamiast kodów błędów. Funkcja obsługująca bloki `try-catch` nie powinna zawierać żadnej innej logiki.
10. **Zasada DRY (Don't Repeat Yourself)**: Eliminacja powtórzeń kodu poprzez wyodrębnianie wspólnych funkcji, klas lub aspektów.

---

## 4. Rozdział: Komentarze (Comments)

Komentarze nie zastępują czystego kodu — stanowią kompensację braku możliwości dostatecznie jasnego wyrażenia intencji w kodzie.

### Dlaczego komentarze bywają szkodliwe?
Kod ewoluuje, a komentarze często nie są aktualizowane, co prowadzi do powstawania dezinformacji. Jedynym precyzyjnym źródłem prawdy o działaniu programu jest sam kod źródłowy.

### Uzasadnione zastosowania komentarzy
* **Komentarze prawne i licencyjne**: Wymagane nagłówki praw autorskich.
* **Objaśnienie zamierzeń i decyzji**: Dokumentowanie powodów wyboru konkretnego algorytmu lub przyjętej architektury.
* **Ostrzeżenia o konsekwencjach**: Ostrzeżenia przed długim czasem wykonania metody lub ubocznymi skutkami operacji.
* **Komentarze TODO**: Tymczasowe przypomnienia o brakujących elementach (wymagają regularnego czyszczenia).
* **Wzmacnianie znaczenia**: Podkreślenie istotności fragmentu logiki, który mógłby wydawać się błędny lub zbędny.
* **Dokumentacja API (Javadoc/Docstring)**: Dokumentacja publicznych interfejsów i bibliotek przeznaczonych dla użytkowników zewnętrznych.

### Komentarze kwalifikujące się do usunięcia
* **Zakomentowany kod**: Należy go bezwzględnie usuwać — historię kodu przechowuje system kontroli wersji.
* **Komentarze powtarzające kod**: Opisywanie w komentarzu tego, co jednoznacznie wynika z samej sygnatury lub nazwy zmiennej.
* **Bełkot i komentarze mruczane**: Niejasne uwagi pisane w pośpiechu.
* **Komentarze dziennika zmian**: Śledzenie historii w plikach źródłowych zamiast w systemie Git.
* **Mylące i nieaktualne uwagi**: Komentarze wprowadzające w błąd.

---

## 5. Rozdział: Formatowanie (Formatting)

Formatowanie kodu jest kluczowym elementem komunikacji i decyduje o łatwości czytania oraz konserwacji oprogramowania.

### Formatowanie pionowe
* **Struktura gazety**: Plik źródłowy powinien zaczynać się od ogólnego opisu i koncepcji najwyższego poziomu, a szczegóły implementacyjne powinny ujawniać się w miarę schodzenia w dół pliku.
* **Pionowe odstępy**: Puste wiersze powinny rozdzielać osobne myśli i koncepcje (np. metody, sekcje importów).
* **Koligacja i bliskość**: Deklaracje zmiennych lokalnych powinny znajdować się blisko miejsca użycia. Funkcje zależne powinny leżeć blisko siebie w pionie (funkcja wywołująca bezpośrednio nad wywoływaną).

### Formatowanie poziome
* **Długość wiersza**: Zoptymalizowana pod kątem czytelności bez konieczności przewijania (zazwyczaj do 100–120 znaków).
* **Odstępy poziome**: Używane do rozdzielania operatorów i uwypuklania relacji między elementami.
* **Spójność wcięć**: Wcięcia odzwierciedlają hierarchię blokową kodu. Należy unikać zwijania jednoliniowych instrukcji w jeden wiersz.
* **Standard zespołowy**: Cały zespół stosuje jeden, ściśle uzgodniony format kodowania.

---

## 6. Rozdział: Obiekty i struktury danych (Objects and Data Structures)

Fundamentalna różnica architektoniczna między obiektami a strukturami danych.

### Abstrakcja danych
Prawdziwa hermetyzacja nie polega na dodaniu metod dostępowych (`get`/`set`) do prywatnych pól, lecz na ukryciu wewnętrznej reprezentacji danych za abstrakcyjnym interfejsem operacji.

### Antysymetria obiektowo-strukturalna
* **Struktury danych**: Ujawniają dane i nie posiadają znaczącej logiki. Ułatwiają dodawanie nowych *funkcji* operujących na danych, ale utrudniają dodawanie nowych *typów struktur* (wymagają modyfikacji wszystkich istniejących funkcji).
* **Obiekty**: Ukrywają dane za abstrakcją i oferują funkcje. Ułatwiają dodawanie nowych *klas/obiektów*, ale utrudniają dodawanie nowych *metod* do istniejących abstrakcji.

Wybór struktury lub obiektu zależy od tego, czy system będzie częściej rozbudowywany o nowe typy danych, czy o nowe funkcje.

### Prawo Demeter
Moduł nie powinien znać wewnętrznej budowy obiektów, z którymi wchodzi w interakcję („rozmawiaj tylko z bezpośrednimi przyjaciółmi”).
Należy unikać **wraków pociągów** (łańcuchowych wywołań akcesorów). Jeżeli obiekt jest prawdziwym obiektem biznesowym, należy nakazać mu wykonanie zadania, zamiast wyciągać z niego głęboko osadzone składowe.

### Struktury DTO i Active Record
Obiekty DTO (Data Transfer Object) oraz Active Record są strukturami danych służącymi do przesyłania lub mapowania informacji. Dodawanie do nich logiki biznesowej tworzy szkodliwe hybrydy — logika biznesowa powinna znajdować się w osobnych obiektach.

---

## 7. Rozdział: Obsługa błędów (Error Handling)

Obsługa błędów jest elementem niezbędnym, ale nie może przesłaniać zasadniczego przepływu logiki biznesowej.

### Zasady czystej obsługi błędów
1. **Wyjątki zamiast kodów błędów**: Kody powrotu zmuszają do ciągłego sprawdzania stanów po każdej operacji. Wyjątki pozwalają odseparować ścieżkę sukcesu od ścieżki awaryjnej.
2. **Transakcyjność bloków `try-catch`**: Blok `try` definiuje spójną transakcję. Pisanie testów wymuszających wyjątki pomaga zachować właściwy stan aplikacji po awarii.
3. **Stosowanie wyjątków niekontrolowanych (Unchecked)**: Wyjątki kontrolowane naruszają zasadę OCP — dodanie wyjątku na niskim poziomie wymaga modyfikacji sygnatur metod we wszystkich warstwach pośrednich.
4. **Wyrazisty kontekst błędu**: Tworzenie wyczerpujących komunikatów błędów z opisem intencji i przyczyn niepowodzenia.
5. **Klasyfikowanie wyjątków według potrzeb wywołującego**: Stosowanie klas osłaniających (wrappery) dla bibliotek zewnętrznych w celu ujednolicenia obsługi błędów i odizolowania własnego kodu od obcych zależności.
6. **Wzorzec przypadku specjalnego (Special Case Pattern)**: Zastępowanie przerywania programu wyjątkiem dedykowanym obiektem obsługującym przypadek skrajny, gdy sytuacja ta jest naturalnym stanem biznesowym.
7. **Eliminacja wartości `null`**: Zwracanie oraz przekazywanie `null` prowadzi do ukrytych błędów i nadmiaru kodów sprawdzających. Należy zwracać puste kolekcje lub obiekty neutralne, a przekazywanie `null` w argumentach wyeliminować.

---

## 8. Rozdział: Granice (Boundaries)

Czyste zarządzanie granicami systemu przy integracji z zewnętrznymi bibliotekami i API.

### Zasady zabezpieczania granic
* **Hermetyzacja zewnętrznych interfejsów**: Zewnętrzne moduły oferują bardzo szerokie interfejsy. Zamiast przekazywać je w całym systemie, należy zamykać je we własnych klasach opartych na interfejsach odpowiadających wyłącznie potrzebom aplikacji.
* **Testy poznawcze (Learning Tests)**: Pisanie automatycznych testów sprawdzających działanie zewnętrznego API. Pozwala to na szybką naukę biblioteki oraz bezpieczną weryfikację nowo wydawanych wersji oprogramowania firm trzecich.
* **Wzorzec Adapter dla nieistniejącego kodu**: Projektując kod zależny od komponentu, który jeszcze nie powstał, należy stworzyć własny interfejs docelowy, a po dostarczeniu zewnętrznego modułu połączyć je adapterem.

---

## 9. Rozdział: Testy jednostkowe (Unit Tests)

Praktyka TDD oraz automatyzacja testów stanowią gwarant elastyczności i jakości kodu produkcyjnego.

### Trzy prawa TDD
1. Pisanie kodu produkcyjnego dopuszczalne jest tylko w celu spełnienia niespełnionego testu jednostkowego.
2. Pisanie testu należy przerwać w momencie uzyskania niepowodzenia (błąd kompilacji jest niepowodzeniem).
3. Pisanie kodu produkcyjnego należy przerwać w momencie, gdy obecny test zaczyna przechodzić.

### Utrzymanie czystości testów
Kod testowy wymaga takiego samego poziomu dbałości jak kod produkcyjny. Złe i nieczytelne testy powodują, że każda modyfikacja kodu produkcyjnego wymaga kosztownych zmian w testach, co ostatecznie prowadzi do ich porzucenia. **Czyste testy dają programistom odwagę do ciągłej refaktoryzacji**.

### Anatomia czystego testu
* **Struktura Given-When-Then (Build-Operate-Check)**: Jasny podział testu na przygotowanie środowiska, wykonanie operacji oraz weryfikację wyników.
* **Zasada jednej koncepcji**: Każdy test powinien sprawdzać tylko jedną koncepcję logiki biznesowej.
* **Podwójny standard**: Kod testowy musi być maksymalnie czytelny, ale może stosować uproszczenia wydajnościowe (np. w alokacji pamięci), które w kodzie produkcyjnym byłyby niedopuszczalne.

### Zasady F.I.R.S.T.
* **F — Fast (Szybkie)**: Testy muszą wykonywać się błyskawicznie, aby były uruchamiane stale.
* **I — Independent (Niezależne)**: Testy nie mogą zależeć od siebie ani od kolejności wykonania.
* **R — Repeatable (Powtarzalne)**: Dają ten sam wynik w dowolnym środowisku.
* **S — Self-Validating (Samokontrolujące)**: Zwracają wynik binarny (`pass`/`fail`).
* **T — Timely (O czasie)**: Pisane tuż przed kodem produkcyjnym.

---

## 10. Rozdział: Klasy (Classes)

Organizacja i czystość klas na wyższych poziomach abstrakcji.

### Organizacja i hermetyzacja
Standardowa struktura klasy obejmuje stałe i zmienne statyczne, zmienne instancyjne, metody publiczne oraz leżące pod nimi prywatne metody pomocnicze. Rozluźnianie prywatności składowych jest dopuszczalne wyłącznie na potrzeby testów.

### Zasada Pojedynczej Odpowiedzialności (SRP)
Klasa powinna być mała i posiadać **tylko jeden powód do zmiany**. Miarą wielkości klasy jest liczba odpowiedzialności, a nie liczba wierszy kodu. Opis funkcji klasy nie powinien zawierać słów koniunkcji („i”, „lub”, „ale”).

### Spójność (Cohesion)
Klasa powinna osiągać wysoką spójność — większość jej metod powinna operować na większości jej zmiennych instancyjnych. Gdy metody zaczynają operować tylko na podzbiorach zmiennych, należy wydzielić nowe klasy.

### Izolacja zmian (OCP i DIP)
Klasy powinny być otwarte na rozszerzanie, ale zamknięte na modyfikacje (OCP). Izolacja od szczegółów implementacyjnych następuje poprzez abstrakcje i odwrócenie zależności (DIP).

---

## 11. Rozdział: Systemy (Systems)

Czystość architektury na poziomie całego systemu.

### Separacja konstrukcji od używania
Tworzenie obiektów oraz składanie zależności aplikacji musi być całkowicie oddzielone od logiki wykonawczej.
* **Moduł inicjalizujący (`main`)**: Budowanie grafu obiektów w osobnym punkcie wejścia.
* **Wstrzykiwanie zależności (Dependency Injection)**: Kontenery IoC automatycznie dostarczają zależności obiektom biznesowym.

### Rozcięcie problemów przekrojowych (AOP)
Problemy przechodzące przez granice obiektów (trwałość, transakcje, bezpieczeństwo) powinny być wyodrębniane deklaratywnie przy użyciu aspektów lub dekoratorów (AOP), pozostawiając kod biznesowy jako czyste obiekty (POJO).

### Ewolucyjna architektura
Architektura systemu powinna rozwijać się stopniowo. Podejmowanie wiążących decyzji architektonicznych powinno nastąpić w ostatnim odpowiedzialnym momencie.

---

## 12. Rozdział: Powstawanie projektu (Emergence)

Cztery zasady prostego projektu Kenta Becka wspierające wyłanianie się czystej architektury.

### Cztery zasady prostego projektu (według hierarchii)
1. **Przechodzenie wszystkich testów**: Dążenie do weryfikowalności wymusza małe, luźno powiązane klasy.
2. **Brak duplikacji (DRY)**: Bezkompromisowa eliminacja powtórzeń kodu i logiki.
3. **Wyrazistość (Expressiveness)**: Jasne komunikowanie intencji przez nazwy, strukturę i wzorce.
4. **Minimalna liczba klas i metod**: Powściągliwość zapobiegająca nadmiernemu skomplikowaniu struktury pod wpływem dogmatyzmu.

---

## 13. Rozdział: Współbieżność (Concurrency)

Tworzenie czystego kodu wielowątkowego poprzez rozdzielenie kwestii *co* od *kiedy*.

### Zasady obrony współbieżności
* **SRP dla współbieżności**: Kod zarządzający wątkami musi być odseparowany od logiki biznesowej.
* **Ograniczanie dostępu do danych wspólnych**: Sekcje krytyczne muszą być minimalne.
* **Stosowanie kopii danych**: Preferowanie pracy na obiektach niezmiennych i kopiach zamiast synchronizacji dostępu do danych dzielonych.
* **Niezależne wątki**: Dążenie do tworzenia wątków przetwarzających dane w sposób odizolowany.
* **Użycie bezpiecznych kolekcji**: Stosowanie dedykowanych bibliotek współbieżnych.

### Testowanie kodu wielowątkowego
Testowanie kodu w środowisku jednowątkowym przed uruchomieniem wątków, testowanie na różnych konfiguracjach oraz celowe wstrząsanie wykonaniem kodu w celu wykrycia wyścigów.

---

## 14. Rozdział: Udane oczyszczanie kodu (Praca z brudnym kodem)

Uniwersalne wnioski z procesu refaktoryzacji i porządkowania kodu.

### Zasady refaktoryzacji
1. **Dwuetapowość tworzenia kodu**: Pierwsza wersja programu jest zgrubnym szkicem, który wymaga bezwzględnego uporządkowania przed włączeniem do głównego wydania.
2. **Modyfikacje przyrostowe**: Kod należy czyścić w bardzo małych krokach, uruchamiając automatyczne testy po każdej poprawce.
3. **Zastępowanie rozrośniętej logiki warunkowej polimorfizmem**: Wydzielanie hierarchii klas w miejsce zagnieżdżonych struktur wyboru.
4. **Odseparowanie obsługi błędów**: Przeniesienie formatowania i zgłaszania błędów do dedykowanych komponentów.

---

## 15. Rozdział: Anatomia refaktoryzacji na podstawie testów

Praktyka udoskonalania działających modułów.

### Wskazówki refaktoryzacyjne
* Warunkiem rozpoczęcia prac jest posiadanie testów zapewniających pełne pokrycie logiki.
* Wyodrębnianie skomplikowanych wyrażeń i warunków brzegowych do opisowych metod pomocniczych.
* Eliminacja efektów ubocznych z nazw metod oraz z ich wnętrza.
* Porządkowanie zależności indeksowych oraz unikanie sztucznych przesunięć wartości.
* Iteracyjny charakter poprawek — każda zmiana ujawnia kolejne możliwości uproszczenia.

---

## 16. Rozdział: Refaktoryzacja kodów dziedziczonych (Legacy Code)

Zasady porządkowania istniejących, zastanych modułów w systemie.

### Działania na kodzie dziedziczonym
1. **Podnoszenie pokrycia testowego**: Przed rozpoczęciem refaktoryzacji należy otoczyć moduł kompletną siecią testów weryfikujących obecne działanie.
2. **Dopasowanie nazw do poziomu abstrakcji**: Nazwa klasy i metody musi precyzyjnie opisywać jej rolę architektoniczną, a nie szczegóły implementacyjne.
3. **Zastępowanie stałych typami wyliczeniowymi**: Eliminowanie interfejsów stałych na rzecz typów `enum` lub wydzielonych modułów.
4. **Przenoszenie odpowiedzialności**: Porządkowanie hierarchii klas i przenoszenie specyficznych metod do klas narzędziowych lub pochodnych.
5. **Niezmienność obiektów (Immutability)**: Projektowanie metod w sposób wyraźnie komunikujący, czy metoda modyfikuje stan, czy zwraca nową instancję.

---

## 17. Rozdział: Zapachy kodu i heurystyki (Code Smells and Heuristics)

Katalog uniwersalnych sygnałów ostrzegawczych w kodzie źródłowym.

### Katalog zapachów kodu i heurystyk

#### Komentarze
* **Niewłaściwe informacje**: Umieszczanie metadanych historycznych w komentarzach zamiast w VCS.
* **Przestarzałe komentarze**: Komentarze nieaktualne lub sprzeczne z kodem.
* **Nadmiarowość**: Opisywanie w komentarzu tego, co jednoznacznie wynika z kodu.
* **Zakomentowany kod**: Przechowywanie nieużywanego kodu w plikach źródłowych.

#### Środowisko i proces
* **Wielokrokowe budowanie i testowanie**: Proces budowania projektu oraz uruchamiania testów musi sprowadzać się do jednego polecenia.

#### Funkcje i metody
* **Nadmiar argumentów**: Funkcje przyjmujące zbyt wiele parametrów (>2-3).
* **Argumenty wyjściowe**: Modyfikowanie parametrów przekazanych do funkcji.
* **Argumenty znacznikowe**: Przekazywanie wartości logicznych sterujących przebiegiem funkcji.
* **Martwy kod**: Nieosiągalne funkcje i warunki.

#### Ogólne heurystyki projektowe
* **Złamanie zasady najmniejszego zaskoczenia**: Kod działający niezgodnie z powszechnym oczekiwaniem.
* **Niewłaściwe działanie na granicach**: Brak testowania wartości skrajnych.
* **Duplikacja kodu (DRY)**: Najważniejsza heurystyka — powielanie tej samej logiki w wielu miejscach.
* **Mieszanie poziomów abstrakcji**: Umieszczanie szczegółów niskopoziomowych obok pojęć biznesowych.
* **Zazdrość o funkcje (Feature Envy)**: Metoda operująca głównie na danych należących do innego obiektu.
* **Zaciemnianie intencji**: Stosowanie magicznych liczb, zawiłych struktur i niejasnych nazw.
* **Złamanie Prawa Demeter**: Łańcuchowe wywołania akcesorów i ujawnianie struktury wewnętrznej.

#### Testowanie
* **Niewystarczające testy**: Pomijanie testowania warunków brzegowych i skrajnych.
* **Powolne testy**: Testy wykonujące się zbyt wolno, co zniechęca do ich stałego uruchamiania.

---

## 18. Dodatek: Współbieżność II (Advanced Concurrency)

Zaawansowane mechanizmy i wyzwania systemów wielowątkowych.

### Kluczowe mechanizmy
* **Brak atomowości operacji**: Złożone instrukcje na poziomie kodu bajtowego wymagają jawnej synchronizacji lub stosowania operacji atomowych.
* **Stosowanie struktur nieblokujących (CAS)**: Wykorzystanie sprzętowych operacji typu *Compare-And-Swap* w celu osiągnięcia wysokiej wydajności bez bliskiego pesymistycznego blokowania.
* **Wymuszanie spójności sekwencji**: Synchronizowanie ciągów wywołań metod po stronie serwera w celu uniknięcia zakleszczeń i wyścigów.
* **Zapobieganie zakleszczeniom**: Eliminacja przynajmniej jednego z czterech warunków zakleszczenia (wzajemne wykluczanie, blokowanie i oczekiwanie, brak wywłaszczania, cykliczne oczekiwanie) — najczęściej poprzez narzucenie ścisłej kolejności chwytania blokad.

---

## Podsumowanie Zasad Inżynieryjnych

| Obszar | Kluczowa Zasada | Celem Jest |
| :--- | :--- | :--- |
| **Nazewnictwo** | Przedstawianie intencji | Wyeliminowanie komentarzy objaśniających role zmiennych i metod. |
| **Funkcje** | Jedna operacja & Jeden poziom abstrakcji | Utrzymanie krótkich, czytelnych i łatwych w testowaniu jednostek. |
| **Komentarze** | Czyszczenie kodu zamiast opisu | Kod będący samowystarczalną dokumentacją. |
| **Architektura** | SRP, DRY, OCP, DIP | Tworzenie elastycznych, odsprzęgniętych i łatwych w rozbudowie systemów. |
| **Obiekty vs Struktury** | Prawidłowy wybór abstrakcji | Świadome decydowanie między łatwością dodawania typów a funkcji. |
| **Testy** | Zasady F.I.R.S.T. | Gwarancja bezpieczeństwa podczas ciągłej refaktoryzacji kodu. |
| **Systemy** | Modułowość i DI | Oddzielenie konfigurowania aplikacji od jej wykonania. |
