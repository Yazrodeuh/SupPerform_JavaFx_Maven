package fr.montpellier.supperform.Affichage;
import fr.montpellier.supperform.FenetreAlert;
import fr.montpellier.supperform.Main;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


public class Accueil extends Group {

    public Accueil (Main main){

        Button boutonNotationNormale = new Button("Notation normale");
        Button boutonNotationUE5 = new Button("Notation UE5");
        Button boutonId = new Button("Identifiants");
        Button support = new Button("Support");
        Button boutonQuitter = new Button("Quitter");

        double largeurButton = 150;
        double hauteurButton = 40;
        boutonId.setPrefSize(largeurButton, hauteurButton);
        boutonId.setOnAction(actionEvent -> {
            Scene identifiant = new Scene(new StackPane(new Identifiant(main)), main.getWidth(), main.getHeight());
            identifiant.setFill(Color.LIGHTGRAY);
            identifiant.getStylesheets().add("/Button.css");
            main.getStage().setTitle("Sup'Perform Identifiant");
            main.getStage().setScene(identifiant);
        });
        boutonId.setTranslateX(main.getWidth()/2 - largeurButton /2);
        boutonId.setTranslateY(main.getHeight()/2 - hauteurButton /2);

        boutonNotationNormale.setPrefSize(largeurButton, hauteurButton);
        boutonNotationNormale.setOnAction(actionEvent -> {
            Scene notationNormale = new Scene(new StackPane(new Notation(main, 0.25)), main.getWidth(), main.getHeight());
            notationNormale.setFill(Color.LIGHTGRAY);
            notationNormale.getStylesheets().add("/Button.css");
            main.getStage().setTitle("Sup'Perform Notation Normale");
            main.getStage().setScene(notationNormale);
        });
        boutonNotationNormale.setTranslateX(main.getWidth()/2 - largeurButton /2);
        boutonNotationNormale.setTranslateY(boutonId.getTranslateY() - 120);

        boutonNotationUE5.setPrefSize(largeurButton, hauteurButton);
        boutonNotationUE5.setOnAction(actionEvent -> {
            Scene notationUE5 = new Scene(new StackPane(new Notation(main, 0.5)), main.getWidth(), main.getHeight());
            notationUE5.setFill(Color.LIGHTGRAY);
            notationUE5.getStylesheets().add("/Button.css");
            main.getStage().setTitle("Sup'Perform Notation UE5");
            main.getStage().setScene(notationUE5);
        });

        boutonNotationUE5.setTranslateX(main.getWidth()/2 - largeurButton /2);
        boutonNotationUE5.setTranslateY(boutonId.getTranslateY() - 60);

        support.setPrefSize(largeurButton, hauteurButton);
        support.setOnAction(actionEvent -> {
            FenetreAlert.info("Pour tout problème ou question, vous pouvez me contacter à l'adresse mail suivante : \n\nmax.poujol21@gmail.com \n");
        });
        support.setTranslateX(main.getWidth()/2 - largeurButton /2);
        support.setTranslateY(boutonId.getTranslateY() + 60);

        boutonQuitter.setPrefSize(largeurButton, hauteurButton);
        boutonQuitter.setOnAction(actionEvent -> System.exit(0));
        boutonQuitter.setTranslateX(main.getWidth()/2 - largeurButton /2);
        boutonQuitter.setTranslateY(boutonId.getTranslateY() + 120);

        this.getChildren().addAll(boutonNotationNormale, boutonNotationUE5, boutonId, support, boutonQuitter);


    }


}
