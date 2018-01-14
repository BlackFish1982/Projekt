package app;

public class Kierowca extends Uzytkownik {
    private boolean jestKierowca;
    private int punktyKarne = 0;

    public Kierowca(String imie, String nazwisko, String pesel) {
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

    // metoda która zwraca nam informacje o tym czy uzytkownik jest kierowca (prawda lub fałsz)
    public boolean getJestKierowca() {
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

    // metoda która zwraca nam aktualną ilość punktów karnych użytkownika
    public int getPunktyKarne() {
        return this.punktyKarne;
    }

    // metoda, która zwraca nam reprezentacje tekstową naszej klasy
    public String toString() {
        String wynik = "Uzytkownik: \nImie: " + this.getImie() + "\nNazwisko: " + this.getNazwisko() + "\nPesel: "
                + this.getPesel() + "\nCzy jest kierowca: " + this.jestKierowca + "\nPunkty: "
                + this.punktyKarne + "\n";
        return wynik;
    }
}
