package app;

public class Uzytkownik {
    String imie;
    String nazwisko;
    String pesel;
    boolean jestKierowca = false;
    int punktyKarne = 0;
    

    public Uzytkownik(String i, String n, String p){ //konstruktor
        imie = i;
        nazwisko = n;
        pesel = p;
    }
    
    public boolean getJestKierowca(){ 
        return jestKierowca;
    }
    public void setJestKierowca(boolean jk){ 
        this.jestKierowca = jk;
    }
    
    public Boolean sprawdzPesel(String pesel){
        if (this.pesel == pesel)
        {
            return true;
        }
        return false;
    }
    
    public String toString(){ //funkcja przedefiniowana przez programistę
        String wynik = "Uzytkownik: \nImie: " + this.imie + "\nNazwisko: " + this.nazwisko + "\nPesel: " 
                + this.pesel + "\nCzy jest kierowca: " + this.jestKierowca + "\nPunkty: "
                + this.punktyKarne + "\n";
        return wynik;
    }
}