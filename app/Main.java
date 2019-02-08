package app;

import app.gui.OknoGlowne;
import app.terminal.AplikacjaKonsolowa;
import javafx.application.Application;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        Aplikacja Okienkowa GUI
//        Application.launch(OknoGlowne.class, args);
//        Aplikacja Konsolowa
        AplikacjaKonsolowa aplikacjaKonsolowa = new AplikacjaKonsolowa();
    }
}