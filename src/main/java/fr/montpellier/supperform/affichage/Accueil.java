package fr.montpellier.supperform.affichage;
import fr.montpellier.supperform.FenetreAlert;
import fr.montpellier.supperform.Main;
import fr.montpellier.supperform.javaFX.ButtonFX;
import javafx.scene.Group;


public class Accueil extends Group {

    public Accueil (){

        double width = 150, height = 40, transX = Main.WIDTH/2 - width /2, transY = Main.HEIGHT/2 - height /2;

        ButtonFX boutonNotationNormale = new ButtonFX("Notation normale", width, height, transX, transY - 120);
        boutonNotationNormale.eventScene(new Notation( 0.25), "Sup'Perform | Notation Normale");

        ButtonFX boutonNotationUE5 = new ButtonFX("Notation UE5", width, height, transX, transY - 60);
        boutonNotationUE5.eventScene(new Notation(0.5), "Sup'Perform | Notation UE5");

        ButtonFX boutonId = new ButtonFX("Identifiants", width, height, transX, transY);
        boutonId.eventScene(new Identifiant(), "Sup'Perform | Identifiant");

        ButtonFX support = new ButtonFX("Support", width, height, transX, transY + 60);
        support.setOnAction(actionEvent -> FenetreAlert.info("Pour tout problème ou question, vous pouvez me contacter à l'adresse mail suivante : \n\nmax.poujol21@gmail.com \n"));

        ButtonFX boutonQuitter = new ButtonFX("Quitter", width, height, transX, transY + 120);
        boutonQuitter.setOnAction(actionEvent -> System.exit(0));

       /* boutonId.setOnAction(actionEvent -> {
            Scene identifiant = new Scene(new StackPane(new Identifiant(main)), main.getWidth(), main.getHeight());
            identifiant.setFill(Color.LIGHTGRAY);
            identifiant.getStylesheets().add("/Button.css");
            main.getStage().setTitle("Sup'Perform Identifiant");
            main.getStage().setScene(identifiant);
        });

        boutonNotationNormale.setOnAction(actionEvent -> {
            Scene notationNormale = new Scene(new StackPane(new Notation(main, 0.25)), main.getWidth(), main.getHeight());
            notationNormale.setFill(Color.LIGHTGRAY);
            notationNormale.getStylesheets().add("/Button.css");
            main.getStage().setTitle("Sup'Perform Notation Normale");
            main.getStage().setScene(notationNormale);
        });

        boutonNotationUE5.setOnAction(actionEvent -> {
            Scene notationUE5 = new Scene(new StackPane(new Notation(main, 0.5)), main.getWidth(), main.getHeight());
            notationUE5.setFill(Color.LIGHTGRAY);
            notationUE5.getStylesheets().add("/Button.css");
            main.getStage().setTitle("Sup'Perform Notation UE5");
            main.getStage().setScene(notationUE5);
        });

       */
        this.getChildren().addAll(boutonNotationNormale, boutonNotationUE5, boutonId, support, boutonQuitter);

    }

}
