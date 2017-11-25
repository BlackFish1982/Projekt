package app;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

public class SystemEwidencji {
    // prywatna zmienna typu "LISTA", która przechowywuje w sobie obiekty typu: "Uzytkownik"
    private List<Uzytkownik>listaUzytkownikow;

    // konstruktor
    public SystemEwidencji(){
        // tworzymy nowy obiekt typu "LISTA" i przypisujemy go do naszej zmiennej klasowej
        this.listaUzytkownikow = new ArrayList<Uzytkownik>();
    }

    // argumenty metody: Użytkownik
    public void dodajUzytkownika(Uzytkownik uzytkownikDoDodania) {
        // do naszej zmiennej klasowej, czyli LISTY dodajemy użytkownika
        this.listaUzytkownikow.add(uzytkownikDoDodania);
        out.println("Użytkownik dodany!\n");
    }

    public void wyswietlWszystkichUzytkownikow() {
        // wypisujemy informacje
        out.println("Lista użytkowników w systemie:\n");

        // pętla ograniczona rozmiarem listy użytkowników
        for(int i = 0; i < this.listaUzytkownikow.size(); i++){
            // z listy użytkowników pobieramy metodą "get" użytkownika, który ma indeks "i" i metodą toString zamieniamy
            // na reprezentacje tekstową do wypisania na ekran
            out.println(this.listaUzytkownikow.get(i).toString());
        }
    }

    // metoda typu Boolean ktora zwraca prawde lub falsz, argumenty: pesel
    public Boolean wyszukajUzytkownika(String pesel)
    {
        out.println("Zaczynam wyszukiwać użytkownika po peselu!\n");
        // pętla ograniczona rozmiarem listy użytkowników
        for (int i = 0; i < this.listaUzytkownikow.size(); i++) {
            // z listy użytkowników pobieramy metodą "get" użytkownika, który ma indeks "i" i przypisujemy do zmiennej
            // pasujacyUzytkownikDoPeselu
            Uzytkownik pasujacyUzytkownikDoPeselu = this.listaUzytkownikow.get(i);
            // wywołujemy metodę na użytkowniku do sprawdzenia czy pesel się zgadza z wyszukiwanym peselem
            if (pasujacyUzytkownikDoPeselu.sprawdzPesel(pesel)) {
                // jeżeli się zgadza to:
                // wypisujemy go
                out.println("Uzytkownik znaleziony!\n");
                out.println(pasujacyUzytkownikDoPeselu);
                // i przerywam pętle po znalezieniu żeby dalej nie szukałą poprzez zwrocenie prawdy ze znalezlismy uzytkownika
                return true;
            }
        }
        // wyświetlamy odpowednią wiadomość jeżeli nie ma użytkowników o takim peselu
        out.println("Brak użytkowników o podanym peselu w bazie ewidencji!\n");
        return false;
    }

    // argumenty metody: Policjant, Użytkownik, liczba punktów
    public void dodajPunkty(Policjant aktualnyPolicjant, Uzytkownik uzytkownikDoUkarania, int liczbaPunktow) {
        // sprawdzamy czy policjant ma odpowiednie uprawnienia
        if (aktualnyPolicjant.sprawdzUprawnienia()) {
            // jeżeli tak to:
            // dodajemy punkty metodą "dodajPunkty" do użytkownika
            uzytkownikDoUkarania.dodajPunkty(liczbaPunktow);
            out.println("Punkty zostały dodane!\n");
        }
    }

    // argumenty metody: Użytkownik
    public void sprawdzPunkty(Uzytkownik aktualnyUzytkownik){
        // na użytkowniku wywołujemy metodą do pobrania ilości punktów karnych i przypisujemy do zmiennej "liczbaPunktowUzytkownika"
        int liczbaPunktowUzytkownika = aktualnyUzytkownik.getPunktyKarne();
        // wypisujemy liczbę punktów
        out.println("Liczba punktów: " + liczbaPunktowUzytkownika + "\n");
    }

    // argumenty metody: Policjant, Użytkownik
    public void kasujPunkty(Policjant karajacyPolicjant, Uzytkownik uzytkownikDoSkasowaniaPunktow) {
        // sprawdzamy czy policjant ma uprawnienia
        if (karajacyPolicjant.sprawdzUprawnienia()) {
            // na użytkowniku wywołujemy metodę do kasowania punktów
            uzytkownikDoSkasowaniaPunktow.kasujPunkty();
            // wypisujemy informacje
            out.println("Punkty zostały usunięte!\n");
        }
    }
}

