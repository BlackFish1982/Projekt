package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DaneWejsciowe {

    //    Tworzymy system
    private static SystemEwidencji glownySystemEwidencji = new SystemEwidencji();

    public ObservableList<Kierowca> ustawWstepneDaneSystemu() {
        //        Wypełniamy wstępnymi danymi:

        //        Tworzymy użytkowników
        Uzytkownik u1 = new Uzytkownik("Jan", "Czerwony", 391239129);
        Uzytkownik u2 = new Uzytkownik("Piotr", "Zółty", 945879588);
        Uzytkownik u3 = new Uzytkownik("Stefan", "Biały", 534543564);
        Uzytkownik u4 = new Uzytkownik("Andrzej", "Niebieski", 534534535);

//        Tworzymy system

//        zmieniamy status na kierowce
        Kierowca k1 = new Kierowca(u1, true, 0);
        Kierowca k2 = new Kierowca(u2, true, 0);
//        Kierowca k3 = new Kierowca(u3, true, 0);
        Kierowca k4 = new Kierowca(u4, true, 0);

        Policjant p1 = new Policjant("h546gh45g64");

        //        Dodajemy uzytkownikow do systemu
        glownySystemEwidencji.dodajUzytkownika(k1);
        glownySystemEwidencji.dodajUzytkownika(k2);
//        glownySystemEwidencji.dodajUzytkownika(u3);
        glownySystemEwidencji.dodajUzytkownika(k4);

        return FXCollections.observableArrayList(glownySystemEwidencji.getListaKierowcow());
    }
}