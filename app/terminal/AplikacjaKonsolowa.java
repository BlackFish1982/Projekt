package app.terminal;

import app.DaneWejsciowe;
import app.KierowcaUkarany;
import app.Policjant;

import java.io.IOException;
import java.util.Scanner;

public class AplikacjaKonsolowa {
    private DaneWejsciowe daneAplikacji = new DaneWejsciowe();
//    skaner ktory sluzy do zczytywania inputu od uzytkownika z konsoli
    private Scanner skanerKonsoli = new Scanner(System.in);
//    zapisujemy zalogowanego policjanta w tej sesji programu
    private Policjant zalogowanyPolicjant = null;

    public AplikacjaKonsolowa() throws IOException {
//        konstruktor aplikacji, w ktorej ustawiamy wstepne dane w systemie
        daneAplikacji.ustawWstepneDaneSystemu();
//        wyswietlamy menu wyboru systemu
        pokazMenuWyboruSystemu();
    }

    private int wyborInt() {
//        funkcja w ktorej sprawdzamy czy zostal wpisany int
        if (skanerKonsoli.hasNextInt()) {
            return Integer.parseInt(skanerKonsoli.nextLine());
        }
//        jezeli to nie int to zczytujemy to linie bez zapamietania
        skanerKonsoli.nextLine();
//        zwracamy zero zeby w switchu byl przypadek default
        return 0;
    }

    private int wyborNumeruPESEL(int oczekiwanaDlugosc) {
//        funkcja zczytywania numeru pesel
        System.out.println();
        String wejsciowyString = skanerKonsoli.nextLine();
//        sprwadzamy dlugosc
        if (wejsciowyString.length() == oczekiwanaDlugosc) {
            try{
//                jezeli nie uda sie przeksztalcic ze stringa na int, tzn. ze jakis zly znak byl i zwracamy zero
                return Integer.parseInt(wejsciowyString);
            }catch (Exception e){
                System.out.println("Nieprawidłowy znak! Tylko liczby!");
                return 0;
            }
        }
        System.out.println("Zła ilość znaków, oczekiwana ilość znaków = " + oczekiwanaDlugosc);
        return 0;
    }

    void pokazMenuWyboruSystemu() throws IOException {
//        menu wyboru systemu
        System.out.println("\n---Wybór Systemu---");
        System.out.println("1. Kierowca");
        System.out.println("2. Policjant");
        System.out.println("3. Wyjdź");
        switch (wyborInt()) {
            case 1: {
                System.out.println("Kierowca");
                pokazMenuLogowania(false);
                break;
            }
            case 2: {
                System.out.println("Policjant");
                pokazMenuLogowania(true);
                break;
            }
            case 3: {
                System.exit(1);
                break;
            }
            default:
//                jak zle wybierzemy to dostajemy okno z powrotem
                pokazMenuWyboruSystemu();
                break;
        }
    }

    void pokazMenuLogowania(boolean systemDlaPolicjanta) throws IOException {
//        menu logowania zaleznie od wyboru systemu
        if (systemDlaPolicjanta) {
            System.out.println("---Podaj identyfikator Policjanta---");
            String identyfikator = skanerKonsoli.nextLine();
//            sprawdzamy czy jest taki policjant i zapisujemy w systemie
            zalogowanyPolicjant = daneAplikacji.pobierzPolicjanta(identyfikator);
            if (zalogowanyPolicjant != null) {
//                jezeli istnieje to wyswietlamy okno
                pokazSystemPolicjanta();
            } else {
                System.out.println("Brak Policjanta o podanym identyfikatorze!");
                pokazMenuWyboruSystemu();
            }
        } else {
            System.out.println("---Podaj PESEL Kierowcy---");

            String peselString = skanerKonsoli.nextLine();
            try {
//                sprawdzamy pesel i szukamy danych uzytkownika w systemie
                int pesel = Integer.parseInt(peselString);
                if (daneAplikacji.pobierzDaneKierowcy(pesel) != null) {
//                    jezeli sa takie dane to jest kierowca i wyswietlamy jego menu
                    pokazSystemKierowcy(daneAplikacji.pobierzDaneKierowcy(pesel));
                } else {
                    System.out.println("Brak Kierowcy o podanym numerze pesel!");
                    pokazMenuWyboruSystemu();
                }
            } catch (NumberFormatException e) {
//                przypadek zlego numeru pesel
                System.out.println("Proszę podać PESEL w formacie samych numerów!");
                pokazMenuLogowania(false);
            }
        }
    }


