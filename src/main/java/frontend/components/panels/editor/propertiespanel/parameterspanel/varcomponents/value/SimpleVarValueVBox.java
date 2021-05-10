package frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.value;

import frontend.components.factorys.FxComponentFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Panel, where the user can add values to simple variables.
 */
public class SimpleVarValueVBox extends VarValueVBox {

    private final VBox valueVBox;
    private final Label varValueLabel;
    private final TextField varValueTextField;

    /**
     * Constructor for SimpleVarValueVBox.
     */
    public SimpleVarValueVBox(){
        valueVBox = new VBox();

        varValueLabel = FxComponentFactory.generateLabel(valueVBox, "Value:", "varValueLabel");
        varValueTextField = FxComponentFactory.generateTextField(valueVBox, "varValueTextField");

        getChildren().add(valueVBox);
    }

    /**
     * Gets variable value text field.
     * @return current variable value text field
     */
    public TextField getVarValueTextField() {
        return varValueTextField;
    }
}
