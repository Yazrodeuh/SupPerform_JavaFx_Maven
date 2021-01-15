package fr.montpellier.supperform.excel;

import fr.montpellier.supperform.affichage.FonctionAffichage;
import fr.montpellier.supperform.FenetreAlert;
import fr.montpellier.supperform.resolution.Calcul;
import org.apache.poi.openxml4j.exceptions.ODFNotOfficeXmlFileException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TableauExcel {

    //private int nombreLigne, nombreColonne;
    private File fileReponse, fileId;
    //private XSSFSheet sheetReponse, sheetId;
    private XSSFWorkbook workbookReponse, workbookId;
    private Calcul calcul;
    private FonctionAffichage notation;
    private Map<String, ArrayList> idEtu = new HashMap<>();
    //private Alert alert;

    public TableauExcel(double retrait){
        //this.notation = notation;
        calcul = new Calcul(retrait);
    }

    public XSSFSheet recuperationFichierReponse(File fileReponse) {
        try {
            //fileReponse = notation.getFileReponse();
            FileInputStream fileInputStreamReponse = new FileInputStream(fileReponse);
            workbookReponse = new XSSFWorkbook(fileInputStreamReponse);
            return workbookReponse.getSheetAt(0);;
        } catch (NullPointerException n) {
            FenetreAlert.erreur("Le fichier n'a pas était trouvé. \nVeuillez réessayer");
            return null;
        } catch (ODFNotOfficeXmlFileException n){
            FenetreAlert.erreur("Le format du fichier est incorect. \nVeuillez réessayer");
            return null;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int verifValueInt(int nb, String text){
        if( nb != 0){
            return nb;
        }else {
            FenetreAlert.erreur(text);
            return 0;
        }
    }

    public XSSFSheet recuperationFichierIdentifiant(File fileId) {
        try {
            //fileId = notation.getFileIdentifiant();
            FileInputStream fileInputStreamId = new FileInputStream(fileId);
            workbookId = new XSSFWorkbook(fileInputStreamId);
            sheetId = workbookId.getSheetAt(0);
            return sheetId;
        }catch (NullPointerException n) {
            FenetreAlert.erreur("Le fichier n'a pas était trouvé. \nVeuillez réessayer");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch (ODFNotOfficeXmlFileException n){
            FenetreAlert.erreur("Le format du fichier est incorect. \nVeuillez réessayer");
            return null;
        }
    }

    /**
     **************************************************************************************
     */

    public void recuperationValeurIdentifiant(int nbLigneEtuId, XSSFSheet sheet){

        for (int i = 0; i < nbLigneEtuId; i++) {
            XSSFCell cellIdNom = sheet.getRow(i + 1).getCell(0);
            String idNomTest = cellIdNom.getStringCellValue().toUpperCase();

            XSSFCell cellIdPrenom = sheet.getRow(i + 1).getCell(1);
            String idPrenomTest = cellIdPrenom.getStringCellValue().toUpperCase();

            XSSFCell cellId = sheet.getRow(i + 1).getCell(2);
            XSSFCell cellIdLieuInscription = sheet.getRow(i + 1).getCell(3);

            ArrayList arrayList = new ArrayList();
            arrayList.add(cellId.getNumericCellValue());
            arrayList.add(cellIdLieuInscription.getStringCellValue());

            idEtu.put(idNomTest + "-" + idPrenomTest, arrayList);
        }


    }



    public void attributionIdentifiant(int nbLigneIdEtu, int nombreLigne, XSSFSheet sheetReponse){
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

            /*for (int j = 1; j < nbLigneIdEtu; j++) {

                XSSFCell cellIdNom = sheetId.getRow(j).getCell(0);
                //ArrayList<String> idNom = calcul.creationTableauEspace(cellIdNom);
                String idNomTest = cellIdNom.getStringCellValue().toUpperCase();

                XSSFCell cellIdPrenom = sheetId.getRow(j).getCell(1);
                //ArrayList<String> idPrenom = calcul.creationTableauEspace(cellIdPrenom);
                String idPrenomTest = cellIdPrenom.getStringCellValue().toUpperCase();

                XSSFCell cellId = sheetId.getRow(j).getCell(2);
                XSSFCell cellIdLieuInscription = sheetId.getRow(j).getCell(3);

                if (idNomTest.equals(reponseNomTest) && idPrenomTest.equals(reponsePrenomTest)) {
                    cellReponseNom.setCellValue(cellIdNom.getStringCellValue());
                    cellReponsePrenom.setCellValue(cellIdPrenom.getStringCellValue());
                    cellReponseId.setCellValue(cellIdLieuInscription.getNumericCellValue());
                    cellReponseLieuInscription.setCellValue(cellId.getStringCellValue());
                }
            }*/

            if(idEtu.containsKey(reponseNomTest + "-" + reponsePrenomTest)){
                cellReponseId.setCellValue(idEtu.get(reponseNomTest + "-" + reponsePrenomTest).get(0));
                cellReponseLieuInscription.setCellValue(cellId.getStringCellValue());
            }

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

            return true;
        }else if(notation.getIntegerTextFieldLigneReponse().getInt() == 0){
            FenetreAlert.erreur("Vous ne pouvez pas entrer les réponses à la ligne 0. \nVeuillez réessayer");
            return false;
        }else {
            FenetreAlert.erreur("La ligne à laquelle vous avez entré les bonnes réponses doit être supérieur au nombre d'étudiant. \nVeuillez réessayer");
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
            FenetreAlert.erreur("Fermer le fichier Excel des Réponses puis recommencer.");
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
            FenetreAlert.info("Le programme a correctement fonctionné !");
            notation.buttonQuitter();
        } catch (FileNotFoundException | NullPointerException n) {
            FenetreAlert.erreur("Fermer le fichier Excel des Identifiants puis recommencer.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
