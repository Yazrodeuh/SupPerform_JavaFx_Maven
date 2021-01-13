package fr.montpellier.supperform.Affichage;

import fr.montpellier.supperform.Main;
import fr.montpellier.supperform.resolution.ExcelNotation;

public class Notation extends FonctionAffichage {

    private final double retrait;

    public Notation(double retrait){
        super(10);
        this.retrait = retrait;
        this.getChildren().addAll(titreReponse(0), fichierReponse(1), nombreEtudiant(2), nombreQCM(3), ligneReponse(4), titreIdentifiant(5), fichierIdentifiant(6), ligneIdentifiant(7), button(8));
    }

    @Override
    public void buttonStart(){
        getStart().setOnAction(actionEvent -> new ExcelNotation(this, retrait));
    }



}
