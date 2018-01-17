package app;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System;

public class SystemEwidencji {
    // prywatna zmienna typu "LISTA", która przechowywuje w sobie obiekty typu: "Kierowca", nie przechowujemy
    // w systemie uzytkownikow, poniewaz jest to system ewidencji kierowcow
    private List<Kierowca> listaKierowcow;

    //    KONSTRUKTOR
    public SystemEwidencji(){
        // tworzymy nowy obiekt typu "LISTA" i przypisujemy go do naszej zmiennej klasowej
        this.listaKierowcow = new ArrayList<Kierowca>();
    }

    //    METODA GET dla listy kierowcow
    public List<Kierowca> getListaKierowcow() {
        return listaKierowcow;
    }

    // dodajemy uzytkownika do systemu - od razu staje sie on kierowca
    public void dodajUzytkownika(Uzytkownik uzytkownikDoDodania) {
        // do naszej zmiennej klasowej, czyli LISTY dodajemy użytkownika
        this.listaKierowcow.add(new Kierowca(uzytkownikDoDodania));
        out.println("Kierowca dodany!\n");
    }

    public void wyswietlWszystkichKierowcow() {
        // wypisujemy informacje
        out.println("Lista kierowców w systemie:\n");

        // pętla ograniczona rozmiarem listy kierowców
        for (int i = 0; i < this.listaKierowcow.size(); i++) {
            // z listy użytkowników pobieramy metodą "get" użytkownika, który ma indeks "i" i metodą toString zamieniamy
            // na reprezentacje tekstową do wypisania na ekran
            out.println(this.listaKierowcow.get(i).toString());
        }
    }

    // metoda typu Kierowca, ktora zwraca nam kierowce do ktorego pasuje podany numer pesel,
//    bedziemy jej uzywac do wyszukiwania kierowcy po numerze pesel
    public Kierowca wyszukajKierowce(int pesel)
    {
        out.println("Zaczynam wyszukiwać kierowce po peselu!\n");
        // pętla ograniczona rozmiarem listy użytkowników
        for (int i = 0; i < this.listaKierowcow.size(); i++) {
            // z listy kierowców pobieramy metodą "get" użytkownika, który ma indeks "i" i przypisujemy do zmiennej
            // pasujacyUzytkownikDoPeselu
            Kierowca pasujacyKierowcaDoPeselu = this.listaKierowcow.get(i);
            // wywołujemy metodę na kierowcy do sprawdzenia czy pesel się zgadza z wyszukiwanym peselem
            if (pasujacyKierowcaDoPeselu.sprawdzPesel(pesel)) {
                // jeżeli się zgadza to:
                // wypisujemy go
                out.println("Kierowca znaleziony!\n");
                out.println(pasujacyKierowcaDoPeselu);
                // i przerywam pętle po znalezieniu żeby dalej nie szukałą poprzez zwrocenie prawdy ze znalezlismy kierowca
                return pasujacyKierowcaDoPeselu;
            }
        }
        // wyświetlamy odpowednią wiadomość jeżeli nie ma użytkowników o takim peselu
        out.println("Brak kierowców o podanym peselu w bazie ewidencji!\n");
        return null;
    }

    // argumenty metody: Policjant, Kierowca, liczba punktów
    public void dodajPunkty(Policjant aktualnyPolicjant, Kierowca kierowcaDoUkarania, int liczbaPunktow) {
        // sprawdzamy czy policjant ma odpowiednie uprawnienia
        if (aktualnyPolicjant.sprawdzUprawnienia()) {
            // jeżeli tak to:
            // dodajemy punkty metodą "dodajPunkty" do Kierowcy
            kierowcaDoUkarania.dodajPunkty(liczbaPunktow);
            out.println("Punkty zostały dodane!\n");
        }
    }

    // argumenty metody: Kierowca
    public void sprawdzPunkty(Kierowca aktualnyKierowca) {
        // na użytkowniku wywołujemy metodą do pobrania ilości punktów karnych i przypisujemy do zmiennej "liczbaPunktowKierowcy"
        int liczbaPunktowKierowcy = aktualnyKierowca.getPunktyKarne();
        // wypisujemy liczbę punktów
        out.println("Liczba punktów: " + liczbaPunktowKierowcy + "\n");
    }

    // argumenty metody: Policjant, Kierowca
    public void kasujPunkty(Policjant karajacyPolicjant, Kierowca kierowcaDoSkasowaniaPunktow) {
        // sprawdzamy czy policjant ma uprawnienia
        if (karajacyPolicjant.sprawdzUprawnienia()) {
            // na Kierowcy wywołujemy metodę do kasowania punktów
            kierowcaDoSkasowaniaPunktow.kasujPunkty();
            // wypisujemy informacje
            out.println("Punkty zostały usunięte!\n");
        }
    }
}


}