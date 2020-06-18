package fr.montpellier.supperform.Affichage;

import fr.montpellier.supperform.Main;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

import java.io.File;

public abstract class FonctionAffichage extends Group {

    private final Font fontTitre = Font.font("Verdana", FontWeight.BOLD, 15);
    private final Font fontText = Font.font("Verdana", FontWeight.SEMI_BOLD, 13);
    private File fileReponse, fileIdentifiant;
    private Group group;
    private Spinner<Integer> spinnerEtudiant, spinnerQCM, spinnerLigneReponse, spinnerLigneIdentifiant;
    private IntegerTextField integerTextFieldEtudiant, integerTextFieldQCM, integerTextFieldLigneReponse, integerTextFieldLigneIdentifiant;
    private SpinnerValueFactory<Integer> spinnerValueFactory ;
    private final int translateX;
    private final double height, decalage = 10, nbLigne;
    private Button start, quitter;
    private Main main;

    public FonctionAffichage(Main main, int nbLigne){
        this.nbLigne = nbLigne;
        this.main = main;
        height = main.getHeight();
        translateX = 80;
    }

    public Label titreReponse(int position){
        Label label = new Label("FICHIER REPONSES : ");
        label.setFont(fontTitre);
        label.setTranslateX(40);
        label.setTranslateY(height/nbLigne * position + decalage);
        //labeltireReponse.setStyle("-fx-border-color: black;");
        //labeltireReponse.setBorder(new Border(new BorderStroke(, BorderStrokeStyle.SOLID, null, new BorderWidths(5))));
        //labeltireReponse.setTextFill(Paint.valueOf("Red"));
        //labeltireReponse.setEffect(new DropShadow());
        //labeltireReponse.setEffect(new Reflection());
        return label;
    }

