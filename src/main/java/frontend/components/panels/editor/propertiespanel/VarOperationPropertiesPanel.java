package frontend.components.panels.editor.propertiespanel;

import frontend.GuiManager;
import frontend.components.panels.editor.propertiespanel.parameterspanel.varoperation.OperationParametersPanel;
import frontend.components.managers.SelectionManager;
import frontend.emuns.StatementType;
import frontend.components.factorys.FxComponentFactory;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Panel, where the user can add variable operation properties.
 */
public class VarOperationPropertiesPanel extends StatementPropertiesPanel {

    private final String[] OPERATION_TYPES = new String[]{"Definition", "Declaration", "Assignment"};
    private final HBox radioButtonHBox;
    private final VBox operationTypeVBox;
    private OperationParametersPanel parametersPanel;
    private final Label operationTypeLabel;
    private final ChoiceBox operationTypeChoiceBox;
    private final RadioButton simpleRadioButton, arrayRadioButton;
    private final ToggleGroup radioButtonToggleGroup;

    /**
     * Constructor for VarOperationPropertiesPanel.
     */
    public VarOperationPropertiesPanel(){
        operationTypeVBox = new VBox();
        radioButtonHBox = new HBox();
        radioButtonToggleGroup = new ToggleGroup();

        radioButtonHBox.setSpacing(5);

        operationTypeLabel = FxComponentFactory.generateLabel(operationTypeVBox, "Operation type:", "operationTypeLabel");
        operationTypeChoiceBox = FxComponentFactory.generateChoiceBox(operationTypeVBox, OPERATION_TYPES, OPERATION_TYPES[0], "operationTypeChoiceBox");

        simpleRadioButton = FxComponentFactory.generateRadioButton(radioButtonHBox, "Simple", "simpleRadioButton", radioButtonToggleGroup);
        arrayRadioButton = FxComponentFactory.generateRadioButton(radioButtonHBox, "Array", "arrayRadioButton", radioButtonToggleGroup);
        simpleRadioButton.setSelected(true);

        operationTypeChoiceBox.setOnAction(e -> initParameters());
        simpleRadioButton.setOnAction(e -> initParameters());
        arrayRadioButton.setOnAction(e -> initParameters());

        parametersPanel = new OperationParametersPanel(operationTypeChoiceBox, simpleRadioButton);

        getChildren().addAll(operationTypeVBox, radioButtonHBox, parametersPanel);

        SelectionManager.setStatementType(StatementType.VAR_OPERATION);
    }

    /**
     * Initialize parameters of variable.
     */
    private void initParameters() {
        parametersPanel = new OperationParametersPanel(operationTypeChoiceBox, simpleRadioButton);
        GuiManager.updatePane(this, 2, parametersPanel);
    }

    /**
     * Gets parameters panel.
     * @return current parameters panel
     */
    public OperationParametersPanel getParametersPanel() {
        return parametersPanel;
    }

    /**
     * Gets operation type choice box.
     * @return current operation type choice box
     */
    public ChoiceBox getOperationTypeChoiceBox() {
        return operationTypeChoiceBox;
    }

    /**
     * Gets radiobutton toggle group.
     * @return current radiobutton toggle group
     */
    public ToggleGroup getRadioButtonToggleGroup() {
        return radioButtonToggleGroup;
    }
}
