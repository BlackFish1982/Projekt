package app;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import static java.lang.System.out;

public class Main extends Application {
    private static SystemEwidencji glownySystemEwidencji = new SystemEwidencji();

    private TableView<Kierowca> table = new TableView<>();
    private final ObservableList<Kierowca> data =
            FXCollections.observableArrayList(glownySystemEwidencji.getListaKierowcow());
    final HBox hb1 = new HBox();
    final HBox hb2 = new HBox();

    final HBox hb = new HBox();

    public static void main(String[] args) {
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

        //        Dodajemy uzytkownikow do systemu
        glownySystemEwidencji.dodajUzytkownika(k1);
        glownySystemEwidencji.dodajUzytkownika(k2);
//        glownySystemEwidencji.dodajUzytkownika(u3);
        glownySystemEwidencji.dodajUzytkownika(k4);


        launch(args);
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }

    @Override
    public void start(Stage stage) {
        TabPane layout = new TabPane();

        Scene scene = new Scene(layout);
        stage.setTitle("System Ewidencji");
        stage.setWidth(650);
        stage.setHeight(550);

        Tab uzytkownikTab = new Tab("Uzytkownik");
        layout.getTabs().add(uzytkownikTab);

        final Label label1 = new Label("System dla Użytkownika");
        label1.setFont(new Font("Arial", 20));

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
        punktyCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Kierowca, Integer>>() {
                    @Override
                    public void handle(CellEditEvent<Kierowca, Integer> t) {
                        ((Kierowca) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).dodajPunkty(t.getNewValue());
                    }
                }
        );

        table.getColumns().addAll(imieCol, nazwiskoCol, peselCol, punktyCol);
        table.setItems(data);

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

        addTextLimiter(dodajPesel, 9);

        final Button addButton = new Button("Dodaj");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new Kierowca(
                        dodajImie.getText(),
                        dodajNazwisko.getText(),
                        Integer.parseInt(dodajPesel.getText())));
                dodajImie.clear();
                dodajNazwisko.clear();
                dodajPesel.clear();
            }
        });

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

        addTextLimiter(wyszukajPesel, 9);

        Label searchResultLabel = new Label("");

        final Button searchButton = new Button("Szukaj");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Kierowca wyszukanyKierowca = glownySystemEwidencji.wyszukajKierowce(Integer.parseInt(wyszukajPesel.getText()));
                if (wyszukanyKierowca != null) {
                    searchResultLabel.setText(wyszukanyKierowca.toString());
                } else {
                    searchResultLabel.setText("brak kierowców o podanym peselu");
                }
                wyszukajPesel.clear();
            }
        });

        hb1.getChildren().addAll(wyszukajPesel, searchButton);
        hb1.setSpacing(3);

        hb2.getChildren().addAll(searchResultLabel);
        hb2.setSpacing(3);

        final VBox vbox1 = new VBox();
        vbox1.setSpacing(5);
        vbox1.setPadding(new Insets(10, 0, 0, 10));
        vbox1.getChildren().addAll(label1, table, hb1, hb2);

        uzytkownikTab.setContent(vbox1);

        final VBox vbox2 = new VBox();
        vbox2.setSpacing(5);
        vbox2.setPadding(new Insets(10, 0, 0, 10));
        vbox2.getChildren().addAll(label2, table, hb);

        policjantTab.setContent(vbox2);

        stage.setScene(scene);
        stage.show();
    }
}