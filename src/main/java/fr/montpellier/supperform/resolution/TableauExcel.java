package fr.montpellier.supperform.resolution;

import fr.montpellier.supperform.Affichage.FonctionAffichage;
import javafx.scene.control.Alert;
import org.apache.poi.openxml4j.exceptions.ODFNotOfficeXmlFileException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TableauExcel {

    private int nombreLigne, nombreColonne;
    private File fileReponse, fileId;
    private XSSFSheet sheetReponse, sheetId;
    private XSSFWorkbook workbookReponse, workbookId;
    private Calcul calcul;
    private FonctionAffichage notation;
    private Alert alert;

    public TableauExcel(FonctionAffichage notation, double retrait){
        calcul = new Calcul(retrait);
        this.notation = notation;
    }

    public boolean recuperationFichierReponse() {

        try {
            fileReponse = notation.getFileReponse();
            FileInputStream fileInputStreamReponse = new FileInputStream(fileReponse);
            workbookReponse = new XSSFWorkbook(fileInputStreamReponse);
            sheetReponse = workbookReponse.getSheetAt(0);
            return true;
        } catch (NullPointerException n) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le fichier n'a pas était trouvé. \nVeuillez réessayer");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ODFNotOfficeXmlFileException n){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le format du fichier est incorect. \nVeuillez réessayer");
            alert.showAndWait();
        }
        return false;
    }

    public boolean recuperationNombreLigne(){
        nombreLigne = notation.getSpinnerEtudiant().getValue();
        if( nombreLigne != 0){
            return true;
        }else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le nombre d'étudiants doit être différent de 0. \nVeuillez réessayer");
            alert.showAndWait();
            return false;
        }

    }

    public boolean recuperationNombreColonne(){
        nombreColonne = notation.getSpinnerNombreQCM().getValue();
        if (nombreColonne != 0){
            return true;
        }else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le nombre de QCM doit être différent de 0. \nVeuillez réessayer");
            alert.showAndWait();
            return false;
        }
    }

    public boolean recuperationFichierIdentifiant() {
        try {
            fileId = notation.getFileIdentifiant();
            FileInputStream fileInputStreamId = new FileInputStream(fileId);
            workbookId = new XSSFWorkbook(fileInputStreamId);
            sheetId = workbookId.getSheetAt(0);
            return true;
        }catch (NullPointerException n) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le fichier n'a pas était trouvé. \nVeuillez réessayer");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ODFNotOfficeXmlFileException n){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le format du fichier est incorect. \nVeuillez réessayer");
            alert.showAndWait();
        }
        return false;
    }

    public boolean attributionIdentifiant(){
        if(notation.getSpinnerNombreLigneIdentifiant().getValue() != 0){
            XSSFCell cellReponseIdentifiant = sheetReponse.getRow(0).getCell(2);
            cellReponseIdentifiant.setCellValue("Identifiant contrôle");

            XSSFCell cellReponseLieu = sheetReponse.getRow(0).getCell(3);
            cellReponseLieu.setCellValue("Lieu d'inscription");

            for (int i = 0; i < nombreLigne; i++) {

                XSSFCell cellReponseNom = sheetReponse.getRow(i + 1).getCell(0);
                ArrayList<String> reponseNom = calcul.creationTableauEspace(cellReponseNom);

                XSSFCell cellReponsePrenom = sheetReponse.getRow(i + 1).getCell(1);
                ArrayList<String> reponsePrenom = calcul.creationTableauEspace(cellReponsePrenom);

                XSSFCell cellReponseId = sheetReponse.getRow(i + 1).createCell(2);
                cellReponseId.setCellValue("");

                XSSFCell cellReponseLieuInscription = sheetReponse.getRow(i + 1).createCell(3);
                cellReponseLieuInscription.setCellValue("");

                for (int j = 1; j < notation.getSpinnerNombreLigneIdentifiant().getValue(); j++) {

                    XSSFCell cellIdNom = sheetId.getRow(j).getCell(0);
                    ArrayList<String> idNom = calcul.creationTableauEspace(cellIdNom);

                    XSSFCell cellIdPrenom = sheetId.getRow(j).getCell(1);
                    ArrayList<String> idPrenom = calcul.creationTableauEspace(cellIdPrenom);

                    XSSFCell cellId = sheetId.getRow(j).getCell(2);
                    XSSFCell cellIdLieuInscription = sheetId.getRow(j).getCell(3);

                    if (idNom.size() == reponseNom.size() && idPrenom.size() == reponsePrenom.size()) {

                        boolean different = false;
                        for (int k = 0; k < reponseNom.size(); k++) {
                            if (!reponseNom.get(k).equals(idNom.get(k))) {
                                different = true;
                                break;
                            }
                        }

                        for (int k = 0; k < reponsePrenom.size(); k++) {

                            if (!reponsePrenom.get(k).equals(idPrenom.get(k))) {
                                different = true;
                                break;
                            }
                        }
                        if (!different) {
                            cellReponseNom.setCellValue(cellIdNom.getStringCellValue());
                            cellReponsePrenom.setCellValue(cellIdPrenom.getStringCellValue());
                            cellReponseId.setCellValue(cellId.getNumericCellValue());
                            cellReponseLieuInscription.setCellValue(cellIdLieuInscription.getStringCellValue());
                        }
                    }
                }
            }
            return true;
        }else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le nombre d'étudiants ayant un identifiant doit être différent de 0. \nVeuillez réessayer");
            alert.showAndWait();
            return false;
        }
    }

    public boolean calculNote(){
        if(notation.getSpinnerLigneReponseQCM().getValue() > nombreLigne){
            double[][] tableauResultat = new double[nombreLigne][nombreColonne];

            for (double[] doubles : tableauResultat) {
                Arrays.fill(doubles, 0);
            }

            for (int i = 0; i < nombreColonne; i++) {

                XSSFCell cellReponseQCM = sheetReponse.getRow(notation.getSpinnerLigneReponseQCM().getValue() - 1).getCell(i);
                ArrayList<String> reponseQCM = calcul.creationTableauReponse(cellReponseQCM);

                //System.out.println(reponseQCM.toString());

                for (int j = 0; j < nombreLigne; j++) {      //génère les résultats de chaque QCM pour chaque Etudiant et l'ajoute dans un tableau à deux dimensions

                    XSSFCell cellReponseEtudiantQCM = sheetReponse.getRow(1 + j).getCell(10 + i);
                    ArrayList<String> reponseEtudiantQCM = calcul.creationTableau(cellReponseEtudiantQCM);

                    //System.out.println(reponseEtudiantQCM.toString());
                    //System.out.println(" ");

                    double resultat = calcul.resultatEtudiantAuQCM(reponseQCM, reponseEtudiantQCM);      //calcul du resultat en appelant la fonction resultatEtudiantAuQCM

                    tableauResultat[j][i] = resultat;        //ajoute les résultats au tableaux

                    XSSFCell cellResultatEtudiantQCM = sheetReponse.getRow(1 + j).createCell(10 + i + nombreColonne + 6); //créé les nouvelles cellules où seront enregistrées les résultats
                    cellResultatEtudiantQCM.setCellValue(false);
                }
            }

            for (int i = 0; i < nombreColonne; i++) {
                for (int j = 0; j < nombreLigne; j++) {
                    XSSFCell cellResultatEtudiantQCM = sheetReponse.getRow(1 + j).getCell(10 + i + nombreColonne + 6); //récupère le contenu de chaque cellule
                    cellResultatEtudiantQCM.setCellValue(tableauResultat[j][i]);    //rempli la cellule avec les résultats
                }
            }

            for (int i = 0; i < nombreLigne; i++) {

                double total = 0;

                XSSFCell cellResultatTotalEtudiantQCM = sheetReponse.getRow(1 + i).createCell(10 + nombreColonne + nombreColonne + 7);
                cellResultatTotalEtudiantQCM.setCellValue(false);

                for (int j = 0; j < nombreColonne; j++) {

                    cellResultatTotalEtudiantQCM = sheetReponse.getRow(1 + i).getCell(10 + nombreColonne + nombreColonne + 7);
                    total += tableauResultat[i][j];
                    cellResultatTotalEtudiantQCM.setCellValue(total);
                }
            }
            return true;
        }else if(notation.getSpinnerLigneReponseQCM().getValue() == 0){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vous ne pouvez pas entrer les réponses à la ligne 0. \nVeuillez réessayer");
            alert.showAndWait();
            return false;
        }else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La ligne à laquelle vous avez entré les bonnes réponses doit être supérieur au nombre d'étudiant. \nVeuillez réessayer");
            alert.showAndWait();
            return false;
        }
    }

    public void fermetureFichierReponse() {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileReponse);
            workbookReponse.write(fileOut);
            workbookReponse.close();
            fileOut.close();
        } catch (FileNotFoundException | NullPointerException n) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Fermer le fichier Excel des réponses puis recommencer.");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fermetureFichierIdentifiant() {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileId);
            workbookId.write(fileOut);
            workbookId.close();
            fileOut.close();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Le programme a correctement foonctionné !\n Vous pouvez quitter.");
            alert.showAndWait();
        } catch (FileNotFoundException | NullPointerException n) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Fermer le fichier Excel des Identifiants puis recommencer.");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
