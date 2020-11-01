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
        this.notation = notation;
        calcul = new Calcul(retrait);
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
        } catch (ODFNotOfficeXmlFileException n){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le format du fichier est incorect. \nVeuillez réessayer");
            alert.showAndWait();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean recuperationNombreLigne(){
        nombreLigne = notation.getIntegerTextFieldEtudiant().getInt();
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
        nombreColonne = notation.getIntegerTextFieldQCM().getInt();
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
        if(notation.getIntegerTextFieldLigneIdentifiant().getInt() != 0){
            XSSFCell cellReponseIdentifiant = sheetReponse.getRow(0).getCell(2);
            cellReponseIdentifiant.setCellValue("Identifiant contrôle");

            XSSFCell cellReponseLieu = sheetReponse.getRow(0).getCell(3);
            cellReponseLieu.setCellValue("Lieu d'inscription");

            for (int i = 0; i < nombreLigne; i++) {

                XSSFCell cellReponseNom = sheetReponse.getRow(i + 1).getCell(0);
                //ArrayList<String> reponseNom = calcul.creationTableauEspace(cellReponseNom);
                String reponseNomTest = cellReponseNom.getStringCellValue().toUpperCase();

                XSSFCell cellReponsePrenom = sheetReponse.getRow(i + 1).getCell(1);
                //ArrayList<String> reponsePrenom = calcul.creationTableauEspace(cellReponsePrenom);
                String reponsePrenomTest = cellReponsePrenom.getStringCellValue().toUpperCase();

                XSSFCell cellReponseId = sheetReponse.getRow(i + 1).createCell(2);
                cellReponseId.setCellValue("");

                XSSFCell cellReponseLieuInscription = sheetReponse.getRow(i + 1).createCell(3);
                cellReponseLieuInscription.setCellValue("");

                for (int j = 1; j < notation.getIntegerTextFieldLigneIdentifiant().getInt(); j++) {

                    XSSFCell cellIdNom = sheetId.getRow(j).getCell(0);
                    //ArrayList<String> idNom = calcul.creationTableauEspace(cellIdNom);
                    String idNomTest = cellIdNom.getStringCellValue().toUpperCase();

                    XSSFCell cellIdPrenom = sheetId.getRow(j).getCell(1);
                    //ArrayList<String> idPrenom = calcul.creationTableauEspace(cellIdPrenom);
                    String idPrenomTest = cellIdPrenom.getStringCellValue().toUpperCase();

                    XSSFCell cellId = sheetId.getRow(j).getCell(2);
                    XSSFCell cellIdLieuInscription = sheetId.getRow(j).getCell(3);

                    if (idNomTest.equals(reponseNomTest) && idPrenomTest.equals(reponsePrenomTest)) {

                        /*boolean different = false;
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
                        if (!different) {*/
                            cellReponseNom.setCellValue(cellIdNom.getStringCellValue());
                            cellReponsePrenom.setCellValue(cellIdPrenom.getStringCellValue());
                            cellReponseId.setCellValue(cellIdLieuInscription.getNumericCellValue());
                            cellReponseLieuInscription.setCellValue(cellId.getStringCellValue());
                        /*}*/
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
        if(notation.getIntegerTextFieldLigneReponse().getInt() > nombreLigne){
            //double[][] tableauResultat = new double[nombreLigne][nombreColonne];

            int positionDebutReponse = 0;
            while (!sheetReponse.getRow(0).getCell(positionDebutReponse).getStringCellValue().startsWith("Réponse 1")){
                //System.out.println(positionDebutReponse);
                positionDebutReponse++;
            }
            System.out.println(positionDebutReponse);

            /*for (double[] doubles : tableauResultat) {
                Arrays.fill(doubles, 0);
            }*/

            sheetReponse.getRow(0).createCell(positionDebutReponse + nombreColonne + 4 + nombreColonne).setCellValue("Note finale");

            for (int i = 0; i < nombreColonne; i++) {

                XSSFCell cellReponseQCM = sheetReponse.getRow(notation.getIntegerTextFieldLigneReponse().getInt() - 1).getCell(i);
                ArrayList<String> reponseQCM = calcul.creationTableauReponse(cellReponseQCM);

                sheetReponse.getRow(0).createCell(positionDebutReponse + i + nombreColonne + 3).setCellValue("Note réponse " + (i+1));

                System.out.println("reponse :" + Arrays.toString(reponseQCM.toArray()));

                for (int j = 0; j < nombreLigne; j++) {      //génère les résultats de chaque QCM pour chaque Etudiant et l'ajoute dans un tableau à deux dimensions

                    //double total = 0;

                    XSSFCell cellReponseEtudiantQCM = sheetReponse.getRow(1 + j).getCell(positionDebutReponse + i);
                    System.out.println(cellReponseEtudiantQCM);
                    ArrayList<String> reponseEtudiantQCM = calcul.creationTableau(cellReponseEtudiantQCM);

                    //System.out.println(reponseEtudiantQCM.toString());
                    //System.out.println(" ");

                    double resultat = calcul.resultatEtudiantAuQCM(reponseQCM, reponseEtudiantQCM);      //calcul du resultat en appelant la fonction resultatEtudiantAuQCM

                    //tableauResultat[j][i] = resultat;        //ajoute les résultats au tableaux

                    XSSFCell cellResultatEtudiantQCM = sheetReponse.getRow(1 + j).createCell(positionDebutReponse + i + nombreColonne + 3); //créé les nouvelles cellules où seront enregistrées les résultats
                    cellResultatEtudiantQCM.setCellValue("");

                    cellResultatEtudiantQCM.setCellValue(resultat);    //rempli la cellule avec les résultats

                    //total += tableauResultat[j][i];

                    if(sheetReponse.getRow(1 + j).getCell(positionDebutReponse + nombreColonne + 4 + nombreColonne) == null){
                        XSSFCell cellResultatTotalEtudiantQCM = sheetReponse.getRow(1 + j).createCell(positionDebutReponse + nombreColonne + 4 + nombreColonne);
                        cellResultatTotalEtudiantQCM.setCellValue(cellResultatTotalEtudiantQCM.getNumericCellValue() + resultat);
                    }else {
                        XSSFCell cellResultatTotalEtudiantQCM = sheetReponse.getRow(1 + j).getCell(positionDebutReponse + nombreColonne + 4 + nombreColonne);
                        cellResultatTotalEtudiantQCM.setCellValue(cellResultatTotalEtudiantQCM.getNumericCellValue() + resultat);
                    }
                }
            }

            /*for (int i = 0; i < nombreColonne; i++) {
                for (int j = 0; j < nombreLigne; j++) {
                    XSSFCell cellResultatEtudiantQCM = sheetReponse.getRow(1 + j).getCell(positionDebutReponse + i + nombreColonne + 6); //récupère le contenu de chaque cellule
                    cellResultatEtudiantQCM.setCellValue(tableauResultat[j][i]);    //rempli la cellule avec les résultats
                }
            }*/

            /*for (int i = 0; i < nombreLigne; i++) {

                double total = 0;

                XSSFCell cellResultatTotalEtudiantQCM = sheetReponse.getRow(1 + i).createCell(10 + nombreColonne + nombreColonne + 7);
                cellResultatTotalEtudiantQCM.setCellValue("");

                for (int j = 0; j < nombreColonne; j++) {

                    cellResultatTotalEtudiantQCM = sheetReponse.getRow(1 + i).getCell(10 + nombreColonne + nombreColonne + 7);
                    total += tableauResultat[i][j];
                    cellResultatTotalEtudiantQCM.setCellValue(total);
                }
            }*/

            return true;
        }else if(notation.getIntegerTextFieldLigneReponse().getInt() == 0){
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
            alert.setContentText("Fermer le fichier Excel des Réponses puis recommencer.");
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
            alert.setContentText("Le programme a correctement fonctionné !");
            alert.showAndWait();
            notation.buttonQuitter();
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
