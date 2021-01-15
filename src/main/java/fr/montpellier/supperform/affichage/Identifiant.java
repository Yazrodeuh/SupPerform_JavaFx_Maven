package fr.montpellier.supperform.affichage;

import fr.montpellier.supperform.javaFX.IntTextFieldFX;
import fr.montpellier.supperform.excel.ExcelIdentifiant;

import java.io.File;

public class Identifiant extends FonctionAffichage {

    private File fileReponse, fileIdentifiant;
    private IntTextFieldFX intTextFieldFXEtudiant, intTextFieldFXQCM, intTextFieldFXLigneReponse, intTextFieldFXLigneIdentifiant;

    public Identifiant() {
        super(7);
        this.getChildren().addAll(
                labelTitre(0, "FICHIER REPONSES : "),
                labelChoixFichier(1, fileReponse),
                labelIntTextField(2, "Nombre d'étudiants : ", intTextFieldFXEtudiant, 150, -4),
                labelTitre(3, "FICHIER Identifiants : "),
                labelChoixFichier(4, fileIdentifiant),
                labelIntTextField(5, "Nombre d'étudiants : ", intTextFieldFXLigneIdentifiant, 300, -4),
                button(6));
    }

    @Override
    public void buttonStart(){
        getStart().setOnAction(actionEvent -> new ExcelIdentifiant(this));
    }

}
