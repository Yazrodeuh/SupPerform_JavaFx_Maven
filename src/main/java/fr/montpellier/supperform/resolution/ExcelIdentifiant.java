package fr.montpellier.supperform.resolution;

import fr.montpellier.supperform.Affichage.FonctionAffichage;

public class ExcelIdentifiant extends TableauExcel{

    public ExcelIdentifiant(FonctionAffichage notation) {
        super(notation, 0);
        recuperationFichierReponse();
        recuperationNombreLigne();
        recuperationFichierIdentifiant();
        attributionIdentifiant();
        fermetureFichierReponse();
        fermetureFichierIdentifiant();
    }
}
