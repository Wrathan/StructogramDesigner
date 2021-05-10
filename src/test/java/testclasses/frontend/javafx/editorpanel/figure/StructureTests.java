package testclasses.frontend.javafx.editorpanel.figure;

import backend.enums.FuncType;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.components.panels.structogram.statements.condstat.SwitchCaseVBox;
import frontend.components.panels.structogram.statements.loop.PreCheckLoopVBox;
import frontend.components.scenes.EditorScene;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import testclasses.frontend.javafx.editorpanel.EditorPanelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StructureTests extends EditorPanelTests {

    @Start
    private void start(Stage stage) {
        StructogramFactory.generateNewStructogram("a", "a", FuncType.VOID);
        stage = GuiManager.openEditorStage();
        statementPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStatementPanel();
        structogramPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStructogramPanel();
        stage.show();
    }

    @Test
    public void addStatements(FxRobot robot){
        insertSimpleDeclaration(robot, "double", "test_double");
        insertForEachLoop(robot, "int", "item", "int_list");
        insertIfWithElseIfAndElseBranches(robot, "test_condition", "elif_condition", 2);
        insertReturn(robot, "test_expression", false);

        assertEquals(structogramPanel.getStructogramWrapperVBox().getChildren().size(), 4);
    }

    @Test
    public void addLoopInnerStatements(FxRobot robot){
        insertWhileLoop(robot, "test_condition");

        PreCheckLoopVBox whileLoopVBox = (PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0);

        robot
                .moveTo(whileLoopVBox)
                .moveBy(0, whileLoopVBox.getHeight()/2 - 5)
                .clickOn(MouseButton.PRIMARY);
        insertIfWithElseIfAndElseBranches(robot, "test_condition", "elif_condition", 2);
        insertSwitchCase(robot, "test_expression", "value", 2, true);
        insertSimpleAssignment(robot, "test_assignment", "test_value");

        assertEquals(structogramPanel.getStructogramWrapperVBox().getChildren().size(), 1);
        assertEquals(whileLoopVBox.getStatementListVBox().getChildren().size(), 3);
    }

    @Test
    public void addLoopInnerInnerStatements(FxRobot robot){
        insertWhileLoop(robot, "test_condition");

        PreCheckLoopVBox whileLoopVBox = (PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0);

        robot
                .moveTo(whileLoopVBox)
                .moveBy(0, whileLoopVBox.getHeight()/2 - 5)
                .clickOn(MouseButton.PRIMARY);
        insertIfWithElseIfAndElseBranches(robot, "test_condition", "elif_condition", 2);
        insertSwitchCase(robot, "test_expression", "value", 2, true);
        insertSimpleAssignment(robot, "test_assignment", "test_value");

        SwitchCaseVBox switchCaseVBox = (SwitchCaseVBox) whileLoopVBox.getStatementListVBox().getChildren().get(1);

        robot
                .moveTo(switchCaseVBox)
                .moveBy(0, switchCaseVBox.getHeight()/2)
                .clickOn(MouseButton.PRIMARY);

        insertForEachLoop(robot, "char", "item", "test_array");
        insertSimpleDefinition(robot, "boolean", "test_def", "test_def_value");

        assertEquals(structogramPanel.getStructogramWrapperVBox().getChildren().size(), 1);
        assertEquals(whileLoopVBox.getStatementListVBox().getChildren().size(), 3);
        assertEquals(switchCaseVBox.getStatementListVBoxArrayList().get(1).getChildren().size(), 2);
    }
}
