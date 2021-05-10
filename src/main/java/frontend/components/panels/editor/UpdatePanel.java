package frontend.components.panels.editor;

import frontend.GuiManager;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.factorys.StructogramFactory;
import frontend.components.managers.QueryManager;
import frontend.components.managers.SelectionManager;
import frontend.components.scenes.EditorScene;
import frontend.stages.EditorStage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Panel, where the user can do insertion operations, and show generation stage.
 */
public class UpdatePanel extends HBox{

    private final String[] INSERTION_TYPES = new String[]{"after", "before", "instead of"};
    private final EditorStage window;
    private final EditorScene scene;
    private HBox insertHBox;
    private ChoiceBox insertTypeChoiceBox;
    private Button insertButton, removeButton, modifyParamsButton,generateButton;
    private Label insertLabel;

    /**
     * Constructor for UpdatePanel.
     * @param frame current Stage
     * @param scene current Scene
     */
    public UpdatePanel(EditorStage frame, EditorScene scene) {
        this.window = frame;
        this.scene = scene;

        setAlignment(Pos.BOTTOM_CENTER);
        setPadding(new Insets(20, 0, 20, 0));
        setSpacing(40);

        initComponents();
    }

    /**
     * Initialize panel components.
     */
    private void initComponents() {
        initInsertionComponents();

        removeButton = FxComponentFactory.generateButton(this, "Remove", "removeButton");
        modifyParamsButton = FxComponentFactory.generateButton(this, "Change properties", "modifyParamsButton", 120, 20);
        generateButton = FxComponentFactory.generateButton(this, "Generate code", "generateButton");

        removeButton.setOnAction(e -> removeStatement());
        modifyParamsButton.setOnAction(e -> openModifyStructogramProperties());
        generateButton.setOnAction(e -> generateCode());
    }

    /**
     * Initialize insertion components.
     */
    private void initInsertionComponents(){
        insertHBox = new HBox();

        insertHBox.setSpacing(10);
        insertHBox.setAlignment(Pos.CENTER);

        insertLabel = FxComponentFactory.generateLabel(insertHBox, "Position:", "insertionLabel");
        insertTypeChoiceBox = FxComponentFactory.generateChoiceBox(insertHBox, INSERTION_TYPES, INSERTION_TYPES[0], "insertTypeChoiceBox");
        insertButton = FxComponentFactory.generateButton(insertHBox, "Insert", "insertButton");

        getChildren().add(insertHBox);

        insertButton.setOnAction(e -> insertStatement());
    }

