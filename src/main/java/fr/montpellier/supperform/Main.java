package fr.montpellier.supperform;

import fr.montpellier.supperform.Affichage.Accueil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private final double width = 800, height = 500;
    private final Scene accueil = new Scene(new StackPane(new Accueil(this)), width, height);
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;

        accueil.setFill(Color.LIGHTGRAY);
        accueil.getStylesheets().add("/Button.css");

        stage.setTitle("Sup'Perform Accueil");
        stage.setScene(accueil);

        stage.show();
    }

    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

}
