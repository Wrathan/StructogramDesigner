package frontend.components.panels.editor;

import frontend.GuiManager;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.panels.editor.propertiespanel.*;
import frontend.components.scenes.EditorScene;
import frontend.stages.EditorStage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Panel, where the user can choose, what kind of statement
 * want to insert, and he/she can give parameters to it.
 */
public class StatementPanel extends HBox {

    private final EditorStage window;
    private final EditorScene scene;
    private Button addVarOpButton, addLoopButton, addCondStatButton, addJumpStatButton, addFuncButton;
    private VBox statementTypeVBox;
    private StatementPropertiesPanel statementPropertiesPanel;

    /**
     * Constructor for StatementPanel.
     * @param frame current Stage
     * @param scene current Scene
     */
    public StatementPanel(EditorStage frame, EditorScene scene) {
        this.window = frame;
        this.scene = scene;

        setAlignment(Pos.CENTER);
        setPadding(new Insets(120, 0, 0, 30));

        initTypeButtonVBox();
        statementPropertiesPanel = new VarOperationPropertiesPanel();
        getChildren().add(statementPropertiesPanel);
    }

    /**
     * Initialize statement type buttons.
     */
    private void initTypeButtonVBox(){
        statementTypeVBox = new VBox();

        statementTypeVBox.setAlignment(Pos.TOP_CENTER);
        statementTypeVBox.setPadding(new Insets(0, 20, 40, 0));
        statementTypeVBox.setSpacing(20);

        addVarOpButton = FxComponentFactory.generateButton(statementTypeVBox, "Variable\noperation", "addVarOpButton", 100, 50);
        addLoopButton = FxComponentFactory.generateButton(statementTypeVBox, "Loop", "addLoopButton", 100, 50);
        addCondStatButton = FxComponentFactory.generateButton(statementTypeVBox, "Conditional\nstatement", "addCondStatButton", 100, 50);
        addJumpStatButton = FxComponentFactory.generateButton(statementTypeVBox, "Jump\nstatement", "addJumpStatButton", 100, 50);
        addFuncButton = FxComponentFactory.generateButton(statementTypeVBox, "Function", "addFuncButton", 100, 50);

        addVarOpButton.setOnAction(e -> initVarOperationProperties());
        addLoopButton.setOnAction(e -> initLoopProperties());
        addCondStatButton.setOnAction(e -> initCondStatementProperties());
        addJumpStatButton.setOnAction(e -> initJumpStatProperties());
        addFuncButton.setOnAction(e -> initFunctionProperties());

        getChildren().add(statementTypeVBox);
    }

    /**
     * Initialize variable operation properties panel.
     */
    private void initVarOperationProperties() {
        statementPropertiesPanel = new VarOperationPropertiesPanel();
        GuiManager.updatePane(this, 1, statementPropertiesPanel);
    }

    /**
     * Initialize loop properties panel.
     */
    private void initLoopProperties() {
        statementPropertiesPanel = new LoopPropertiesPanel();
        GuiManager.updatePane(this, 1, statementPropertiesPanel);
    }

    /**
     * Initialize conditional statement properties panel.
     */
    private void initCondStatementProperties() {
        statementPropertiesPanel = new CondStatPropertiesPanel();
        GuiManager.updatePane(this, 1, statementPropertiesPanel);
    }

    /**
     * Initialize jump statement properties panel.
     */
    private void initJumpStatProperties() {
        statementPropertiesPanel = new JumpStatPropertiesPanel();
        GuiManager.updatePane(this, 1, statementPropertiesPanel);
    }

    /**
     * Initialize function call properties panel.
     */
    private void initFunctionProperties(){
        statementPropertiesPanel = new FunctionPropertiesPanel();
        GuiManager.updatePane(this, 1, statementPropertiesPanel);
    }

    /**
     * Gets statement properties panel.
     * @return current statement properties panel
     */
    public StatementPropertiesPanel getStatementPropertiesPanel() {
        return statementPropertiesPanel;
    }
}
