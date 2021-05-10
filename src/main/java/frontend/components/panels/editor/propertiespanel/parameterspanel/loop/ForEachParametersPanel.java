package frontend.components.panels.editor.propertiespanel.parameterspanel.loop;

import frontend.components.factorys.FxComponentFactory;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.VarTypeVBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Panel, where the user can add for-each loop parameters.
 */
public class ForEachParametersPanel extends LoopParametersPanel {

    private final VarTypeVBox itemTypeVBox;
    private final VBox actualItemVBox, arrayVarVBox;
    private final Label actualItemLabel, arrayVarLabel;
    private final TextField actualItemTextField, arrayVarTextField;

    /**
     * Constructor of ForEachParametersPanel.
     */
    public ForEachParametersPanel(){
        itemTypeVBox = new VarTypeVBox();
        actualItemVBox = new VBox();
        arrayVarVBox = new VBox();

        actualItemLabel = FxComponentFactory.generateLabel(actualItemVBox,  "Item name:", "actualItemLabel");
        actualItemTextField = FxComponentFactory.generateTextField(actualItemVBox, "actualItemTextField");
        arrayVarLabel = FxComponentFactory.generateLabel(arrayVarVBox, "Array name:", "arrayVarLabel");
        arrayVarTextField = FxComponentFactory.generateTextField(arrayVarVBox, "arrayVarTextField");

        getChildren().addAll(itemTypeVBox, actualItemVBox, arrayVarVBox);
    }

    /**
     * Gets array element type variable pane.
     * @return current array element type variable pane
     */
    public VarTypeVBox getItemTypeVBox() {
        return itemTypeVBox;
    }

    /**
     * Gets array element variable text field.
     * @return current array element variable pane
     */
    public TextField getActualItemTextField() {
        return actualItemTextField;
    }

    /**
     * Gets array variable text field.
     * @return current array element variable text field
     */
    public TextField getArrayVarTextField() {
        return arrayVarTextField;
    }
}
