package app;

public class Uzytkownik {
    private String imie;
    private String nazwisko;
    private String pesel;
    private boolean jestKierowca = false;
    private int punktyKarne = 0;
    

    public Uzytkownik(String i, String n, String p){
        this.imie = i;
        this.nazwisko = n;
        this.pesel = p;
    }
    
    public boolean getJestKierowca(){ 
        return this.jestKierowca;
    }
    public void setJestKierowca(boolean jk){ 
        this.jestKierowca = jk;
    }

    public void zmienIloscPunktow(int pktKarne){
        this.punktyKarne += pktKarne;
    }

    public void kasujPunkty(int pktKarne){
        this.punktyKarne = pktKarne;
    }
    
    public Boolean sprawdzPesel(String pesel){
        if (this.pesel == pesel)
        {
            return true;
        }
        return false;
    }
    public int getPunktyKarne(){
        return this.punktyKarne;
    }
    
    public String toString(){
        String wynik = "Uzytkownik: \nImie: " + this.imie + "\nNazwisko: " + this.nazwisko + "\nPesel: " 
                + this.pesel + "\nCzy jest kierowca: " + this.jestKierowca + "\nPunkty: "
                + this.punktyKarne + "\n";
        return wynik;
    }
}