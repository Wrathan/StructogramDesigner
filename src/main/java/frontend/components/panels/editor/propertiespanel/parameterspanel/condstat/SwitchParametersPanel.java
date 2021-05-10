package frontend.components.panels.editor.propertiespanel.parameterspanel.condstat;

import frontend.components.factorys.FxComponentFactory;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;

/**
 * Panel, where the user can add switch parameters.
 */
public class SwitchParametersPanel extends CondStatParametersPanel {

    private final VBox switchExprVBox;
    private final HBox switchExprHeadHBox, caseOperationElemsHBox;
    private VBox caseExpressionsVBox;
    private final StackPane exprIntoStackPane;
    private FlowPane caseExpressionsFlowPane;
    private final Label switchExpressionLabel;
    private final TextField switchExprTextField;
    private final Button addCaseExprButton;
    private final ImageView exprInfoImageView;
    private final CheckBox defaultCaseCheckBox;

    /**
     * Constructor for SwitchParametersPanel.
     */
    public SwitchParametersPanel(){
        super("case", "expression");
        switchExprVBox = new VBox();
        caseOperationElemsHBox = new HBox();
        switchExprHeadHBox = new HBox();
        exprIntoStackPane = new StackPane();
        exprInfoImageView = new ImageView(new Image("/images/info_icon.png"));
        caseExpressionsFlowPane = new FlowPane(Orientation.VERTICAL);

        Tooltip.install(exprInfoImageView, new Tooltip(
                "Enabled components (converted to the right format in each programming language):\n" +
                   "-numbers (0, 1, 2..)\n" +
                   "-chars ('a', 'b', 'c'..)\n" +
                   "-strings (\"some string\" ..)"
        ));

        exprIntoStackPane.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(exprIntoStackPane, Priority.ALWAYS);
        caseOperationElemsHBox.setSpacing(10);
        caseOperationElemsHBox.setAlignment(Pos.CENTER_LEFT);
        caseExpressionsFlowPane.setHgap(10);

        switchExpressionLabel = FxComponentFactory.generateLabel(switchExprHeadHBox, "Expression:", "switchExpressionLabel");

        exprIntoStackPane.getChildren().add(exprInfoImageView);
        switchExprHeadHBox.getChildren().add(exprIntoStackPane);
        switchExprVBox.getChildren().add(switchExprHeadHBox);

        switchExprTextField = FxComponentFactory.generateTextField(switchExprVBox, "switchExprTextField", 155, 20);
        switchExprTextField.setMaxSize(155, 20);

        addCaseExprButton = FxComponentFactory.generateButton(caseOperationElemsHBox, "+ case", "addCaseExprButton", 55, 20);
        defaultCaseCheckBox = FxComponentFactory.generateCheckBox(caseOperationElemsHBox, "default case", "defaultCaseCheckBox");

        addCaseExprButton.setOnAction(e -> addNewOptionalParameter());

        getChildren().addAll(switchExprVBox, caseOperationElemsHBox);
    }

    /**
     * Gets case value text fields
     * @return current case value text fields
     */
    public TextField getSwitchExprTextField() {
        return switchExprTextField;
    }

    /**
     * Gets case value text fields
     * @return current case value text fields
     */
    public ArrayList<TextField> getCaseTextFieldArrayList(){
        ArrayList<TextField> caseTextFieldArrayList = new ArrayList<>();

        for (Node item : optParamFlowPane.getChildren()){
            caseTextFieldArrayList.add((TextField) ((HBox) item).getChildren().get(0));
        }
        
        return caseTextFieldArrayList;
    }

    /**
     * Gets that is there default case.
     * @return default case state
     */
    public CheckBox getDefaultCaseCheckBox() {
        return defaultCaseCheckBox;
    }
}
