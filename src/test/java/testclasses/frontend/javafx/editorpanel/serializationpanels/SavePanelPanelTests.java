package testclasses.frontend.javafx.editorpanel.serializationpanels;

import backend.enums.FuncType;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.components.scenes.EditorScene;
import frontend.stages.popup.ImageStage;
import frontend.stages.popup.serialization.SaveStage;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SavePanelPanelTests extends SerializationPanelTests {

    @Start
    private void start(Stage stage) {
        StructogramFactory.generateNewStructogram("a", "a", FuncType.VOID);
        stage = GuiManager.openEditorStage();
        statementPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStatementPanel();
        structogramPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStructogramPanel();
        stage.show();
    }

    @Test
    public void saveItem(){
        assertTrue(file.exists());
        assertTrue(imgFile.exists());
    }

    @Test
    public void saveItemAndTryToOverwriteIt(FxRobot robot){
        robot
                .clickOn("#fileMenu")
                .clickOn("#saveMenuItem")
                .clickOn("#serializationButton");

        checkConfirmStage(robot, "Structogram with this name already exists.\nDo you want to rewrite it?");
    }

    @Test
    public void saveItemAndOverwriteIt(FxRobot robot){
        robot
                .clickOn("#fileMenu")
                .clickOn("#saveMenuItem")
                .clickOn("#serializationButton");

        checkConfirmStage(robot, "Structogram with this name already exists.\nDo you want to rewrite it?");

        robot.clickOn("#yesButton");

        assertTrue(file.exists());
        assertTrue(imgFile.exists());
    }

    @Test
    public void saveItemAndDenyOverwritingIt(FxRobot robot){
        robot
                .clickOn("#fileMenu")
                .clickOn("#saveMenuItem")
                .clickOn("#serializationButton");

        checkConfirmStage(robot, "Structogram with this name already exists.\nDo you want to rewrite it?");

        robot.clickOn("#noButton");

        assertTrue(getTopModalStage(robot) instanceof SaveStage);
    }

    @Test
    public void saveItemChangeNameAndSaveAgain(FxRobot robot){
        robot
                .clickOn("#fileMenu")
                .clickOn("#saveMenuItem")
                .clickOn("#operationTextField")
                .write("a");

        FxAssert.verifyThat("#operationTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals("aa");
        });
    }

    @Test
    public void showPicture(FxRobot robot){;
        robot
                .clickOn("#fileMenu")
                .clickOn("#saveMenuItem");
        Iterator it = robot.lookup("#showLinkColumn").queryAll().iterator();

        //table column header
        it.next();

        robot.clickOn((TableCell) it.next());
        assertTrue(getTopModalStage(robot) instanceof ImageStage);
    }

    @Test
    public void removeSavedStructogram(FxRobot robot){
        int beginSize = countFiles();
        int newSize;

        robot
                .clickOn("#fileMenu")
                .clickOn("#saveMenuItem");
        Iterator it = robot.lookup("#deleteLinkColumn").queryAll().iterator();

        //table column header
        it.next();

        robot.clickOn((TableCell) it.next());

        newSize = countFiles();
        assertEquals(beginSize - 1, newSize);
    }
}
