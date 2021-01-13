package fr.montpellier.supperform.javaFX;

import fr.montpellier.supperform.Main;
import javafx.scene.control.Button;
import javafx.scene.Parent;

public class ButtonFX extends Button {

    public ButtonFX(String text, double width, double hight, double transX, double transY){
        setText(text);
        setPrefSize(width, hight);
        setTranslateX(transX);
        setTranslateY(transY);
    }

    public void eventScene(Parent parent, String title){
        this.setOnAction(actionEvent -> {
            Main.STAGE.setTitle(title);
            Main.STAGE.setScene(new SceneFX(parent));
        });

    }


}
