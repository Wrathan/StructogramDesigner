package testclasses.frontend.javafx.editorpanel.updatepanel;

import backend.entities.statements.loops.WhileLoop;
import backend.enums.FuncType;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.components.panels.structogram.statements.loop.PreCheckLoopVBox;
import frontend.components.scenes.EditorScene;
import frontend.stages.popup.GenerationStage;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import testclasses.frontend.javafx.editorpanel.EditorPanelTests;

import static org.junit.jupiter.api.Assertions.*;

public class UpdatePanelTests extends EditorPanelTests {

    @Start
    private void start(Stage stage) {
        StructogramFactory.generateNewStructogram("a", "a", FuncType.VOID);
        stage = GuiManager.openEditorStage();
        statementPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStatementPanel();
        structogramPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStructogramPanel();
        stage.show();
    }

    @Test
    public void checkPositionChoiceBoxBeforeElement(FxRobot robot){
        String position = "before";
        chooseChoiceBoxItem(robot, "#insertTypeChoiceBox", position);
        FxAssert.verifyThat("#insertTypeChoiceBox", (ChoiceBox choiceBox) -> {
            String text = choiceBox.getValue().toString();
            return text.equals(position);
        });
    }

    @Test
    public void checkPositionChoiceBoxAfterElement(FxRobot robot){
        String position = "after";
        chooseChoiceBoxItem(robot, "#insertTypeChoiceBox", position);
        FxAssert.verifyThat("#insertTypeChoiceBox", (ChoiceBox choiceBox) -> {
            String text = choiceBox.getValue().toString();
            return text.equals(position);
        });
    }

    @Test
    public void checkPositionChoiceBoxInsteadOfElement(FxRobot robot){
        String position = "instead of";
        chooseChoiceBoxItem(robot, "#insertTypeChoiceBox", position);
        FxAssert.verifyThat("#insertTypeChoiceBox", (ChoiceBox choiceBox) -> {
            String text = choiceBox.getValue().toString();
            return text.equals(position);
        });
    }

    @Test
    public void checkPositionCheckBoxInvalidElement(FxRobot robot){
        String position = "invalid";
        assertThrows(NullPointerException.class, () -> {
            chooseChoiceBoxItem(robot, "#insertTypeChoiceBox", position);
        });
    }

    @Test
    public void checkPositionCheckBoxSize(FxRobot robot){
        int checkBoxSize = robot.lookup("#insertTypeChoiceBox").queryAs(ChoiceBox.class).getItems().size();

        assertEquals(checkBoxSize, 3);
    }

    @Test
    public void clickOnInsertButtonEmptyParameters(FxRobot robot){
        robot.clickOn("#insertButton");
        checkErrorStage(robot, "Please fill all fields.");
    }

    @Test
    public void clickOnRemoveButtonEmptyStructogram(FxRobot robot){
        robot.clickOn("#removeButton");
        checkErrorStage(robot, "There is no selected element.");
    }

    @Test
    public void clickOnInsertButton(FxRobot robot){
        String testCondition = "test_condition";
        insertWhileLoop(robot, testCondition);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof PreCheckLoopVBox);
        assertEquals(((PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getConditionLabel().getText(), testCondition);
        assertEquals(((PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementListVBox().getChildren().size(), 0);
        assertTrue(((PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof WhileLoop);
        assertEquals(((WhileLoop) ((PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity()).getCondition(), testCondition);
        assertEquals(((WhileLoop) ((PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity()).getStatementList().size(), 0);
    }

    @Test
    public void clickOnRemoveButton(FxRobot robot){
        String testCondition = "test_condition";
        insertWhileLoop(robot, testCondition);

        robot.clickOn("#removeButton");

        checkStructogramEntityAndFigureSize(0);
        assertFalse(structogramPanel.isStructogramFigureVisible());
    }

    @Test
    public void clickOnGenerateButton(FxRobot robot){
        robot.clickOn("#generateButton");
        assertTrue(getTopModalStage(robot) instanceof GenerationStage);
    }
}
