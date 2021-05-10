package frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents;

import frontend.components.factorys.FxComponentFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Panel, where the user can add variable name.
 */
public class VarNameVBox extends VBox {

    private final Label varNameLabel;
    private final TextField varNameTextField;

    /**
     * Constructor for VarNameVBox.
     */
    public VarNameVBox() {
        varNameLabel = FxComponentFactory.generateLabel(this, "Name:", "varNameLabel");
        varNameTextField = FxComponentFactory.generateTextField(this, "varNameTextField");
        varNameTextField.setMaxWidth(120);
    }

    /**
     * Gets variable name text field.
     * @return current variable name text field
     */
    public TextField getVarNameTextField() {
        return varNameTextField;
    }
}
