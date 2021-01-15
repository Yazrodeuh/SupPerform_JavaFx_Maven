package fr.montpellier.supperform.javaFX;

import fr.montpellier.supperform.Main;
import javafx.scene.control.Button;
import javafx.scene.Parent;

public class ButtonFX extends Button {

    public ButtonFX(String text, double width, double height, double transX, double transY){
        setText(text);
        setPrefSize(width, height);

        if(transX != 0) setTranslateX(transX);
        if(transY != 0) setTranslateY(transY);
    }

    public void eventScene(Parent parent, String title){
        this.setOnAction(actionEvent -> {
            Main.STAGE.setTitle(title);
            Main.STAGE.setScene(new SceneFX(parent));
        });

    }


}
