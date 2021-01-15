package fr.montpellier.supperform.excel;

import fr.montpellier.supperform.FenetreAlert;
import fr.montpellier.supperform.affichage.Identifiant;
import fr.montpellier.supperform.affichage.Notation;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelIdentifiant extends TableauExcel {

    public ExcelIdentifiant(Identifiant identifiant) {

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

        workbookId = fermetureFichier(workbookId, identifiant.getFileIdentifiant());

        try {
            workbookId.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ExcelIdentifiant(Notation notation) {

        XSSFWorkbook workbookId = recuperationFichier(notation.getFileIdentifiant());

        XSSFSheet sheetId = workbookId.getSheetAt(0);

        int nombreLigneQCM = 0, nombreLigneId = 0;

        if(notation.getNbEtudiantQCM() != null &&  notation.getNbIdentifiant() != null){
            nombreLigneQCM = verifValueInt(notation.getNbEtudiantQCM().getInt(), "Le nombre d'étudiants doit être différent de 0.");
            nombreLigneId = verifValueInt(notation.getNbIdentifiant().getInt(), "Le nombre d'étudiant ayant un identifian doit etre différent de 0.");
        }else {
            FenetreAlert.erreur("Tous les champs doivent être rempli.");
        }

        String[][] identifiantEtu = recuperationValeurIdentifiant(nombreLigneId, sheetId);

        attributionIdentifiant(nombreLigneQCM, sheetId, identifiantEtu);

        workbookId = fermetureFichier(workbookId, notation.getFileIdentifiant());

        try {
            workbookId.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
