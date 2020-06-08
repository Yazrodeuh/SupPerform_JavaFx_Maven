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
                case "Ë":
                    list.add("E");
                    break;
                case "ï":
                case "î":
                case "Ï":
                case "Î":
                    list.add("I");
                    break;
                case "ö":
                case "ô":
                case "Ö":
                case "Ô":
                    list.add("O");
                    break;
                case "ç":
                    list.add("C");
                    break;
                default:
                    list.add(value.toUpperCase());
                    break;
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

    public ArrayList<String> creationTableau(XSSFCell cell) {

        tableau = new ArrayList<>();

        String reponseQuestionEtudiant = cell.getStringCellValue();
        ArrayList<String> tableauReponseQuestionEtudiant = stringVersList(reponseQuestionEtudiant);

        //System.out.println(tableauReponseQuestionEtudiant.toString());

        String tireDuSix = "-";

        for (int i = 0; i < tableauReponseQuestionEtudiant.size(); i++) {
            for (String s : tableauCarractere) {

                if (tableauReponseQuestionEtudiant.get(i).equals(s)) {
                    if(i != tableauReponseQuestionEtudiant.size()-1 && tableauReponseQuestionEtudiant.get(i+1).equals(tireDuSix)){
                        tableau.add(s);
                    }

                }
            }
        }
        return tableau;
    }

    public ArrayList<String> creationTableauEspace(XSSFCell cell) {

        tableau = new ArrayList<>();
        String id = cell.getStringCellValue();
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
        return tableau;
    }

    public double resultatEtudiantAuQCM(ArrayList<String> reponseQCM, ArrayList<String> reponseEtudiant){
        double resultat = 1;
        int positionI = 0, positionJ =0;
        boolean aEteTrouve = false;

        ArrayList<String> copieReponseQCM = copierTableau(reponseQCM);
        ArrayList<String> copieReponseEtudiant = copierTableau(reponseEtudiant);
        System.out.println("Etudiant : " + copieReponseEtudiant.toString());
        System.out.println("QCM : " + copieReponseQCM.toString());

        if(copieReponseEtudiant.size() == 0){
            System.out.println("1");
            return 0;
        }else if(copieReponseQCM.size() == 1 && copieReponseEtudiant.size() == 1){
            System.out.println("2");
            if(copieReponseQCM.get(0).equals("F") && copieReponseEtudiant.get(0).equals(copieReponseQCM.get(0))) {
                System.out.println("3");
                return resultat;
            }else if(!copieReponseQCM.get(0).equals("F") && copieReponseEtudiant.get(0).equals(copieReponseQCM.get(0))){
                System.out.println("4");
                return resultat;
            }else if(copieReponseQCM.get(0).equals("F") && !copieReponseEtudiant.get(0).equals(copieReponseQCM.get(0))){
                return 0;
            } else{
                System.out.println("5");
                return 0.5;
            }
        }else{
            for (String s : copieReponseEtudiant) {
                if (s.equals("F")) {
                    return 0;
                }
            }
            for (int i = 0; i < copieReponseQCM.size(); i++) {
                if(aEteTrouve){
                    aEteTrouve = false;
                    i = 0;
                }
                for (int j = 0; j < copieReponseEtudiant.size(); j++) {
                    if (copieReponseQCM.get(i).contentEquals(copieReponseEtudiant.get(j))) {
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
        }
        resultat -= (copieReponseEtudiant.size()+copieReponseQCM.size()) * retrait;
        if(resultat < 0.5){     resultat = 0; }
        return resultat;
    }

}
