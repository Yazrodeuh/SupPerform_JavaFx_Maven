package fr.montpellier.supperform.affichage;

import fr.montpellier.supperform.FenetreAlert;
import fr.montpellier.supperform.Main;
import fr.montpellier.supperform.javaFX.*;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

import java.io.File;

public abstract class FonctionAffichage extends Group {

    private final Font fontTitre = Font.font("Verdana", FontWeight.BOLD, 15);
    private final Font fontText = Font.font("Verdana", FontWeight.SEMI_BOLD, 13);
    //private File fileReponse, fileIdentifiant;
    //private IntTextFieldFX intTextFieldFXEtudiant, intTextFieldFXQCM, intTextFieldFXLigneReponse, intTextFieldFXLigneIdentifiant;
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

    public Group labelIntTextField(int position, String textLabel, IntTextFieldFX textFieldFX, double transX, double transY){
        GroupFX group = new GroupFX(translateX, height/nbLigne * position + decalage);
        LabelFX label = new LabelFX(textLabel, fontText);

        textFieldFX = new IntTextFieldFX(transX, transY);

        group.addChild(label, textFieldFX);
        return group;
    }

    public Group labelChoixFichier(int position, File file){
        GroupFX group = new GroupFX(translateX, Main.HEIGHT/nbLigne * position + decalage);

        LabelFX label = new LabelFX("Choisir un fichier", fontText);
        LabelFX labelCheminAcces = new LabelFX("", fontText, 270, 0);

        ButtonFX button = new ButtonFX("Choisir un fichier", 130, 15, 140, -4);
        button.setOnAction(actionEvent -> selectionFichier(labelCheminAcces, file));

        group.addChild(label, button, labelCheminAcces);
        return group;
    }



    /**
     ********************************************************************************************************************
     */


    /*public Label titreReponse(int position){
         return new LabelFX("FICHIER REPONSES : ", fontTitre, 40, height/nbLigne * position + decalage);
    }



    public Group fichierReponse(int position){

        GroupFX group = new GroupFX(translateX, height/nbLigne * position + decalage);

        LabelFX labelCheminAcces = new LabelFX("", fontText, 270, 0);

        ButtonFX button = new ButtonFX("Choisir un fichier", 130, 15, 140, -4);

        button.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Fichier réponse");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier XLSX (.xlsx)", "*.xlsx"));
            try {
                fileReponse = fileChooser.showOpenDialog(Main.STAGE);
                fileChooser.setInitialDirectory(fileReponse.getParentFile());
                labelCheminAcces.setText("" + fileReponse);
            }catch (NullPointerException ignored){

            }

        });

        group.addChild(new LabelFX("Fichier réponse  :  ", fontText), button, labelCheminAcces);

        return group;
    }

    public Group nombreEtudiant(int position){
        GroupFX group = new GroupFX(translateX, height/nbLigne * position + decalage);

        LabelFX label = new LabelFX("Nombre d'étudiants : ", fontText);
        intTextFieldFXEtudiant = new IntTextFieldFX(0, 0, 150, -4);

        group.addChild(label, intTextFieldFXEtudiant);
        return group;
    }

    public Group nombreQCM(int position){
        GroupFX group = new GroupFX(translateX, height/nbLigne * position + decalage);;

        intTextFieldFXQCM = new IntTextFieldFX(150, -4);

        group.addChild(new LabelFX("Nombre de QCM : ", fontText), intTextFieldFXQCM);
        return group;
    }




    public Group ligneReponse(int position){
        GroupFX group = new GroupFX(translateX, height/nbLigne * position + decalage);
        LabelFX label = new LabelFX("Ligne à laquelle vous avez entré les bonnes réponses : ", fontText);

        intTextFieldFXLigneReponse = new IntTextFieldFX(370, -4);

        group.getChildren().addAll(label, intTextFieldFXLigneReponse);

        return group;
    }

    public Label titreIdentifiant(int position){
        Label label = new Label("FICHIER Identifiants : ");
        label.setFont(fontTitre);
        label.setTranslateX(40);
        label.setTranslateY(height/nbLigne * position + decalage);
        return label;
    }

    public Group fichierIdentifiant(int position){

        group = new Group();

        Label label = new Label("Fichier identifiant  :  ");
        label.setFont(fontText);

        LabelFX labelCheminAcces = new LabelFX("", fontText, 270, 0);

        Button button = buttonChoixFichier();
        button.setOnAction(actionEvent -> fileIdentifiant = selectionFichier(labelCheminAcces));

        group.getChildren().addAll(label, button, labelCheminAcces);
        group.setTranslateX(translateX);
        group.setTranslateY(height/nbLigne * position + decalage);

        return group;
    }

    public Group ligneIdentifiant(int position){
        group = new Group();

        Label label = new Label("Nombre d'étudiants ayant un identifiant  : ");
        label.setFont(fontText);

        intTextFieldFXLigneIdentifiant = new IntTextFieldFX();
        intTextFieldFXLigneIdentifiant.setTranslateX(300);
        intTextFieldFXLigneIdentifiant.setTranslateY(-4);

        group.getChildren().addAll(label, intTextFieldFXLigneIdentifiant);
        group.setTranslateX(translateX);
        group.setTranslateY(height/nbLigne * position + decalage);

        return group;
    }*/

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



/*
    public File getFileReponse() {
        return fileReponse;
    }

    public File getFileIdentifiant() {
        return fileIdentifiant;
    }

    public IntTextFieldFX getIntegerTextFieldEtudiant() {
        return intTextFieldFXEtudiant;
    }

    public IntTextFieldFX getIntegerTextFieldQCM() {
        return intTextFieldFXQCM;
    }

    public IntTextFieldFX getIntegerTextFieldLigneReponse() {
        return intTextFieldFXLigneReponse;
    }

    public IntTextFieldFX getIntegerTextFieldLigneIdentifiant() {
        return intTextFieldFXLigneIdentifiant;
    }*/




    public Button getStart() {
        return start;
    }

    private File selectionFichier(Label label, File file){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner un fichier");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Excel .xlsx", "*.xlsx"));

        file = fileChooser.showOpenDialog(Main.STAGE);

        if(file != null){
            label.setText("" + file);
            return file;
        }else {
            FenetreAlert.erreur("Vous n'avez pas choisi de fichier \nVeuillez réessayer");
            return null;
        }


       // if(label.getText().equals("null")) label.setText(" ");
    }

}
