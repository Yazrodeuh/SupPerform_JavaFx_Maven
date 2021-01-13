package fr.montpellier.supperform.javaFX;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class LabelFX extends Label {

    public LabelFX(String text, Font font, double transX, double transY){
        setText(text);
        setFont(font);

        if(transX != 0){
            setTranslateX(transX);
        }

        if(transY != 0){
            setTranslateY(transY);
        }
    }

}
