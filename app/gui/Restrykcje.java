package app.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;


public class Restrykcje {
    private static int dlugosc = 0;

    // force the field to be numeric only
    public static void restrykcjaTypu(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public static void restrykcjaDlugosci(TextField tf, final int maxLength) {
        Restrykcje.dlugosc = maxLength;
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

    public static void restrykcjaPoprawnosci(Dialog dialog, ButtonType loginButtonType, TextField textField){
        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == Restrykcje.dlugosc){
                loginButton.setDisable(false);
            }
        });
    }
}
