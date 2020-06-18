package fr.montpellier.supperform.Affichage;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


public class IntegerTextField extends TextField {


    public IntegerTextField() {
        super();

        addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (isValid(getText())) {
                event.consume();
            }
        });

        textProperty().addListener((observableValue, oldValue, newValue) -> {
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
