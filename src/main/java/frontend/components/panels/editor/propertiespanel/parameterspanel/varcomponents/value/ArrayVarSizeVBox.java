package frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.value;

import frontend.components.factorys.FxComponentFactory;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Panel, where the user can add size to array.
 */
public class ArrayVarSizeVBox extends VarValueVBox{

    protected final HBox sizeHBox;
    protected final Label sizeLabel;
    protected final TextField sizeTextField;

    /**
     * COnstructor for ArrayVarSizeVBox.
     */
    public ArrayVarSizeVBox() {
        sizeHBox = new HBox();

        sizeHBox.setSpacing(5);
        sizeHBox.setAlignment(Pos.CENTER_LEFT);

        sizeLabel = FxComponentFactory.generateLabel(this, "Size:", "sizeLabel");
        sizeTextField = FxComponentFactory.generateTextField(sizeHBox, "sizeTextField");

        getChildren().add(sizeHBox);
    }

    /**
     * Gets array size text field.
     * @return current array size text field
     */
    public TextField getSizeTextField() {
        return sizeTextField;
    }
}
