package frontend.components.panels.editor.propertiespanel.parameterspanel.condstat;

import frontend.components.factorys.FxComponentFactory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Panel, where the user can add if parameters.
 */
public class IfParametersPanel extends CondStatParametersPanel {

    private final VBox conditionVBox;
    private final HBox conditionHeadHBox, ifOperationElementsHBox;
    private final Label conditionLabel;
    private final TextField conditionTextField;
    private final Button addElseIfButton;
    private final CheckBox elseBranchCheckBox;
    private final ImageView condInfoImageView;
    private final StackPane condInfoStackPane;

    /**
     * Constructor for IfParametersPanel.
     */
    public IfParametersPanel(){
        super("else if", "condition");
        conditionVBox = new VBox();
        conditionHeadHBox = new HBox();
        condInfoStackPane = new StackPane();
        condInfoImageView = new ImageView(new Image("/images/info_icon.png"));
        ifOperationElementsHBox = new HBox();

        Tooltip.install(condInfoImageView, new Tooltip(
                "Enabled components (converted to the right format in each programming language):\n" +
                "-logical expressions (AND, OR, NOT, TRUE, FALSE, ==, !=, >, >=, <, <=)\n" +
                "-numbers (0, 1, 2..)\n" +
                "-chars ('a', 'b', 'c'..)\n" +
                "-strings (\"some string\" ..)"
        ));

        condInfoStackPane.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(condInfoStackPane, Priority.ALWAYS);
        ifOperationElementsHBox.setSpacing(10);
        ifOperationElementsHBox.setAlignment(Pos.CENTER_LEFT);

        conditionLabel = FxComponentFactory.generateLabel(conditionHeadHBox, "Condition:", "conditionLabel");

        condInfoStackPane.getChildren().add(condInfoImageView);
        conditionHeadHBox.getChildren().add(condInfoStackPane);
        conditionVBox.getChildren().add(conditionHeadHBox);

        conditionTextField = FxComponentFactory.generateTextField(conditionVBox, "conditionTextField", 155, 20);
        conditionTextField.setMaxSize(155, 20);
        addElseIfButton = FxComponentFactory.generateButton(ifOperationElementsHBox, "+ elif", "addElseIfButton", 55, 20);
        elseBranchCheckBox = FxComponentFactory.generateCheckBox(ifOperationElementsHBox, "else branch", "elseBranchCheckBox");

        addElseIfButton.setOnAction(e -> addNewOptionalParameter());

        getChildren().addAll(conditionVBox, ifOperationElementsHBox);
    }

    /**
     * Gets else if condition text fields.
     * @return current else if condition text fields
     */
    public ArrayList<TextField> getConditionTextFieldArrayList(){
        ArrayList<TextField> conditionTextFieldArrayList = new ArrayList<>();

        conditionTextFieldArrayList.add(conditionTextField);

        if (optParamFlowPane != null){
            for (Node item : optParamFlowPane.getChildren()){
                conditionTextFieldArrayList.add((TextField) ((HBox) item).getChildren().get(0));
            }
        }

        return conditionTextFieldArrayList;
    }

    /**
     * Gets that is there else branch.
     * @return else branch state
     */
    public CheckBox getElseBranchCheckBox() {
        return elseBranchCheckBox;
    }
}
