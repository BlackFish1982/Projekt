package app.gui;

import app.DaneWejsciowe;
import app.Kierowca;
import app.Policjant;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    private TableView<Kierowca> table = new TableView<>();
    private Optional<Pair<String, String>> result;
    private DaneWejsciowe daneAplikacji = new DaneWejsciowe();

    private ObservableList<Kierowca> data = FXCollections.observableArrayList(daneAplikacji.ustawWstepneDaneSystemu());

    @Override
    public void start(Stage glowneOkno) throws Exception {

//         okno wyboru typu loginu = policjant czy uzytkownik
        final Stage wersjaLoginuOkno = new Stage();
        HBox ukladHoryzontalnyBoxWyborLogowania = new HBox();
        VBox ukladWertykalnyBoxWyborLogowania = new VBox();

        ukladHoryzontalnyBoxWyborLogowania.setPadding(new Insets(10));
        ukladHoryzontalnyBoxWyborLogowania.setSpacing(10);
        ukladHoryzontalnyBoxWyborLogowania.setAlignment(Pos.CENTER);

        ukladWertykalnyBoxWyborLogowania.setPadding(new Insets(10));
        ukladWertykalnyBoxWyborLogowania.setSpacing(10);
        ukladWertykalnyBoxWyborLogowania.setAlignment(Pos.CENTER);

        Button uzytkownikButton = new Button("Uzytkownik");
        Button policjantButton = new Button("Policjant");
        Label informacjaLogowania = new Label("");
        informacjaLogowania.setTextFill(Color.web("#dd3735"));

        ukladHoryzontalnyBoxWyborLogowania.getChildren().addAll(uzytkownikButton, policjantButton);
        ukladWertykalnyBoxWyborLogowania.getChildren().addAll(ukladHoryzontalnyBoxWyborLogowania, informacjaLogowania);


        Scene secondaryScene = new Scene(ukladWertykalnyBoxWyborLogowania, 300, 100);
        wersjaLoginuOkno.setTitle("Wybierz wersje:");
        wersjaLoginuOkno.setScene(secondaryScene);

        wersjaLoginuOkno.show();

//        Tworzymy samo okno logowania zaleznie od wybranej wersji

        uzytkownikButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginDialog dialogLogowaniaUzytkownika = new LoginDialog();
                result = dialogLogowaniaUzytkownika.go(false);
                if (result.isPresent()) {
                    if(daneAplikacji.pobierzDaneKierowcy(Integer.parseInt(result.get().getValue())) != null){
                        wersjaLoginuOkno.close();
                        pokazOknoGlowne(result.get().getKey(), result.get().getValue());
                    }else{
                        informacjaLogowania.setText("Brak uzytkownika o takim numerze pesel!");
                    }
                }
            }
        });

        policjantButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginDialog dialogLogowaniaPolicjanta = new LoginDialog();
                result = dialogLogowaniaPolicjanta.go(true);
                if (result.isPresent()) {
                    if(daneAplikacji.pobierzPolicjanta(result.get().getValue()) != null){
                        wersjaLoginuOkno.close();
                        pokazOknoGlowne(result.get().getKey(), result.get().getValue());
                    }else{
                        informacjaLogowania.setText("Brak policjanta o podanym identyfikatorze!");
                    }
                }
            }
        });
