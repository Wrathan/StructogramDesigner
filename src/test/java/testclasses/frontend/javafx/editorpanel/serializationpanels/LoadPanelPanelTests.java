package testclasses.frontend.javafx.editorpanel.serializationpanels;

import backend.entities.structograms.Structogram;
import backend.enums.FuncType;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.components.scenes.EditorScene;
import frontend.stages.popup.ImageStage;
import javafx.scene.control.TableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class LoadPanelPanelTests extends SerializationPanelTests {

    Structogram oldStructogram;
    VBox oldStructogramVBox;

    @Start
    private void start(Stage stage) {
        StructogramFactory.generateNewStructogram("a", "a", FuncType.VOID);
        stage = GuiManager.openEditorStage();
        statementPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStatementPanel();
        structogramPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStructogramPanel();
        stage.show();
    }

    @Test
    public void loadItem(FxRobot robot){
        oldStructogram = StructogramFactory.getStructogram();
        oldStructogramVBox = structogramPanel.getStructogramFigureVBox();

        robot
                .clickOn("#fileMenu")
                .clickOn("#loadMenuItem");
        chooseTableViewItem(robot, "a");
        robot.clickOn("#serializationButton");

        assertNotNull(StructogramFactory.getStructogram());
        assertEquals(StructogramFactory.getStructogram(), oldStructogram);
        assertEquals(structogramPanel.getStructogramFigureVBox().getChildren().size(), oldStructogramVBox.getChildren().size());
        assertEquals(structogramPanel.getStructogramFigureVBox().getChildren().get(0).getStyle(), oldStructogramVBox.getChildren().get(0).getStyle());
    }

    @Test
    public void showPicture(FxRobot robot){
        robot
                .clickOn("#fileMenu")
                .clickOn("#loadMenuItem");
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
                .clickOn("#loadMenuItem");
        Iterator it = robot.lookup("#deleteLinkColumn").queryAll().iterator();

        //table column header
        it.next();

        robot.clickOn((TableCell) it.next());

        newSize = countFiles();
        assertEquals(beginSize - 1, newSize);
    }
}
