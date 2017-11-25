package app;

public class Uzytkownik {
    // zmienne klasy użytkownik
    private String imie;
    private String nazwisko;
    private String pesel;
    private boolean jestKierowca = false;
    private int punktyKarne = 0;


    //konstruktor który pobiera imie, nazwisko, pesel
    public Uzytkownik(String i, String n, String p){
        // i przypisuje je do zmiennych klasowych
        this.imie = i;
        this.nazwisko = n;
        this.pesel = p;
    }

    // metoda która zwraca nam informacje o tym czy uzytkownik jest kierowca (prawda lub fałsz)
    public boolean getJestKierowca(){ 
        return this.jestKierowca;
    }

    // metoda która ustawia nam fakt czy użytkownik jest kierowcą (prawda lub fałsz)
    public void setJestKierowca(boolean jestKierowca) {
        this.jestKierowca = jestKierowca;
    }

    // metoda która dodaje nam punkty karne do punktów karnych użytkownika
    public void dodajPunkty(int pktKarne) {
        this.punktyKarne += pktKarne;
    }

    // metoda która kasuje punkty karne użytkownika czyli ustawia zero
    public void kasujPunkty() {
        this.punktyKarne = 0;
    }

    // metoda która sprawdza pesel poprzez przyrównanie peselu, który został przekazany jako argument metody do peselu
    // który ma aktualny użytkownik (this) i zwraca prawde lub fałsz zależnie czy pasuje czy nie
    public Boolean sprawdzPesel(String pesel){
        if (this.pesel == pesel)
        {
            return true;
        }
        return false;
    }

    // metoda która zwraca nam aktualną ilość punktów karnych użytkownika
    public int getPunktyKarne(){
        return this.punktyKarne;
    }

    // metoda, która zwraca nam reprezentacje tekstową naszej klasy
    public String toString(){
        String wynik = "Uzytkownik: \nImie: " + this.imie + "\nNazwisko: " + this.nazwisko + "\nPesel: " 
                + this.pesel + "\nCzy jest kierowca: " + this.jestKierowca + "\nPunkty: "
                + this.punktyKarne + "\n";
        return wynik;
    }
}