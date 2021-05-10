package frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.value;

import frontend.components.factorys.FxComponentFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Panel, where the user can add values to array variables.
 */
public class ArrayVarValueVBox extends SimpleVarValueVBox {

    private final VBox itemIndexVBox;
    private final Label itemIndexLabel;
    private final TextField itemIndexTextField;

    /**
     * Constructor for ArrayVarValueVBox.
     */
    public ArrayVarValueVBox() {
        setSpacing(10);

        itemIndexVBox = new VBox();
        itemIndexLabel = FxComponentFactory.generateLabel(itemIndexVBox, "Array index:", "itemIndexLabel");
        itemIndexTextField = FxComponentFactory.generateTextField(itemIndexVBox, "itemIndexTextField");
        itemIndexTextField.setMaxWidth(60);

        getChildren().add(itemIndexVBox);
    }

    /**
     * Gets array item index text field.
     * @return current array item index text field
     */
    public TextField getItemIndexTextField() {
        return itemIndexTextField;
    }
}
