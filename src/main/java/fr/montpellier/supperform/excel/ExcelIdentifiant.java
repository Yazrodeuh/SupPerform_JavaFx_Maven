package fr.montpellier.supperform.excel;

import fr.montpellier.supperform.affichage.FonctionAffichage;

public class ExcelIdentifiant extends TableauExcel {


    public ExcelIdentifiant(FonctionAffichage notation) {
        super(notation, 0);
        recuperationFichierReponse();
        verifValueInt();
        recuperationFichierIdentifiant();
        attributionIdentifiant();
        fermetureFichierReponse();
        fermetureFichierIdentifiant();
    }
}
