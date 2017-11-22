package app;
import static java.lang.System.out;

public class Policjant {
    
    public Policjant(String nazwa){ //konstruktor
        String identyfikator = nazwa;
    }
    
    //    id kierowcy, punkty, okres
    public static void dodajPunkty(){
    }
    
    public void ukaraj_kierowce(Uzytkownik uk, int punkty){
        
        if(uk.jestKierowca == true){
            uk.punktyKarne += punkty;
            out.println("\nPunkty dodane!\n");
        }else{
            out.println("\nNie mozna dodac punktow, bo nie jest kierowca!\n");
        }
    }
}
