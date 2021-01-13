package fr.montpellier.supperform.javaFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonFX extends Button {



    public ButtonFX(String text, double width, double hight, double transX, double transY){
        setText(text);
        setPrefSize(width, hight);
        setTranslateX(transX);
        setTranslateY(transY);
    }


}
