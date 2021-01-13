package fr.montpellier.supperform;

import fr.montpellier.supperform.Affichage.Accueil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static final double WIDTH = 800, HEIGHT = 500;
    private final Scene accueil = new Scene(new StackPane(new Accueil()), WIDTH, HEIGHT);
    public static Stage STAGE;

    @Override
    public void start(Stage primaryStage) {

        STAGE = primaryStage;

        accueil.setFill(Color.LIGHTGRAY);
        accueil.getStylesheets().add("/Button.css");

        STAGE.setTitle("Sup'Perform Accueil");
        STAGE.setScene(accueil);

        STAGE.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
