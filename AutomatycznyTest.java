package app;
import static java.lang.System.out;

public class AutomatycznyTest {
    public static void main(String[] args) {
//        testy
//        dodajemy uzytkownikow
        Uzytkownik u1 = new Uzytkownik("Jan", "Kowalski", "3912391299");
        out.println(u1.toString());
        Uzytkownik u2 = new Uzytkownik("Piotr", "Zielinski", "9458795882");
        out.println(u2.toString());
        
//        Tworzymy system
        System systemGlowny = new System();
        
//        Dodajemy uzytkownikow do systemu
//        systemGlowny.dodajKierowce(u1);
//        systemGlowny.dodajKierowce(u2);

        systemGlowny.stworzKierowce("dupa", "cycki", "53453453", Boolean.TRUE);
        
//        wyswietlamy
        out.println("teraz wyswietlamy w petli uzytkownikow w systemie");
        systemGlowny.wyswietlWszystkichKierowcow();

//        zmieniamy status na kierowce
        u1.setJestKierowca(true);

//        dodajemy policjanta
        Policjant p1 = new Policjant("Policjant 1");
        
//        policjant daje punkty
        p1.ukaraj_kierowce(u1, 5);
        p1.ukaraj_kierowce(u2, 2);
        
//        wypisujemy informacje na temat uzytkownikow
        out.println(u1.toString());
        out.println(u2.toString());
    }
}
