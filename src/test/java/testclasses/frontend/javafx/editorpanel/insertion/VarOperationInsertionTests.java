package testclasses.frontend.javafx.editorpanel.insertion;

import backend.entities.statements.varoperations.assignment.ArrayAssignment;
import backend.entities.statements.varoperations.assignment.ArrayItemAssignment;
import backend.entities.statements.varoperations.assignment.SimpleAssignment;
import backend.entities.statements.varoperations.declaration.ArrayDeclaration;
import backend.entities.statements.varoperations.declaration.ArrayDeclarationWithSize;
import backend.entities.statements.varoperations.declaration.SimpleDeclaration;
import backend.entities.statements.varoperations.definition.ArrayDefinition;
import backend.entities.statements.varoperations.definition.SimpleDefinition;
import backend.enums.FuncType;
import backend.enums.VarType;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.components.panels.structogram.statements.SingleRowStatementVBox;
import frontend.components.scenes.EditorScene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import static org.junit.jupiter.api.Assertions.*;

public class VarOperationInsertionTests extends InsertionTests {

    @Start
    protected void start(Stage stage) {
        StructogramFactory.generateNewStructogram("a", "a", FuncType.VOID);
        stage = GuiManager.openEditorStage();
        statementPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStatementPanel();
        structogramPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStructogramPanel();
        stage.show();
    }

    private void checkArrayDefinitionFieldValuesDeleteItem(FxRobot robot, String arrayType, String varName, String arrayVarName, int size, int deletedRow){
        initArrayDefinition(robot, arrayType, varName, arrayVarName, size);
        checkArrayItemFieldsValuesDeleteItem(robot, arrayType, varName, arrayVarName, size, deletedRow);
    }

    private void checkArrayAssignmentFieldValuesDeleteItem(FxRobot robot, String arrayType, String varName, String arrayVarName, int size, int deletedRow){
        initArrayAssignment(robot, arrayType, varName, arrayVarName, size);
        checkArrayItemFieldsValuesDeleteItem(robot, arrayType, varName, arrayVarName, size, deletedRow);
    }

