package frontend.components.panels.editor.propertiespanel.parameterspanel.varoperation;

import frontend.components.panels.editor.propertiespanel.parameterspanel.ParametersPanel;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.ArrayAssignmentVBox;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.VarNameVBox;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.VarTypeVBox;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.value.ArrayVarSizeVBox;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.value.ArrayVarValuesVBox;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.value.SimpleVarValueVBox;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.value.VarValueVBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;

/**
 * Panel, where the user can add parameters to variables.
 * Modifying dynamically, depending on what kind of variable is,
 * the user want to insert.
 */
public class OperationParametersPanel extends ParametersPanel {

    VarNameVBox varNameVBox;
    VarTypeVBox varTypeVBox;
    VarValueVBox varValueVBox;

    /**
     * Constructor of OperationParametersPanel.
     * @param operationTypeChoiceBox choice box of operation type
     * @param simpleRadioButton simple variable radio button
     */
    public OperationParametersPanel(ChoiceBox operationTypeChoiceBox, RadioButton simpleRadioButton) {
        setSpacing(10);
        initElements(operationTypeChoiceBox, simpleRadioButton);
    }

    /**
     * Initialize elements of the pane.
     * @param operationTypeChoiceBox choice box of operation type
     * @param simpleRadioButton simple variable radio button
     */
    private void initElements(ChoiceBox operationTypeChoiceBox, RadioButton simpleRadioButton) {
        varNameVBox =  new VarNameVBox();
        if (operationTypeChoiceBox.getValue().equals("Definition")) {
            varTypeVBox = new VarTypeVBox();
            getChildren().addAll(varTypeVBox, varNameVBox);

        } else if (operationTypeChoiceBox.getValue().equals("Declaration")) {
            varTypeVBox = new VarTypeVBox();
            getChildren().addAll(varTypeVBox, varNameVBox);

        } else if (operationTypeChoiceBox.getValue().equals("Assignment")) {
            getChildren().add(varNameVBox);
        }

        if (simpleRadioButton.isSelected()) {
            if (operationTypeChoiceBox.getValue().equals("Definition") || operationTypeChoiceBox.getValue().equals("Assignment")) {
                varValueVBox = new SimpleVarValueVBox();
                getChildren().add(varValueVBox);
            }
        } else {
            if (operationTypeChoiceBox.getValue().equals("Definition")) {
                varValueVBox = new ArrayVarValuesVBox();
                getChildren().add(varValueVBox);
            } else if (operationTypeChoiceBox.getValue().equals("Declaration")) {
                varValueVBox = new ArrayVarSizeVBox();
                getChildren().add(varValueVBox);
            } else if (operationTypeChoiceBox.getValue().equals("Assignment")) {
                varValueVBox = new ArrayAssignmentVBox();
                getChildren().add(varValueVBox);
            }
        }
    }

    /**
     * Gets variable name panel.
     * @return current variable name panel
     */
    public VarNameVBox getVarNameVBox() {
        return varNameVBox;
    }

    /**
     * Gets variable type panel.
     * @return current variable type panel
     */
    public VarTypeVBox getVarTypeVBox() {
        return varTypeVBox;
    }

    /**
     * Gets variable value panel.
     * @return current variable value panel
     */
    public VarValueVBox getVarValueVBox() {
        return varValueVBox;
    }
}
