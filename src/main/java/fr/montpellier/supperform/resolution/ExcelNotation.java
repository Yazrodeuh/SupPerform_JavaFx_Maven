package fr.montpellier.supperform.resolution;

import fr.montpellier.supperform.Affichage.FonctionAffichage;

public class ExcelNotation extends TableauExcel{
    public ExcelNotation(FonctionAffichage notation, double retrait) {
        super(notation, retrait);
        if(recuperationFichierReponse()){
            if(recuperationNombreLigne()){
                if (recuperationNombreColonne()){
                    if (recuperationFichierIdentifiant()){
                        if (attributionIdentifiant()){
                            if (calculNote()){
                                fermetureFichierReponse();
                                fermetureFichierIdentifiant();
                            }
                        }
                    }
                }
            }
        }
    }
}
