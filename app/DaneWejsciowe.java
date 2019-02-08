package app;
import java.util.List;

public class DaneWejsciowe {

    //    Tworzymy system
    private static SystemEwidencji glownySystemEwidencji = new SystemEwidencji();

    public List<KierowcaUkarany> ustawWstepneDaneSystemu() {
        //        Wypełniamy wstępnymi danymi:

        //        Tworzymy kierowcow
        KierowcaUkarany k1 = new KierowcaUkarany("Jan", "Czerwony", 391239129);
        KierowcaUkarany k2 = new KierowcaUkarany("Piotr", "Zółty", 945879588);
        KierowcaUkarany k3 = new KierowcaUkarany("Stefan", "Biały", 534543564);
        KierowcaUkarany k4 = new KierowcaUkarany("Andrzej", "Niebieski", 534534535);


        Policjant p1 = new Policjant("h546gh45g64");

        //        Dodajemy kierowcow do systemu
        glownySystemEwidencji.dodajKierowce(k1);
        glownySystemEwidencji.dodajKierowce(k2);
//        glownySystemEwidencji.dodajKierowce(u3);
        glownySystemEwidencji.dodajKierowce(k4);
        glownySystemEwidencji.dodajPolicjanta(p1);

        return glownySystemEwidencji.getListaKierowcow();
    }

    public Policjant pobierzPolicjanta(String identyfikator){
//        funkcja ktora pobiera policjanta i ma dostep do systemu ewidencji
        return glownySystemEwidencji.pobierzPolicjanta(identyfikator);
    }

    public String pobierzDaneKierowcy(int pesel){
//        funkcja ktora pobiera dane kierowcy na podstawie numeru pesel
        KierowcaUkarany wyszukanyKierowca = glownySystemEwidencji.wyszukajKierowce(pesel);
        if (wyszukanyKierowca == null){
            return null;
        }else{
            return wyszukanyKierowca.toString();
        }
    }
    public void wyswietlKierowcow(){
//        wyswietlamy wszystkich kierowcow w postaci konsolowej
        glownySystemEwidencji.wyswietlWszystkichKierowcow(true);
    }

    public void dodajKierowce(KierowcaUkarany nowyKierowca){
//        dodajemy kierowce
        glownySystemEwidencji.dodajKierowce(nowyKierowca);
    }

    public void zmienPunkty(Policjant policjant, int pesel, int nowePunkty){
//        zmieniamy punkty kierowcy poprzez policjanta w systemie
        glownySystemEwidencji.dodajPunkty(policjant, glownySystemEwidencji.wyszukajKierowce(pesel), nowePunkty);
    }
}