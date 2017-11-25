package app;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {

//        Tworzymy użytkowników
        Uzytkownik u1 = new Uzytkownik("Jan", "Czerwony", "3912391299");
        Uzytkownik u2 = new Uzytkownik("Piotr", "Zółty", "9458795882");
        Uzytkownik u3 = new Uzytkownik("Stefan", "Biały", "5345435645");
        Uzytkownik u4 = new Uzytkownik("Andrzej", "Niebieski", "53453453545");

//        Tworzymy system
        SystemEwidencji glownySystemEwidencji = new SystemEwidencji();
        
//        Dodajemy uzytkownikow do systemu
        glownySystemEwidencji.dodajUzytkownika(u1);
        glownySystemEwidencji.dodajUzytkownika(u2);
        glownySystemEwidencji.dodajUzytkownika(u3);
        glownySystemEwidencji.dodajUzytkownika(u4);


//        wyswietlamy wszystkich użytkowników w systemie
        glownySystemEwidencji.wyswietlWszystkichUzytkownikow();

//        zmieniamy status na kierowce
        u1.setJestKierowca(true);
        u2.setJestKierowca(true);
        u3.setJestKierowca(true);
        u4.setJestKierowca(true);

//        sprawdzamy czy użytkownik "u2" jest kierowcą
        u2.getJestKierowca();

//        sprawdzamy punkty uzytkownika "u1"
        glownySystemEwidencji.sprawdzPunkty(u1);

//        tworzymy policjanta
        Policjant p1 = new Policjant("Policjant 1");

//        policjant "p1" daje punkty uzytkownikowi "u2"
        glownySystemEwidencji.dodajPunkty(p1, u2, 5);

//        wyswietlamy na ekran uzytkownika "u2" poprzez wyszukanie go po peselu
        glownySystemEwidencji.wyszukajUzytkownika("9458795882");

//        kasujemy poprzez system punkty uzytkownikowi "u2", bierze w tym udział policjant "p1"
        glownySystemEwidencji.kasujPunkty(p1, u2);

//        wyswietlamy na ekran uzytkownika "u2"
        out.println(u2);
    }
}
