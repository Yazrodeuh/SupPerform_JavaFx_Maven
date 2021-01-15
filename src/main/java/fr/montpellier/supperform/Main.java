package fr.montpellier.supperform;

import fr.montpellier.supperform.affichage.Accueil;
import fr.montpellier.supperform.javaFX.SceneFX;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static final double WIDTH = 800, HEIGHT = 500;
    public static Stage STAGE;

    @Override
    public void start(Stage primaryStage) {

        STAGE = primaryStage;
        STAGE.setTitle("Sup'Perform | Accueil");
        STAGE.setScene(new SceneFX(new Accueil()));
        STAGE.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
