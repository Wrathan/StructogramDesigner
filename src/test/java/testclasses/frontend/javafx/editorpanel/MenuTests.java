package testclasses.frontend.javafx.editorpanel;

import backend.enums.FuncType;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.components.scenes.EditorScene;
import frontend.stages.popup.serialization.LoadStage;
import frontend.stages.popup.serialization.SaveStage;
import frontend.stages.popup.structogramproperties.NewPropertiesStage;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuTests extends EditorPanelTests {

    @Start
    private void start(Stage stage) {
        StructogramFactory.generateNewStructogram("a", "a", FuncType.VOID, "", "");
        stage = GuiManager.openEditorStage();
        statementPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStatementPanel();
        structogramPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStructogramPanel();
        stage.show();
    }

    @Test
    public void clickOnNewMenuItem(FxRobot robot){
        robot
                .clickOn("#fileMenu")
                .clickOn("#newMenuItem");
        assertTrue(getTopModalStage(robot) instanceof NewPropertiesStage);
    }

    @Test
    public void clickOnLoadMenuItem(FxRobot robot){
        robot
                .clickOn("#fileMenu")
                .clickOn("#loadMenuItem");
        assertTrue(getTopModalStage(robot) instanceof LoadStage);
    }

    @Test
    public void clickOnSaveMenuItem(FxRobot robot){
        robot
                .clickOn("#fileMenu")
                .clickOn("#saveMenuItem");
        assertTrue(getTopModalStage(robot) instanceof SaveStage);
    }

    /*@Test
    public void clickOnDocMenuItem(FxRobot robot){
        robot
                .clickOn("#helpMenu")
                .clickOn("#docMenuItem");
        FxAssert.verifyThat();
    }*/
}
