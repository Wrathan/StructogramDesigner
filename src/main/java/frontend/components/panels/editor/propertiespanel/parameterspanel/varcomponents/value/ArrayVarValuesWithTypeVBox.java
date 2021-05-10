package frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.value;

import frontend.GuiManager;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.VarTypeVBox;

/**
 * Panel, where the user can add values to array variables with array type.
 */
public class ArrayVarValuesWithTypeVBox extends ArrayVarValuesVBox {

    private VarTypeVBox varTypeVBox;

    /**
     * Constructor for ArrayVarValuesWithTypeVBox.
     */
    public ArrayVarValuesWithTypeVBox() {
        varTypeVBox = new VarTypeVBox();
        getChildren().add(0, varTypeVBox);
    }

    /**
     *Overriding delArrayPanel(), what delete all array item panels.
     */
    @Override
    protected void delArrayPanel() {
        if (getChildren().size() == 4){
            GuiManager.removePane(this, 3);
            initArrayVBox = null;
        }
    }

    /**
     * Gets array type panel.
     * @return current array type panel
     */
    public VarTypeVBox getVarTypeVBox() {
        return varTypeVBox;
    }
}
