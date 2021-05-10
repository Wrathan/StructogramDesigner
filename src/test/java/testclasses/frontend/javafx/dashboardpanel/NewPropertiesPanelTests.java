package testclasses.frontend.javafx.dashboardpanel;

import backend.entities.statements.varoperations.declaration.ArrayDeclaration;
import backend.entities.statements.varoperations.declaration.SimpleDeclaration;
import backend.enums.FuncType;
import backend.enums.VarType;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.stages.DashboardStage;
import frontend.stages.EditorStage;
import frontend.stages.popup.structogramproperties.NewPropertiesStage;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.matcher.control.LabeledMatchers;

import static org.junit.jupiter.api.Assertions.*;

public class NewPropertiesPanelTests extends DashboardPanelTests {

    private final String FILE_NAME = "test_file";
    private final String FUNC_NAME = "test_func";
    private final FuncType FUNC_TYPE = FuncType.VOID;
    private final String PRECONDITION = "test_precond";
    private final String POSTCONDITION = "test_postcond";
    private final String PARAM_NAME = "test_param";
    private final String ERROR_MESSAGE = "Please fill all mandatory fields.";

    @BeforeEach
    private void clickOnNewButton(FxRobot robot){
        robot.clickOn("#newButton");
    }

    @Test
    public void openNewPropertiesPageAndClose(FxRobot robot){
        robot
                .clickOn("#cancelButton");
        assertNull(getTopModalStage(robot));
        assertTrue(GuiManager.getScreen() instanceof DashboardStage);
    }

    @Test
    public void checkFileNameLabelText(FxRobot robot){
        FxAssert.verifyThat("#fileNameLabel", LabeledMatchers.hasText("File name:"));
    }

    @Test
    public void checkFuncNameLabelText(FxRobot robot){
        FxAssert.verifyThat("#funcNameLabel", LabeledMatchers.hasText("Function name:"));
    }

    @Test
    public void checkFuncTypeLabelText(FxRobot robot){
        FxAssert.verifyThat("#typeLabel", LabeledMatchers.hasText("Type:"));
    }

    @Test
    public void checkOptionalLabelText(FxRobot robot){
        FxAssert.verifyThat("#optionalLabel", LabeledMatchers.hasText("Optional elements"));
    }

    @Test
    public void checkPrecondLabelText(FxRobot robot){
        FxAssert.verifyThat("#precondLabel", LabeledMatchers.hasText("Precondition:"));
    }

    @Test
    public void checkPostcondLabelText(FxRobot robot){
        FxAssert.verifyThat("#postcondLabel", LabeledMatchers.hasText("Postcondition:"));
    }

    @Test
    public void newPropertiesFullEmpty(FxRobot robot){
        robot
                .clickOn("#okButton");
        checkErrorStage(robot, ERROR_MESSAGE);
    }

    @Test
    public void newPropertiesOnlyFileNameFilled(FxRobot robot){
        robot
                .clickOn("#fileNameTextField")
                .write(FILE_NAME)
                .clickOn("#okButton");
        checkErrorStage(robot, ERROR_MESSAGE);
    }

    @Test
    public void newPropertiesEmptyFuncName(FxRobot robot){
        robot
                .clickOn("#fileNameTextField")
                .write(FILE_NAME)
                .clickOn("#typeChoiceBox")
                .type(KeyCode.ENTER)
                .clickOn("#okButton");
        checkErrorStage(robot, ERROR_MESSAGE);
    }

    @Test
    public void newPropertiesEmptyFuncType(FxRobot robot){
        robot
                .clickOn("#fileNameTextField")
                .write(FILE_NAME)
                .clickOn("#funcNameTextField")
                .write(FUNC_NAME)
                .clickOn("#okButton");
        checkErrorStage(robot, ERROR_MESSAGE);
    }

    @Test
    public void newPropertiesEmptyOkErrorStage(FxRobot robot){
        robot
                .clickOn("#okButton")
                .clickOn("#errorButton");
        assertTrue(getTopModalStage(robot) instanceof NewPropertiesStage);
    }

    @Test
    public void newPropertiesFilledCorrectlyEmptyParameterList(FxRobot robot){
        addMandatoryFuncPropertiesToOpenPropertiesPanel(robot, FILE_NAME, FUNC_NAME, FUNC_TYPE);
        robot.clickOn("#okButton");
        assertNull(getTopModalStage(robot));
        assertTrue(GuiManager.getScreen() instanceof EditorStage);
    }