    void pokazSystemKierowcy(String daneKierowcy) throws IOException {
//        w menu kierowcy po zalogowaniu mozemy tylko wyswietlic dane i wyjsc
        System.out.println("\n---System Kierowcy---");
        System.out.println("1. Wyświetl dane Kierowcy");
        System.out.println("2. Wyjdź");
        switch (wyborInt()) {
            case 1: {
                System.out.println(daneKierowcy);
                pokazSystemKierowcy(daneKierowcy);
                break;
            }
            case 2: {
                System.exit(1);
                break;
            }
            default:
                pokazSystemKierowcy(daneKierowcy);
                break;
        }
    }

    void pokazSystemPolicjanta() throws IOException {
//        System dla policjanta ma pare opcji ponizej:
        System.out.println("\n---System Policjanta---");
        System.out.println("1. Wyświetl wszystkich Kierowców");
        System.out.println("2. Dodaj/Usuń punkty Kierowcy");
        System.out.println("3. Dodaj Kierowce");
        System.out.println("4. Wyjdź");
        switch (wyborInt()) {
            case 1: {
                System.out.println("Imie || Nazwisko || PESEL || Punkty");
                daneAplikacji.wyswietlKierowcow();
//                po wyswietleniu listy kierowcow wyswietlamy menu policjanta jeszcze raz
                pokazSystemPolicjanta();
                break;
            }
            case 2: {
                pokazMenuDodawaniaUsuwaniaPunktow();
                break;
            }
            case 3: {
                pokazMenuDodawaniaKierowcy();
                break;
            }
            case 4: {
                System.exit(1);
                break;
            }
            default:
                pokazSystemPolicjanta();
                break;
        }
    }

    void pokazMenuDodawaniaKierowcy() throws IOException {
//        menu dodawania kierowcy
//        zczytujemy kolejno imie, nazwisko i pesel
        System.out.println("\n---Dodawanie Nowego Kierowcy---");
        System.out.println("Podaj imie:");
        String imie = skanerKonsoli.nextLine();

        System.out.println("Podaj nazwisko:");
        String nazwisko = skanerKonsoli.nextLine();

        int pesel = 0;
        while (pesel == 0) {
//            pesel czytamy do momentu az bedzie poprawny - niepoprawny to jest o wartosci 0
            System.out.println("Podaj PESEL:");
            pesel = wyborNumeruPESEL(9);
        }
//        dodajemy kierowce
        daneAplikacji.dodajKierowce(new KierowcaUkarany(imie, nazwisko, pesel));
//        wyswietlamy ponownie system dla policjanta
        pokazSystemPolicjanta();
    }

    void pokazMenuDodawaniaUsuwaniaPunktow() throws IOException {
//        menu dodawania i usuwania punktow
        System.out.println("\n---Dodawanie/UsuwaniePunktow---");
//        kierowce ktoremu mamy dodac punkty wyszukujemy po numerze pesel
        int pesel = 0;
        int liczbaPunktow = 0;
        while (pesel == 0) {
            System.out.println("Podaj PESEL kierowcy:");
            pesel = wyborNumeruPESEL(9);
        }

//        jezeli istnieje taki kierowca to czytamy liczbe punktow ktore chcemy dodac/odjac
        if (daneAplikacji.pobierzDaneKierowcy(pesel) != null){
            System.out.println("Podaj ilość punktów, które chcesz dodać/odjąć:");
            liczbaPunktow = wyborInt();
//            zmieniamy punkty z uprawnieniami policjanta
            daneAplikacji.zmienPunkty(zalogowanyPolicjant, pesel, liczbaPunktow);
        }
//        wracamy do systemu policjanta
        pokazSystemPolicjanta();
    }
}