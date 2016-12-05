1. Preambułę uznaję jako rozdział nr. 0.

2. class Constitution:

    - Lista rozdziałów

    protected sections

    - Aktualna ilośc artykułów

    private articlesAmount


    - Hashmapa której kluczem jest numer artykułu, przetrzymywana jest sekcja, w której jest zawarty dany artykuł.

    private articlesLocation


    - Konstruktor przyjmuje jako argument ścieżkę do pliku z treścia konstytucji, uruchamia proces tworzenia konstytucji.
    Przyjmuje różne parametry:
      - String fileLocalization, int section  wyświetli jeden rozdział
      - String fileLocalization, int section, int articleBeginning, int articleEnd wyświetli artykuły z zakresu articleBeginning - articleBeginning z rozdziału section

    public Constitution


    - Przyjmuje jako parametr ścieżke do pliku z treścią konstytucji. Czyta plik, i go dzieli na rodziały, dla każdego rozdziału tworzy nowy obiekt Section, podczas tworzenia przekazuje treść rozdziału

    private divideIntoSections


    - Wyświetla treść rozdziału, przyjmuje jako paramtr numer rozdziału

    public showSection


    - Wyświetla treść artykułu/artykułów przyjmuje jako parametr numer rozdziału, i zakres artykułów do wyświetlenia.
     np. shopArticles(2,3,10) Rozdział 2. artykuły 2 do 10
         showArticles(2,3,3) Rozdział 2. artykuł 3

     public showArticles

    - Rejestruje artykuł, daje informacje o tym jakie id może mieć
     public registerArticle

3. class Article
    - Numer artykułu

    private int id

    - Treść artykułu

    private String content

    - Konstruktor przyjmuje jako argument treść artykułu

    public Article

    - Nadpisanie funkcji

    public String toString()

4. class Section extends Article

  - Lista artykułów
  
  private articles

  - Konstruktor przyjmuje jako argument treść rozdziału

  public Constitution


  - Dzieli na artykuły, i zapisuje je w liście, pyta konstytucję o id artykułu, i informuje ją o tym że zapisuje artykuł

  private divideIntoArticles

  - Nadpisanie funkcji
  
  public String toString()

5. class Preabule extends Section
6. class inputParser
