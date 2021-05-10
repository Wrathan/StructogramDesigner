package frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents;

import backend.enums.VarType;
import frontend.components.factorys.FxComponentFactory;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Panel, where the user can add variable type.
 */
public class VarTypeVBox extends VBox {

    private final Label varTypeLabel;
    private final ChoiceBox varTypeChoiceBox;

    /**
     * Constructor for VarTypeVBox.
     */
    public VarTypeVBox() {
        varTypeLabel = FxComponentFactory.generateLabel(this, "Variable type:", "varTypeLabel");
        varTypeChoiceBox = FxComponentFactory.generateChoiceBox(this, "varTypeChoiceBox", FXCollections.observableArrayList(VarType.values()));
    }

    /**
     * Gets variable type choice box.
     * @return current variable type choice box
     */
    public ChoiceBox getVarTypeChoiceBox() {
        return varTypeChoiceBox;
    }
}
