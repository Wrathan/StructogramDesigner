package testclasses.frontend.javafx.editorpanel.insertion;

import backend.entities.statements.functionoperations.FunctionAssignment;
import backend.entities.statements.functionoperations.FunctionCall;
import backend.entities.statements.functionoperations.FunctionDefinition;
import backend.enums.FuncType;
import backend.enums.VarType;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.components.panels.editor.propertiespanel.FunctionPropertiesPanel;
import frontend.components.panels.structogram.statements.SingleRowStatementVBox;
import frontend.components.scenes.EditorScene;
import frontend.components.tableviews.FunctionTableView;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionOperationTests extends InsertionTests {

    File file, imgFile;

    @BeforeEach
    private void saveStructogram(FxRobot robot) {
        ArrayList<File> files = saveBasicStructogramWithOneParameter(robot, VarType.STRING.toString(), TEST_PARAM_NAME, false, 0);
        file = files.get(0);
        imgFile = files.get(1);
    }

    @AfterEach
    private void deleteFiles(){
        file.delete();
        imgFile.delete();
    }

    @Start
    private void start(Stage stage) {
        StructogramFactory.generateNewStructogram("a", "a", FuncType.VOID);
        stage = GuiManager.openEditorStage();
        statementPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStatementPanel();
        structogramPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStructogramPanel();
        stage.show();
    }

    @Test
    public void checkSimpleFunctionCallFields(FxRobot robot){

        chooseTableViewItem(robot, "a");
        robot.clickOn("#addFuncButton");

        FunctionTableView tableView = ((FunctionPropertiesPanel) statementPanel.getStatementPropertiesPanel()).getFunctionTableView();

        robot.clickOn(tableView.lookupAll(".table-row-cell").iterator().next());

        chooseChoiceBoxItem(robot, "#insertTypeChoiceBox", "Simple call");

        robot
                .clickOn("#funcVarTextField1")
                .write(TEST_VAR_VALUE);

        FxAssert.verifyThat("#insertTypeChoiceBox", (ChoiceBox choiceBox) -> {
            String text = choiceBox.getValue().toString();
            return text.equals("Simple call");
        });
        FxAssert.verifyThat("#funcVarTextField1", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_VAR_VALUE);
        });
    }

    @Test
    public void checkFunctionAssignmentFields(FxRobot robot){

        chooseTableViewItem(robot, "a");
        robot.clickOn("#addFuncButton");

        FunctionTableView tableView = ((FunctionPropertiesPanel) statementPanel.getStatementPropertiesPanel()).getFunctionTableView();

        robot.clickOn(tableView.lookupAll(".table-row-cell").iterator().next());

        chooseChoiceBoxItem(robot, "#insertTypeChoiceBox", "Assignment");

        robot
                .clickOn("#funcVarTextField1")
                .write(TEST_VAR_VALUE);

        FxAssert.verifyThat("#insertTypeChoiceBox", (ChoiceBox choiceBox) -> {
            String text = choiceBox.getValue().toString();
            return text.equals("Assignment");
        });
        FxAssert.verifyThat("#funcVarTextField1", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_VAR_VALUE);
        });
    }

    @Test
    public void checkFunctionDefinitionFields(FxRobot robot){

        chooseTableViewItem(robot, "a");
        robot.clickOn("#addFuncButton");

        FunctionTableView tableView = ((FunctionPropertiesPanel) statementPanel.getStatementPropertiesPanel()).getFunctionTableView();

        robot.clickOn(tableView.lookupAll(".table-row-cell").iterator().next());

        chooseChoiceBoxItem(robot, "#insertTypeChoiceBox", "Definition");

        robot
                .clickOn("#funcVarTextField1")
                .write(TEST_VAR_VALUE);

        FxAssert.verifyThat("#insertTypeChoiceBox", (ChoiceBox choiceBox) -> {
            String text = choiceBox.getValue().toString();
            return text.equals("Definition");
        });
        FxAssert.verifyThat("#funcVarTextField1", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_VAR_VALUE);
        });
    }

    @Test
    public void insertSimpleFunctionCall(FxRobot robot){
        chooseTableViewItem(robot, "a");
        robot.clickOn("#addFuncButton");

        FunctionTableView tableView = ((FunctionPropertiesPanel) statementPanel.getStatementPropertiesPanel()).getFunctionTableView();

        robot.clickOn(tableView.lookupAll(".table-row-cell").iterator().next());

        chooseChoiceBoxItem(robot, "#insertTypeChoiceBox", "Simple call");

        robot
                .clickOn("#funcVarTextField1")
                .write(TEST_VAR_VALUE)
                .clickOn("#insertButton");

        checkStructogramEntityAndFigureSize(2);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(1) instanceof FunctionCall);
        assertTrue(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(1)).getStatementEntity() instanceof FunctionCall);
        assertSame(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(1)).getStatementEntity(),
                StructogramFactory.getStructogram().getStatementList().get(1));

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(1) instanceof SingleRowStatementVBox);
        assertEquals(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(1)).getStatementLabel().getText(),
                "a(" + TEST_VAR_VALUE + ")");
    }

    @Test
    public void insertFunctionAssignment(FxRobot robot){

        chooseTableViewItem(robot, "a");
        robot.clickOn("#addFuncButton");

        FunctionTableView tableView = ((FunctionPropertiesPanel) statementPanel.getStatementPropertiesPanel()).getFunctionTableView();

        robot.clickOn(tableView.lookupAll(".table-row-cell").iterator().next());

        chooseChoiceBoxItem(robot, "#insertTypeChoiceBox", "Assignment");

        robot
                .clickOn("#varNameTextField")
                .write(TEST_VAR_NAME)
                .clickOn("#funcVarTextField1")
                .write(TEST_VAR_VALUE)
                .clickOn("#insertButton");

        checkStructogramEntityAndFigureSize(2);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(1) instanceof FunctionAssignment);
        assertTrue(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(1)).getStatementEntity() instanceof FunctionAssignment);
        assertSame(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(1)).getStatementEntity(),
                StructogramFactory.getStructogram().getStatementList().get(1));

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(1) instanceof SingleRowStatementVBox);
        assertEquals(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(1)).getStatementLabel().getText(),
                TEST_VAR_NAME + " = a(" + TEST_VAR_VALUE + ")");
    }

    @Test
    public void insertFunctionDefinition(FxRobot robot){
        String type = "string";

        chooseTableViewItem(robot, "a");
        robot.clickOn("#addFuncButton");

        FunctionTableView tableView = ((FunctionPropertiesPanel) statementPanel.getStatementPropertiesPanel()).getFunctionTableView();

        robot.clickOn(tableView.lookupAll(".table-row-cell").iterator().next());

        chooseChoiceBoxItem(robot, "#insertTypeChoiceBox", "Definition");

        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", type);
        robot
                .clickOn("#varNameTextField")
                .write(TEST_VAR_NAME)
                .clickOn("#funcVarTextField1")
                .write(TEST_VAR_VALUE)
                .clickOn("#insertButton");

        checkStructogramEntityAndFigureSize(2);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(1) instanceof FunctionDefinition);
        assertTrue(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(1)).getStatementEntity() instanceof FunctionDefinition);
        assertSame(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(1)).getStatementEntity(),
                StructogramFactory.getStructogram().getStatementList().get(1));

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(1) instanceof SingleRowStatementVBox);
        assertEquals(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(1)).getStatementLabel().getText(),
                type+  " " + TEST_VAR_NAME + " = a(" + TEST_VAR_VALUE + ")");
    }
}
