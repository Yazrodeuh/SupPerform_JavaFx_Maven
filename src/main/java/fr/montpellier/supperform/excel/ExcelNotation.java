package fr.montpellier.supperform.excel;

import fr.montpellier.supperform.affichage.FenetreAlert;
import fr.montpellier.supperform.affichage.Notation;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelNotation extends TableauExcel {

    public ExcelNotation(Notation notation, double retrait) {

        XSSFWorkbook workbookReponse = recuperationFichier(notation.getFileReponse());
        XSSFSheet sheetRep = workbookReponse.getSheetAt(0);

        int nombreLigneQCM = 0, nombreColonne = 0, ligneRep = 0;

        if(notation.getNbEtudiantQCM() != null && notation.getNbQCM() != null && notation.getLigneReponse() != null && notation.getNbIdentifiant() != null){
            nombreLigneQCM = verifValueInt(notation.getNbEtudiantQCM().getInt(), "Le nombre d'étudiants doit être différent de 0.");
            nombreColonne = verifValueInt(notation.getNbQCM().getInt(), "Le nombre de QCM doit être différent de 0.");
            ligneRep = verifValueInt(notation.getLigneReponse().getInt(), "La ligne à laquelle vous avez insérer les réponses doit être différent de 0.");
        }else {
            FenetreAlert.erreur("Tous les champs doivent être rempli.");
        }

        calculNote(ligneRep, nombreLigneQCM, nombreColonne, sheetRep, retrait);

        workbookReponse = fermetureFichier(workbookReponse, notation.getFileReponse());

        try {
            workbookReponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
