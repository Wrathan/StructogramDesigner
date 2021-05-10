package testclasses.frontend.javafx.editorpanel.figure;

import backend.enums.FuncType;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.components.managers.SelectionManager;
import frontend.components.panels.structogram.statements.SingleRowStatementVBox;
import frontend.components.panels.structogram.statements.condstat.IfVBox;
import frontend.components.panels.structogram.statements.loop.AfterCheckLoopVBox;
import frontend.components.panels.structogram.statements.loop.PreCheckLoopVBox;
import frontend.components.scenes.EditorScene;
import javafx.geometry.Insets;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import testclasses.frontend.javafx.editorpanel.EditorPanelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SelectionTests extends EditorPanelTests {

    Background selectedBg= new Background(new BackgroundFill(Color.rgb(123, 162, 199), CornerRadii.EMPTY, Insets.EMPTY));
    Background standingOnBg = new Background(new BackgroundFill(Color.rgb(209, 225, 240), CornerRadii.EMPTY, Insets.EMPTY));
    Background emptyBg = Background.EMPTY;

    @Start
    private void start(Stage stage) {
        StructogramFactory.generateNewStructogram("a", "a", FuncType.VOID);
        stage = GuiManager.openEditorStage();
        statementPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStatementPanel();
        structogramPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStructogramPanel();
        stage.show();
    }

    @Test
    public void addStatement(FxRobot robot){
        insertSimpleDeclaration(robot, "string", "decl_test");
        SingleRowStatementVBox declVBox = (SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0);

        assertEquals(SelectionManager.getSelectedList(), structogramPanel.getStructogramWrapperVBox());
        assertEquals(SelectionManager.getSelectedStatement(), declVBox);
        assertEquals(declVBox.getBackground(), selectedBg);
    }

    @Test
    public void addStatements(FxRobot robot){
        insertSimpleDeclaration(robot, "string", "decl_test");
        insertForLoop(robot, "i", "0", "n");
        PreCheckLoopVBox forLoopVBox = (PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(1);

        assertEquals(SelectionManager.getSelectedList(), structogramPanel.getStructogramWrapperVBox());
        assertEquals(SelectionManager.getSelectedStatement(), forLoopVBox);
        assertEquals(forLoopVBox.getBackground(), selectedBg);
    }

    @Test
    public void addStatementsBefore(FxRobot robot){
        chooseChoiceBoxItem(robot, "#insertTypeChoiceBox", "before");
        insertSimpleDeclaration(robot, "string", "decl_test");
        insertIfWithElseBranch(robot, "test");
        IfVBox ifVBox = (IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0);

        assertEquals(SelectionManager.getSelectedList(), structogramPanel.getStructogramWrapperVBox());
        assertEquals(SelectionManager.getSelectedStatement(), ifVBox);
        assertEquals(ifVBox.getBackground(), selectedBg);
    }

    @Test
    public void addStatementsInsteadOf(FxRobot robot){
        chooseChoiceBoxItem(robot, "#insertTypeChoiceBox", "instead of");
        insertSimpleDeclaration(robot, "string", "decl_test");
        insertIfWithElseBranch(robot, "test");
        IfVBox ifVBox = (IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0);

        assertEquals(SelectionManager.getSelectedList(), structogramPanel.getStructogramWrapperVBox());
        assertEquals(SelectionManager.getSelectedStatement(), ifVBox);
        assertEquals(ifVBox.getBackground(), selectedBg);
    }

    @Test
    public void addStatementsDeselectLast(FxRobot robot){
        insertSimpleDeclaration(robot, "string", "decl_test");
        insertIfWithElseBranch(robot, "test");
        insertForLoop(robot, "i", "0", "n");
        PreCheckLoopVBox forLoopVBox = (PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(2);
        robot
                .moveTo(forLoopVBox)
                .moveBy(0, -(forLoopVBox.getHeight()/2 - 10))
                .clickOn(MouseButton.PRIMARY)
                .moveBy(-200, 0);

        assertNull(SelectionManager.getSelectedList());
        assertNull(SelectionManager.getSelectedStatement());
        assertEquals(forLoopVBox.getBackground(), emptyBg);
    }

    @Test
    public void addStatementsDeselectLastLeaveMouseOnThat(FxRobot robot){
        insertSimpleDeclaration(robot, "string", "decl_test");
        insertIfWithElseBranch(robot, "test");
        insertForLoop(robot, "i", "0", "n");
        PreCheckLoopVBox forLoopVBox = (PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(2);
        robot
                .moveTo(forLoopVBox)
                .moveBy(0, -(forLoopVBox.getHeight()/2 - 10))
                .clickOn(MouseButton.PRIMARY);

        assertNull(SelectionManager.getSelectedList());
        assertNull(SelectionManager.getSelectedStatement());
        assertEquals(forLoopVBox.getBackground(), standingOnBg);
    }

    @Test
    public void addStatementsSelectMiddle(FxRobot robot){
        insertSimpleDeclaration(robot, "string", "decl_test");
        insertIfWithElseBranch(robot, "test");
        insertForLoop(robot, "i", "0", "n");
        IfVBox ifVBox = (IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(1);
        robot
                .moveTo(ifVBox)
                .moveBy(0, -(ifVBox.getHeight()/2 - 10))
                .clickOn(MouseButton.PRIMARY);

        assertEquals(SelectionManager.getSelectedList(), structogramPanel.getStructogramWrapperVBox());
        assertEquals(SelectionManager.getSelectedStatement(), ifVBox);
        assertEquals(ifVBox.getBackground(), selectedBg);
    }

    @Test
    public void addStatementsSelectLastsStatementList(FxRobot robot){
        insertSimpleDeclaration(robot, "string", "decl_test");
        insertIfWithElseBranch(robot, "test");
        insertForLoop(robot, "i", "0", "n");
        PreCheckLoopVBox forLoopVBox = (PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(2);

        robot
                .moveTo(forLoopVBox)
                .moveBy(0, forLoopVBox.getHeight()/2 - 5)
                .clickOn(MouseButton.PRIMARY);

        assertEquals(SelectionManager.getSelectedList(), forLoopVBox.getStatementListVBox());
        assertNull(SelectionManager.getSelectedStatement());
        assertEquals(forLoopVBox.getStatementListVBox().getBackground(), selectedBg);
    }

    @Test
    public void addStatementsStandOnLastsStatementList(FxRobot robot){
        insertSimpleDeclaration(robot, "string", "decl_test");
        insertIfWithElseBranch(robot, "test");
        insertForLoop(robot, "i", "0", "n");
        PreCheckLoopVBox forLoopVBox = (PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(2);

        robot
                .moveTo(forLoopVBox)
                .moveBy(0, forLoopVBox.getHeight()/2 - 5);

        assertEquals(SelectionManager.getSelectedList(), structogramPanel.getStructogramWrapperVBox());
        assertEquals(SelectionManager.getSelectedStatement(), forLoopVBox);
        assertEquals(forLoopVBox.getBackground(), selectedBg);
        assertEquals(forLoopVBox.getStatementListVBox().getBackground(), standingOnBg);
    }

    @Test
    public void addStatementsAddMiddleListItem(FxRobot robot){
        insertSimpleDeclaration(robot, "string", "decl_test");
        insertIfWithElseBranch(robot, "test");
        insertForLoop(robot, "i", "0", "n");
        IfVBox ifVBox = (IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(1);
        robot
                .moveTo(ifVBox)
                .moveBy(-10, ifVBox.getHeight()/2 - 5)
                .clickOn(MouseButton.PRIMARY);

        insertArrayDefinition(robot, "int", "test_array", "value", 3);

        SingleRowStatementVBox arrayDefVBox = (SingleRowStatementVBox) ifVBox.getStatementListVBoxArrayList().get(0).getChildren().get(0);

        assertEquals(SelectionManager.getSelectedList(), ifVBox.getStatementListVBoxArrayList().get(0));
        assertEquals(SelectionManager.getSelectedStatement(), arrayDefVBox);
        assertEquals(arrayDefVBox.getBackground(), selectedBg);
    }

    @Test
    public void addStatementsAddMiddleListItems(FxRobot robot){
        insertSimpleDeclaration(robot, "string", "decl_test");
        insertIfWithElseBranch(robot, "test");
        insertForLoop(robot, "i", "0", "n");
        IfVBox ifVBox = (IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(1);
        robot
                .moveTo(ifVBox)
                .moveBy(-10, ifVBox.getHeight()/2 - 5)
                .clickOn(MouseButton.PRIMARY);

        insertArrayDefinition(robot, "int", "test_array", "value", 3);
        insertDoWhileLoop(robot, "test_condition");

        AfterCheckLoopVBox doWhileVBox = (AfterCheckLoopVBox) ifVBox.getStatementListVBoxArrayList().get(0).getChildren().get(1);

        assertEquals(SelectionManager.getSelectedList(), ifVBox.getStatementListVBoxArrayList().get(0));
        assertEquals(SelectionManager.getSelectedStatement(), doWhileVBox);
        assertEquals(doWhileVBox.getBackground(), selectedBg);
    }

    @Test
    public void addStatementsAddMiddleListItemsStandOnParentStatement(FxRobot robot){
        insertSimpleDeclaration(robot, "string", "decl_test");
        insertIfWithElseBranch(robot, "test");
        insertForLoop(robot, "i", "0", "n");
        IfVBox ifVBox = (IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(1);
        robot
                .moveTo(ifVBox)
                .moveBy(-10, ifVBox.getHeight()/2 - 5)
                .clickOn(MouseButton.PRIMARY);

        insertArrayDefinition(robot, "int", "test_array", "value", 3);
        insertDoWhileLoop(robot, "test_condition");

        robot
                .moveTo(ifVBox)
                .moveBy(0, -(ifVBox.getHeight()/2 - 10));

        AfterCheckLoopVBox doWhileVBox = (AfterCheckLoopVBox) ifVBox.getStatementListVBoxArrayList().get(0).getChildren().get(1);

        assertEquals(SelectionManager.getSelectedList(), ifVBox.getStatementListVBoxArrayList().get(0));
        assertEquals(SelectionManager.getSelectedStatement(), doWhileVBox);
        assertEquals(doWhileVBox.getBackground(), selectedBg);
        assertEquals(ifVBox.getBackground(), standingOnBg);
    }

    @Test
    public void addStatementsAddMiddleListItemsAddItemToLastInnerList(FxRobot robot){
        insertSimpleDeclaration(robot, "string", "decl_test");
        insertIfWithElseBranch(robot, "test");
        insertForLoop(robot, "i", "0", "n");
        IfVBox ifVBox = (IfVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(1);
        robot
                .moveTo(ifVBox)
                .moveBy(-10, ifVBox.getHeight()/2 - 5)
                .clickOn(MouseButton.PRIMARY);

        insertArrayDefinition(robot, "int", "test_array", "value", 3);
        insertDoWhileLoop(robot, "test_condition");

        AfterCheckLoopVBox doWhileVBox = (AfterCheckLoopVBox) ifVBox.getStatementListVBoxArrayList().get(0).getChildren().get(1);

        robot
                .moveTo(doWhileVBox)
                .moveBy(0, -(doWhileVBox.getHeight()/2 - 10))
                .clickOn(MouseButton.PRIMARY);

        insertReturn(robot, "TRUE", false);

        SingleRowStatementVBox returnVBox = (SingleRowStatementVBox) doWhileVBox.getStatementListVBox().getChildren().get(0);

        assertEquals(SelectionManager.getSelectedList(), doWhileVBox.getStatementListVBox());
        assertEquals(SelectionManager.getSelectedStatement(), returnVBox);
        assertEquals(returnVBox.getBackground(), selectedBg);
    }
}
