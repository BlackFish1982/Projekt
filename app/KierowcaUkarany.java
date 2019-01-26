package app;

public class KierowcaUkarany {
    // zmienne klasy użytkownik
    private String imie;
    private String nazwisko;
    private int pesel;
    private boolean jestKierowca;
    private int punktyKarne = 0;

    //    KONSTRUKTORY
    public KierowcaUkarany(String imie, String nazwisko, int pesel) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;

        this.jestKierowca = true;
        this.punktyKarne = 0;
    }

    //    METODY GET
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

    //    METODY SET

    // metoda która zwraca nam aktualną ilość punktów karnych użytkownika
    public int getPunktyKarne() {
        return this.punktyKarne;
    }

    //    INNE METODY
    // Zarzadza punktami - ustalamy wartosc samemu, czyli podajemy ile punktow
//    potrzebny jest informacja z systemu na temat weryfikacji uprawnien (policjanta)
    public void zarzadzajPunktami(int pktKarne, boolean weryfikacjaUprawnien) {
        if (weryfikacjaUprawnien == true) {
            this.punktyKarne = pktKarne;
        }
    }

    // metoda, która zwraca nam reprezentacje tekstową naszej klasy
    public String toString() {
        String wynik = "Dane Kierowcy: \nImie: " + this.getImie() + "\nNazwisko: " + this.getNazwisko() + "\nPesel: "
                + this.getPesel() + "\nPunkty: "
                + this.punktyKarne + "\n";
        return wynik;
    }
}