    /**
     * Insert statement to structogram.
     */
    private void insertStatement() {
        if (QueryManager.getStructogramWrapperVBox().getChildren().size() > 0 && SelectionManager.getSelectedStatement() == null && SelectionManager.getSelectedList() == null) {
            GuiManager.openErrorStage("There is no selected element.");
        } else if (QueryManager.getStructogramWrapperVBox().getChildren().size() > 0 && insertTypeChoiceBox.getValue() == null){
            GuiManager.openErrorStage("There is no selected position.");
        } else {
            String position = insertTypeChoiceBox.getValue().toString();

            try{
                switch (SelectionManager.getStatementType()) {
                    case VAR_OPERATION:
                        if (QueryManager.getVarOperationSubType().equals("Simple")) {
                            if (QueryManager.getVarOperationType().equals("Definition")) {
                                StructogramFactory.generateSimpleDefinition(position, QueryManager.getVarName(), QueryManager.getVarType(), QueryManager.getSimpleVarValue());

                            } else if (QueryManager.getVarOperationType().equals("Declaration")) {
                                StructogramFactory.generateSimpleDeclaration(position, QueryManager.getVarName(), QueryManager.getVarType());

                            } else if (QueryManager.getVarOperationType().equals("Assignment")) {
                                StructogramFactory.generateSimpleAssignment(position, QueryManager.getVarName(), QueryManager.getSimpleVarValue());
                            }
                        } else if (QueryManager.getVarOperationSubType().equals("Array")) {
                            if (QueryManager.getVarOperationType().equals("Definition")) {
                                StructogramFactory.generateArrayDefinition(position, QueryManager.getVarName(), QueryManager.getVarType(), QueryManager.getStringsFromTextFields(QueryManager.getDefArrayItemTextFields()));

                            } else if (QueryManager.getVarOperationType().equals("Declaration")) {
                                StructogramFactory.generateArrayDeclarationWithSize(position, QueryManager.getVarName(), QueryManager.getVarType(), QueryManager.getArrayVarSize());

                            } else if (QueryManager.getVarOperationType().equals("Assignment")) {
                                if (QueryManager.getArrayAssignmentType().equals("Array")){
                                    StructogramFactory.generateArrayAssignment(position, QueryManager.getVarName(), QueryManager.getVarType(), QueryManager.getStringsFromTextFields(QueryManager.getAssignArrayItemTextFields()));
                                } else if (QueryManager.getArrayAssignmentType().equals("One item")){
                                    StructogramFactory.generateArrayItemAssignment(position, QueryManager.getArrayItemIndex(), QueryManager.getVarName(), QueryManager.getArraySimpleVarValue());
                                }
                            }
                        }
                        break;
                    case LOOP:
                        if (QueryManager.getLoopType().equals("for")){
                            StructogramFactory.generateForLoop(position, QueryManager.getForLoopVarName(), QueryManager.getForLoopStart(), QueryManager.getForLoopEnd());
                        }
                        else if (QueryManager.getLoopType().equals("for-each")){
                            StructogramFactory.generateForEachLoop(position, QueryManager.getForEachLoopVarType(), QueryManager.getForEachLoopItemName(), QueryManager.getForEachLoopArrayVarName());
                        }
                        else if (QueryManager.getLoopType().equals("while")){
                            if (QueryManager.getWhileLoopType().equals("while")){
                                StructogramFactory.generateWhileLoop(position, QueryManager.getWhileLoopCondition());
                            }
                            else if (QueryManager.getWhileLoopType().equals("do..while")) {
                                StructogramFactory.generateDoWhileLoop(position, QueryManager.getWhileLoopCondition());
                            }
                        }
                        break;
                    case COND_STATEMENT:
                        if (QueryManager.getCondStatType().equals("if")){
                            StructogramFactory.generateIf(position, QueryManager.getStringsFromTextFields(QueryManager.getIfConditionTextFields()), QueryManager.getElseCheckBoxStatus());
                        }
                        else if (QueryManager.getCondStatType().equals("switch")){
                            StructogramFactory.generateSwitchCase(position, QueryManager.getSwitchExpression(), QueryManager.getStringsFromTextFields(QueryManager.getCaseExpressionTextFields()), QueryManager.getDefaultCaseCheckBoxStatus());
                        }
                        break;
                    case JUMP_STATEMENT:
                        if (QueryManager.getJumpStatementType().equals("return")){
                            StructogramFactory.generateReturn(position, QueryManager.getReturnExpression(), QueryManager.getCheckPostconditionCheckBox());
                        }
                        else if (QueryManager.getJumpStatementType().equals("break")){
                            StructogramFactory.generateBreak(position);
                        }
                        else if(QueryManager.getJumpStatementType().equals("continue")){
                            StructogramFactory.generateContinue(position);
                        }
                        break;
                    case FUNCTION:
                        if (QueryManager.getFunctionInsertType().equals("Simple call")){
                            StructogramFactory.insertFunctionCall(position, QueryManager.getSelectedFunction(), QueryManager.getFunctionParameterList());
                        } else if (QueryManager.getFunctionInsertType().equals("Assignment")){
                            StructogramFactory.insertFunctionAssignment(position, QueryManager.getSelectedFunction(), QueryManager.getFunctionParameterList(), QueryManager.getFunctionVarName());
                        } else if (QueryManager.getFunctionInsertType().equals("Definition")) {
                            StructogramFactory.insertFunctionDefinition(position, QueryManager.getSelectedFunction(), QueryManager.getFunctionParameterList(), QueryManager.getFunctionVarName(), QueryManager.getFunctionVarType());
                        }
                        break;
                }
            } catch (NullPointerException | IllegalArgumentException e){
                GuiManager.openErrorStage("Please fill all fields.");
            }

        }
    }

    /**
     * Remove selected statement from structogram.
     */
    private void removeStatement() {
        if (SelectionManager.getSelectedStatement() == null) {
            GuiManager.openErrorStage("There is no selected element.");
        } else {
            SelectionManager.getSelectedList().getEntityStatementList().remove(SelectionManager.getSelectedStatement().getStatementEntity());
            SelectionManager.getSelectedList().removeChild(SelectionManager.getSelectedIndex(), SelectionManager.getSelectedStatement());

            SelectionManager.setInitialValues();

            if (QueryManager.getStructogramWrapperVBox().getChildren().size() == 0) {
                setStructogramFigureVisibility(false);
                SelectionManager.setSelectedList(QueryManager.getStructogramWrapperVBox());
            }
        }
    }

    /**
     * Open modify structogram properties panel.
     */
    private void openModifyStructogramProperties() {
        GuiManager.openModifyPropertiesStage();
        scene.getStructogramPanel().setFunctionString();
    }

    /**
     * Open generate code panel.
     */
    private void generateCode(){
        if (QueryManager.getStructogramWrapperVBox() == null){
            GuiManager.openErrorStage("Empty structogram.");
        } else {
            GuiManager.openGenerationStage();
        }
    }

    /**
     * Sets the structogram figure visibility.
     * @param isVisible structogram visible, or not
     */
    private void setStructogramFigureVisibility(boolean isVisible){
        QueryManager.getStructogramFigureVBox().setVisible(isVisible);
    }
}