    @Test
    public void newPropertiesFilledCorrectlyNotEmptyParameterList(FxRobot robot){
        addMandatoryFuncPropertiesToOpenPropertiesPanel(robot, FILE_NAME, FUNC_NAME, FUNC_TYPE);
        addAllTypesOfParametersToOpenPropertiesPanel(robot, PARAM_NAME);
        robot.clickOn("#okButton");
        assertNull(getTopModalStage(robot));
        assertTrue(GuiManager.getScreen() instanceof EditorStage);
    }

    @Test
    public void checkCorrectParametersFieldValues(FxRobot robot){
        addAllTypesOfParametersToOpenPropertiesPanel(robot, PARAM_NAME);

        for (int i = 0; i < VarType.values().length; i++){
            int finalI = i;
            FxAssert.verifyThat("#paramTypeChoiceBox" + (finalI + 1), (ChoiceBox choiceBox) -> {
                String text = choiceBox.getValue().toString();
                return text.equals(VarType.values()[finalI].toString());
            });
            FxAssert.verifyThat("#paramTextField" + (finalI + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals(PARAM_NAME + (finalI + 1));
            });
            FxAssert.verifyThat("#isArrayCheckBox" + (finalI + 1), (CheckBox checkBox) ->{
                String text = checkBox.getText();
                return text.equals("[]");
            });
            if (finalI == 0 || finalI == VarType.values().length/2 || finalI == VarType.values().length - 1){
                assertTrue(robot.lookup("#isArrayCheckBox" + (finalI + 1)).queryAs(CheckBox.class).isSelected());
            } else {
                assertFalse(robot.lookup("#isArrayCheckBox" + (finalI + 1)).queryAs(CheckBox.class).isSelected());
            }
        }
    }

    @Test
    public void checkCorrectParametersFieldValuesDeleteFirstItem(FxRobot robot){
        addAllTypesOfParametersToOpenPropertiesPanel(robot, PARAM_NAME);
        robot.clickOn("#removeParamButton1");

        for (int i = 0; i < VarType.values().length - 1; i++){
            int finalI = i;
            FxAssert.verifyThat("#paramTypeChoiceBox" + (finalI + 1), (ChoiceBox choiceBox) -> {
                String text = choiceBox.getValue().toString();
                return text.equals(VarType.values()[finalI + 1].toString());
            });
            FxAssert.verifyThat("#paramTextField" + (finalI + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals(PARAM_NAME + (finalI + 2));
            });
            FxAssert.verifyThat("#isArrayCheckBox" + (finalI + 1), (CheckBox checkBox) ->{
                String text = checkBox.getText();
                return text.equals("[]");
            });
            if (finalI == VarType.values().length/2 - 1 || finalI == VarType.values().length - 2){
                assertTrue(robot.lookup("#isArrayCheckBox" + (finalI + 1)).queryAs(CheckBox.class).isSelected());
            } else {
                assertFalse(robot.lookup("#isArrayCheckBox" + (finalI + 1)).queryAs(CheckBox.class).isSelected());
            }
        }
    }

    @Test
    public void checkCorrectParametersFieldValuesDeleteMiddleItem(FxRobot robot) {
        int deletedRow = VarType.values().length/2 + 1;

        addAllTypesOfParametersToOpenPropertiesPanel(robot, PARAM_NAME);
        robot.clickOn("#removeParamButton" + deletedRow);

        for (int i = 0; i < VarType.values().length - 1; i++){
            int finalI = i;
            if (i < deletedRow - 1){
                FxAssert.verifyThat("#paramTypeChoiceBox" + (finalI + 1), (ChoiceBox choiceBox) -> {
                    String text = choiceBox.getValue().toString();
                    return text.equals(VarType.values()[finalI].toString());
                });
                FxAssert.verifyThat("#paramTextField" + (finalI + 1), (TextField textField) -> {
                    String text = textField.getText();
                    return text.equals(PARAM_NAME + (finalI + 1));
                });
            } else {
                FxAssert.verifyThat("#paramTypeChoiceBox" + (finalI + 1), (ChoiceBox choiceBox) -> {
                    String text = choiceBox.getValue().toString();
                    return text.equals(VarType.values()[finalI + 1].toString());
                });
                FxAssert.verifyThat("#paramTextField" + (finalI + 1), (TextField textField) -> {
                    String text = textField.getText();
                    return text.equals(PARAM_NAME + (finalI + 2));
                });
            }
            FxAssert.verifyThat("#isArrayCheckBox" + (finalI + 1), (CheckBox checkBox) ->{
                String text = checkBox.getText();
                return text.equals("[]");
            });
            if (finalI == 0 || finalI == VarType.values().length - 2){
                assertTrue(robot.lookup("#isArrayCheckBox" + (finalI + 1)).queryAs(CheckBox.class).isSelected());
            } else {
                assertFalse(robot.lookup("#isArrayCheckBox" + (finalI + 1)).queryAs(CheckBox.class).isSelected());
            }
        }
    }

