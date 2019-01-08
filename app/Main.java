package app;
import app.gui.Login;
import app.gui.LoginDialog;

import app.gui.OknoGlowne;
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

import java.util.ArrayList;

import static java.lang.System.out;

public class Main {

//    Tworzymy system
    private static SystemEwidencji glownySystemEwidencji = new SystemEwidencji();
    Policjant p1 = new Policjant("h546gh45g64");

    private final ObservableList<Kierowca> data =
            FXCollections.observableArrayList(glownySystemEwidencji.getListaKierowcow());

    private static void ustawWstepneDaneSystemu(){
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

        Policjant p1 = new Policjant("h546gh45g64");

        //        Dodajemy uzytkownikow do systemu
        glownySystemEwidencji.dodajUzytkownika(k1);
        glownySystemEwidencji.dodajUzytkownika(k2);
//        glownySystemEwidencji.dodajUzytkownika(u3);
        glownySystemEwidencji.dodajUzytkownika(k4);
    }

    private static void pokazOknoLoginu(String[] args){
        Login log = new Login();
        log.go(args);
    }

    private static void pokazOknoGlowne(String[] args){
        OknoGlowne okno = new OknoGlowne();
        okno.go(args);
    }

//    private void pokazOknoLogowaniaPolicjanta() {
//        LoginDialog logDial = new LoginDialog();
//        logDial.go(true);
//    }
//
//    private void pokazOknoLogowaniaUzytkownika() {
//        LoginDialog logDial = new LoginDialog();
//        logDial.go(false);
//    }

    public static void main(String[] args) {
        ustawWstepneDaneSystemu();
        pokazOknoLoginu(args);
        pokazOknoGlowne(args);

    }
}