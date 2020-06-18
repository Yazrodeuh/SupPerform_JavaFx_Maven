package fr.montpellier.supperform.Affichage;

import fr.montpellier.supperform.Main;
import fr.montpellier.supperform.resolution.ExcelIdentifiant;

public class Identifiant extends FonctionAffichage {

    public Identifiant(Main main) {
        super(main, 7);
        this.getChildren().addAll(titreReponse(0), fichierReponse(1), nombreEtudiant(2), titreIdentifiant(3), fichierIdentifiant(4), ligneIdentifiant(5), button(6));
    }

    @Override
    public void buttonStart(){
        getStart().setOnAction(actionEvent -> new ExcelIdentifiant(this));
    }

}
