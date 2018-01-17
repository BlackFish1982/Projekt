package app;

public class Uzytkownik {
    // zmienne klasy użytkownik
    private String imie;
    private String nazwisko;
    private int pesel;

    //    konstruktor podstawowy
    public Uzytkownik() {
        this.imie = "";
        this.nazwisko = "";
        this.pesel = 0;
    }

    //  konstruktor który pobiera imie, nazwisko, pesel
    public Uzytkownik(String i, String n, int p) {
        // i przypisuje je do zmiennych klasowych
        this.imie = i;
        this.nazwisko = n;
        this.pesel = p;
    }

    //  Metody pobierajce pola klasy/obiektu
    public String getImie() {
        return this.imie;
    }

    public String getNazwisko() {
        return this.nazwisko;
    }

    public int getPesel() {
        return this.pesel;
    }

    //    Metody ustawiajace pola klasy/obiektu
    public String setImie(String i) {
        return this.imie = i;
    }

    public String setNazwisko(String n) {
        return this.nazwisko = n;
    }

    public int setPesel(int p) {
        return this.pesel = p;
    }

    //    metoda, która zwraca nam reprezentacje tekstową naszej klasy
    public String toString() {
        String wynik = "Uzytkownik: \nImie: " + this.imie + "\nNazwisko: " + this.nazwisko + "\nPesel: "
                + this.pesel;
        return wynik;
    }
}