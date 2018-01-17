package app;

public class Kierowca extends Uzytkownik {
    private boolean jestKierowca;
    private int punktyKarne = 0;

    //    KONSTRUKTORY
    public Kierowca(String imie, String nazwisko, int pesel) {
        super(imie, nazwisko, pesel);

        this.jestKierowca = true;
        this.punktyKarne = 0;
    }

    public Kierowca(Uzytkownik uzytkownik) {
        super(uzytkownik.getImie(), uzytkownik.getNazwisko(), uzytkownik.getPesel());

        this.jestKierowca = true;
        this.punktyKarne = 0;
    }

    public Kierowca(Uzytkownik uzytkownik, boolean jk, int pkt) {
        super(uzytkownik.getImie(), uzytkownik.getNazwisko(), uzytkownik.getPesel());

        this.jestKierowca = jk;
        this.punktyKarne = pkt;
    }

    //    METODY GET
    // metoda która zwraca nam informacje o tym czy uzytkownik jest kierowca (prawda lub fałsz)
    public boolean getJestKierowca() {
        return this.jestKierowca;
    }

    //    METODY SET
    // metoda która ustawia nam fakt czy użytkownik jest kierowcą (prawda lub fałsz)
    public void setJestKierowca(boolean jestKierowca) {
        this.jestKierowca = jestKierowca;
    }

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
        String wynik = "Uzytkownik: \nImie: " + this.getImie() + "\nNazwisko: " + this.getNazwisko() + "\nPesel: "
                + this.getPesel() + "\nCzy jest kierowca: " + this.jestKierowca + "\nPunkty: "
                + this.punktyKarne + "\n";
        return wynik;
    }
}