//        koniec okna wyboru typu loginu
    }

    private void pokazOknoGlowne(String wersja, String identyfikacja){
        Stage glowneOkno = new Stage();
        glowneOkno.setTitle("System Ewidencji");
        glowneOkno.setWidth(650);
        glowneOkno.setHeight(550);

        Scene scene;

        if (wersja.equals("Identyfikator")) {
            final HBox ukladHoryzontalnyDodawaniaKierowcy = new HBox();

            TableColumn imieCol = new TableColumn("Imie");
            imieCol.setMinWidth(100);
            imieCol.setCellValueFactory(
                    new PropertyValueFactory<Kierowca, String>("imie"));

            TableColumn nazwiskoCol = new TableColumn("Nazwisko");
            nazwiskoCol.setMinWidth(100);
            nazwiskoCol.setCellValueFactory(
                    new PropertyValueFactory<Kierowca, String>("nazwisko"));
            imieCol.setCellFactory(TextFieldTableCell.forTableColumn());

            TableColumn peselCol = new TableColumn("Pesel");
            peselCol.setMinWidth(200);
            peselCol.setCellValueFactory(
                    new PropertyValueFactory<Kierowca, String>("pesel"));


            TableColumn punktyCol = new TableColumn("Punkty Karne");
            punktyCol.setMinWidth(100);
            table.getColumns().addAll(imieCol, nazwiskoCol, peselCol, punktyCol);


            final TextField dodajImie = new TextField();
            dodajImie.setPromptText("Imie");
            final TextField dodajNazwisko = new TextField();
            dodajNazwisko.setPromptText("Nazwisko");
            final TextField dodajPesel = new TextField();
            dodajPesel.setPromptText("Pesel");
            Restrykcje.restrykcjaTypu(dodajPesel);
            Restrykcje.restrykcjaDlugosci(dodajPesel, 9);

            final Button dodajKierowcePrzycisk = new Button("Dodaj");

            ukladHoryzontalnyDodawaniaKierowcy.getChildren().addAll(dodajImie, dodajNazwisko, dodajPesel, dodajKierowcePrzycisk);
            ukladHoryzontalnyDodawaniaKierowcy.setSpacing(3);

            final VBox ukladWertyklanyPolicjanta = new VBox();
            ukladWertyklanyPolicjanta.setSpacing(5);
            ukladWertyklanyPolicjanta.setPadding(new Insets(10, 0, 0, 10));
            final Label wersjaSystemuDlaPolicjantaEtykieta = new Label("System dla Policjanta");
            wersjaSystemuDlaPolicjantaEtykieta.setFont(new Font("Arial", 20));
            ukladWertyklanyPolicjanta.getChildren().addAll(wersjaSystemuDlaPolicjantaEtykieta, table, ukladHoryzontalnyDodawaniaKierowcy);

            Policjant aktualnyPolicjant = daneAplikacji.pobierzPolicjanta(identyfikacja);
            table.setEditable(true);
            table.getSelectionModel().cellSelectionEnabledProperty().set(true);
            ustawDane(data);

            punktyCol.setCellValueFactory(
                    new PropertyValueFactory<Kierowca, Integer>("punktyKarne"));
            punktyCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            punktyCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Kierowca, Integer>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<Kierowca, Integer> t) {
                            ((Kierowca) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).zarzadzajPunktami(t.getNewValue(), aktualnyPolicjant.sprawdzUprawnienia());
                        }
                    }
            );

            // force the field to be numeric only
            dodajPesel.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                                    String newValue) {
                    if (!newValue.matches("\\d*")) {
                        dodajPesel.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
            dodajKierowcePrzycisk.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    data.add(new Kierowca(
                            dodajImie.getText(),
                            dodajNazwisko.getText(),
                            Integer.parseInt(dodajPesel.getText())));
                    dodajImie.clear();
                    dodajNazwisko.clear();
                    dodajPesel.clear();
                    ustawDane(data);
                }
            });

            scene = new Scene(ukladWertyklanyPolicjanta);

        }else{

            final HBox ukladWynikuWyszukiwaniaHoryzontalnyBox = new HBox();

            final TextField wyszukajPesel = new TextField();
            wyszukajPesel.setPromptText("wpisz pesel");
            Label wynikWyszukiwaniaEtykieta = new Label("");


            ukladWynikuWyszukiwaniaHoryzontalnyBox.getChildren().addAll(wynikWyszukiwaniaEtykieta);
            ukladWynikuWyszukiwaniaHoryzontalnyBox.setSpacing(3);

            final VBox ukladWertykalnyUzytkownika = new VBox();
            ukladWertykalnyUzytkownika.setAlignment(Pos.CENTER);
            ukladWertykalnyUzytkownika.setSpacing(5);
            ukladWertykalnyUzytkownika.setPadding(new Insets(10, 0, 0, 10));
            final Label wersjaSystemuDlaUzytkownikaEtykieta = new Label("System dla Uzytkownika");
            wersjaSystemuDlaUzytkownikaEtykieta.setFont(new Font("Arial", 20));
            ukladWertykalnyUzytkownika.getChildren().addAll(wersjaSystemuDlaUzytkownikaEtykieta, ukladWynikuWyszukiwaniaHoryzontalnyBox);

            wynikWyszukiwaniaEtykieta.setText(daneAplikacji.pobierzDaneKierowcy(Integer.parseInt(identyfikacja)));

            wyszukajPesel.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                                    String newValue) {
                    if (!newValue.matches("\\d*")) {
                        wyszukajPesel.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
            scene = new Scene(ukladWertykalnyUzytkownika);
        }
        glowneOkno.setScene(scene);

        glowneOkno.show();
    }

    private void ustawDane(ObservableList<Kierowca> data){
        table.setItems(data);
    }
}
