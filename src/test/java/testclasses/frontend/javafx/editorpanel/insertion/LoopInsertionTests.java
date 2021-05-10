package testclasses.frontend.javafx.editorpanel.insertion;

import backend.entities.statements.loops.DoWhileLoop;
import backend.entities.statements.loops.ForEachLoop;
import backend.entities.statements.loops.ForLoop;
import backend.entities.statements.loops.WhileLoop;
import backend.enums.FuncType;
import backend.enums.VarType;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.components.panels.structogram.statements.loop.AfterCheckLoopVBox;
import frontend.components.panels.structogram.statements.loop.PreCheckLoopVBox;
import frontend.components.scenes.EditorScene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

public class LoopInsertionTests extends InsertionTests{

    @Start
    protected void start(Stage stage) {
        StructogramFactory.generateNewStructogram("a", "a", FuncType.VOID);
        stage = GuiManager.openEditorStage();
        statementPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStatementPanel();
        structogramPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStructogramPanel();
        stage.show();
    }

    @Test
    public void checkForLoopFieldValues(FxRobot robot){
        String start = "0";
        String end = "5";
        initForLoop(robot, TEST_FOR_VAR_NAME, start, end);
        FxAssert.verifyThat("#varNameTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_FOR_VAR_NAME);
        });
        FxAssert.verifyThat("#startIntTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(start);
        });
        FxAssert.verifyThat("#endIntTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(end);
        });
    }

    @Test
    public void addForLoop(FxRobot robot){
        String start = "0";
        String end = "n-1";

        insertForLoop(robot, TEST_FOR_VAR_NAME, start, end);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof ForLoop);
        assertTrue(((PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof ForLoop);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((ForLoop) StructogramFactory.getStructogram().getStatementList().get(0)).getVarName(), TEST_FOR_VAR_NAME);
        assertEquals(((ForLoop) StructogramFactory.getStructogram().getStatementList().get(0)).getStart(), start);
        assertEquals(((ForLoop) StructogramFactory.getStructogram().getStatementList().get(0)).getEnd(), end);
        assertNotNull(((ForLoop) StructogramFactory.getStructogram().getStatementList().get(0)).getStatementList());
        assertEquals(((ForLoop) StructogramFactory.getStructogram().getStatementList().get(0)).getStatementList().size(), 0);

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof PreCheckLoopVBox);
        assertEquals(((PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getConditionLabel().getText(),
                TEST_FOR_VAR_NAME + ": " + start + ".." + end + "-1");
    }

    @Test
    public void addForLoopEmptyLoopVarName(FxRobot robot){
        robot
                .clickOn("#addLoopButton")
                .clickOn("#startIntTextField")
                .write("0")
                .clickOn("#endIntTextField")
                .write("n-1")
                .clickOn("#insertButton");

        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addForLoopEmptyStartValue(FxRobot robot){
        robot
                .clickOn("#addLoopButton")
                .clickOn("#varNameTextField")
                .write(TEST_FOR_VAR_NAME)
                .clickOn("#endIntTextField")
                .write("n-1")
                .clickOn("#insertButton");

        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addForLoopEmptyEndValue(FxRobot robot){
        robot
                .clickOn("#addLoopButton")
                .clickOn("#varNameTextField")
                .write(TEST_FOR_VAR_NAME)
                .clickOn("#startIntTextField")
                .write("0")
                .clickOn("#insertButton");

        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void checkForEachLoopFieldValues(FxRobot robot){
        String type = "int";
        initForEachLoop(robot, type, TEST_FOREACH_ITEM, TEST_FOREACH_ARRAY);
        FxAssert.verifyThat("#varTypeChoiceBox", (ChoiceBox choiceBox) -> {
            String text = choiceBox.getValue().toString();
            return text.equals(type);
        });
        FxAssert.verifyThat("#actualItemTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_FOREACH_ITEM);
        });
        FxAssert.verifyThat("#arrayVarTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_FOREACH_ARRAY);
        });
    }

    @Test
    public void addForEachLoop(FxRobot robot){
        String type = "boolean";
        insertForEachLoop(robot, type, TEST_FOREACH_ITEM, TEST_FOREACH_ARRAY);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof ForEachLoop);
        assertTrue(((PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof ForEachLoop);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((ForEachLoop) StructogramFactory.getStructogram().getStatementList().get(0)).getItemType(), VarType.valueOf(type.toUpperCase()));
        assertEquals(((ForEachLoop) StructogramFactory.getStructogram().getStatementList().get(0)).getActualItemName(), TEST_FOREACH_ITEM);
        assertEquals(((ForEachLoop) StructogramFactory.getStructogram().getStatementList().get(0)).getArrayVarName(), TEST_FOREACH_ARRAY);
        assertNotNull(((ForEachLoop) StructogramFactory.getStructogram().getStatementList().get(0)).getStatementList());
        assertEquals(((ForEachLoop) StructogramFactory.getStructogram().getStatementList().get(0)).getStatementList().size(), 0);

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof PreCheckLoopVBox);
        assertEquals(((PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getConditionLabel().getText(),
                TEST_FOREACH_ITEM + " in " + TEST_FOREACH_ARRAY);
    }

    @Test
    public void addForEachLoopEmptyType(FxRobot robot){
        robot
                .clickOn("#addLoopButton");
        chooseChoiceBoxItem(robot, "#loopTypeChoiceBox", "for-each");
        robot
                .clickOn("#actualItemTextField")
                .write(TEST_FOREACH_ITEM)
                .clickOn("#arrayVarTextField")
                .write(TEST_FOREACH_ARRAY)
                .clickOn("#insertButton");

        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addForEachLoopEmptyActualItem(FxRobot robot){
        String type = "char";
        robot
                .clickOn("#addLoopButton");
        chooseChoiceBoxItem(robot, "#loopTypeChoiceBox", "for-each");
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", type);
        robot
                .clickOn("#arrayVarTextField")
                .write(TEST_FOREACH_ARRAY)
                .clickOn("#insertButton");

        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addForEachLoopEmptyArrayVar(FxRobot robot){
        String type = "char";
        robot
                .clickOn("#addLoopButton");
        chooseChoiceBoxItem(robot, "#loopTypeChoiceBox", "for-each");
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", type);
        robot
                .clickOn("#actualItemTextField")
                .write(TEST_FOREACH_ITEM)
                .clickOn("#insertButton");

        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void checkWhileLoopFieldValues(FxRobot robot){
        initWhileLoop(robot, TEST_COND);
        FxAssert.verifyThat("#conditionTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_COND);
        });
    }

    @Test
    public void addWhileLoop(FxRobot robot){
        insertWhileLoop(robot, TEST_COND);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof WhileLoop);
        assertTrue(((PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof WhileLoop);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((WhileLoop) StructogramFactory.getStructogram().getStatementList().get(0)).getCondition(), TEST_COND);
        assertNotNull(((WhileLoop) StructogramFactory.getStructogram().getStatementList().get(0)).getStatementList());
        assertEquals(((WhileLoop) StructogramFactory.getStructogram().getStatementList().get(0)).getStatementList().size(), 0);

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof PreCheckLoopVBox);
        assertEquals(((PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getConditionLabel().getText(),
                TEST_COND);
    }

    @Test
    public void addDoWhileLoop(FxRobot robot){
        insertDoWhileLoop(robot, TEST_COND);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof DoWhileLoop);
        assertTrue(((AfterCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof DoWhileLoop);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((AfterCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((WhileLoop) StructogramFactory.getStructogram().getStatementList().get(0)).getCondition(), TEST_COND);
        assertNotNull(((WhileLoop) StructogramFactory.getStructogram().getStatementList().get(0)).getStatementList());
        assertEquals(((WhileLoop) StructogramFactory.getStructogram().getStatementList().get(0)).getStatementList().size(), 0);

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof AfterCheckLoopVBox);
        assertEquals(((AfterCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getConditionLabel().getText(),
                TEST_COND);
    }

    @Test
    public void addWhileLoopEmptyCondition(FxRobot robot){
        robot.clickOn("#addLoopButton");
        chooseChoiceBoxItem(robot, "#loopTypeChoiceBox", "while");
        robot
                .clickOn("#whileRadioButton")
                .clickOn("#insertButton");

        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addWhileLoopEmptySubType(FxRobot robot){
        robot.clickOn("#addLoopButton");
        chooseChoiceBoxItem(robot, "#loopTypeChoiceBox", "while");
        robot
                .clickOn("#conditionTextField")
                .write(TEST_COND)
                .clickOn("#insertButton");

        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }
}
