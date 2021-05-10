package frontend.components.panels.editor.propertiespanel.parameterspanel.condstat;

import frontend.GuiManager;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.panels.editor.propertiespanel.parameterspanel.ParametersPanel;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Panel, where the user can add conditional statement parameters.
 */
public abstract class CondStatParametersPanel extends ParametersPanel {

    protected VBox optParamVBox;
    protected FlowPane optParamFlowPane;
    protected String optionalParameterName, optionalParameterType;

    /**
     * Constructor for CondStatParametersPanel.
     * @param optionalParameterName name of optional parameters
     * @param optionalParameterType type of optional parameters
     */
    protected CondStatParametersPanel(String optionalParameterName, String optionalParameterType) {
        this.optionalParameterName = optionalParameterName;
        this.optionalParameterType = optionalParameterType;
    }

    /**
     * Adds new optional fields.
     */
    protected void addNewOptionalParameter() {
        if (optParamVBox == null){
            optParamVBox = new VBox();
            optParamFlowPane = new FlowPane(Orientation.VERTICAL);
            Label optionalParametersLabel = FxComponentFactory.generateLabel(optParamVBox, optionalParameterName.substring(0, 1).toUpperCase() + optionalParameterName.substring(1) + " " + optionalParameterType + "s:", "optionalParametersLabel");

            optParamVBox.setSpacing(5);
            optParamFlowPane.setHgap(10);

            optParamVBox.getChildren().add(optParamFlowPane);
            getChildren().add(optParamVBox);
        }
        HBox optionalParameterHBox = new HBox();
        TextField optParamNameTextField;
        Button removeOptParamButton;

        optionalParameterHBox.setSpacing(5);
        optionalParameterHBox.setPadding(new Insets(0, 0, 5, 0));

        optParamNameTextField = FxComponentFactory.generateTextField(optionalParameterHBox, "optParamNameTextField" + (optParamFlowPane.getChildren().size() + 1),  110, 20);
        optParamNameTextField.setPromptText((optParamFlowPane.getChildren().size() + 1) + ". " + optionalParameterType);
        removeOptParamButton = FxComponentFactory.generateButton(optionalParameterHBox, "-", "removeOptParamButton" + (optParamFlowPane.getChildren().size() + 1), 20, 20);

        removeOptParamButton.setOnAction(e -> removeOptionalParameter(optParamFlowPane, optionalParameterHBox));

        optParamFlowPane.getChildren().add(optionalParameterHBox);
    }

    /**
     * Remove an optional parameter.
     * @param optionalParametersFlowPane panel of optional panels
     * @param optionalParameterHBox optional panel what will be removed
     */
    private void removeOptionalParameter(FlowPane optionalParametersFlowPane, HBox optionalParameterHBox) {
        int optIndex = optionalParametersFlowPane.getChildren().indexOf(optionalParameterHBox);
        int optFlowPaneSize = optionalParametersFlowPane.getChildren().size();

        optionalParametersFlowPane.getChildren().remove(optionalParameterHBox);

        for (int i = optIndex; i < optFlowPaneSize - 1; i++){
            ((TextField) ((HBox) optionalParametersFlowPane.getChildren().get(i)).getChildren().get(0)).setPromptText((i + 1) + ". " + optionalParameterType);
            ((HBox) optionalParametersFlowPane.getChildren().get(i)).getChildren().get(0).setId("optParamNameTextField" + (i + 1));
            ((HBox) optionalParametersFlowPane.getChildren().get(i)).getChildren().get(1).setId("removeOptParamButton" + (i + 1));
        }

        if (optionalParametersFlowPane.getChildren().size() == 0){
            GuiManager.removePane(this, 2);
            optParamVBox = null;
        }
    }
}