    @Test
    public void checkCorrectParametersFieldValuesDeleteLastItem(FxRobot robot) {
        int deletedRow = VarType.values().length;

        addAllTypesOfParametersToOpenPropertiesPanel(robot, PARAM_NAME);
        robot.clickOn("#removeParamButton" + deletedRow);

        for (int i = 0; i < VarType.values().length - 1; i++){
            int finalI = i;
            FxAssert.verifyThat("#paramTypeChoiceBox" + (finalI + 1), (ChoiceBox choiceBox) -> {
                String text = choiceBox.getValue().toString();
                return text.equals(VarType.values()[finalI].toString());
            });
            FxAssert.verifyThat("#paramTextField" + (finalI + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals(PARAM_NAME + (finalI + 1));
            });
            FxAssert.verifyThat("#isArrayCheckBox" + (finalI + 1), (CheckBox checkBox) ->{
                String text = checkBox.getText();
                return text.equals("[]");
            });
            if (finalI == 0 || finalI == VarType.values().length/2){
                assertTrue(robot.lookup("#isArrayCheckBox" + (finalI + 1)).queryAs(CheckBox.class).isSelected());
            } else {
                assertFalse(robot.lookup("#isArrayCheckBox" + (finalI + 1)).queryAs(CheckBox.class).isSelected());
            }
        }
    }

