package fr.montpellier.supperform.affichage;

import fr.montpellier.supperform.javaFX.GroupFX;
import fr.montpellier.supperform.javaFX.IntTextFieldFX;
import fr.montpellier.supperform.excel.ExcelIdentifiant;

import java.io.File;

public class Identifiant extends FonctionAffichage {

    private final GroupFX fichierReponse = labelChoixFichier(1);
    private final GroupFX labelNbEtu = labelIntTextField(2, "Nombre d'étudiants : ",  150, -4);
    private final GroupFX fichierId = labelChoixFichier(4);
    private final GroupFX labelNbId = labelIntTextField(5, "Nombre d'étudiants ayant un identifiant : ",300, -4);

    public Identifiant() {
        super(7);
        this.getChildren().addAll(
                labelTitre(0, "FICHIER REPONSES : "),
                fichierReponse, labelNbEtu,
                labelTitre(3, "FICHIER Identifiants : "),
                fichierId, labelNbId,
                button(6));
    }

    @Override
    public void buttonStart(){
        getStart().setOnAction(actionEvent -> new ExcelIdentifiant(this));
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
     * @return nombre d'étudiant ayant un identifiant
     */
    public IntTextFieldFX getNbIdentifiant() {
        return labelNbId.getIntTextFieldFX(1);
    }
}
