package fr.montpellier.supperform.excel;

import fr.montpellier.supperform.FenetreAlert;
import fr.montpellier.supperform.affichage.Notation;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelNotation extends TableauExcel {

    private final Notation notation;
    private XSSFSheet sheetRep, sheetId;
    private int nombreLigneQCM, nombreColonne, ligneRep, nombreLigneId;

    public ExcelNotation(double retrait, Notation notation) {
        super(retrait);
        this.notation = notation;

        sheetRep = recuperationFichierReponse(notation.getFileReponse());
        sheetId = recuperationFichierIdentifiant(notation.getFileIdentifiant());

        if(notation.getNbEtudiantQCM() != null && notation.getNbQCM() != null && notation.getLigneReponse() != null && notation.getNbIdentifiant() != null){
            nombreLigneQCM = verifValueInt(notation.getNbEtudiantQCM().getInt(), "Le nombre d'étudiants doit être différent de 0.");
            nombreColonne = verifValueInt(notation.getNbQCM().getInt(), "Le nombre de QCM doit être différent de 0.");
            ligneRep = verifValueInt(notation.getLigneReponse().getInt(), "La ligne à laquelle vous avez insérer les réponses doit être différent de 0.");
            nombreLigneId = verifValueInt(notation.getNbIdentifiant().getInt(), "Le nombre d'étudiant ayant un identifian doit etre différent de 0.");
        }else {
            FenetreAlert.erreur("Tous les chmps doivent être rempli.");
        }




        if(recuperationFichierReponse()){
            if(verifValueInt()){
                if (recupNbColonne()){
                    if (recuperationFichierIdentifiant()){
                        if (attributionIdentifiant()){
                            if (calculNote()){
                                fermetureFichierReponse();
                                fermetureFichierIdentifiant();
                            }
                        }
                    }
                }
            }
        }
    }


}
