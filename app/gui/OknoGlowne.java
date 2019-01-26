package app.gui;

import app.DaneWejsciowe;
import app.KierowcaUkarany;
import app.Policjant;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.converter.IntegerStringConverter;

import java.util.Optional;

public class OknoGlowne extends Application{

//    tabela przypominajace excel do zarzadzania danymi dla policjanta
    private TableView<KierowcaUkarany> tabela = new TableView<>();
//    w tej zmiennej zawiera sie pesel/identyfikator i wartosc tego peselu/identyfikatora pobierana z okna logowania
    private Optional<Pair<String, String>> wynikOknaDoLogowania;
//    dane wejsciowe do aplikacji jak system ewidencji
    private DaneWejsciowe daneAplikacji = new DaneWejsciowe();
//    konwersje danych z systemu ewidencji do specjalnego typu ktory mozna umiescic w tabeli
    private ObservableList<KierowcaUkarany> danePoKonwersjiDoTabeli = FXCollections.observableArrayList(daneAplikacji.ustawWstepneDaneSystemu());

//    funkcja JAVAFX ktora automatycznie uruchamia okno
    @Override
    public void start(Stage glowneOkno) throws Exception {

//         okno wyboru typu loginu = policjant czy uzytkownik
        final Stage wyborLoginuOkno = new Stage();
        HBox ukladHoryzontalnyBoxWyborLogowania = new HBox();
        VBox ukladWertykalnyBoxWyborLogowania = new VBox();
//        ustawiamy wyglad i rozmieszczenie
        ukladHoryzontalnyBoxWyborLogowania.setPadding(new Insets(10));
        ukladHoryzontalnyBoxWyborLogowania.setSpacing(10);
        ukladHoryzontalnyBoxWyborLogowania.setAlignment(Pos.CENTER);
        ukladWertykalnyBoxWyborLogowania.setPadding(new Insets(10));
        ukladWertykalnyBoxWyborLogowania.setSpacing(10);
        ukladWertykalnyBoxWyborLogowania.setAlignment(Pos.CENTER);
//        przyciski wyboru systemu dla kierowcy lub policjanta
        Button kierowcaPrzycisk = new Button("Kierowca");
        Button policjantPrzycisk = new Button("Policjant");
//        etykieta ktora wyswietli informacje w przypadku podania zlego pesela lub identyfikatora
        Label etykietaInformacjiLogowania = new Label("");
//        ustawiamy kolor etykiety na czerwony
        etykietaInformacjiLogowania.setTextFill(Color.web("#dd3735"));

//        dodajemy do ukladow typu BOX przyciski i etykiety ktore stworzylismy wyzej
        ukladHoryzontalnyBoxWyborLogowania.getChildren().addAll(kierowcaPrzycisk, policjantPrzycisk);
        ukladWertykalnyBoxWyborLogowania.getChildren().addAll(ukladHoryzontalnyBoxWyborLogowania, etykietaInformacjiLogowania);

//        dodajemy gotowy uklad BOX do sceny
        Scene scenaOknoWyboruLoginu = new Scene(ukladWertykalnyBoxWyborLogowania, 300, 100);
//        ustawiamy tytul okna
        wyborLoginuOkno.setTitle("Wybierz wersje:");
//        dodajemy scene do okna wyboru loginu
        wyborLoginuOkno.setScene(scenaOknoWyboruLoginu);

//        wyswietlamy okno wyboru logowania
        wyborLoginuOkno.show();


//        Tworzymy samo okno logowania zaleznie od wybranej wersji w oknie wyboru loginu
        kierowcaPrzycisk.setOnAction(new EventHandler<ActionEvent>() {
//            przechwytujemy event klikniecia w przycisk kierowcy
            @Override
            //                funkcja ktora przetwarza ten event
            public void handle(ActionEvent event) {
//                tworzymy specjalne okno - dialog aby wpisac dane do logowania
                LoginDialog dialogLogowaniaUzytkownika = new LoginDialog();
//                wyswietlamy dane okno z argumentem ze ma byc to okno dla kierowcy i czekamy na wpisane dane
                wynikOknaDoLogowania = dialogLogowaniaUzytkownika.pokazOkno(false);
//                jezeli wpisane dane istnieja
                if (wynikOknaDoLogowania.isPresent()) {
//                    sprawdzamy czy mozemy pobrac dane kierowcy na podstawie wpisanego peselu
//                    jednoczesnie konwertujemy string na int z pola tekstowego peselu
                    if(daneAplikacji.pobierzDaneKierowcy(Integer.parseInt(wynikOknaDoLogowania.get().getValue())) != null){
//                        jezeli znalezlismy takiego kierowce to zamykamy okno wyboru loginu
                        wyborLoginuOkno.close();
//                        pokazujemy okno glowne z danymi wpisanymi podczas logowania
                        pokazOknoGlowne(wynikOknaDoLogowania.get().getKey(), wynikOknaDoLogowania.get().getValue());
                    }else{
//                        jezeli nie ma takiego kierowcy wyswietlamy komunikat i czekamy na nowe logowanie
                        etykietaInformacjiLogowania.setText("Brak uzytkownika o takim numerze pesel!");
                    }
                }
            }
        });

//        identyczna sytuacja dla klikniecia wyboru logowania dla policjanta
        policjantPrzycisk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                tworzymy specjalne okno - dialog aby wpisac dane do logowania
                LoginDialog dialogLogowaniaPolicjanta = new LoginDialog();
//                wyswietlamy dane okno z argumentem ze ma byc to policjant i czekamy na wpisane dane
                wynikOknaDoLogowania = dialogLogowaniaPolicjanta.pokazOkno(true);
                //                jezeli wpisane dane istnieja
                if (wynikOknaDoLogowania.isPresent()) {
//                    sprawdzamy czy mozemy pobrac polcijanta na podstawie wpisanego identyfikatora
//                    jednoczesnie konwertujemy string na int z pola tekstowego peselu
                    if(daneAplikacji.pobierzPolicjanta(wynikOknaDoLogowania.get().getValue()) != null){
//                        jezeli znalezlismy takiego kierowce to zamykamy okno wyboru loginu
                        wyborLoginuOkno.close();
//                        pokazujemy okno glowne z danymi wpisanymi podczas logowania
                        pokazOknoGlowne(wynikOknaDoLogowania.get().getKey(), wynikOknaDoLogowania.get().getValue());
                    }else{
//                        jezeli nie ma takiego kierowcy wyswietlamy komunikat i czekamy na nowe logowanie
                        etykietaInformacjiLogowania.setText("Brak policjanta o podanym identyfikatorze!");
                    }
                }
            }
        });
