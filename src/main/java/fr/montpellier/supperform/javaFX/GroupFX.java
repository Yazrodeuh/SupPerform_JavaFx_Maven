package fr.montpellier.supperform.javaFX;

import javafx.scene.Group;
import javafx.scene.Node;

import java.io.File;

public class GroupFX extends Group {


    public GroupFX( double transX, double transY){
        setTranslateX(transX);
        setTranslateY(transY);
    }


    public void addChild(Node... nodes){
        this.getChildren().addAll(nodes);
    }

    public IntTextFieldFX getIntTextFieldFX(int index){
        return (IntTextFieldFX) this.getChildren().get(index);
    }

    public File getFile(int index){
        return new File(String.valueOf(this.getChildren().get(index)));
    }


}
