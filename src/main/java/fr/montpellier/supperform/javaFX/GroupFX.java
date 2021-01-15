package fr.montpellier.supperform.javaFX;

import javafx.scene.Group;
import javafx.scene.Node;

public class GroupFX extends Group {


    public GroupFX( double transX, double transY){

        setTranslateX(transX);
        setTranslateY(transY);

    }


    public void addChild(Node... nodes){
        this.getChildren().addAll(nodes);
    }





}