//        koniec okna wyboru typu loginu
    }

//    funkcja ktora tworczy okno glowne
//    przyjmuje wersje jaka ma byc dla policjanta czy kierowce i dany numer pesel/identyfikator
    private void pokazOknoGlowne(String wersja, String identyfikacja){
//        ustawiamy atrybuty okna
        Stage glowneOkno = new Stage();
        glowneOkno.setTitle("System Ewidencji");
        glowneOkno.setWidth(650);
        glowneOkno.setHeight(550);

        Scene scenaGlownegoOkna;

//        jezeli wersja okna dla policjanta
        if (wersja.equals("Identyfikator")) {
//            uklad boxowy dodawania nowego kierowcy
            final HBox ukladHoryzontalnyDodawaniaKierowcy = new HBox();

//            tworzymy wszystkie kolumny tabeli
            TableColumn imieCol = new TableColumn("Imie");
            imieCol.setMinWidth(100);
            imieCol.setCellValueFactory(
                    new PropertyValueFactory<KierowcaUkarany, String>("imie"));

            TableColumn nazwiskoCol = new TableColumn("Nazwisko");
            nazwiskoCol.setMinWidth(100);
            nazwiskoCol.setCellValueFactory(
                    new PropertyValueFactory<KierowcaUkarany, String>("nazwisko"));
            imieCol.setCellFactory(TextFieldTableCell.forTableColumn());

            TableColumn peselCol = new TableColumn("Pesel");
            peselCol.setMinWidth(200);
            peselCol.setCellValueFactory(
                    new PropertyValueFactory<KierowcaUkarany, String>("pesel"));

            TableColumn punktyCol = new TableColumn("Punkty Karne");
            punktyCol.setMinWidth(100);
//            dodajemy kolumny do tabeli
            tabela.getColumns().addAll(imieCol, nazwiskoCol, peselCol, punktyCol);

//            Tworzymy pola tekstowe w celu dodania nowego kierowcy do tabeli/systemu
            final TextField poleTekstoweDodajImie = new TextField();
            poleTekstoweDodajImie.setPromptText("Imie");
            final TextField poleTekstoweDodajNazwisko = new TextField();
            poleTekstoweDodajNazwisko.setPromptText("Nazwisko");
            final TextField poleTekstoweDodajPesel = new TextField();
            poleTekstoweDodajPesel.setPromptText("Pesel");
//            dodajemy restyrkcje na te pola zeby poprawne dane byly wpisywane
            Restrykcje.restrykcjaTypu(poleTekstoweDodajPesel);
            Restrykcje.restrykcjaDlugosci(poleTekstoweDodajPesel, 9);

//            tworzymy przycisk dodania
            final Button dodajKierowcePrzycisk = new Button("Dodaj");

//            dodajemy wszystkie elemnty do ukladu
            ukladHoryzontalnyDodawaniaKierowcy.getChildren().addAll(poleTekstoweDodajImie, poleTekstoweDodajNazwisko, poleTekstoweDodajPesel, dodajKierowcePrzycisk);
            ukladHoryzontalnyDodawaniaKierowcy.setSpacing(3);

//            tworzymy dodatkowe potrzebne ukladu aby okno ladnie sie wyswietlalo
            final VBox ukladWertyklanyPolicjanta = new VBox();
            ukladWertyklanyPolicjanta.setSpacing(5);
            ukladWertyklanyPolicjanta.setPadding(new Insets(10, 0, 0, 10));
            final Label wersjaSystemuDlaPolicjantaEtykieta = new Label("System dla Policjanta");
            wersjaSystemuDlaPolicjantaEtykieta.setFont(new Font("Arial", 20));
//            dodajemy te uklady i elementy
            ukladWertyklanyPolicjanta.getChildren().addAll(wersjaSystemuDlaPolicjantaEtykieta, tabela, ukladHoryzontalnyDodawaniaKierowcy);

//            pobieramy policjanta ktory zalogowal sie do systemu
            Policjant aktualnyPolicjant = daneAplikacji.pobierzPolicjanta(identyfikacja);
//            zmieniamy tabele na wersje ktora mozna edytowac
            tabela.setEditable(true);
            tabela.getSelectionModel().cellSelectionEnabledProperty().set(true);
//            wypelniamy tabele danymi
            ustawDane(danePoKonwersjiDoTabeli);

//            spoecjanlna funkcja ktora przechwytuje event edycji tabeli
            punktyCol.setCellValueFactory(
                    new PropertyValueFactory<KierowcaUkarany, Integer>("punktyKarne"));
            punktyCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//            na edycji danej komorki w tabeli
            punktyCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<KierowcaUkarany, Integer>>() {
//                        dodajemy punkty danemu kierowcy po sprawdzeniu uprawnien policjanta
                        @Override
                        public void handle(TableColumn.CellEditEvent<KierowcaUkarany, Integer> t) {
                            ((KierowcaUkarany) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).zarzadzajPunktami(t.getNewValue(), aktualnyPolicjant.sprawdzUprawnienia());
                        }
                    }
            );

//            funkcja ktora wywolywana jest na kliknieciu przycisku dodania nowego kierowcy
            dodajKierowcePrzycisk.setOnAction(new EventHandler<ActionEvent>() {
                @Override
//                przechwytujemy ten event
                public void handle(ActionEvent e) {
//                    do danych tabeli dodajemy wartosci z pol tekstowych
                    danePoKonwersjiDoTabeli.add(new KierowcaUkarany(
                            poleTekstoweDodajImie.getText(),
                            poleTekstoweDodajNazwisko.getText(),
                            Integer.parseInt(poleTekstoweDodajPesel.getText())));
//                    czyscimy pola tekstowe
                    poleTekstoweDodajImie.clear();
                    poleTekstoweDodajNazwisko.clear();
                    poleTekstoweDodajPesel.clear();
//                    ustawiamy dane do tabeli do wyswietlenia
                    ustawDane(danePoKonwersjiDoTabeli);
                }
            });

//            ustawiamy gotowy oklad w scenie
            scenaGlownegoOkna = new Scene(ukladWertyklanyPolicjanta);

        }else{
//            tutaj tworzymy wersje okna dla kierowcy jezeli wczesniejszy if nie dopasowal wersji do identyfiaktora tylko peselu

//            wynik danych przetrzymywany w etykiecie
            Label wynikWyszukiwaniaEtykieta = new Label("");


//            uklad werytkalny
            final VBox ukladWertykalnyUzytkownika = new VBox();
            ukladWertykalnyUzytkownika.setAlignment(Pos.CENTER);
            ukladWertykalnyUzytkownika.setSpacing(5);
            ukladWertykalnyUzytkownika.setPadding(new Insets(10, 0, 0, 10));
//            etykieta informacyjna dla kogo jest to system
            final Label wersjaSystemuDlaUzytkownikaEtykieta = new Label("System dla Uzytkownika");
            wersjaSystemuDlaUzytkownikaEtykieta.setFont(new Font("Arial", 20));
//            dodajemy uklady i etykiety do glowne ukladu box
            ukladWertykalnyUzytkownika.getChildren().addAll(wersjaSystemuDlaUzytkownikaEtykieta, wynikWyszukiwaniaEtykieta);

//            ustawiamy dane kierowcy z systemu do etykiety informacyjnej
            wynikWyszukiwaniaEtykieta.setText(daneAplikacji.pobierzDaneKierowcy(Integer.parseInt(identyfikacja)));

//            dodajemy uklad do sceny
            scenaGlownegoOkna = new Scene(ukladWertykalnyUzytkownika);
        }
//        dodajemy scene do glownego okna
        glowneOkno.setScene(scenaGlownegoOkna);

//        wyswietlamy okno glowne
        glowneOkno.show();
    }

//    funkcja ktora ustawia dane do tabeli
    private void ustawDane(ObservableList<KierowcaUkarany> data){
        tabela.setItems(data);
    }
}
