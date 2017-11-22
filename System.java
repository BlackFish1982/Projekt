package app;//aray list trzeba urzyć do ewidencjonowania użytkowników.
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

// wszystkie operacje mozna robic przez system, wydaje mi sie ze to logiczne zalozenie
public class System {
    private List<Uzytkownik>listaUzytkownikow;
    
    public System(){ //konstruktor
        listaUzytkownikow = new ArrayList<Uzytkownik>();//dodajemy element typu Object,ciągi znaków
    }
    
    public void stworzKierowce(String i, String n, String p, Boolean jk)
    {
        Uzytkownik u = new Uzytkownik(i,n,p);
        u.setJestKierowca(jk);
        this.dodajKierowce(u);
    }
    public void dodajKierowce(Uzytkownik uzytkownikDoDodania){
        this.listaUzytkownikow.add(uzytkownikDoDodania);
    }
    
    public void wyswietlWszystkichKierowcow(){
        for(int i = 0; i < this.listaUzytkownikow.size(); i++){
            out.println(this.listaUzytkownikow.get(i).toString());
        }
    }
    
    public Uzytkownik wyszukajKierowce(String pesel)
    {
        for(int i = 0; i < this.listaUzytkownikow.size(); i++){
            Boolean wynik = this.listaUzytkownikow.get(i).sprawdzPesel(pesel);
            if (wynik){
                return this.listaUzytkownikow.get(i);
            }
        }

        return null;
    }
        

//    funkcja dla uzytkownika
    public void sprawdzPunkty(){
    }
//    automatyczny proces
//    kasujemy wszystkie punkty po danym okresie, czy bierzemy pod uwage kiedy
//    zostały dodane?
    public void kasujPunkty(){  
    }
}