    @Test
    public void checkCorrectFuncFieldValues(FxRobot robot){
        addMandatoryFuncPropertiesToOpenPropertiesPanel(robot, FILE_NAME, FUNC_NAME, FUNC_TYPE);

        FxAssert.verifyThat("#fileNameTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(FILE_NAME);
        });
        FxAssert.verifyThat("#funcNameTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(FUNC_NAME);
        });
        FxAssert.verifyThat("#typeChoiceBox", (ChoiceBox choiceBox) -> {
            String text = choiceBox.getValue().toString();
            return text.equals(robot.lookup("#typeChoiceBox").queryAs(ChoiceBox.class).getItems().get(2));
        });
    }

    @Test
    public void newPropertiesFuncPropertiesEmptyParameterMandatoryFieldNotFilled(FxRobot robot){
        robot
                .clickOn("#addParameterButton")
                .clickOn("#paramTypeChoiceBox1")
                .type(KeyCode.ENTER)
                .clickOn("#addParameterButton")
                .clickOn("#paramTextField2")
                .write(PARAM_NAME)
                .clickOn("#addParameterButton")
                .clickOn("#isArrayCheckBox2")
                .clickOn("#okButton");
        checkErrorStage(robot, ERROR_MESSAGE);
    }

    @Test
    public void newPropertiesFuncPropertiesNotEmptyOnlyParameterChoiceBoxFilled(FxRobot robot){
        addMandatoryFuncPropertiesToOpenPropertiesPanel(robot, FILE_NAME, FUNC_NAME, FUNC_TYPE);
        robot
                .clickOn("#addParameterButton")
                .clickOn("#paramTypeChoiceBox1")
                .type(KeyCode.ENTER)
                .clickOn("#okButton");
        checkErrorStage(robot, ERROR_MESSAGE);
    }

    @Test
    public void newPropertiesFuncPropertiesNotEmptyOnlyParameterTextFieldFilled(FxRobot robot){
        addMandatoryFuncPropertiesToOpenPropertiesPanel(robot, FILE_NAME, FUNC_NAME, FUNC_TYPE);
        robot
                .clickOn("#addParameterButton")
                .clickOn("#paramTextField1")
                .write(PARAM_NAME)
                .clickOn("#okButton");
        checkErrorStage(robot, ERROR_MESSAGE);
    }

    @Test
    public void newPropertiesFuncPropertiesNotEmptyOnlyIsArrayCheckBoxFilled(FxRobot robot){
        addMandatoryFuncPropertiesToOpenPropertiesPanel(robot, FILE_NAME, FUNC_NAME, FUNC_TYPE);
        robot
                .clickOn("#addParameterButton")
                .clickOn("#isArrayCheckBox1")
                .clickOn("#okButton");
        checkErrorStage(robot, ERROR_MESSAGE);
    }

    @Test
    public void newPropertiesFuncPropertiesNotEmptyOnlyParameterChoiceBoxNotFilled(FxRobot robot){
        addMandatoryFuncPropertiesToOpenPropertiesPanel(robot, FILE_NAME, FUNC_NAME, FUNC_TYPE);
        robot
                .clickOn("#addParameterButton")
                .clickOn("#paramTextField1")
                .write(PARAM_NAME)
                .clickOn("#isArrayCheckBox1")
                .clickOn("#okButton");
        checkErrorStage(robot, ERROR_MESSAGE);
    }

    @Test
    public void newPropertiesFuncPropertiesNotEmptyOnlyParameterTextFieldNotFilled(FxRobot robot){
        addMandatoryFuncPropertiesToOpenPropertiesPanel(robot, FILE_NAME, FUNC_NAME, FUNC_TYPE);
        robot
                .clickOn("#addParameterButton")
                .clickOn("#paramTypeChoiceBox1")
                .type(KeyCode.ENTER)
                .clickOn("#isArrayCheckBox1")
                .clickOn("#okButton");
        checkErrorStage(robot, ERROR_MESSAGE);
    }

    @Test
    public void newPropertiesFuncPropertiesNotEmptyOnlyArrayCheckBoxNotFilled(FxRobot robot){
        addMandatoryFuncPropertiesToOpenPropertiesPanel(robot, FILE_NAME, FUNC_NAME, FUNC_TYPE);
        robot
                .clickOn("#addParameterButton")
                .clickOn("#paramTypeChoiceBox1")
                .type(KeyCode.ENTER)
                .clickOn("#paramTextField1")
                .write(PARAM_NAME)
                .clickOn("#okButton");
        assertNull(getTopModalStage(robot));
        assertTrue(GuiManager.getScreen() instanceof EditorStage);
    }

    @Test
    public void newPropertiesFuncPropertiesNotEmptyEveryParameterFieldFilled(FxRobot robot){
        addMandatoryFuncPropertiesToOpenPropertiesPanel(robot, FILE_NAME, FUNC_NAME, FUNC_TYPE);
        robot
                .clickOn("#addParameterButton")
                .clickOn("#paramTypeChoiceBox1")
                .type(KeyCode.ENTER)
                .clickOn("#paramTextField1")
                .write(PARAM_NAME)
                .clickOn("#isArrayCheckBox1")
                .clickOn("#okButton");
        assertNull(getTopModalStage(robot));
        assertTrue(GuiManager.getScreen() instanceof EditorStage);
    }

    @Test
    public void checkParameterNumber(FxRobot robot){
        addAllTypesOfParametersToOpenPropertiesPanel(robot, PARAM_NAME);

        assertEquals(((FlowPane) (((VBox) ((VBox) getTopModalStage(robot).getScene().getRoot().getChildrenUnmodifiable().get(1))
                .getChildren().get(2)).getChildren()).get(1)).getChildren().size(), VarType.values().length);
    }

    @Test
    public void generateNewStructogramWithoutParameters(FxRobot robot){
        addMandatoryFuncPropertiesToOpenPropertiesPanel(robot, FILE_NAME, FUNC_NAME, FUNC_TYPE);
        robot.clickOn("#okButton");
        assertNull(getTopModalStage(robot));
        assertNotNull(StructogramFactory.getStructogram());
        assertEquals(StructogramFactory.getStructogram().getFileName(), FILE_NAME);
        assertEquals(StructogramFactory.getStructogram().getFuncName(), FUNC_NAME);
        assertEquals(StructogramFactory.getStructogram().getParameterList().size(), 0);
    }

    @Test
    public void generateNewStructogramWithOneParameter(FxRobot robot){
        addMandatoryFuncPropertiesToOpenPropertiesPanel(robot, FILE_NAME, FUNC_NAME, FUNC_TYPE);
        robot
                .clickOn("#addParameterButton")
                .clickOn("#paramTypeChoiceBox1")
                .type(KeyCode.ENTER)
                .clickOn("#paramTextField1")
                .write(PARAM_NAME)
                .clickOn("#okButton");
        assertNull(getTopModalStage(robot));
        assertTrue(GuiManager.getScreen() instanceof EditorStage);
        assertNotNull(StructogramFactory.getStructogram());
        assertEquals(StructogramFactory.getStructogram().getFileName(), FILE_NAME);
        assertEquals(StructogramFactory.getStructogram().getFuncName(), FUNC_NAME);
        assertEquals(StructogramFactory.getStructogram().getFuncType(), FUNC_TYPE);
        assertEquals(StructogramFactory.getStructogram().getPrecondition(), "");
        assertEquals(StructogramFactory.getStructogram().getPostcondition(), "");
        assertEquals(StructogramFactory.getStructogram().getParameterList().size(), 1);
        assertTrue(StructogramFactory.getStructogram().getParameterList().get(0) instanceof SimpleDeclaration);
        assertEquals(StructogramFactory.getStructogram().getStatementList().size(), 0);

    }

    @Test
    public void generateNewStructogramWithAllPreAndPostcondition(FxRobot robot){
        addMandatoryFuncPropertiesToOpenPropertiesPanel(robot, FILE_NAME, FUNC_NAME, FUNC_TYPE);
        addPreAndPostConditionToOpenPropertiesPanel(robot, PRECONDITION, POSTCONDITION);
        robot.clickOn("#okButton");
        assertNull(getTopModalStage(robot));
        assertTrue(GuiManager.getScreen() instanceof EditorStage);
        assertNotNull(StructogramFactory.getStructogram());
        assertEquals(StructogramFactory.getStructogram().getFileName(), FILE_NAME);
        assertEquals(StructogramFactory.getStructogram().getFuncName(), FUNC_NAME);
        assertEquals(StructogramFactory.getStructogram().getPrecondition(), PRECONDITION);
        assertEquals(StructogramFactory.getStructogram().getPostcondition(), POSTCONDITION);
        assertEquals(StructogramFactory.getStructogram().getParameterList().size(), 0);
        assertEquals(StructogramFactory.getStructogram().getStatementList().size(), 0);
    }

    @Test
    public void generateNewStructogramWithAllTypesParameters(FxRobot robot){
        addMandatoryFuncPropertiesToOpenPropertiesPanel(robot, FILE_NAME, FUNC_NAME, FUNC_TYPE);
        robot.clickOn("#okButton");
        assertNull(getTopModalStage(robot));
        assertTrue(GuiManager.getScreen() instanceof EditorStage);
        assertNotNull(StructogramFactory.getStructogram());
        assertEquals(StructogramFactory.getStructogram().getFileName(), FILE_NAME);
        assertEquals(StructogramFactory.getStructogram().getFuncName(), FUNC_NAME);
        assertEquals(StructogramFactory.getStructogram().getPrecondition(), "");
        assertEquals(StructogramFactory.getStructogram().getPostcondition(), "");
        assertEquals(StructogramFactory.getStructogram().getParameterList().size(), VarType.values().length);
        assertEquals(StructogramFactory.getStructogram().getStatementList().size(), 0);

        for (int i = 0; i < VarType.values().length; i++){
            if (i == 0 || i == VarType.values().length/2 || i == VarType.values().length - 1){
                assertTrue(StructogramFactory.getStructogram().getParameterList().get(i) instanceof ArrayDeclaration);
            } else {
                assertTrue(StructogramFactory.getStructogram().getParameterList().get(i) instanceof SimpleDeclaration);
            }
        }
    }

    @Test
    public void generateNewStructogramWithInitializeAllField(FxRobot robot){
        addMandatoryFuncPropertiesToOpenPropertiesPanel(robot, FILE_NAME, FUNC_NAME, FUNC_TYPE);
        addPreAndPostConditionToOpenPropertiesPanel(robot, PRECONDITION, POSTCONDITION);
        addAllTypesOfParametersToOpenPropertiesPanel(robot, PARAM_NAME);
        robot.clickOn("#okButton");
        assertNull(getTopModalStage(robot));
        assertTrue(GuiManager.getScreen() instanceof EditorStage);
        assertNotNull(StructogramFactory.getStructogram());
        assertEquals(StructogramFactory.getStructogram().getFileName(), FILE_NAME);
        assertEquals(StructogramFactory.getStructogram().getFuncName(), FUNC_NAME);
        assertEquals(StructogramFactory.getStructogram().getPrecondition(), PRECONDITION);
        assertEquals(StructogramFactory.getStructogram().getPostcondition(), POSTCONDITION);
        assertEquals(StructogramFactory.getStructogram().getParameterList().size(), VarType.values().length);
        assertEquals(StructogramFactory.getStructogram().getStatementList().size(), 0);

        for (int i = 0; i < VarType.values().length; i++){
            if (i == 0 || i == VarType.values().length/2 || i == VarType.values().length - 1){
                assertTrue(StructogramFactory.getStructogram().getParameterList().get(i) instanceof ArrayDeclaration);
            } else {
                assertTrue(StructogramFactory.getStructogram().getParameterList().get(i) instanceof SimpleDeclaration);
            }
        }
    }
}
