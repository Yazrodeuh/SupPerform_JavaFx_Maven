package fr.montpellier.supperform.affichage;

import fr.montpellier.supperform.excel.ExcelIdentifiant;
import fr.montpellier.supperform.javaFX.GroupFX;
import fr.montpellier.supperform.javaFX.IntTextFieldFX;
import fr.montpellier.supperform.excel.ExcelNotation;

import java.io.File;

public class Notation extends FonctionAffichage {

    private final double retrait;

    private final GroupFX fichierReponse = labelChoixFichier(1);
    private final GroupFX labelNbEtu = labelIntTextField(2, "Nombre d'étudiants : ",  150, -4);
    private final GroupFX labelNbQCM = labelIntTextField(3, "Nombre de QCM : ", 150, -4);
    private final GroupFX labelLigneR = labelIntTextField(4, "Ligne à laquelle vous avez entré les bonnes réponses : ", 370, -4);
    private final GroupFX fichierId = labelChoixFichier(6);
    private final GroupFX labelNbId = labelIntTextField(7, "Nombre d'étudiants ayant un identifiant : ",300, -4);

    public Notation(double retrait){
        super(10);
        this.retrait = retrait;
        this.getChildren().addAll(
                labelTitre(0, "FICHIER REPONSES : "),
                fichierReponse, labelNbEtu, labelNbQCM, labelLigneR,
                labelTitre(5, "FICHIER Identifiants : "),
                fichierId, labelNbId,
                button(8));
    }

    @Override
    public void buttonStart(){
        getStart().setOnAction(actionEvent -> {
            new ExcelIdentifiant(this);
            new ExcelNotation(this, retrait);
        });
    }

    /**
     *
     * @return fichier contenant les réponses des étudiants
     */
    public File getFileReponse() {
        return fichierReponse.getFile(2);
    }

    /**
     *
     * @return fichier contenant les identifiants des étudiants
     */
    public File getFileIdentifiant() {
        return fichierId.getFile(2);
    }

    /**
     *
     * @return nombre d'étudiant qui ont fait le QCM
     */
    public IntTextFieldFX getNbEtudiantQCM()  {
        return labelNbEtu.getIntTextFieldFX(1);
    }

    /**
     *
     * @return nombre de QCM posé
     */
    public IntTextFieldFX getNbQCM() {
        return labelNbQCM.getIntTextFieldFX(1);
    }

    /**
     *
     * @return ligne à laquelle on a les réponses
     */
    public IntTextFieldFX getLigneReponse() {
        return labelLigneR.getIntTextFieldFX(1);
    }

    /**
     *
     * @return nombre d'étudiant ayant un identifiant
     */
    public IntTextFieldFX getNbIdentifiant() {
        return labelNbId.getIntTextFieldFX(1);
    }
}
