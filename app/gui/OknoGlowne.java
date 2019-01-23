package app.gui;

import app.DaneWejsciowe;
import app.Kierowca;
import app.Policjant;
import app.SystemEwidencji;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.converter.IntegerStringConverter;

import java.util.Optional;

public class OknoGlowne extends Application{
//    private TableView table = new TableView();

    private TableView<Kierowca> table = new TableView<>();
    private static boolean uprawnienia = false;
    private Optional<Pair<String, String>> result;
    private DaneWejsciowe daneAplikacji = new DaneWejsciowe();

    private ObservableList<Kierowca> data = FXCollections.observableArrayList(daneAplikacji.ustawWstepneDaneSystemu());

    @Override
    public void start(Stage glowneOkno) throws Exception {

//         okno wyboru typu loginu = policjant czy uzytkownik
        final Stage wersjaLoginuOkno = new Stage();
        HBox ukladHoryzontalnyBox = new HBox();
        ukladHoryzontalnyBox.setPadding(new Insets(10));
        ukladHoryzontalnyBox.setSpacing(10);
        ukladHoryzontalnyBox.setAlignment(Pos.CENTER);

        Button uzytkownikButton = new Button("Uzytkownik");
        Button policjantButton = new Button("Policjant");

        ukladHoryzontalnyBox.getChildren().addAll(uzytkownikButton, policjantButton);

        Scene secondaryScene = new Scene(ukladHoryzontalnyBox, 300, 50);
        wersjaLoginuOkno.setTitle("Wybierz wersje:");
        wersjaLoginuOkno.setScene(secondaryScene);

        wersjaLoginuOkno.show();

//        Tworzymy samo okno logowania zaleznie od wybranej wersji
        LoginDialog dialogLogowania = new LoginDialog();

        uzytkownikButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                wersjaLoginuOkno.close();
                result = dialogLogowania.go(true);
            }
        });

        policjantButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                wersjaLoginuOkno.close();
                result = dialogLogowania.go(true);
                if (result.isPresent()) {
                    pokazOknoGlowne(result.get().getKey(), result.get().getValue());
                    System.out.println("test");
                }
            }
        });
//        koniec okna wyboru typu loginu
    }

    private void pokazOknoGlowne(String wersja, String identyfikacja){
        if (wersja.equals("Identyfikator")){

        }else{

        }
        Stage glowneOkno = new Stage();
        final HBox ukladWyszukiwaniaHoryzontalnyBox = new HBox();
        final HBox ukladWynikuWyszukiwaniaHoryzontalnyBox = new HBox();

        final HBox hb = new HBox();

        TabPane layout = new TabPane();

        Scene scene = new Scene(layout);
        glowneOkno.setTitle("System Ewidencji");
        glowneOkno.setWidth(650);
        glowneOkno.setHeight(550);

        Tab uzytkownikTab = new Tab("Uzytkownik");
        layout.getTabs().add(uzytkownikTab);

        final Label etykietaWersji = new Label("System dla Użytkownika");
        etykietaWersji.setFont(new Font("Arial", 20));

        Tab policjantTab = new Tab("Policjant");
        layout.getTabs().add(policjantTab);

        final Label label2 = new Label("System dla Policjanta");
        label2.setFont(new Font("Arial", 20));

        table.setEditable(true);

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
        punktyCol.setCellValueFactory(
                new PropertyValueFactory<Kierowca, Integer>("punktyKarne"));
        punktyCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        punktyCol.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<Kierowca, Integer>>() {
//                    @Override
//                    public void handle(TableColumn.CellEditEvent<Kierowca, Integer> t) {
//                        ((Kierowca) t.getTableView().getItems().get(
//                                t.getTablePosition().getRow())
//                        ).zarzadzajPunktami(t.getNewValue(), p1.sprawdzUprawnienia());
//                    }
//                }
//        );

        table.getColumns().addAll(imieCol, nazwiskoCol, peselCol, punktyCol);

        final TextField dodajImie = new TextField();
        dodajImie.setPromptText("Imie");
        final TextField dodajNazwisko = new TextField();
        dodajNazwisko.setPromptText("Nazwisko");
        final TextField dodajPesel = new TextField();
        dodajPesel.setPromptText("Pesel");

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

        final Button addButton = new Button("Dodaj");
//        addButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                data.add(new Kierowca(
//                        dodajImie.getText(),
//                        dodajNazwisko.getText(),
//                        Integer.parseInt(dodajPesel.getText())));
//                dodajImie.clear();
//                dodajNazwisko.clear();
//                dodajPesel.clear();
//            }
//        });

        hb.getChildren().addAll(dodajImie, dodajNazwisko, dodajPesel, addButton);
        hb.setSpacing(3);

        final TextField wyszukajPesel = new TextField();
        wyszukajPesel.setPromptText("wpisz pesel");

        wyszukajPesel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    wyszukajPesel.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        Label wynikWyszukiwaniaEtykieta = new Label("");

        final Button searchButton = new Button("Szukaj");


        ukladWyszukiwaniaHoryzontalnyBox.getChildren().addAll(wyszukajPesel, searchButton);
        ukladWyszukiwaniaHoryzontalnyBox.setSpacing(3);

        ukladWynikuWyszukiwaniaHoryzontalnyBox.getChildren().addAll(wynikWyszukiwaniaEtykieta);
        ukladWynikuWyszukiwaniaHoryzontalnyBox.setSpacing(3);

        final VBox ukladWertykalnyCalegoOkna = new VBox();
        ukladWertykalnyCalegoOkna.setSpacing(5);
        ukladWertykalnyCalegoOkna.setPadding(new Insets(10, 0, 0, 10));
        ukladWertykalnyCalegoOkna.getChildren().addAll(etykietaWersji, table, ukladWyszukiwaniaHoryzontalnyBox, ukladWynikuWyszukiwaniaHoryzontalnyBox);

        uzytkownikTab.setContent(ukladWertykalnyCalegoOkna);

        final VBox vbox2 = new VBox();
        vbox2.setSpacing(5);
        vbox2.setPadding(new Insets(10, 0, 0, 10));
        vbox2.getChildren().addAll(label2, table, hb);

        policjantTab.setContent(vbox2);

        glowneOkno.setScene(scene);

        glowneOkno.show();

    }

    private void pokazOknoLogowaniaPolicjanta() {
        LoginDialog logDial = new LoginDialog();
        logDial.go(true);
    }

    private void pokazOknoLogowaniaUzytkownika() {
        LoginDialog logDial = new LoginDialog();
        logDial.go(false);
    }

    private void ustawDane(ObservableList<Kierowca> data){
        table.setItems(data);
    }

    private  void wyszukaj(){}
}
