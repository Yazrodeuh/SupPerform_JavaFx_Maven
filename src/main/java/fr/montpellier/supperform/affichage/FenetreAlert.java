package fr.montpellier.supperform.affichage;

import javafx.scene.control.Alert;

public class FenetreAlert {

    public static void erreur(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static void info(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static void support(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Pour tout problème ou questions, vous pouvez me contacter à l'adresse mail suivante : \n\nmax.poujol21@gmail.com \n");
        alert.showAndWait();
    }




}
