package fr.montpellier.supperform.resolution;

import org.apache.poi.xssf.usermodel.XSSFCell;
import java.util.ArrayList;

public class Calcul {

    public String[] tableauCarractereReponse = {"A", "B", "C", "D", "E", "F", "G", "H", "I","J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "-"};
    public String[] tableauCarractere = {"A", "B", "C", "D", "E", "F"};
    public ArrayList<String> tableau;
    public double retrait;

    public Calcul(double retrait){
        this.retrait = retrait;
    }

    public ArrayList<String> stringVersList(String string){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            String value = string.substring(i, i+1);
            switch (value) {
                case "ê":
                case "é":
                case "ë":
                case "è":
                case "È":
                case "Ê":
                case "É":
                case "Ë" :
                    list.add("E");
                case "ï":
                case "î":
                case "Ï":
                case "Î":
                    list.add("I");
                case "ö":
                case "ô":
                case "Ö":
                case "Ô":
                    list.add("O");
                case "ç": list.add("C");
                default : list.add(value.toUpperCase());
            }
        }
        return list;
    }


    public ArrayList<String> copierTableau(ArrayList<String> tableauACopier){
        return new ArrayList<>(tableauACopier);
    }

    public ArrayList<String> creationTableauReponse(XSSFCell cell) {
        tableau = new ArrayList<>();
        String reponseQuestionEtudiant = cell.getStringCellValue();
        tableau = stringVersList(reponseQuestionEtudiant);
        return tableau;
    }

    /*public String creationStringReponseEtudiant(XSSFCell cell) {

        //tableau = new ArrayList<>();
        StringBuilder reponse = new StringBuilder();

        String reponseQuestionEtudiant = cell.getStringCellValue();

        for (int i = 0; i < reponseQuestionEtudiant.length(); i++) {
            if(reponseQuestionEtudiant.charAt(i) == 'A'){
                //tableau.add("A");
                reponse.append("A");
            }else if(reponseQuestionEtudiant.charAt(i) == 'B'){
                //tableau.add("B");
                reponse.append("B");
            }else if(reponseQuestionEtudiant.charAt(i) == 'C'){
                //tableau.add("C");
                reponse.append("C");
            }else if(reponseQuestionEtudiant.charAt(i) == 'D'){
                //tableau.add("D");
                reponse.append("D");
            }else if(reponseQuestionEtudiant.charAt(i) == 'E'){
                //tableau.add("E");
                reponse.append("E");
            }else if(reponseQuestionEtudiant.charAt(i) == 'F'){
                //tableau.add("F");
                reponse.append("F");
            }
        }
        return reponse.toString();
    }*/

    public ArrayList<String> creationTableau(XSSFCell cell) {

        tableau = new ArrayList<>();

        String reponseQuestionEtudiant = cell.getStringCellValue();

        for (int i = 0; i < reponseQuestionEtudiant.length(); i++) {
            if(reponseQuestionEtudiant.charAt(i) == 'A'){
                tableau.add("A");
            }else if(reponseQuestionEtudiant.charAt(i) == 'B'){
                tableau.add("B");
            }else if(reponseQuestionEtudiant.charAt(i) == 'C'){
                tableau.add("C");
            }else if(reponseQuestionEtudiant.charAt(i) == 'D'){
                tableau.add("D");
            }else if(reponseQuestionEtudiant.charAt(i) == 'E'){
                tableau.add("E");
            }else if(reponseQuestionEtudiant.charAt(i) == 'F'){
                tableau.add("F");
            }
        }

        /*ArrayList<String> tableauReponseQuestionEtudiant = stringVersList(reponseQuestionEtudiant);

        //System.out.println(tableauReponseQuestionEtudiant.toString());

        for (int i = 0; i < tableauReponseQuestionEtudiant.size(); i++) {
            for (String s : tableauCarractere) {

                if (tableauReponseQuestionEtudiant.get(i).equals(s)) {
                    if(i != tableauReponseQuestionEtudiant.size()-1 && tableauReponseQuestionEtudiant.get(i+1).equals("-")){
                        tableau.add(s);
                    }

                }
            }
        }*/
        return tableau;
    }

    /*public ArrayList<String> creationTableauEspace(XSSFCell cell) {

        tableau = new ArrayList<>();
        String id = cell.getStringCellValue();
        //System.out.println(id);
        //System.out.println(id.toUpperCase());
        ArrayList<String> tableauDesId = stringVersList(id);

        boolean position;

        for (int i = 0; i< tableauDesId.size(); i++) {
            position = false;
            for (String s : tableauCarractereReponse) {
                if (tableauDesId.get(i).equals(s)) {
                    position = true;
                    tableau.add(s);
                }
            }
            if (!position) {
                i = tableauDesId.size()-1;
            }
        }
        //System.out.println(Arrays.toString(tableau.toArray()));
        return tableau;
    }*/

    public double resultatEtudiantAuQCM(ArrayList<String> reponseQCM, ArrayList<String> reponseEtudiant){

        double resultat = 1;
        int positionI = 0, positionJ = 0;
        boolean aEteTrouve = false;

        ArrayList<String> copieReponseQCM = copierTableau(reponseQCM);
        ArrayList<String> copieReponseEtudiant = copierTableau(reponseEtudiant);
        System.out.println("Etudiant : " + copieReponseEtudiant.toString());
        System.out.println("QCM : " + copieReponseQCM.toString());

        if(copieReponseEtudiant.size() == 0){
            return 0;
        }else if(copieReponseQCM.size() == 1 && copieReponseEtudiant.size() == 1){
            if(copieReponseEtudiant.get(0).equals(copieReponseQCM.get(0))) {
                return 1;
            }else if((copieReponseQCM.get(0).equals("F") || copieReponseEtudiant.get(0).equals("F")) && !copieReponseEtudiant.get(0).equals(copieReponseQCM.get(0))){
                return 0;
            } else{
                //resultat -= 2 * retrait;
                return resultat - (2 * retrait);
            }
        }else{
            if(copieReponseEtudiant.contains("F")) return 0;

            /*
            for (int i = 0; i < copieReponseQCM.size(); i++) {
                if(aEteTrouve){
                    aEteTrouve = false;
                    i = 0;
                }
                for (int j = 0; j < copieReponseEtudiant.size(); j++) {
                    if(copieReponseQCM.get(i).equals(copieReponseEtudiant.get(j))) {
                        aEteTrouve = true;
                        positionI = i;
                        positionJ = j;
                    }
                }
                if(aEteTrouve){
                    copieReponseQCM.remove(positionI);
                    copieReponseEtudiant.remove(positionJ);
                    i = -1;
                }
            }
            */
            for (int i = 0; i < copieReponseQCM.size(); i++) {
                for (int j = 0; j < copieReponseEtudiant.size(); j++) {
                    if(copieReponseQCM.get(i).equals(copieReponseEtudiant.get(j))) {
                        copieReponseQCM.remove(i);
                        copieReponseEtudiant.remove(j);
                        i = 0;
                        j = -1;
                    }
                }
            }
        }

        return (resultat -= (copieReponseEtudiant.size() + copieReponseQCM.size()) * retrait) < 0.5 ? 0 : resultat;

        //resultat -= (copieReponseEtudiant.size() + copieReponseQCM.size()) * retrait;
        //if(resultat < 0.5)     return 0;
        //return resultat;
    }

}
