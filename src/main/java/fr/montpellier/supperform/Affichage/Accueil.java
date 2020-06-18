package fr.montpellier.supperform.Affichage;
import fr.montpellier.supperform.Main;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;


public class Accueil extends Group {

    public Accueil (Main main){

        Button boutonNotationNormale = new Button("Notation normale");
        Button boutonNotationUE7 = new Button("Notation UE7");
        Button boutonId = new Button("Identifiants");
        Button support = new Button("Support");
        Button boutonQuitter = new Button("Quitter");

        double largeurButton = 150;
        double hauteurButton = 40;
        boutonId.setPrefSize(largeurButton, hauteurButton);
        boutonId.setOnAction(actionEvent -> {
            Scene identifiant = new Scene(new Identifiant(main), main.getWidth(), main.getHeight());
            identifiant.setFill(Color.LIGHTGRAY);
            identifiant.getStylesheets().add("fr/montpellier/supperform/Button.css");
            main.getStage().setTitle("Sup'Perform Identifiant");
            main.getStage().setScene(identifiant);
        });
        boutonId.setTranslateX(main.getWidth()/2 - largeurButton /2);
        boutonId.setTranslateY(main.getHeight()/2 - hauteurButton /2);

        boutonNotationNormale.setPrefSize(largeurButton, hauteurButton);
        boutonNotationNormale.setOnAction(actionEvent -> {
            Scene notationNormale = new Scene(new Notation(main, 0.25), main.getWidth(), main.getHeight());
            notationNormale.setFill(Color.LIGHTGRAY);
            notationNormale.getStylesheets().add("fr/montpellier/supperform/Button.css");
            main.getStage().setTitle("Sup'Perform Notation normale");
            main.getStage().setScene(notationNormale);
        });
        boutonNotationNormale.setTranslateX(main.getWidth()/2 - largeurButton /2);
        boutonNotationNormale.setTranslateY(boutonId.getTranslateY() - 120);

        boutonNotationUE7.setPrefSize(largeurButton, hauteurButton);
        boutonNotationUE7.setOnAction(actionEvent -> {
            Scene notationUE7 = new Scene(new Notation(main, 0.5), main.getWidth(), main.getHeight());
            notationUE7.setFill(Color.LIGHTGRAY);
            notationUE7.getStylesheets().add("fr/montpellier/supperform/Button.css");
            main.getStage().setTitle("Sup'Perform Notation UE7");
            main.getStage().setScene(notationUE7);
        });
        boutonNotationUE7.setTranslateX(main.getWidth()/2 - largeurButton /2);
        boutonNotationUE7.setTranslateY(boutonId.getTranslateY() - 60);

        support.setPrefSize(largeurButton, hauteurButton);
        support.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Pour tout problème ou question, vous pouvez me contacter à l'adresse mail suivante : \n\nmax.poujol21@gmail.com \n");

            alert.showAndWait();
        });
        support.setTranslateX(main.getWidth()/2 - largeurButton /2);
        support.setTranslateY(boutonId.getTranslateY() + 60);

        boutonQuitter.setPrefSize(largeurButton, hauteurButton);
        boutonQuitter.setOnAction(actionEvent -> System.exit(0));
        boutonQuitter.setTranslateX(main.getWidth()/2 - largeurButton /2);
        boutonQuitter.setTranslateY(boutonId.getTranslateY() + 120);

        this.getChildren().addAll(boutonNotationNormale, boutonNotationUE7, boutonId, support, boutonQuitter);


    }


}
