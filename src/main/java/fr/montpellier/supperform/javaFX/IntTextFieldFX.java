package fr.montpellier.supperform.javaFX;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class IntTextFieldFX extends TextField {

    public IntTextFieldFX(double transX, double transY){
        super();
        setTranslateX(transX);
        setTranslateY(transY);
        intValues();
    }

    public IntTextFieldFX(double width, double height, double transX, double transY) {
        this(transX, transY);
        if(width != 0 && height != 0) setPrefSize(width, height);

    }

    private void intValues(){

        this.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (isValid(getText())) {
                event.consume();
            }
        });

        this.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (isValid(newValue)) {
                setText(oldValue);
            }
        });

    }

    private boolean isValid(String value) {
        if (value.length() == 0 || value.equals("-")) {
            return false;
        }

        try {
            Integer.parseInt(value);
            return false;
        } catch (NumberFormatException ex) {
            return true;
        }
    }

    public int getInt() {
        try {
            return Integer.parseInt(getText());
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }
}
