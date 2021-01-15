package fr.montpellier.supperform.affichage;

import fr.montpellier.supperform.javaFX.IntTextFieldFX;
import fr.montpellier.supperform.excel.ExcelNotation;

import java.io.File;

public class Notation extends FonctionAffichage {

    private final double retrait;
    private File fileReponse, fileIdentifiant;
    private IntTextFieldFX intTextFieldFXEtudiant, intTextFieldFXQCM, intTextFieldFXLigneReponse, intTextFieldFXLigneIdentifiant;

    public Notation(double retrait){
        super(10);
        this.retrait = retrait;
        this.getChildren().addAll(
                labelTitre(0, "FICHIER REPONSES : "),
                labelChoixFichier(1, fileReponse),
                labelIntTextField(2, "Nombre d'étudiants : ", intTextFieldFXEtudiant, 150, -4),
                labelIntTextField(3, "Nombre de QCM : ", intTextFieldFXQCM, 150, -4),
                labelIntTextField(4, "Ligne à laquelle vous avez entré les bonnes réponses : ", intTextFieldFXLigneReponse, 370, -4),
                labelTitre(5, "FICHIER Identifiants : "),
                labelChoixFichier(6, fileIdentifiant),
                labelIntTextField(7, "Nombre d'étudiants ayant un identifiant : ", intTextFieldFXLigneIdentifiant, 300, -4),
                button(8));
    }

    @Override
    public void buttonStart(){
        getStart().setOnAction(actionEvent -> new ExcelNotation(retrait, this));
    }

    /**
     *
     * @return fichier contenant les réponses des étudiants
     */
    public File getFileReponse() {
        return fileReponse;
    }

    /**
     *
     * @return fichier contenant les identifiants des étudiants
     */
    public File getFileIdentifiant() {
        return fileIdentifiant;
    }

    /**
     *
     * @return nombre d'étudiant qui ont fait le QCM
     */
    public IntTextFieldFX getNbEtudiantQCM() {
        return intTextFieldFXEtudiant;
    }

    /**
     *
     * @return nombre de QCM posé
     */
    public IntTextFieldFX getNbQCM() {
        return intTextFieldFXQCM;
    }

    /**
     *
     * @return ligne à laquelle on a les réponses
     */
    public IntTextFieldFX getLigneReponse() {
        return intTextFieldFXLigneReponse;
    }

    /**
     *
     * @return nombre d'étudiant ayant un identifiant
     */
    public IntTextFieldFX getNbIdentifiant() {
        return intTextFieldFXLigneIdentifiant;
    }
}
