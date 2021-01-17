package fr.montpellier.supperform.affichage;

import fr.montpellier.supperform.Main;
import fr.montpellier.supperform.javaFX.*;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

import java.io.File;

public abstract class FonctionAffichage extends Group {

    private final Font fontTitre = Font.font("Verdana", FontWeight.BOLD, 15);
    private final Font fontText = Font.font("Verdana", FontWeight.SEMI_BOLD, 13);
    private final int translateX;
    private final double height, decalage = 10, nbLigne;
    private ButtonFX start, quitter;

    public FonctionAffichage(int nbLigne){
        this.nbLigne = nbLigne;
        height = Main.HEIGHT;
        translateX = 40;
    }

    public LabelFX labelTitre(int position, String titre){
        //label.setStyle("-fx-border-color: black;");
        //label.setBorder(new Border(new BorderStroke(, BorderStrokeStyle.SOLID, null, new BorderWidths(5))));
        //label.setTextFill(Paint.valueOf("Red"));
        //label.setEffect(new DropShadow());
        //label.setEffect(new Reflection());
        return new LabelFX(titre, fontTitre, 0, Main.HEIGHT/nbLigne * position + decalage);
    }

    public GroupFX labelIntTextField(int position, String textLabel, double transX, double transY){
        GroupFX group = new GroupFX(translateX, height/nbLigne * position + decalage);
        LabelFX label = new LabelFX(textLabel, fontText);

        IntTextFieldFX textFieldFX = new IntTextFieldFX(transX, transY);

        group.addChild(label, textFieldFX);
        return group;
    }

    public GroupFX labelChoixFichier(int position){
        GroupFX group = new GroupFX(translateX, Main.HEIGHT/nbLigne * position + decalage);

        LabelFX label = new LabelFX("Choisir un fichier", fontText);
        LabelFX labelCheminAcces = new LabelFX("", fontText, 270, 0);

        ButtonFX button = new ButtonFX("Choisir un fichier", 130, 15, 140, -4);
        button.setOnAction(actionEvent -> labelCheminAcces.setText(selectionFichier()));

        group.addChild(label, button, labelCheminAcces);
        return group;
    }

    public Group button(int position){
        GroupFX group = new GroupFX(Main.WIDTH/4, height/nbLigne * position + decalage);

        start = new ButtonFX("Start", 70, 15, 1, 0);
        quitter = new ButtonFX("Retour", 70, 15, 70 + 20, 0);
        buttonStart();

        quitter.setOnAction(actionEvent -> buttonQuitter());

        group.getChildren().addAll(start, quitter);
        return group;
    }

    public abstract void buttonStart();

    public void buttonQuitter(){
        SceneFX accueil = new SceneFX(new Accueil());
        Main.STAGE.setTitle("Sup'Perform | Accueil");
        Main.STAGE.setScene(accueil);
    }

    public Button getStart() {
        return start;
    }

    private String selectionFichier(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner un fichier");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Excel .xlsx", "*.xlsx"));

        File file = fileChooser.showOpenDialog(Main.STAGE);

        if(file != null){
            return file + "";
        }else {
            FenetreAlert.erreur("Vous n'avez pas choisi de fichier \nVeuillez réessayer");
            return null;
        }
    }

}
