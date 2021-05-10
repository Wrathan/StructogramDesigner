package frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents;

import frontend.GuiManager;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.value.ArrayVarValueVBox;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.value.ArrayVarValuesWithTypeVBox;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.value.VarValueVBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Panel, where the user can assign values to array.
 */
public class ArrayAssignmentVBox extends VarValueVBox {

    private final VBox assignmentTypeVBox;
    private final HBox assignmentTypeHBox;
    private final Label assignmentTypeLabel;
    private final ToggleGroup arrayAssignmentTypeToggleGroup;
    private final RadioButton allItemRadioButton, oneItemRadioButton;
    private VarValueVBox innerVarValueVBox;

    /**
     * Constructor for ArrayAssignmentVBox.
     */
    public ArrayAssignmentVBox() {
        setSpacing(10);

        assignmentTypeVBox = new VBox();
        assignmentTypeLabel = FxComponentFactory.generateLabel(assignmentTypeVBox, "Assingment type:", "assignmentTypeLabel");

        assignmentTypeHBox = new HBox();
        assignmentTypeHBox.setSpacing(5);
        arrayAssignmentTypeToggleGroup = new ToggleGroup();
        allItemRadioButton = FxComponentFactory.generateRadioButton(assignmentTypeHBox, "Array", "allItemRadioButton", arrayAssignmentTypeToggleGroup);
        oneItemRadioButton = FxComponentFactory.generateRadioButton(assignmentTypeHBox, "One item", "oneItemRadioButton", arrayAssignmentTypeToggleGroup);
        allItemRadioButton.setSelected(true);


        innerVarValueVBox = new ArrayVarValuesWithTypeVBox();

        assignmentTypeVBox.getChildren().add(assignmentTypeHBox);
        getChildren().addAll(assignmentTypeVBox, innerVarValueVBox);

        allItemRadioButton.setOnAction(e -> initAllItemVBox());
        oneItemRadioButton.setOnAction(e -> initOneItemVBox());
    }

    /**
     * Initialize panels for all items.
     */
    private void initAllItemVBox() {
        innerVarValueVBox = new ArrayVarValuesWithTypeVBox();
        GuiManager.updatePane(this, 1, innerVarValueVBox);
    }

    /**
     * Initialize panel for one item.
     */
    private void initOneItemVBox() {
        innerVarValueVBox = new ArrayVarValueVBox();
        GuiManager.updatePane(this, 1, innerVarValueVBox);
    }

    /**
     * Gets array assignment variable value panel.
     * @return current array assignment variable value panel
     */
    public VarValueVBox getInnerVarValueVBox() {
        return innerVarValueVBox;
    }

    /**
     * Gets array assignment type toggle group.
     * @return current array assignment type toggle group
     */
    public ToggleGroup getArrayAssignmentTypeToggleGroup() {
        return arrayAssignmentTypeToggleGroup;
    }
}
