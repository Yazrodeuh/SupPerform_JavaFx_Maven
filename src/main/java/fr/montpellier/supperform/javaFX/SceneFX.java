package fr.montpellier.supperform.javaFX;

import fr.montpellier.supperform.Main;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class SceneFX extends Scene {

    public SceneFX(Parent parent) {
        super(new StackPane(parent), Main.WIDTH, Main.HEIGHT);
        //this.setFill(Color.YELLOW);
        this.getStylesheets().add("/Button.css");
    }

}