    public Group fichierReponse(int position){

        group = new Group();

        Label label = new Label("Fichier réponse  :  ");
        label.setFont(fontText);

        Label labelCheminAcces = new Label();
        labelCheminAcces.setFont(fontText);
        labelCheminAcces.setTranslateX(270);

        Button button = new Button("Choisir un fichier");
        button.setPrefSize(130, 15);
        button.setTranslateX(140);
        button.setTranslateY(-4);
        button.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Fichier réponse");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier XLSX (.xlsx)", "*.xlsx"));
            try {
                fileReponse = fileChooser.showOpenDialog(new Main().getStage());
                fileChooser.setInitialDirectory(fileReponse.getParentFile());
                labelCheminAcces.setText("" + fileReponse);
            }catch (NullPointerException e ){
                System.out.println("i");
                //TODO mettre une boite de dialogue
            }

        });

        group.getChildren().addAll(label, button, labelCheminAcces);
        group.setTranslateX(translateX);
        group.setTranslateY(height/nbLigne * position + decalage);

        return group;
    }

    public Group nombreEtudiant(int position){
        group = new Group();

        Label label = new Label("Nombre d'étudiants : ");
        label.setFont(fontText);

        /*spinnerEtudiant = new Spinner<>();
        spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0);
        spinnerEtudiant.setValueFactory(spinnerValueFactory);
        spinnerEtudiant.setTranslateX(150);
        spinnerEtudiant.setTranslateY(-4);
        spinnerEtudiant.setEditable(true);*/
        //spinner.valueProperty().addListener(observable -> System.out.printf("Valeur sélectionnée: %s", spinner.getValue()).println());

        integerTextFieldEtudiant = new IntegerTextField();
        integerTextFieldEtudiant.setTranslateX(150);
        integerTextFieldEtudiant.setTranslateY(-4);

        group.getChildren().addAll(label, integerTextFieldEtudiant);
        group.setTranslateX(translateX);
        group.setTranslateY(height/nbLigne * position + decalage);

        return group;
    }

    public Group nombreQCM(int position){
        group = new Group();

        Label label = new Label("Nombre de QCM : ");
        label.setFont(fontText);

        /*spinnerQCM = new Spinner<>();
        spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0);
        spinnerQCM.setValueFactory(spinnerValueFactory);
        spinnerQCM.setTranslateX(150);
        spinnerQCM.setTranslateY(-4);
        spinnerQCM.setEditable(true);
        //spinner.valueProperty().addListener(observable -> System.out.printf("Valeur sélectionnée: %s", spinner.getValue()).println());*/

        integerTextFieldQCM = new IntegerTextField();
        integerTextFieldQCM.setTranslateX(150);
        integerTextFieldQCM.setTranslateY(-4);

        group.getChildren().addAll(label, integerTextFieldQCM);
        group.setTranslateX(translateX);
        group.setTranslateY(height/nbLigne * position + decalage);

        return group;
    }

    public Group ligneReponse(int position){
        group = new Group();

        Label label = new Label("Ligne à laquelle vous avez entré les bonnes réponses : ");
        label.setFont(fontText);

        /*spinnerLigneReponse = new Spinner<>();
        spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0);
        spinnerLigneReponse.setValueFactory(spinnerValueFactory);
        spinnerLigneReponse.setTranslateX(370);
        spinnerLigneReponse.setTranslateY(-4);
        spinnerLigneReponse.setEditable(true);
        //spinner.valueProperty().addListener(observable -> System.out.printf("Valeur sélectionnée: %s", spinner.getValue()).println());*/

        integerTextFieldLigneReponse = new IntegerTextField();
        integerTextFieldLigneReponse.setTranslateX(370);
        integerTextFieldLigneReponse.setTranslateY(-4);

        group.getChildren().addAll(label, integerTextFieldLigneReponse);
        group.setTranslateX(translateX);
        group.setTranslateY(height/nbLigne * position + decalage);

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

        Label labelCheminAcces = new Label();
        labelCheminAcces.setFont(fontText);
        labelCheminAcces.setTranslateX(270);

        Button button = new Button("Choisir un fichier");
        button.setPrefSize(130, 15);
        button.setTranslateX(140);
        button.setTranslateY(-4);
        button.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Fichier réponse");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Excel .xlsx", "*.xlsx"));
            try {
                fileIdentifiant = fileChooser.showOpenDialog(new Main().getStage());
                fileChooser.setInitialDirectory(fileIdentifiant.getParentFile());
                labelCheminAcces.setText("" + fileIdentifiant);
            }catch (NullPointerException e ){
                System.out.println("i");
                //TODO mettre une boite de dialogue
            }
        });

        group.getChildren().addAll(label, button, labelCheminAcces);
        group.setTranslateX(translateX);
        group.setTranslateY(height/nbLigne * position + decalage);

        return group;
    }

    public Group ligneIdentifiant(int position){
        group = new Group();

        Label label = new Label("Nombre d'étudiants ayant un identifiant  : ");
        label.setFont(fontText);

        /*spinnerLigneIdentifiant = new Spinner<>();
        spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0);
        spinnerLigneIdentifiant.setValueFactory(spinnerValueFactory);
        spinnerLigneIdentifiant.setTranslateX(300);
        spinnerLigneIdentifiant.setTranslateY(-4);
        spinnerLigneIdentifiant.setEditable(true);
        //spinner.valueProperty().addListener(observable -> System.out.printf("Valeur sélectionnée: %s", spinner.getValue()).println());*/

        integerTextFieldLigneIdentifiant = new IntegerTextField();
        integerTextFieldLigneIdentifiant.setTranslateX(300);
        integerTextFieldLigneIdentifiant.setTranslateY(-4);


        group.getChildren().addAll(label, integerTextFieldLigneIdentifiant);
        group.setTranslateX(translateX);
        group.setTranslateY(height/nbLigne * position + decalage);

        return group;
    }

    public Group button(int position){
        group = new Group();

        start = new Button("Start");
        start.setPrefSize(70, 15);
        start.setTranslateX(400-translateX - start.getWidth()/2 - 60);

        quitter = new Button("Retour");
        quitter.setPrefSize(70, 15);
        quitter.setTranslateX(400-translateX - quitter.getWidth()/2 + 60);

        buttonStart();
        buttonQuitter();

        group.getChildren().addAll(start, quitter);
        group.setTranslateX(translateX);
        group.setTranslateY(height/nbLigne * position + decalage);

        return group;
    }

    public void buttonStart(){}
    public void buttonQuitter(){
        quitter.setOnAction(actionEvent -> {
            Scene accueil = new Scene(new Accueil(main), main.getWidth(), main.getHeight());
            accueil.setFill(Color.LIGHTGRAY);
            accueil.getStylesheets().add("fr/montpellier/supperform/Button.css");
            main.getStage().setTitle("Sup'Perform Accueil");
            main.getStage().setScene(accueil);


        });
    }

    public File getFileReponse() {
        return fileReponse;
    }

    public File getFileIdentifiant() {
        return fileIdentifiant;
    }

    /*public Spinner<Integer> getSpinnerEtudiant() {
        return spinnerEtudiant;
    }

    public Spinner<Integer> getSpinnerNombreQCM() {
        return spinnerQCM;
    }

    public Spinner<Integer> getSpinnerLigneReponseQCM() {
        return spinnerLigneReponse;
    }

    public Spinner<Integer> getSpinnerNombreLigneIdentifiant() {
        return spinnerLigneIdentifiant;
    }*/

    public IntegerTextField getIntegerTextFieldEtudiant() {
        return integerTextFieldEtudiant;
    }

    public IntegerTextField getIntegerTextFieldQCM() {
        return integerTextFieldQCM;
    }

    public IntegerTextField getIntegerTextFieldLigneReponse() {
        return integerTextFieldLigneReponse;
    }

    public IntegerTextField getIntegerTextFieldLigneIdentifiant() {
        return integerTextFieldLigneIdentifiant;
    }

    public Button getStart() {
        return start;
    }

    public Button getQuitter() {
        return quitter;
    }
}
