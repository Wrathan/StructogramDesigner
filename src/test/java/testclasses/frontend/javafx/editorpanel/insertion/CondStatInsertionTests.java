package testclasses.frontend.javafx.editorpanel.insertion;

import backend.entities.statements.condstatements.If;
import backend.entities.statements.condstatements.SwitchCase;
import backend.enums.FuncType;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.components.panels.structogram.statements.condstat.IfVBox;
import frontend.components.panels.structogram.statements.condstat.SwitchCaseVBox;
import frontend.components.scenes.EditorScene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

public class CondStatInsertionTests extends InsertionTests {

    @Start
    private void start(Stage stage) {
        StructogramFactory.generateNewStructogram("a", "a", FuncType.VOID);
        stage = GuiManager.openEditorStage();
        statementPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStatementPanel();
        structogramPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStructogramPanel();
        stage.show();
    }

    private void checkCondStatDeleteOptParam(FxRobot robot, String condition, String optText, int numberOfOptParams, int deletedRow){
        robot.clickOn("#removeOptParamButton" + deletedRow);

        for (int i = 0; i < numberOfOptParams - 1; i++){
            int finalI = i;
            FxAssert.verifyThat("#optParamNameTextField" + (i + 1), (TextField textField) -> {
                String text = textField.getText();
                if (finalI < deletedRow - 1){
                    return text.equals(optText + (finalI + 1));
                } else {
                    return text.equals(optText + (finalI + 2));
                }
            });
        }
    }

    private void checkIfWithElseIfBranchesEmptyElseIfConditionDeleteItem(FxRobot robot, String condition, String elifCondition, int numberOfElifs, int deletedRow){
        initIfWithElseIfBranches(robot, condition, elifCondition, numberOfElifs);

        FxAssert.verifyThat("#conditionTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(condition);
        });

