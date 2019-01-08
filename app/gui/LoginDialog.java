package app.gui;

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

public class LoginDialog {

    private static void pokazOknoGlowne(String[] args){
        OknoGlowne okno = new OknoGlowne();
        okno.go(args);
    }

    public void go(boolean wersjaSystemuDlaPolicjanta){
        String wersjaTekstu = "Pesel";
        if (wersjaSystemuDlaPolicjanta){
            wersjaTekstu = "Identyfikator";
        }
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Logowanie do systemu:");
        dialog.setHeaderText("Proszę podaj dane:");

// Set the icon (must be included in the project).

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Zaloguj", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, cancelButtonType);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField textField = new TextField();
        textField.setPromptText("wpisz " + wersjaTekstu.toLowerCase());
        Restrykcje.restrykcjaPoprawnosci(dialog, loginButtonType, textField);
        Restrykcje.restrykcjaTypu(textField);
        Restrykcje.restrykcjaDlugosci(textField, 9);

        grid.add(new Label(wersjaTekstu + ":"), 0, 0);
        grid.add(textField, 1, 0);

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
//        Platform.runLater(() -> textField.requestFocus());

// Kopiujemy zmienna zeby mozna bylo jej uzyc w funkcji lambda
        final String wersjaTekstuLambda = wersjaTekstu;

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(wersjaTekstuLambda, textField.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(dane -> {
            System.out.println(dane.getKey() + ", " + wersjaTekstuLambda + "=" + dane.getValue());
        });


//        return result.getValue();
    }
}
