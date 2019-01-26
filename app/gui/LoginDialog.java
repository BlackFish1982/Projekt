package app.gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

public class LoginDialog {
//    specjalna klasa okna dialogowego
//    obiekt okna dialogowego JAVAFX
    private Dialog<Pair<String, String>> dialog = new Dialog<>();

//    funkcja ktora dodaje nasze elementy do okna dialogu JAVAFX
    public Optional<Pair<String, String>> pokazOkno(boolean wersjaSystemuDlaPolicjanta){
//        zaleznie od argumenty wypalniamy dane dla policjanta czy kierowcy
        String wersjaTekstu = "Pesel";
        if (wersjaSystemuDlaPolicjanta){
            wersjaTekstu = "Identyfikator";
        }
//        ustawiamy tytul i tekst
        dialog.setTitle("Logowanie do systemu:");
        dialog.setHeaderText("Proszę podaj dane:");

//        tworzymy przyciski ktore automatycznie dadza nam event po klikniecu
        ButtonType przyciskZaloguj = new ButtonType("Zaloguj", ButtonBar.ButtonData.OK_DONE);
        ButtonType przyciskAnuluj = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);

//        dodajemy te przyciski do okna dialogu
        dialog.getDialogPane().getButtonTypes().addAll(przyciskZaloguj, przyciskAnuluj);

//        tworzy uklad typu siatka
        GridPane grid = new GridPane();
//        dodajemy jej wymiary
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

//        tworzymy pole tekstowe aby wpisac identyfikator lub pesel
        TextField poleTekstoweDoWpisaniaIdentyfikacji = new TextField();
//        wyswietlamy co ma wpisac uzytkownik
        poleTekstoweDoWpisaniaIdentyfikacji.setPromptText("wpisz " + wersjaTekstu.toLowerCase());
//        dodajemy restrykcje zaleznie od wersji loginu aby poprawne dane wpisywac
        Restrykcje.restrykcjaPoprawnosci(dialog, przyciskZaloguj, poleTekstoweDoWpisaniaIdentyfikacji);
        if (!wersjaSystemuDlaPolicjanta){
            Restrykcje.restrykcjaTypu(poleTekstoweDoWpisaniaIdentyfikacji);
            Restrykcje.restrykcjaDlugosci(poleTekstoweDoWpisaniaIdentyfikacji, 9);
        }else{
            Restrykcje.restrykcjaDlugosci(poleTekstoweDoWpisaniaIdentyfikacji, 11);
        }

//        do siatki dodajemy etykiete ktora opisuje wersje
        grid.add(new Label(wersjaTekstu + ":"), 0, 0);
        grid.add(poleTekstoweDoWpisaniaIdentyfikacji, 1, 0);

//        ustawiamy uklad dla okna dialogu
        dialog.getDialogPane().setContent(grid);

// Kopiujemy zmienna zeby mozna bylo jej uzyc w funkcji lambda
        final String wersjaTekstuLambda = wersjaTekstu;

//        robimy konwersje do pary wersjas systemu i pesel/identyfiktor po kliknieciu przycisku zalogu
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == przyciskZaloguj) {
//                jezeli przycisk zaloguj tworzymy wynik jako para wartosci
                return new Pair<>(wersjaTekstuLambda, poleTekstoweDoWpisaniaIdentyfikacji.getText());
            }
//            jezeli przycisk anuluj zwracamy nic
            return null;
        });

//        wyswietlamy okno i czekamy na wpisane dane i zatwierdzenie
        Optional<Pair<String, String>> result = dialog.showAndWait();

//        zwracamy taka informacje z okna dialogu na zewnatrz
        return result;
    }
}
