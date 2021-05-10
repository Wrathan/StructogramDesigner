package frontend.components.panels.editor.propertiespanel.parameterspanel.jumpstat;

import frontend.components.factorys.FxComponentFactory;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Panel, where the user can add return parameters.
 */
public class ReturnParametersPanel extends JumpStatParametersPanel {

    private final VBox valueVBox;
    private final Label expressionLabel;
    private final TextField expressionTextField;
    private final CheckBox checkPostCondCheckBox;

    /**
     * Constructor for ReturnParametersPanel.
     */
    public ReturnParametersPanel() {
        valueVBox = new VBox();

        expressionLabel = FxComponentFactory.generateLabel(valueVBox, "Return value:", "expressionLabel");
        expressionTextField = FxComponentFactory.generateTextField(valueVBox, "expressionTextField");

        getChildren().add(valueVBox);

        checkPostCondCheckBox = FxComponentFactory.generateCheckBox(this, "check postcondition", "checkPostCondCheckBox");
    }

    /**
     * Get return expression text field.
     * @return current return expression text field
     */
    public TextField getExpressionTextField() {
        return expressionTextField;
    }

    /**
     * Gets that is we need to check postconidion in this return.
     * @return current postcondition checking state
     */
    public CheckBox getCheckPostCondCheckBox() {
        return checkPostCondCheckBox;
    }
}
