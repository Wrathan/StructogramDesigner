package testclasses.frontend.javafx.editorpanel.insertion;

import backend.entities.statements.jumpstatements.Break;
import backend.entities.statements.jumpstatements.Continue;
import backend.entities.statements.jumpstatements.Return;
import backend.enums.FuncType;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.components.panels.structogram.statements.SingleRowStatementVBox;
import frontend.components.scenes.EditorScene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

public class JumpStatTests extends InsertionTests {

    @Start
    private void start(Stage stage) {
        StructogramFactory.generateNewStructogram("a", "a", FuncType.VOID);
        stage = GuiManager.openEditorStage();
        statementPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStatementPanel();
        structogramPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStructogramPanel();
        stage.show();
    }

    @Test
    public void addBreak(FxRobot robot){
        insertBreak(robot);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof Break);
        assertTrue(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof Break);
        assertSame(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity(),
                StructogramFactory.getStructogram().getStatementList().get(0));

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof SingleRowStatementVBox);
        assertEquals(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementLabel().getText(),
                "break");
    }

    @Test
    public void addContinue(FxRobot robot){
        insertContinue(robot);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof Continue);
        assertTrue(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof Continue);
        assertSame(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity(),
                StructogramFactory.getStructogram().getStatementList().get(0));

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof SingleRowStatementVBox);
        assertEquals(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementLabel().getText(),
                "continue");
    }

    @Test
    public void addReturn(FxRobot robot){
        insertReturn(robot, TEST_RETURN_VALUE, false);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof Return);
        assertTrue(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof Return);
        assertEquals(((Return) StructogramFactory.getStructogram().getStatementList().get(0)).getExpression(), TEST_RETURN_VALUE);
        assertFalse(((Return) StructogramFactory.getStructogram().getStatementList().get(0)).isCheckPostCond());
        assertSame(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity(),
                StructogramFactory.getStructogram().getStatementList().get(0));

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof SingleRowStatementVBox);
        assertEquals(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementLabel().getText(),
                "return " + TEST_RETURN_VALUE);
    }

    @Test
    public void addReturnWithPostcondCheck(FxRobot robot){
        initReturn(robot, TEST_RETURN_VALUE, true);
        robot.clickOn("#insertButton");

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof Return);
        assertTrue(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof Return);
        assertEquals(((Return) StructogramFactory.getStructogram().getStatementList().get(0)).getExpression(), TEST_RETURN_VALUE);
        assertTrue(((Return) StructogramFactory.getStructogram().getStatementList().get(0)).isCheckPostCond());
        assertSame(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity(),
                StructogramFactory.getStructogram().getStatementList().get(0));

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof SingleRowStatementVBox);
        assertEquals(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementLabel().getText(),
                "return " + TEST_RETURN_VALUE + " (postc. check)");
    }
}
