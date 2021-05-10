package frontend.components.panels.editor.propertiespanel.parameterspanel.loop;

import frontend.components.factorys.FxComponentFactory;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * Panel, where the user can add while loop parameters.
 */
public class WhileParametersPanel extends LoopParametersPanel {

    private final VBox conditionVBox;
    private final ToggleGroup radioButtonToggleGroup;
    private final Label conditionLabel;
    private final TextField conditionTextField;
    private final RadioButton whileRadioButton, doWhileRadioButton;

    /**
     * Constructor for WhileParametersPanel.
     */
    public WhileParametersPanel(){
        conditionVBox = new VBox();
        radioButtonToggleGroup = new ToggleGroup();

        conditionLabel = FxComponentFactory.generateLabel(conditionVBox, "Condition:", "conditionLabel");
        conditionTextField = FxComponentFactory.generateTextField(conditionVBox, "conditionTextField", 140, 20);

        getChildren().add(conditionVBox);

        whileRadioButton = FxComponentFactory.generateRadioButton(this, "while", "whileRadioButton", radioButtonToggleGroup);
        doWhileRadioButton = FxComponentFactory.generateRadioButton(this, "do..while", "doWhileRadioButton", radioButtonToggleGroup);
    }

    /**
     * Gets loop condition text field.
     * @return current loop condition text field
     */
    public TextField getConditionTextField() {
        return conditionTextField;
    }

    /**
     * Gets loop subtype radio button.
     * @return current loop subtype radio button
     */
    public ToggleGroup getRadioButtonToggleGroup() {
        return radioButtonToggleGroup;
    }
}
