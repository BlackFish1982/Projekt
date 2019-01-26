package app;

import java.util.ArrayList;
import java.util.List;
import java.lang.System;


public class SystemEwidencji {
    // prywatna zmienna typu "LISTA", która przechowywuje w sobie obiekty typu: "KierowcaUkarany", nie przechowujemy
    // lista policjantow w systemie ewidencji aby dodawac punktu ukaranym
    private List<KierowcaUkarany> listaKierowcow;
    private List<Policjant> listaPolicjantow;

    //    KONSTRUKTOR
    public SystemEwidencji() {
        // tworzymy nowy obiekt typu "LISTA" i przypisujemy pokazOkno do naszej zmiennej klasowej
        this.listaKierowcow = new ArrayList<KierowcaUkarany>();
        this.listaPolicjantow = new ArrayList<Policjant>();
    }

    //    METODA GET dla listy kierowcow
    public List<KierowcaUkarany> getListaKierowcow() {
        return listaKierowcow;
    }

    // dodajemy kierowce do systemu - od razu staje sie on kierowca
    public void dodajKierowce(KierowcaUkarany kierowcaDoDodania) {
        // do naszej zmiennej klasowej, czyli LISTY dodajemy kierowce
        this.listaKierowcow.add(kierowcaDoDodania);
        System.out.println("KierowcaUkarany dodany!\n");
    }

    // dodajemy policjanta do systemu
    public void dodajPolicjanta(Policjant policjantDoDodania) {
        // do naszej zmiennej klasowej, czyli LISTY dodajemy policjanta
        this.listaPolicjantow.add(policjantDoDodania);
        System.out.println("Policjant dodany!\n");
    }

    public Policjant pobierzPolicjanta(String identyfikatorPolicjanta){
        // pętla ograniczona rozmiarem listy policjantow
        for (int i = 0; i < this.listaPolicjantow.size(); i++) {
            // z listy policjantow pobieramy metodą "get" użytkownika, który ma indeks "i" i przypisujemy do zmiennej
            // policjant
            Policjant policjant = this.listaPolicjantow.get(i);
            // wywołujemy metodę na policjancie do sprawdzenia czy identyfikator się zgadza z wyszukiwanym identyfikatorem
            if (policjant.getIdentyfikator().equals(identyfikatorPolicjanta)) {
                // jeżeli się zgadza to:
                // przerywam pętle po znalezieniu żeby dalej nie szukałą poprzez zwrocenie danego policjanta
                return policjant;
            }
        }
        return null;
    }

    public void wyswietlWszystkichKierowcow() {
        // wypisujemy informacje
        System.out.println("Lista kierowców w systemie:\n");

        // pętla ograniczona rozmiarem listy kierowców
        for (int i = 0; i < this.listaKierowcow.size(); i++) {
            // z listy użytkowników pobieramy metodą "get" użytkownika, który ma indeks "i" i metodą toString zamieniamy
            // na reprezentacje tekstową do wypisania na ekran
            System.out.println(this.listaKierowcow.get(i).toString());
        }
    }

    // metoda typu KierowcaUkarany, ktora zwraca nam kierowce do ktorego pasuje podany numer pesel,
//    bedziemy jej uzywac do wyszukiwania kierowcy po numerze pesel
    public KierowcaUkarany wyszukajKierowce(int pesel) {
        System.out.println("Zaczynam wyszukiwać kierowce po peselu!\n");
        // pętla ograniczona rozmiarem listy użytkowników
        for (int i = 0; i < this.listaKierowcow.size(); i++) {
            // z listy kierowców pobieramy metodą "get" użytkownika, który ma indeks "i" i przypisujemy do zmiennej
            // pasujacyKierowcaDoPeselu
            KierowcaUkarany pasujacyKierowcaDoPeselu = this.listaKierowcow.get(i);
            // wywołujemy metodę na kierowcy do sprawdzenia czy pesel się zgadza z wyszukiwanym peselem
            if (pasujacyKierowcaDoPeselu.getPesel() == pesel) {
                // jeżeli się zgadza to:
                // wypisujemy pokazOkno
                System.out.println("KierowcaUkarany znaleziony!\n");
                System.out.println(pasujacyKierowcaDoPeselu);
                // i przerywam pętle po znalezieniu żeby dalej nie szukałą poprzez zwrocenie prawdy ze znalezlismy kierowca
                return pasujacyKierowcaDoPeselu;
            }
        }
        // wyświetlamy odpowednią wiadomość jeżeli nie ma użytkowników o takim peselu
        System.out.println("Brak kierowców o podanym peselu w bazie ewidencji!\n");
        return null;
    }

    // argumenty metody: Policjant, KierowcaUkarany, liczba punktów
    public void dodajPunkty(Policjant aktualnyPolicjant, KierowcaUkarany kierowcaDoUkarania, int liczbaPunktow) {
        // sprawdzamy czy policjant ma odpowiednie uprawnienia
        if (aktualnyPolicjant.sprawdzUprawnienia()) {
            // jeżeli tak to:
            // dodajemy punkty metodą "dodajPunkty" do Kierowcy
            kierowcaDoUkarania.zarzadzajPunktami(liczbaPunktow, aktualnyPolicjant.sprawdzUprawnienia());
            System.out.println("Punkty zostały dodane!\n");
        }
    }

    // argumenty metody: KierowcaUkarany
    public void sprawdzPunkty(KierowcaUkarany aktualnyKierowca) {
        // na użytkowniku wywołujemy metodą do pobrania ilości punktów karnych i przypisujemy do zmiennej "liczbaPunktowKierowcy"
        int liczbaPunktowKierowcy = aktualnyKierowca.getPunktyKarne();
        // wypisujemy liczbę punktów
        System.out.println("Liczba punktów: " + liczbaPunktowKierowcy + "\n");
    }

    // argumenty metody: Policjant, KierowcaUkarany
    public void kasujPunkty(Policjant karajacyPolicjant, KierowcaUkarany kierowcaDoSkasowaniaPunktow) {
        // sprawdzamy czy policjant ma uprawnienia
        if (karajacyPolicjant.sprawdzUprawnienia()) {
            // na Kierowcy wywołujemy metodę do kasowania punktów
            kierowcaDoSkasowaniaPunktow.zarzadzajPunktami(0, karajacyPolicjant.sprawdzUprawnienia());
            // wypisujemy informacje
            System.out.println("Punkty zostały usunięte!\n");
        }
    }
}