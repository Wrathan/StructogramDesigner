package testclasses.frontend.javafx;

import backend.entities.structograms.Structogram;
import backend.enums.FuncType;
import backend.enums.VarType;
import frontend.stages.popup.ConfirmStage;
import frontend.stages.popup.ErrorStage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableRow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.matcher.control.LabeledMatchers;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
public abstract class TestFXBase {

    @AfterEach
    public void afterEachTest(FxRobot robot){
        robot.release(new KeyCode[]{});
        robot.release(new MouseButton[]{});
    }

    protected Stage getTopModalStage(FxRobot robot) {
        final List<Window> allWindows = new ArrayList<>(robot.listTargetWindows());
        Collections.reverse(allWindows);

        return (Stage) allWindows
                .stream()
                .filter(window -> window instanceof Stage)
                .filter(window -> ((Stage) window).getModality() == Modality.APPLICATION_MODAL)
                .findFirst()
                .orElse(null);
    }

    protected void checkErrorStage(FxRobot robot, String message) {
        assertTrue(getTopModalStage(robot) instanceof ErrorStage);
        FxAssert.verifyThat(robot.lookup("#messageLabel"), LabeledMatchers.hasText(message));
    }

    protected void checkConfirmStage(FxRobot robot, String message) {
        assertTrue(getTopModalStage(robot) instanceof ConfirmStage);
        FxAssert.verifyThat(robot.lookup("#messageLabel"), LabeledMatchers.hasText(message));
    }

    protected void chooseChoiceBoxItem(FxRobot robot, String choiceBoxId, String item) throws NullPointerException{
        boolean isFound = false;
        int i = 0;

        ChoiceBox choiceBox = robot.lookup(choiceBoxId).queryAs(ChoiceBox.class);
        while(!isFound && i < choiceBox.getItems().size()) {
            if (choiceBox.getItems().get(i).toString().equals(item)){
                isFound = true;
            } else {
                i += 1;
            }
        }
        if (isFound){
            if (choiceBox.getValue() == null){
                i += 1;
            }
            robot
                    .clickOn(choiceBoxId)
                    .type(KeyCode.DOWN, i)
                    .type(KeyCode.ENTER);
        } else {
            throw new NullPointerException();
        }
    }

    protected void chooseTableViewItem(FxRobot robot, String fileName){
        TableRow row;
        Set rowSet = robot.lookup(".table-row-cell").queryAllAs(TableRow.class);
        for (Iterator it = rowSet.iterator(); it.hasNext();){
            row = (TableRow) it.next();
            if (((Structogram) row.getItem()).getFileName().equals(fileName)){
                robot.clickOn(row);
                break;
            }
        }
    }

    protected void chooseTableViewItem(FxRobot robot, int idx){
        TableRow row;
        int counter = 0;
        Set rowSet = robot.lookup(".table-row-cell").queryAllAs(TableRow.class);
        for (Iterator it = rowSet.iterator(); it.hasNext();){
            row = (TableRow) it.next();
            if (counter == idx){
                robot.clickOn(row);
                break;
            } else {
                counter++;
            }
        }
    }

    protected void addAllTypesOfParametersToOpenPropertiesPanel(FxRobot robot, String parameterName){
        for (int i = 0; i < VarType.values().length; i++){
            robot
                    .clickOn("#addParameterButton")
                    .clickOn("#paramTypeChoiceBox" + (i + 1));
            for (int j = 0; j < i; j++){
                robot.type(KeyCode.DOWN);
            }
            robot
                    .type(KeyCode.ENTER)
                    .clickOn("#paramTextField" + (i + 1))
                    .write(parameterName + (i + 1));
        }
        robot
                .clickOn("#isArrayCheckBox" + 1)
                .clickOn("#isArrayCheckBox" + (VarType.values().length/2 + 1))
                .clickOn("#isArrayCheckBox" + (VarType.values().length));
    }

    protected void initParameterToOpenPropertiesPanel(FxRobot robot, String parameterType, String parameterName, boolean isArray, int idx){
        chooseChoiceBoxItem(robot, "#paramTypeChoiceBox" + (idx + 1), parameterType);

        robot
                .clickOn("#paramTextField" + (idx + 1))
                .write(parameterName);

        if (isArray){
            robot
                    .clickOn("#isArrayCheckBox" + (idx + 1));
        }
    }

    protected void addMandatoryFuncPropertiesToOpenPropertiesPanel(FxRobot robot, String filename, String funcName, FuncType funcType){
        robot
                .clickOn("#fileNameTextField")
                .write(filename)
                .clickOn("#funcNameTextField")
                .write(funcName);
        chooseChoiceBoxItem(robot, "#typeChoiceBox", funcType.toString());
    }

    protected void addPreAndPostConditionToOpenPropertiesPanel(FxRobot robot, String precondition, String postcondition){
        robot
                .clickOn("#precondTextField")
                .write(precondition)
                .clickOn("#postcondTextField")
                .write(postcondition);
    }
}