    private void checkArrayItemFieldsValuesDeleteItem(FxRobot robot, String arrayType, String varName, String arrayVarName, int size, int deletedRow){
        robot.clickOn("#removeArrayItemButton" + deletedRow);

        FxAssert.verifyThat("#varTypeChoiceBox", (ChoiceBox choiceBox) -> {
            String text = choiceBox.getValue().toString();
            return text.equals(arrayType);
        });
        FxAssert.verifyThat("#varNameTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(varName);
        });
        FxAssert.verifyThat("#sizeTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(Integer.toString(size - 1));
        });
        for (int i = 0; i < size - 1; i++){
            int finalI = i;
            FxAssert.verifyThat("#arrayItemTextField" + (i + 1), (TextField textField) -> {
                String text = textField.getText();
                if (finalI < deletedRow - 1){
                    return text.equals(arrayVarName + (finalI + 1));
                } else {
                    return text.equals(arrayVarName + (finalI + 2));
                }
            });
        }
    }

    private void addArrayAssignmentDeleteItem(FxRobot robot, String arrayType, String varName, String arrayVarName, int size, int deletedRow){
        StringBuilder itemListString = new StringBuilder();

        initArrayAssignment(robot, arrayType, varName, arrayVarName, size);
        robot
                .clickOn("#removeArrayItemButton" + (deletedRow))
                .clickOn("#insertButton");

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof ArrayAssignment);
        assertTrue(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof ArrayAssignment);
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof SingleRowStatementVBox);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((ArrayAssignment) StructogramFactory.getStructogram().getStatementList().get(0)).getVarType(), VarType.valueOf(arrayType.toUpperCase()));
        assertEquals(((ArrayAssignment) StructogramFactory.getStructogram().getStatementList().get(0)).getVarName(), varName);
        assertEquals(((ArrayAssignment) StructogramFactory.getStructogram().getStatementList().get(0)).getValueList().size(), size - 1);
        for (int i = 0; i < size - 1; i++){
            if (i < deletedRow - 1){
                assertEquals(((ArrayAssignment) StructogramFactory.getStructogram().getStatementList().get(0)).getValueList().get(i), arrayVarName + (i + 1));
                if (itemListString.toString().equals("")) {
                    itemListString.append(arrayVarName).append(i + 1);
                } else {
                    itemListString.append(", ").append(arrayVarName).append(i + 1);
                }
            } else {
                assertEquals(((ArrayAssignment) StructogramFactory.getStructogram().getStatementList().get(0)).getValueList().get(i), arrayVarName + (i + 2));
                if (itemListString.toString().equals("")) {
                    itemListString.append(arrayVarName).append(i + 2);
                } else {
                    itemListString.append(", ").append(arrayVarName).append(i + 2);
                }
            }
        }
        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertEquals(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementLabel().getText(),
                varName + " = " + "[" + itemListString.toString() + "]");
    }

    private void addArrayDefinitionDeleteItem(FxRobot robot, String arrayType, String varName, String arrayVarName, int size, int deletedRow){
        StringBuilder itemListString = new StringBuilder();

        initArrayDefinition(robot, arrayType, varName, arrayVarName, size);
        robot
                .clickOn("#removeArrayItemButton" + (deletedRow))
                .clickOn("#insertButton");
        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof ArrayDefinition);
        assertTrue(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof ArrayDefinition);
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof SingleRowStatementVBox);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((ArrayDefinition) StructogramFactory.getStructogram().getStatementList().get(0)).getVarType(), VarType.valueOf(arrayType.toUpperCase()));
        assertEquals(((ArrayDefinition) StructogramFactory.getStructogram().getStatementList().get(0)).getVarName(), varName);
        assertEquals(((ArrayDefinition) StructogramFactory.getStructogram().getStatementList().get(0)).getValueList().size(), size - 1);

        for (int i = 0; i < size - 1; i++){
            if (i < deletedRow - 1){
                assertEquals(((ArrayDefinition) StructogramFactory.getStructogram().getStatementList().get(0)).getValueList().get(i), arrayVarName + (i + 1));
                if (itemListString.toString().equals("")) {
                    itemListString.append(arrayVarName).append(i + 1);
                } else {
                    itemListString.append(", ").append(arrayVarName).append(i + 1);
                }
            } else {
                assertEquals(((ArrayDefinition) StructogramFactory.getStructogram().getStatementList().get(0)).getValueList().get(i), arrayVarName + (i + 2));
                if (itemListString.toString().equals("")) {
                    itemListString.append(arrayVarName).append(i + 2);
                } else {
                    itemListString.append(", ").append(arrayVarName).append(i + 2);
                }
            }
        }

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertEquals(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementLabel().getText(),
                arrayType + "[" + (size - 1) + "] " + varName + " = " + "[" + itemListString.toString() + "]");
    }

    private String getListStringFromItems(String valueName, int size){
        StringBuilder sb = new StringBuilder();
        sb.append(valueName).append(1);
        for (int i = 1; i < size; i++){
            sb.append(", ").append(valueName).append(i + 1);
        }
        return sb.toString();
    }

    @Test
    public void checkVarOperationPropertiesPanelTextCorrectness(FxRobot robot){
        robot.clickOn("#addVarOpButton");
        FxAssert.verifyThat("#operationTypeLabel", LabeledMatchers.hasText("Operation type:"));
        FxAssert.verifyThat("#simpleRadioButton", LabeledMatchers.hasText("Simple"));
        FxAssert.verifyThat("#arrayRadioButton", LabeledMatchers.hasText("Array"));
    }

    @Test
    public void checkDefaultVarType(FxRobot robot){
        assertEquals(robot.lookup("#operationTypeChoiceBox").queryAs(ChoiceBox.class).getValue().toString(), "Definition");
        assertTrue(robot.lookup("#simpleRadioButton").queryAs(RadioButton.class).isSelected());
        assertFalse(robot.lookup("#arrayRadioButton").queryAs(RadioButton.class).isSelected());
    }

    @Test
    public void chooseInvalidVarType(FxRobot robot){
        assertThrows(NullPointerException.class, () -> {
            chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Invalid");
        });
    }

    @Test
    public void checkSimpleDefinitionFieldValues(FxRobot robot){
        initSimpleDefinition(robot, "boolean", TEST_VAR_NAME, TEST_STRING);
        FxAssert.verifyThat("#varTypeChoiceBox", (ChoiceBox choiceBox) -> {
            String text = choiceBox.getValue().toString();
            return text.equals("boolean");
        });
        FxAssert.verifyThat("#varNameTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_VAR_NAME);
        });
        FxAssert.verifyThat("#varValueTextField", (TextField textField) -> {
            String text = textField.getText();;
            return text.equals(TEST_STRING);
        });
    }

    @Test
    public void addSimpleDefinition(FxRobot robot){
        String actualType = "string";
        insertSimpleDefinition(robot, actualType, TEST_VAR_NAME, TEST_STRING);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof SimpleDefinition);
        assertTrue(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof SimpleDefinition);
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof SingleRowStatementVBox);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((SimpleDefinition) StructogramFactory.getStructogram().getStatementList().get(0)).getVarType(), VarType.valueOf(actualType.toUpperCase()));
        assertEquals(((SimpleDefinition) StructogramFactory.getStructogram().getStatementList().get(0)).getVarName(), TEST_VAR_NAME);
        assertEquals(((SimpleDefinition) StructogramFactory.getStructogram().getStatementList().get(0)).getVarValue(), TEST_STRING);

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertEquals(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementLabel().getText(),
                actualType + " " + TEST_VAR_NAME + " = " + TEST_STRING);
    }

    @Test
    public void checkSimpleDeclarationFieldValues(FxRobot robot){
        initSimpleDeclaration(robot, "char", TEST_VAR_NAME);
        FxAssert.verifyThat("#varTypeChoiceBox", (ChoiceBox choiceBox) -> {
            String text = choiceBox.getValue().toString();
            return text.equals("char");
        });
        FxAssert.verifyThat("#varNameTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_VAR_NAME);
        });
    }

    @Test
    public void addSimpleDeclaration(FxRobot robot){
        String actualType = "int";
        insertSimpleDeclaration(robot, actualType, TEST_VAR_NAME);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof SimpleDeclaration);
        assertTrue(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof SimpleDeclaration);
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof SingleRowStatementVBox);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((SimpleDeclaration) StructogramFactory.getStructogram().getStatementList().get(0)).getVarType(), VarType.valueOf(actualType.toUpperCase()));
        assertEquals(((SimpleDeclaration) StructogramFactory.getStructogram().getStatementList().get(0)).getVarName(), TEST_VAR_NAME);

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertEquals(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementLabel().getText(), actualType + " " + TEST_VAR_NAME);
    }

    @Test
    public void checkSimpleAssignmentFieldValues(FxRobot robot){
        initSimpleAssignment(robot, TEST_VAR_NAME, TEST_DOUBLE);
        FxAssert.verifyThat("#varNameTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_VAR_NAME);
        });
        FxAssert.verifyThat("#varValueTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_DOUBLE);
        });
    }

    @Test
    public void addSimpleAssignment(FxRobot robot){
        insertSimpleAssignment(robot, TEST_VAR_NAME, TEST_CHAR);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof SimpleAssignment);
        assertTrue(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof SimpleAssignment);
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof SingleRowStatementVBox);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((SimpleAssignment) StructogramFactory.getStructogram().getStatementList().get(0)).getVarName(), TEST_VAR_NAME);
        assertEquals(((SimpleAssignment) StructogramFactory.getStructogram().getStatementList().get(0)).getVarValue(), TEST_CHAR);

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertEquals(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementLabel().getText(), TEST_VAR_NAME + " = " + TEST_CHAR);
    }

    @Test
    public void addSimpleDefinitionEmptyTypeChoiceBox(FxRobot robot){
        robot
                .clickOn("#varNameTextField")
                .write(TEST_VAR_NAME)
                .clickOn("#varValueTextField")
                .write(TEST_STRING)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addSimpleDefinitionEmptyNameTextField(FxRobot robot){
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", "string");
        robot
                .clickOn("#varValueTextField")
                .write(TEST_STRING)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addSimpleDefinitionEmptyValueTextField(FxRobot robot){
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", "string");
        robot
                .clickOn("#varNameTextField")
                .write(TEST_VAR_NAME)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addSimpleDeclarationEmptyTypeChoiceBox(FxRobot robot){
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Declaration");
        robot
                .clickOn("#varNameTextField")
                .write(TEST_VAR_NAME)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addSimpleDeclarationEmptyNameTextField(FxRobot robot){
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Declaration");
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", "double");
        robot
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addSimpleAssignmentEmptyValueTextField(FxRobot robot){
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Assignment");
        robot
                .clickOn("#varNameTextField")
                .write(TEST_VAR_NAME)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addSimpleAssignmentEmptyNameTextField(FxRobot robot){
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Assignment");
        robot
                .clickOn("#varValueTextField")
                .write(TEST_STRING)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void checkArrayDefinitionFieldValues(FxRobot robot){
        String arrayType = "double";
        int size = 5;
        initArrayDefinition(robot, arrayType, TEST_VAR_NAME, TEST_ARRAY_VALUE, size);
        FxAssert.verifyThat("#varTypeChoiceBox", (ChoiceBox choiceBox) -> {
            String text = choiceBox.getValue().toString();
            return text.equals(arrayType);
        });
        FxAssert.verifyThat("#varNameTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_VAR_NAME);
        });
        FxAssert.verifyThat("#sizeTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(Integer.toString(size));
        });
        for (int i = 0; i < size; i++){
            int finalI = i;
            FxAssert.verifyThat("#arrayItemTextField" + (i + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals(TEST_ARRAY_VALUE + (finalI + 1));
            });
        }
    }

    @Test
    public void checkArrayDefinitionFieldValuesDeleteMiddleItem(FxRobot robot){
        int size = 5;
        checkArrayDefinitionFieldValuesDeleteItem(robot, "double", TEST_VAR_NAME, TEST_ARRAY_VALUE, size, size/2 + 1);
    }

    @Test
    public void checkArrayDefinitionFieldValuesDeleteFirstItem(FxRobot robot){
        checkArrayDefinitionFieldValuesDeleteItem(robot, "double", TEST_VAR_NAME, TEST_ARRAY_VALUE, 4, 1);
    }

    @Test
    public void checkArrayDefinitionFieldValuesDeleteLastItem(FxRobot robot){
        int size = 3;
        checkArrayDefinitionFieldValuesDeleteItem(robot, "double", TEST_VAR_NAME, TEST_ARRAY_VALUE, size, size);
    }

    @Test
    public void checkArrayDefinitionFieldValuesReInitItems(FxRobot robot){
        int size = 4;
        initArrayDefinition(robot, "char", TEST_VAR_NAME, TEST_ARRAY_VALUE, size);
        robot.lookup("#sizeTextField").queryAs(TextField.class).clear();
        robot
                .clickOn("#sizeTextField")
                .write(Integer.toString(size))
                .clickOn("#initArrayButton");
        for (int i = 0; i < size; i++){
            FxAssert.verifyThat("#arrayItemTextField" + (i + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals("");
            });
        }
    }

    @Test
    public void checkArrayDefinitionFieldValuesReInitItemsSizeUp(FxRobot robot){
        int size = 4;
        initArrayDefinition(robot, "char", TEST_VAR_NAME, TEST_ARRAY_VALUE, size);
        robot.lookup("#sizeTextField").queryAs(TextField.class).clear();
        robot
                .clickOn("#sizeTextField")
                .write(Integer.toString(size + 3))
                .clickOn("#initArrayButton");
        for (int i = 0; i < size + 3; i++){
            FxAssert.verifyThat("#arrayItemTextField" + (i + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals("");
            });
        }
    }

    @Test
    public void checkArrayDefinitionFieldValuesReInitItemsSizeDown(FxRobot robot){
        int size = 4;
        initArrayDefinition(robot, "char", TEST_VAR_NAME, TEST_ARRAY_VALUE, size);
        robot.lookup("#sizeTextField").queryAs(TextField.class).clear();
        robot
                .clickOn("#sizeTextField")
                .write(Integer.toString(size - 2))
                .clickOn("#initArrayButton");
        for (int i = 0; i < size - 2; i++){
            FxAssert.verifyThat("#arrayItemTextField" + (i + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals("");
            });
        }
    }

    @Test
    public void checkArrayDefinitionFieldValuesModifyItemSizeUp(FxRobot robot){
        int size = 4;
        initArrayDefinition(robot, "char", TEST_VAR_NAME, TEST_ARRAY_VALUE, size);
        robot.lookup("#sizeTextField").queryAs(TextField.class).clear();
        robot
                .clickOn("#sizeTextField")
                .write(Integer.toString(size + 2))
                .clickOn("#modifySizeButton");
        for (int i = 0; i < size + 2; i++){
            if (i < size){
                int finalI = i;
                FxAssert.verifyThat("#arrayItemTextField" + (i + 1), (TextField textField) -> {
                    String text = textField.getText();
                    return text.equals(TEST_ARRAY_VALUE + (finalI + 1));
                });
            } else {
                FxAssert.verifyThat("#arrayItemTextField" + (i + 1), (TextField textField) -> {
                    String text = textField.getText();
                    return text.equals("");
                });
            }
        }
    }

    @Test
    public void checkArrayDefinitionFieldValuesModifyItemSizeDown(FxRobot robot){
        int size = 4;
        initArrayDefinition(robot, "char", TEST_VAR_NAME, TEST_ARRAY_VALUE, size);
        robot.lookup("#sizeTextField").queryAs(TextField.class).clear();
        robot
                .clickOn("#sizeTextField")
                .write(Integer.toString(size -2))
                .clickOn("#modifySizeButton");
        for (int i = 0; i < size - 2; i++){
            int finalI = i;
            FxAssert.verifyThat("#arrayItemTextField" + (i + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals(TEST_ARRAY_VALUE + (finalI + 1));
            });
        }
    }

    @Test
    public void addArrayDefinition(FxRobot robot){
        String arrayType = "double";
        int size = 5;

        insertArrayDefinition(robot, arrayType, TEST_VAR_NAME, TEST_ARRAY_VALUE, size);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof ArrayDefinition);
        assertTrue(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof ArrayDefinition);
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof SingleRowStatementVBox);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((ArrayDefinition) StructogramFactory.getStructogram().getStatementList().get(0)).getVarType(), VarType.valueOf(arrayType.toUpperCase()));
        assertEquals(((ArrayDefinition) StructogramFactory.getStructogram().getStatementList().get(0)).getVarName(), TEST_VAR_NAME);
        assertEquals(((ArrayDefinition) StructogramFactory.getStructogram().getStatementList().get(0)).getValueList().size(), size);
        for (int i = 0; i < size; i++){
            assertEquals(((ArrayDefinition) StructogramFactory.getStructogram().getStatementList().get(0)).getValueList().get(i), TEST_ARRAY_VALUE + (i + 1));
        }

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertEquals(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementLabel().getText(),
                arrayType + "[" + size + "]" + " " + TEST_VAR_NAME + " = " + "[" + getListStringFromItems(TEST_ARRAY_VALUE, size) + "]");
    }

    @Test
    public void addArrayDefinitionDeleteFirstItem(FxRobot robot){
        addArrayDefinitionDeleteItem(robot, "string", TEST_VAR_NAME, TEST_ARRAY_VALUE, 3, 1);
    }

    @Test
    public void addArrayDefinitionDeleteMiddleItem(FxRobot robot){
        int size = 3;
        addArrayDefinitionDeleteItem(robot, "string", TEST_VAR_NAME, TEST_ARRAY_VALUE, size, size/2 + 1);
    }

    @Test
    public void addArrayDefinitionDeleteLast(FxRobot robot){
        int size = 3;
        addArrayDefinitionDeleteItem(robot, "string", TEST_VAR_NAME, TEST_ARRAY_VALUE, size, size);
    }

    @Test
    public void addArrayDefinitionEmptyItemTextField(FxRobot robot){
        String arrayType = "double";
        robot.clickOn("#arrayRadioButton");
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", arrayType);
        robot
                .clickOn("#varNameTextField")
                .write(TEST_VAR_NAME)
                .clickOn("#sizeTextField")
                .write(Integer.toString(2))
                .clickOn("#initArrayButton")
                .clickOn("#arrayItemTextField1")
                .write(TEST_ARRAY_VALUE + 1)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addArrayDefinitionEmptyVarType(FxRobot robot){
        robot.clickOn("#arrayRadioButton");
        robot
                .clickOn("#varNameTextField")
                .write(TEST_VAR_NAME)
                .clickOn("#sizeTextField")
                .write(Integer.toString(2))
                .clickOn("#initArrayButton")
                .clickOn("#arrayItemTextField1")
                .write(TEST_ARRAY_VALUE + 1)
                .clickOn("#arrayItemTextField2")
                .write(TEST_ARRAY_VALUE + 2)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addArrayDefinitionEmptyVarName(FxRobot robot){
        String arrayType = "char";
        robot.clickOn("#arrayRadioButton");
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", arrayType);
        robot
                .clickOn("#sizeTextField")
                .write(Integer.toString(2))
                .clickOn("#initArrayButton")
                .clickOn("#arrayItemTextField1")
                .write(TEST_ARRAY_VALUE + 1)
                .clickOn("#arrayItemTextField2")
                .write(TEST_ARRAY_VALUE + 2)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addArrayDefinitionItemsNotInitialized(FxRobot robot){
        String arrayType = "boolean";
        robot.clickOn("#arrayRadioButton");
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", arrayType);
        robot
                .clickOn("#varNameTextField")
                .write(TEST_VAR_NAME)
                .clickOn("#sizeTextField")
                .write(Integer.toString(2))
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addArrayDefinitionDataDeleteItems(FxRobot robot){
        String type = "boolean";
        initArrayDefinition(robot, type, TEST_VAR_NAME, TEST_ARRAY_VALUE, 3);
        robot
                .clickOn("#delArrayButton")
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addArrayDefinitionDataAddItemAfterInitItems(FxRobot robot){
        String type = "boolean";
        int size = 3;
        initArrayDefinition(robot, type, TEST_VAR_NAME, TEST_ARRAY_VALUE, size);
        robot
                .clickOn("#addItemButton")
                .clickOn("#arrayItemTextField" + (size + 1))
                .write(TEST_ARRAY_VALUE + (size + 1));
        FxAssert.verifyThat("#arrayItemTextField" + (size + 1), (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_ARRAY_VALUE + (size + 1));
        });
        FxAssert.verifyThat("#sizeTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(Integer.toString(size + 1));
        });
    }

    @Test
    public void checkArrayDeclarationFieldValues(FxRobot robot){
        initArrayDeclarationWithSize(robot, "char", TEST_VAR_NAME, TEST_ARRAY_SIZE);
        FxAssert.verifyThat("#varTypeChoiceBox", (ChoiceBox choiceBox) -> {
            String text = choiceBox.getValue().toString();
            return text.equals("char");
        });
        FxAssert.verifyThat("#varNameTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_VAR_NAME);
        });
        FxAssert.verifyThat("#sizeTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_ARRAY_SIZE);
        });
    }

    @Test
    public void addArrayDeclaration(FxRobot robot){
        String arrayType = "char";

        insertArrayDeclarationWithSize(robot, arrayType, TEST_VAR_NAME, TEST_ARRAY_SIZE);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof ArrayDeclarationWithSize);
        assertTrue(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof ArrayDeclarationWithSize);
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof SingleRowStatementVBox);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((ArrayDeclaration) StructogramFactory.getStructogram().getStatementList().get(0)).getVarType(), VarType.valueOf(arrayType.toUpperCase()));
        assertEquals(((ArrayDeclaration) StructogramFactory.getStructogram().getStatementList().get(0)).getVarName(), TEST_VAR_NAME);

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertEquals(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementLabel().getText(),
                arrayType + "["+ TEST_ARRAY_SIZE +"]" + " " + TEST_VAR_NAME);
    }

    @Test
    public void addArrayDeclarationEmptyType(FxRobot robot){
        robot.clickOn("#addVarOpButton");
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Declaration");
        robot
                .clickOn("#arrayRadioButton");
        robot
                .clickOn("#varNameTextField")
                .write(TEST_VAR_NAME)
                .clickOn("#sizeTextField")
                .write(TEST_ARRAY_SIZE)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addArrayDeclarationEmptyName(FxRobot robot){
        robot.clickOn("#addVarOpButton");
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Declaration");
        robot
                .clickOn("#arrayRadioButton");
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", "boolean");
        robot
                .clickOn("#sizeTextField")
                .write(TEST_ARRAY_SIZE)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addArrayDeclarationEmptySize(FxRobot robot){
        robot.clickOn("#addVarOpButton");
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Declaration");
        robot
                .clickOn("#arrayRadioButton");
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", "boolean");
        robot
                .clickOn("#varNameTextField")
                .write(TEST_VAR_NAME)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void checkArrayAssignmentFieldValues(FxRobot robot){
        String arrayType = "boolean";
        int size = 4;
        initArrayAssignment(robot, arrayType, TEST_VAR_NAME, TEST_ARRAY_VALUE, 4);
        FxAssert.verifyThat("#varNameTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_VAR_NAME);
        });
        FxAssert.verifyThat("#varTypeChoiceBox", (ChoiceBox choiceBox) -> {
            String text = choiceBox.getValue().toString();
            return text.equals(arrayType);
        });
        FxAssert.verifyThat("#sizeTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(Integer.toString(size));
        });
        for (int i = 0; i < size; i++){
            int finalI = i;
            FxAssert.verifyThat("#arrayItemTextField" + (i + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals(TEST_ARRAY_VALUE + (finalI + 1));
            });
        }
    }

    @Test
    public void checkArrayAssignmentFieldValuesDeleteFirstItem(FxRobot robot){
        checkArrayAssignmentFieldValuesDeleteItem(robot, "char", TEST_VAR_NAME, TEST_ARRAY_VALUE, 5, 1);
    }

    @Test
    public void checkArrayAssignmentFieldValuesDeleteMiddleItem(FxRobot robot){
        int size = 4;
        checkArrayAssignmentFieldValuesDeleteItem(robot, "char", TEST_VAR_NAME, TEST_ARRAY_VALUE, size, size/2 + 1);
    }

    @Test
    public void checkArrayAssignmentFieldValuesDeleteLastItem(FxRobot robot){
        int size = 4;
        checkArrayAssignmentFieldValuesDeleteItem(robot, "char", TEST_VAR_NAME, TEST_ARRAY_VALUE, size, size);
    }

    @Test
    public void checkArrayAssignmentFieldValuesReInitItems(FxRobot robot){
        int size = 4;
        initArrayAssignment(robot, "char", TEST_VAR_NAME, TEST_ARRAY_VALUE, size);
        robot.lookup("#sizeTextField").queryAs(TextField.class).clear();
        robot
                .clickOn("#sizeTextField")
                .write(Integer.toString(size))
                .clickOn("#initArrayButton");
        for (int i = 0; i < size; i++){
            FxAssert.verifyThat("#arrayItemTextField" + (i + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals("");
            });
        }
    }

    @Test
    public void checkArrayAssignmentFieldValuesReInitItemsSizeUp(FxRobot robot){
        int size = 4;
        initArrayAssignment(robot, "char", TEST_VAR_NAME, TEST_ARRAY_VALUE, size);
        robot.lookup("#sizeTextField").queryAs(TextField.class).clear();
        robot
                .clickOn("#sizeTextField")
                .write(Integer.toString(size + 3))
                .clickOn("#initArrayButton");
        for (int i = 0; i < size + 3; i++){
            FxAssert.verifyThat("#arrayItemTextField" + (i + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals("");
            });
        }
    }

    @Test
    public void checkArrayAssignmentFieldValuesReInitItemsSizeDown(FxRobot robot){
        int size = 4;
        initArrayAssignment(robot, "char", TEST_VAR_NAME, TEST_ARRAY_VALUE, size);
        robot.lookup("#sizeTextField").queryAs(TextField.class).clear();
        robot
                .clickOn("#sizeTextField")
                .write(Integer.toString(size - 2))
                .clickOn("#initArrayButton");
        for (int i = 0; i < size - 2; i++){
            FxAssert.verifyThat("#arrayItemTextField" + (i + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals("");
            });
        }
    }

    @Test
    public void checkArrayAssignmentFieldValuesModifyItemSizeUp(FxRobot robot){
        int size = 4;
        initArrayAssignment(robot, "char", TEST_VAR_NAME, TEST_ARRAY_VALUE, size);
        robot.lookup("#sizeTextField").queryAs(TextField.class).clear();
        robot
                .clickOn("#sizeTextField")
                .write(Integer.toString(size + 2))
                .clickOn("#modifySizeButton");
        for (int i = 0; i < size + 2; i++){
            if (i < size){
                int finalI = i;
                FxAssert.verifyThat("#arrayItemTextField" + (i + 1), (TextField textField) -> {
                    String text = textField.getText();
                    return text.equals(TEST_ARRAY_VALUE + (finalI + 1));
                });
            } else {
                FxAssert.verifyThat("#arrayItemTextField" + (i + 1), (TextField textField) -> {
                    String text = textField.getText();
                    return text.equals("");
                });
            }
        }
    }

    @Test
    public void checkArrayAssignmentFieldValuesModifyItemSizeDown(FxRobot robot){
        int size = 4;
        initArrayAssignment(robot, "char", TEST_VAR_NAME, TEST_ARRAY_VALUE, size);
        robot.lookup("#sizeTextField").queryAs(TextField.class).clear();
        robot
                .clickOn("#sizeTextField")
                .write(Integer.toString(size -2))
                .clickOn("#modifySizeButton");
        for (int i = 0; i < size - 2; i++){
            int finalI = i;
            FxAssert.verifyThat("#arrayItemTextField" + (i + 1), (TextField textField) -> {
                String text = textField.getText();
                return text.equals(TEST_ARRAY_VALUE + (finalI + 1));
            });
        }
    }

    @Test
    public void addArrayAssignment(FxRobot robot){
        String arrayType = "double";
        int size = 5;

        insertArrayAssignment(robot, arrayType, TEST_VAR_NAME, TEST_ARRAY_VALUE, size);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof ArrayAssignment);
        assertTrue(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof ArrayAssignment);
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof SingleRowStatementVBox);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((ArrayAssignment) StructogramFactory.getStructogram().getStatementList().get(0)).getVarType(), VarType.valueOf(arrayType.toUpperCase()));
        assertEquals(((ArrayAssignment) StructogramFactory.getStructogram().getStatementList().get(0)).getVarName(), TEST_VAR_NAME);
        assertEquals(((ArrayAssignment) StructogramFactory.getStructogram().getStatementList().get(0)).getValueList().size(), size);

        for (int i = 0; i < size; i++){
            assertEquals(((ArrayAssignment) StructogramFactory.getStructogram().getStatementList().get(0)).getValueList().get(i), TEST_ARRAY_VALUE + (i + 1));
        }

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertEquals(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementLabel().getText(),
                TEST_VAR_NAME + " = " + "[" + getListStringFromItems(TEST_ARRAY_VALUE, size) +"]");
    }

    @Test
    public void addArrayAssignmentDeleteFirstItem(FxRobot robot){
        addArrayAssignmentDeleteItem(robot, "string", TEST_VAR_NAME, TEST_ARRAY_VALUE, 3, 1);
    }

    @Test
    public void addArrayAssignmentDeleteMiddleItem(FxRobot robot){
        int size = 7;
        addArrayAssignmentDeleteItem(robot, "string", TEST_VAR_NAME, TEST_ARRAY_VALUE, size, size/2 + 1);
    }

    @Test
    public void addArrayAssignmentDeleteLast(FxRobot robot){
        int size = 7;
        addArrayAssignmentDeleteItem(robot, "string", TEST_VAR_NAME, TEST_ARRAY_VALUE, size, size);
    }

    @Test
    public void addArrayAssignmentEmptyItemTextField(FxRobot robot){
        String arrayType = "int";
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Assignment");
        robot
                .clickOn("#arrayRadioButton")
                .clickOn("#varNameTextField")
                .write(TEST_VAR_NAME);
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", arrayType);
        robot
                .clickOn("#sizeTextField")
                .write(Integer.toString(2))
                .clickOn("#initArrayButton")
                .clickOn("#arrayItemTextField1")
                .write(TEST_ARRAY_VALUE + 1)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addArrayAssignmentEmptyVarType(FxRobot robot){
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Assignment");
        robot.clickOn("#arrayRadioButton");
        robot
                .clickOn("#varNameTextField")
                .write(TEST_VAR_NAME)
                .clickOn("#sizeTextField")
                .write(Integer.toString(2))
                .clickOn("#initArrayButton")
                .clickOn("#arrayItemTextField1")
                .write(TEST_ARRAY_VALUE + 1)
                .clickOn("#arrayItemTextField2")
                .write(TEST_ARRAY_VALUE + 2)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addArrayAssignmentEmptyVarName(FxRobot robot){
        String arrayType = "char";
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Assignment");
        robot
                .clickOn("#arrayRadioButton")
                .clickOn("#sizeTextField")
                .write(Integer.toString(2));
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", arrayType);
        robot
                .clickOn("#initArrayButton")
                .clickOn("#arrayItemTextField1")
                .write(TEST_ARRAY_VALUE + 1)
                .clickOn("#arrayItemTextField2")
                .write(TEST_ARRAY_VALUE + 2)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addArrayAssignmentItemsNotInitialized(FxRobot robot){
        String arrayType = "boolean";
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Assignment");
        robot
                .clickOn("#arrayRadioButton")
                .clickOn("#varNameTextField")
                .write(TEST_VAR_NAME);
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", arrayType);
        robot
                .clickOn("#sizeTextField")
                .write(Integer.toString(2))
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addArrayAssignmentDataDeleteItems(FxRobot robot){
        String type = "boolean";
        initArrayAssignment(robot, type, TEST_VAR_NAME, TEST_ARRAY_VALUE, 3);
        robot
                .clickOn("#delArrayButton")
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addArrayAssignmentDataAddItemAfterInitItems(FxRobot robot){
        String type = "boolean";
        int size = 3;
        initArrayAssignment(robot, type, TEST_VAR_NAME, TEST_ARRAY_VALUE, size);
        robot
                .clickOn("#addItemButton")
                .clickOn("#arrayItemTextField" + (size + 1))
                .write(TEST_ARRAY_VALUE + (size + 1));
        FxAssert.verifyThat("#arrayItemTextField" + (size + 1), (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_ARRAY_VALUE + (size + 1));
        });
        FxAssert.verifyThat("#sizeTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(Integer.toString(size + 1));
        });
    }

    @Test
    public void checkArrayItemFieldValues(FxRobot robot){
        initArrayItemAssignment(robot, TEST_VAR_NAME, TEST_INT, TEST_INDEX);
        FxAssert.verifyThat("#varNameTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_VAR_NAME);
        });
        FxAssert.verifyThat("#varValueTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_INT);
        });
        FxAssert.verifyThat("#itemIndexTextField", (TextField textField) -> {
            String text = textField.getText();
            return text.equals(TEST_INDEX);
        });
    }

    @Test
    public void addArrayItemAssignment(FxRobot robot){
        insertArrayItemAssignment(robot, TEST_VAR_NAME, TEST_STRING, TEST_INDEX);

        checkStructogramEntityAndFigureSize(1);
        assertTrue(StructogramFactory.getStructogram().getStatementList().get(0) instanceof ArrayItemAssignment);
        assertTrue(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity() instanceof ArrayItemAssignment);
        assertTrue(structogramPanel.getStructogramWrapperVBox().getChildren().get(0) instanceof SingleRowStatementVBox);
        assertSame(StructogramFactory.getStructogram().getStatementList().get(0),
                ((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementEntity());

        assertEquals(((ArrayItemAssignment) StructogramFactory.getStructogram().getStatementList().get(0)).getVarName(),TEST_VAR_NAME);
        assertEquals(((ArrayItemAssignment) StructogramFactory.getStructogram().getStatementList().get(0)).getVarValue(), TEST_STRING);
        assertEquals(((ArrayItemAssignment) StructogramFactory.getStructogram().getStatementList().get(0)).getIndex(), TEST_INDEX);

        assertTrue(structogramPanel.isStructogramFigureVisible());
        assertEquals(((SingleRowStatementVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(0)).getStatementLabel().getText(),
                TEST_VAR_NAME + "[" + TEST_INDEX + "]" + " = " + TEST_STRING);
    }

    @Test
    public void addArrayItemAssignmentEmptyVarName(FxRobot robot){
        robot.clickOn("#addVarOpButton");
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Assignment");
        robot
                .clickOn("#arrayRadioButton")
                .clickOn("#oneItemRadioButton")
                .clickOn("#varValueTextField")
                .write(TEST_CHAR)
                .clickOn("#itemIndexTextField")
                .write(TEST_INDEX)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addArrayItemAssignmentEmptyVarValue(FxRobot robot){
        robot.clickOn("#addVarOpButton");
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Assignment");
        robot
                .clickOn("#arrayRadioButton")
                .clickOn("#varNameTextField")
                .write(TEST_VAR_NAME)
                .clickOn("#oneItemRadioButton")
                .clickOn("#itemIndexTextField")
                .write(TEST_INDEX)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }

    @Test
    public void addArrayItemAssignmentEmptyIndex(FxRobot robot){
        robot.clickOn("#addVarOpButton");
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Assignment");
        robot
                .clickOn("#arrayRadioButton")
                .clickOn("#varNameTextField")
                .write(TEST_VAR_NAME)
                .clickOn("#oneItemRadioButton")
                .clickOn("#varValueTextField")
                .write(TEST_CHAR)
                .clickOn("#insertButton");
        checkErrorStage(robot, ERROR_MESSAGE);
        checkStructogramEntityAndFigureSize(0);
    }
}