        checkCondStatDeleteOptParam(robot, condition, elifCondition, numberOfElifs, deletedRow);
    }

    private void checkSwitchCaseWithCasesDeleteItem(FxRobot robot, String expression, String caseValue, int numberOfCases, boolean isDefaultCase, int deletedRow){
        initSwitchCase(robot, expression, caseValue, numberOfCases, isDefaultCase);

        FxAssert.verifyThat("#switchExprTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(expression);
        });

        checkCondStatDeleteOptParam(robot, expression, caseValue, numberOfCases, deletedRow);
    }

    private void addIfWithElseBranchesDeleteItem(FxRobot robot, String condition, String elifCondition, int numberOfElifs, int deletedRow){
        initIfWithElseIfBranches(robot, condition, elifCondition, numberOfElifs);

        robot
                .clickOn("#removeOptParamButton" + deletedRow)
                .clickOn("#insertButton");

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof If);
        assertTrue(((IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof If);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().size(), numberOfElifs);
        assertNotNull(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_COND));
        assertEquals(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_COND).size(), 0);
        assertNull(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getFalseBranchStatementList());

        for (int i = 0; i < numberOfElifs - 1; i++){
            if (i < deletedRow - 1){
                assertNotNull(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_ELIF_COND + (i + 1)));
                assertEquals(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_ELIF_COND + (i + 1)).size(), 0);
            } else {
                assertNotNull(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_ELIF_COND + (i + 2)));
                assertEquals(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_ELIF_COND + (i + 2)).size(), 0);
            }
        }

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof IfVBox);
        assertEquals(((Label) structogramPanel.getStructogramWrapperVBox().getChildren().get(0).lookup("#conditionLabel")).getText(), TEST_COND);
    }

    private void addSwitchCaseDeleteItem(FxRobot robot, String expression, String caseValue, int numberOfCases, boolean isDefaultCase, int deletedRow){
        initSwitchCase(robot, expression, caseValue, numberOfCases, isDefaultCase);

        robot
                .clickOn("#removeOptParamButton" + deletedRow)
                .clickOn("#insertButton");

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof SwitchCase);
        assertTrue(((SwitchCaseVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof SwitchCase);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((SwitchCaseVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getExpression(), expression);
        assertEquals(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().size(), numberOfCases - 1);
        if (isDefaultCase){
            assertNotNull(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getFalseBranchStatementList());
            assertEquals(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getFalseBranchStatementList().size(), 0);
        } else {
            assertNull(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getFalseBranchStatementList());
        }

        for (int i = 0; i < numberOfCases - 1; i++){
            if (i < deletedRow - 1){
                assertNotNull(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(caseValue + (i + 1)));
                assertEquals(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(caseValue + (i + 1)).size(), 0);
            } else {
                assertNotNull(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(caseValue + (i + 2)));
                assertEquals(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(caseValue + (i + 2)).size(), 0);
            }

        }

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof SwitchCaseVBox);
        assertEquals(((Label) structogramPanel.getStructogramWrapperVBox().getChildren().get(0).lookup("#expressionLabel")).getText(), expression);
    }

    @Test
    public void checkIfWithoutAnyBranchesFieldValues(FxRobot robot){
        initIfWithoutAnyBranches(robot, TEST_COND);

        assertFalse(robot.lookup("#elseBranchCheckBox").queryAs(CheckBox.class).isSelected());
        FxAssert.verifyThat("#conditionTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_COND);
        });
    }

    @Test
    public void checkIfWithElseBranchFieldValues(FxRobot robot){
        initIfWithElseBranch(robot, TEST_COND);

        assertTrue(robot.lookup("#elseBranchCheckBox").queryAs(CheckBox.class).isSelected());
        FxAssert.verifyThat("#conditionTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_COND);
        });
    }

    @Test
    public void checkIfWithElseIfBranchesFieldValues(FxRobot robot){
        int numberOfBranches = 3;

        initIfWithElseIfBranches(robot, TEST_COND, TEST_ELIF_COND, numberOfBranches);

        assertFalse(robot.lookup("#elseBranchCheckBox").queryAs(CheckBox.class).isSelected());
        FxAssert.verifyThat("#conditionTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_COND);
        });
        for (int i = 0; i < numberOfBranches; i++){
            int finalI = i;
            FxAssert.verifyThat("#optParamNameTextField" + (i + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals(TEST_ELIF_COND + (finalI + 1));
            });
        }
    }

    @Test
    public void checkIfWithElseIfAndElseBranchesFieldValues(FxRobot robot){
        int numberOfElifs = 4;

        initIfWithElseIfAndElseBranches(robot, TEST_COND, TEST_ELIF_COND, numberOfElifs);

        assertTrue(robot.lookup("#elseBranchCheckBox").queryAs(CheckBox.class).isSelected());
        FxAssert.verifyThat("#conditionTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_COND);
        });

        for (int i = 0; i < numberOfElifs; i++){
            int finalI = i;
            FxAssert.verifyThat("#optParamNameTextField" + (i + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals(TEST_ELIF_COND + (finalI + 1));
            });
        }
    }

    @Test
    public void checkIfWithElseIfBranchesEmptyElseIfConditionDeleteFirstItem(FxRobot robot){
        checkIfWithElseIfBranchesEmptyElseIfConditionDeleteItem(robot, TEST_COND, TEST_ELIF_COND, 5, 1);
    }

    @Test
    public void checkIfWithElseIfBranchesEmptyElseIfConditionDeleteMiddleItem(FxRobot robot){
        int numberOfElifs = 5;
        checkIfWithElseIfBranchesEmptyElseIfConditionDeleteItem(robot, TEST_COND, TEST_ELIF_COND, numberOfElifs, numberOfElifs/2 - 1);
    }

    @Test
    public void checkIfWithElseIfBranchesEmptyElseIfConditionDeleteLastItem(FxRobot robot){
        int numberOfElifs = 5;
        checkIfWithElseIfBranchesEmptyElseIfConditionDeleteItem(robot, TEST_COND, TEST_ELIF_COND, numberOfElifs, numberOfElifs);
    }

    @Test
    public void addIfWithoutAnyBranches(FxRobot robot){
        insertIfWithoutAnyBranches(robot, TEST_COND);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof If);
        assertTrue(((IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof If);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().size(), 1);
        assertNotNull(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_COND));
        assertEquals(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_COND).size(), 0);
        assertNull(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getFalseBranchStatementList());

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof IfVBox);
        assertEquals(((Label) structogramPanel.getStructogramWrapperVBox().getChildren().get(0).lookup("#conditionLabel")).getText(), TEST_COND);
    }

    @Test
    public void addIfWithoutAnyBranchesEmptyCondition(FxRobot robot){
        robot
                .clickOn("#addCondStatButton")
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addIfWithElseBranch(FxRobot robot){
        insertIfWithElseBranch(robot, TEST_COND);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof If);
        assertTrue(((IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof If);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().size(), 1);
        assertNotNull(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_COND));
        assertEquals(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_COND).size(), 0);
        assertNotNull(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getFalseBranchStatementList());
        assertEquals(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getFalseBranchStatementList().size(), 0);

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof IfVBox);
        assertEquals(((Label) structogramPanel.getStructogramWrapperVBox().getChildren().get(0).lookup("#conditionLabel")).getText(), TEST_COND);
    }

    @Test void addIfWithElseBranchEmptyCondition(FxRobot robot){
        robot
                .clickOn("#addCondStatButton")
                .clickOn("#elseBranchCheckBox")
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addIfWithElseIfBranches(FxRobot robot){
        int numberOfElifs = 3;
        insertIfWithElseIfBranches(robot, TEST_COND, TEST_ELIF_COND, numberOfElifs);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof If);
        assertTrue(((IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof If);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().size(), numberOfElifs + 1);
        assertNotNull(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_COND));
        assertEquals(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_COND).size(), 0);
        assertNull(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getFalseBranchStatementList());

        for (int i = 0; i < numberOfElifs; i++){
            assertNotNull(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_ELIF_COND + (i + 1)));
            assertEquals(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_ELIF_COND + (i + 1)).size(), 0);
        }

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof IfVBox);
        assertEquals(((Label) structogramPanel.getStructogramWrapperVBox().getChildren().get(0).lookup("#conditionLabel")).getText(), TEST_COND);
    }

    @Test
    public void addIfWithElseIfBranchesDeleteFirstItem(FxRobot robot){
        addIfWithElseBranchesDeleteItem(robot, TEST_COND, TEST_ELIF_COND, 3, 1);
    }

    @Test
    public void addIfWithElseIfBranchesDeleteMiddleItem(FxRobot robot){
        int numberOfElifs = 3;
        addIfWithElseBranchesDeleteItem(robot, TEST_COND, TEST_ELIF_COND, numberOfElifs, numberOfElifs/2 + 1);
    }

    @Test
    public void addIfWithElseIfBranchesDeleteLastItem(FxRobot robot){
        int numberOfElifs = 4;
        addIfWithElseBranchesDeleteItem(robot, TEST_COND, TEST_ELIF_COND, numberOfElifs, numberOfElifs);
    }

    @Test
    public void addIfWithElseIfBranchesEmptyItem(FxRobot robot){
        initIfWithoutAnyBranches(robot, TEST_COND);

        robot
                .clickOn("#addElseIfButton")
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addIfWithElseIfAndElseBranches(FxRobot robot){
        int numberOfElifs = 2;
        insertIfWithElseIfAndElseBranches(robot, TEST_COND, TEST_ELIF_COND, numberOfElifs);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof If);
        assertTrue(((IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof If);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().size(), numberOfElifs + 1);
        assertNotNull(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_COND));
        assertEquals(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_COND).size(), 0);
        assertNotNull(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getFalseBranchStatementList());
        assertEquals(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getFalseBranchStatementList().size(), 0);

        for (int i = 0; i < numberOfElifs; i++){
            assertNotNull(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_ELIF_COND + (i + 1)));
            assertEquals(((If) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_ELIF_COND + (i + 1)).size(), 0);
        }

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof IfVBox);
        assertEquals(((Label) structogramPanel.getStructogramWrapperVBox().getChildren().get(0).lookup("#conditionLabel")).getText(), TEST_COND);
    }

    @Test
    public void checkSwitchCaseValueFieldsWithoutDefaultCase(FxRobot robot){
        int numberOfCases = 3;

        initSwitchCase(robot, TEST_EXPRESSION, TEST_CASE_VALUE, numberOfCases, false);

        FxAssert.verifyThat("#switchExprTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_EXPRESSION);
        });
        for (int i = 0; i < numberOfCases; i++){
            int finalI = i;
            FxAssert.verifyThat("#optParamNameTextField" + (i + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals(TEST_CASE_VALUE + (finalI + 1));
            });
        }
        assertFalse(robot.lookup("#defaultCaseCheckBox").queryAs(CheckBox.class).isSelected());
    }

    @Test
    public void checkSwitchCaseValueFieldsWithDefaultCase(FxRobot robot){
        int numberOfCases = 3;

        initSwitchCase(robot, TEST_EXPRESSION, TEST_CASE_VALUE, numberOfCases, true);

        FxAssert.verifyThat("#switchExprTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_EXPRESSION);
        });
        for (int i = 0; i < numberOfCases; i++){
            int finalI = i;
            FxAssert.verifyThat("#optParamNameTextField" + (i + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals(TEST_CASE_VALUE + (finalI + 1));
            });
        }
        assertTrue(robot.lookup("#defaultCaseCheckBox").queryAs(CheckBox.class).isSelected());
    }

    @Test
    public void checkSwitchCaseWithCasesDeleteFirstItem(FxRobot robot){
        checkSwitchCaseWithCasesDeleteItem(robot, TEST_EXPRESSION, TEST_CASE_VALUE, 5, true, 1);
    }

    @Test
    public void checkSwitchCaseWithCasesDeleteMiddleItem(FxRobot robot){
        int numberOfCases = 4;
        checkSwitchCaseWithCasesDeleteItem(robot, TEST_EXPRESSION, TEST_CASE_VALUE, numberOfCases, true, numberOfCases/2 + 1);
    }

    @Test
    public void checkSwitchCaseWithCasesDeleteLastItem(FxRobot robot){
        int numberOfCases = 6;
        checkSwitchCaseWithCasesDeleteItem(robot, TEST_EXPRESSION, TEST_CASE_VALUE, numberOfCases, true, numberOfCases);
    }

    @Test
    public void addSwitchCaseWithDefaultCase(FxRobot robot){
        int numberOfCases = 3;

        insertSwitchCase(robot, TEST_EXPRESSION, TEST_CASE_VALUE, numberOfCases, true);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof SwitchCase);
        assertTrue(((SwitchCaseVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof SwitchCase);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((SwitchCaseVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getExpression(), TEST_EXPRESSION);
        assertEquals(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().size(), numberOfCases);
        assertNotNull(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getFalseBranchStatementList());
        assertEquals(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getFalseBranchStatementList().size(), 0);

        for (int i = 0; i < numberOfCases; i++){
            assertNotNull(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_CASE_VALUE + (i + 1)));
            assertEquals(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_CASE_VALUE + (i + 1)).size(), 0);
        }

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof SwitchCaseVBox);
        assertEquals(((Label) structogramPanel.getStructogramWrapperVBox().getChildren().get(0).lookup("#expressionLabel")).getText(), TEST_EXPRESSION);
    }

    @Test
    public void addSwitchCaseWithoutDefaultCase(FxRobot robot){
        int numberOfCases = 3;

        insertSwitchCase(robot, TEST_EXPRESSION, TEST_CASE_VALUE, numberOfCases, false);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof SwitchCase);
        assertTrue(((SwitchCaseVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof SwitchCase);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((SwitchCaseVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getExpression(), TEST_EXPRESSION);
        assertEquals(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().size(), numberOfCases);
        assertNull(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getFalseBranchStatementList());

        for (int i = 0; i < numberOfCases; i++){
            assertNotNull(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_CASE_VALUE + (i + 1)));
            assertEquals(((SwitchCase) StructogramFactory.getStructogram().getStatementList().get(0)).getBranches().get(TEST_CASE_VALUE + (i + 1)).size(), 0);
        }

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof SwitchCaseVBox);
        assertEquals(((Label) structogramPanel.getStructogramWrapperVBox().getChildren().get(0).lookup("#expressionLabel")).getText(), TEST_EXPRESSION);
    }

    @Test
    public void addSwitchCaseEmptyExpression(FxRobot robot){
        robot.clickOn("#addCondStatButton");
        chooseChoiceBoxItem(robot, "#condStatementChoiceBox", "switch");
        robot
                .clickOn("#addCaseExprButton")
                .clickOn("#optParamNameTextField1")
                .write(TEST_CASE_VALUE + 1)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addSwitchCaseDeleteFirstItem(FxRobot robot){
        addSwitchCaseDeleteItem(robot, TEST_EXPRESSION, TEST_CASE_VALUE, 3, false, 1);
    }

    @Test
    public void addSwitchCaseDeleteMiddleItem(FxRobot robot){
        int numberOfCases = 3;
        addSwitchCaseDeleteItem(robot, TEST_EXPRESSION, TEST_CASE_VALUE, numberOfCases, true, numberOfCases/2 + 1);
    }

    @Test
    public void addSwitchCaseDeleteLastItem(FxRobot robot){
        int numberOfCases = 4;
        addSwitchCaseDeleteItem(robot, TEST_EXPRESSION, TEST_CASE_VALUE, numberOfCases, false, numberOfCases);
    }

    @Test
    public void addSwitchCaseEmptyCase(FxRobot robot){
        robot.clickOn("#addCondStatButton");
        chooseChoiceBoxItem(robot, "#condStatementChoiceBox", "switch");
        robot
                .clickOn("#switchExprTextField")
                .write(TEST_EXPRESSION)
                .clickOn("#addCaseExprButton")
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }
}
