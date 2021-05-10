package testclasses.frontend.javafx.editorpanel;

import backend.enums.FuncType;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.components.panels.editor.propertiespanel.VarOperationPropertiesPanel;
import frontend.components.scenes.EditorScene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import static org.junit.jupiter.api.Assertions.*;

public class StatementPanelItemTests extends EditorPanelTests {

    @Start
    private void start(Stage stage) {
        StructogramFactory.generateNewStructogram("a", "a", FuncType.VOID);
        stage = GuiManager.openEditorStage();
        statementPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStatementPanel();
        structogramPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStructogramPanel();
        stage.show();
    }

    @Test
    public void checkOperationButtonsText(){
        FxAssert.verifyThat("#addVarOpButton", LabeledMatchers.hasText("Variable\noperation"));
        FxAssert.verifyThat("#addLoopButton", LabeledMatchers.hasText("Loop"));
        FxAssert.verifyThat("#addCondStatButton", LabeledMatchers.hasText("Conditional\nstatement"));
        FxAssert.verifyThat("#addJumpStatButton", LabeledMatchers.hasText("Jump\nstatement"));
        FxAssert.verifyThat("#addFuncButton", LabeledMatchers.hasText("Function"));
    }

    @Test
    public void checkNumberOfButtons(){
        assertEquals(((VBox) statementPanel.getChildren().get(0)).getChildren().size(), 5);
    }

    @Test
    public void checkDefaultStatementPropertiesPanel(){
        assertTrue(statementPanel.getChildren().get(1) instanceof VarOperationPropertiesPanel);
    }

    @Test
    public void errorStagePressOk(FxRobot robot){
        robot
                .clickOn("#insertButton")
                .clickOn("#errorButton");
        assertNull(getTopModalStage(robot));
    }

    @Test
    public void structogramVisibility(){
        assertFalse(structogramPanel.isStructogramFigureVisible());
    }
}
