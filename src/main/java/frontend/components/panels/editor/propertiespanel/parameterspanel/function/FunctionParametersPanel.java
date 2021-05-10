package frontend.components.panels.editor.propertiespanel.parameterspanel.function;

import frontend.components.panels.editor.propertiespanel.parameterspanel.ParametersPanel;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.VarNameVBox;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.VarTypeVBox;
import javafx.scene.control.ChoiceBox;

/**
 * Panel, where the user can add function parameters.
 */
public class FunctionParametersPanel extends ParametersPanel {

    private VarNameVBox varNameVBox;
    private VarTypeVBox varTypeVBox;

    /**
     * Constructor for FunctionParametersPanel.
     * @param insertTypeChoiceBox current insertion type
     */
    public FunctionParametersPanel(ChoiceBox insertTypeChoiceBox) {

        if (insertTypeChoiceBox.getValue().equals("Assignment")){
            varNameVBox = new VarNameVBox();
            getChildren().add(varNameVBox);
        } else if (insertTypeChoiceBox.getValue().equals("Definition")){
            varNameVBox = new VarNameVBox();
            varTypeVBox = new VarTypeVBox();
            getChildren().addAll(varNameVBox, varTypeVBox);
        }
    }

    /**
     * Gets variable name panel
     * @return current variable name panel
     */
    public VarNameVBox getVarNameVBox() {
        return varNameVBox;
    }

    /**
     * Gets variable type panel
     * @return current variable type panel
     */
    public VarTypeVBox getVarTypeVBox() {
        return varTypeVBox;
    }
}
