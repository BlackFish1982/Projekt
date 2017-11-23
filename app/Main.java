package app;
import java.lang.System;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {

        Uzytkownik u1 = new Uzytkownik("Jan", "Czerwony", "3912391299");
        Uzytkownik u2 = new Uzytkownik("Piotr", "Zółty", "9458795882");
        Uzytkownik u3 = new Uzytkownik("Stefan", "Biały", "5345435645");
        Uzytkownik u4 = new Uzytkownik("Andrzej", "Niebieski", "53453453545");

//        Tworzymy system
        SystemEwidencji glownySystemEwidencji = new SystemEwidencji();
        
//        Dodajemy uzytkownikow do systemu
        glownySystemEwidencji.dodajKierowce(u1);
        glownySystemEwidencji.dodajKierowce(u2);
        glownySystemEwidencji.dodajKierowce(u3);
        glownySystemEwidencji.dodajKierowce(u4);


//        wyswietlamy
        glownySystemEwidencji.wyswietlWszystkichKierowcow();

//        zmieniamy status na kierowce
        u1.setJestKierowca(true);
        u2.setJestKierowca(true);
        u3.setJestKierowca(true);
        u4.setJestKierowca(true);

        glownySystemEwidencji.sprawdzPunkty(u1);


//        dodajemy policjanta
        Policjant p1 = new Policjant("Policjant 1");
        
//        policjant daje punkty
        glownySystemEwidencji.dodajPunkty(p1, "5345435645", 5);

        out.println(u3);

        glownySystemEwidencji.kasujPunkty(p1,"5345435645");

        out.println(u3);
    }
}
