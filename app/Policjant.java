package app;

public class Policjant {
    // prywatna zmienne:
    // identyfikator(string)
    // uprawnienia(boolean)
    private String identyfikator;
    private Boolean uprawnienia;

    // konstruktor, który potrzebuje zmiennej tekstowej oznaczającej identyfikator
    public Policjant(String identyfikator){
        // przypisujemy go do zmiennej klasowej
        this.identyfikator = identyfikator;
        // ustawiamy uprawnienia na prawdę
        this.uprawnienia = true;
    }

    // metoda która zwraca identyfikator
    public String getIdentyfikator(){
        return this.identyfikator;
    }

    // metoda która zwraca nam uprawnienia (prawda lub fałsz)
    public Boolean sprawdzUprawnienia(){
        return this.uprawnienia;
    }

    // metoda, która zwraca nam reprezentacje tekstową naszej klasy
    public String toString(){
        String wynik = "Policjant: \nIdentyfikator: " + this.identyfikator + "\nUprawnienia: " + this.uprawnienia + "\n";
        return wynik;
    }
}
