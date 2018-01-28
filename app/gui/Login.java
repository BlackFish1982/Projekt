package app.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Optional;

public class Login extends Application{
    public void start(Stage stage){
        HBox root = new HBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        Button uzytkownikButton = new Button("Uzytkownik");
        Button policjantButton = new Button("Policjant");

        uzytkownikButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pokazOknoLogowaniaUzytkownika();
            }
        });

        policjantButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pokazOknoLogowaniaPolicjanta();
            }
        });

        root.getChildren().addAll(uzytkownikButton, policjantButton);

        Scene scene = new Scene(root, 300, 50);
        stage.setTitle("Wybierz wersje:");
        stage.setScene(scene);

        stage.show();
    }

    private void pokazOknoLogowaniaPolicjanta() {
        LoginDialog logDial = new LoginDialog();
        logDial.go(true);
    }

    private void pokazOknoLogowaniaUzytkownika() {
        LoginDialog logDial = new LoginDialog();
        logDial.go(false);
    }

    public void go(String args[]){
        launch(args);
    }
}
