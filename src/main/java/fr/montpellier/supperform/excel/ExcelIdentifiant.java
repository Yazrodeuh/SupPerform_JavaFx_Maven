package fr.montpellier.supperform.excel;

import fr.montpellier.supperform.affichage.FenetreAlert;
import fr.montpellier.supperform.affichage.FonctionAffichage;
import fr.montpellier.supperform.affichage.Identifiant;
import fr.montpellier.supperform.affichage.Notation;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelIdentifiant extends TableauExcel {

    public ExcelIdentifiant(FonctionAffichage affichage) {


        if(affichage instanceof Notation){

            Notation identifiant = (Notation) affichage;
            XSSFWorkbook workbookId = recuperationFichier(identifiant.getFileIdentifiant());
            XSSFSheet sheetId = workbookId.getSheetAt(0);

            int nombreLigneQCM = 0, nombreLigneId = 0;

            if(identifiant.getNbEtudiantQCM() != null &&  identifiant.getNbIdentifiant() != null){
                nombreLigneQCM = verifValueInt(identifiant.getNbEtudiantQCM().getInt(), "Le nombre d'étudiants doit être différent de 0.");
                nombreLigneId = verifValueInt(identifiant.getNbIdentifiant().getInt(), "Le nombre d'étudiant ayant un identifian doit etre différent de 0.");
            }else {
                FenetreAlert.erreur("Tous les champs doivent être rempli.");
            }

            String[][] identifiantEtu = recuperationValeurIdentifiant(nombreLigneId, sheetId);

            attributionIdentifiant(nombreLigneQCM, sheetId, identifiantEtu);

            fermetureFichier(workbookId, identifiant.getFileIdentifiant());

        }else if(affichage instanceof Identifiant){

            Identifiant identifiant = (Identifiant) affichage;
            XSSFWorkbook workbookId = recuperationFichier(identifiant.getFileIdentifiant());
            XSSFSheet sheetId = workbookId.getSheetAt(0);

            int nombreLigneQCM = 0, nombreLigneId = 0;

            if(identifiant.getNbEtudiantQCM() != null &&  identifiant.getNbIdentifiant() != null){
                nombreLigneQCM = verifValueInt(identifiant.getNbEtudiantQCM().getInt(), "Le nombre d'étudiants doit être différent de 0.");
                nombreLigneId = verifValueInt(identifiant.getNbIdentifiant().getInt(), "Le nombre d'étudiant ayant un identifian doit etre différent de 0.");
            }else {
                FenetreAlert.erreur("Tous les champs doivent être rempli.");
            }

            String[][] identifiantEtu = recuperationValeurIdentifiant(nombreLigneId, sheetId);

            attributionIdentifiant(nombreLigneQCM, sheetId, identifiantEtu);

            fermetureFichier(workbookId, identifiant.getFileIdentifiant());
        }

    }

}
