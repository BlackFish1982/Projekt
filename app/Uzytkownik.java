package app;

public class Uzytkownik {
    // zmienne klasy użytkownik
    private String imie;
    private String nazwisko;
    private String pesel;

    //    konstruktor podstawowy
    public Uzytkownik() {
        this.imie = "";
        this.nazwisko = "";
        this.pesel = "";
    }

    //konstruktor który pobiera imie, nazwisko, pesel
    public Uzytkownik(String i, String n, String p){
        // i przypisuje je do zmiennych klasowych
        this.imie = i;
        this.nazwisko = n;
        this.pesel = p;
    }

    // metoda która zwraca nam informacje o imieniu
    public String getImie() {
        return this.imie;
    }

    public String setImie(String i) {
        return this.imie = i;
    }

    // metoda która zwraca nam informacje o nazwisku
    public String getNazwisko() {
        return this.nazwisko;
    }

    public String setNazwisko(String n) {
        return this.nazwisko = n;
    }

    // metoda która zwraca nam informacje o peselu
    public String getPesel() {
        return this.pesel;
    }

    public String setPesel(String p) {
        return this.pesel = p;
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

    // metoda, która zwraca nam reprezentacje tekstową naszej klasy
    public String toString(){
        String wynik = "Uzytkownik: \nImie: " + this.imie + "\nNazwisko: " + this.nazwisko + "\nPesel: "
                + this.pesel;
        return wynik;
    }
}