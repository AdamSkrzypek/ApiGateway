# Ustalenia współpracy

- Zawsze odpowiadaj i przygotowuj dokumentację po polsku.
- Przy prośbie o review najpierw przeczytaj `docs/reviewer.md` i stosuj opisany tam proces.
- Review ma być pogłębione: prześledź przepływ od wejścia i konfiguracji przez kod aplikacji aż do zachowania używanych frameworków i bibliotek.
- Podawaj wyłącznie konkretne, możliwe do zweryfikowania znaleziska, w kolejności ważności. Każde znalezisko powinno wskazywać plik i miejsce, scenariusz skutku oraz dowód lub sposób odtworzenia.
- Klasyfikuj znaleziska jako **Krytyczne**, **Ważne** albo **Warte poprawy**.
- Gdy to możliwe, potwierdzaj obserwacje istniejącymi testami, ukierunkowanym testem regresyjnym albo odtworzeniem zachowania. Oddzielaj potwierdzone problemy od ryzyk i nie przedstawiaj braku testu jako dowodu błędu.
- Nie zmieniaj implementacji w ramach samego review, chyba że użytkownik wyraźnie poprosi o naprawę.
- Przy ocenie rozwiązania lub kierunku technicznego stosuj `docs/architect.md`.
- Przy tworzeniu lub doprecyzowaniu funkcji i wymagań stosuj `docs/product-owner-analityk.md`; wymagania opisują zachowanie biznesowe, a nie gotową implementację.
