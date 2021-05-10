package testclasses.frontend.javafx.editorpanel;

import backend.serialization.Serialize;
import frontend.components.factorys.StructogramFactory;
import frontend.components.panels.editor.StatementPanel;
import frontend.components.panels.editor.StructogramPanel;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import testclasses.frontend.javafx.TestFXBase;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public abstract class EditorPanelTests extends TestFXBase {

    protected StatementPanel statementPanel;
    protected StructogramPanel structogramPanel;

    protected void checkStructogramEntityAndFigureSize(int size){
        assertEquals(structogramPanel.getStructogramWrapperVBox().getChildren().size(), size);
        assertEquals(StructogramFactory.getStructogram().getStatementList().size(), size);
    }

    protected ArrayList<File> saveBasicStructogram(FxRobot robot) {
        ArrayList<File> files = new ArrayList<>();

        insertSimpleDeclaration(robot, "char", "test_var");

        robot
                .clickOn("#fileMenu")
                .clickOn("#saveMenuItem")
                .clickOn("#serializationButton");

        files.add(new File(Serialize.getConfigHomePath() + "/saves/a.ser"));
        files.add(new File (Serialize.getConfigHomePath() + "/saves/images/a.png"));

        return files;
    }

    protected ArrayList<File> saveBasicStructogramWithOneParameter(FxRobot robot, String parameterType, String parameterName, boolean isArray, int idx) {
        ArrayList<File> files = new ArrayList<>();

        insertSimpleDeclaration(robot, "char", "test_var");

        robot
                .clickOn("#modifyParamsButton")
                .clickOn("#addParameterButton");

        initParameterToOpenPropertiesPanel(robot, parameterType, parameterName, isArray, idx);

        robot
                .clickOn("#okButton")
                .clickOn("#fileMenu")
                .clickOn("#saveMenuItem")
                .clickOn("#serializationButton");

        files.add(new File(Serialize.getConfigHomePath() + "/saves/a.ser"));
        files.add(new File (Serialize.getConfigHomePath() + "/saves/images/a.png"));

        return files;
    }

    protected void initSimpleDefinition(FxRobot robot, String type, String name, String value){
        robot.clickOn("#addVarOpButton");
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Definition");
        robot.clickOn("#simpleRadioButton");
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", type);
        robot
                .clickOn("#varNameTextField")
                .write(name)
                .clickOn("#varValueTextField")
                .write(value);
    }

    protected void insertSimpleDefinition(FxRobot robot, String type, String name, String value){
        initSimpleDefinition(robot, type, name, value);
        robot.clickOn("#insertButton");
    }

    protected void initSimpleDeclaration(FxRobot robot, String type, String name){
        robot.clickOn("#addVarOpButton");
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Declaration");
        robot.clickOn("#simpleRadioButton");
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", type);
        robot
                .clickOn("#varNameTextField")
                .write(name);
    }

    protected void insertSimpleDeclaration(FxRobot robot, String type, String name){
        initSimpleDeclaration(robot, type, name);
        robot.clickOn("#insertButton");
    }

    protected void initSimpleAssignment(FxRobot robot, String name, String value){
        robot.clickOn("#addVarOpButton");
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Assignment");
        robot.clickOn("#simpleRadioButton");
        robot
                .clickOn("#varNameTextField")
                .write(name)
                .clickOn("#varValueTextField")
                .write(value);
    }

    protected void insertSimpleAssignment(FxRobot robot, String name, String value){
        initSimpleAssignment(robot, name, value);
        robot.clickOn("#insertButton");
    }

    protected void initArrayDefinition(FxRobot robot, String type, String name, String valueName, int size){
        robot
                .clickOn("#addVarOpButton")
                .clickOn("#arrayRadioButton");
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", type);
        robot
                .clickOn("#varNameTextField")
                .write(name)
                .clickOn("#sizeTextField")
                .write(Integer.toString(size))
                .clickOn("#initArrayButton");
        initArrayItems(robot, valueName, size);
    }

    protected void insertArrayDefinition(FxRobot robot, String type, String name, String valueName, int size){
        initArrayDefinition(robot, type, name, valueName, size);
        robot.clickOn("#insertButton");
    }

    protected void initArrayItems(FxRobot robot, String valueName, int size){
        for (int i = 0; i < size; i++){
            robot
                    .clickOn("#arrayItemTextField" + (i + 1))
                    .write(valueName + (i + 1));
        }
    }

    protected void initArrayDeclarationWithSize(FxRobot robot, String arrayType, String arrayName, String size){
        robot.clickOn("#addVarOpButton");
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Declaration");
        robot
                .clickOn("#arrayRadioButton");
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", arrayType);
        robot
                .clickOn("#varNameTextField")
                .write(arrayName)
                .clickOn("#sizeTextField")
                .write(size);
    }

    protected void insertArrayDeclarationWithSize(FxRobot robot, String arrayType, String arrayName, String size){
        initArrayDeclarationWithSize(robot, arrayType, arrayName, size);
        robot.clickOn("#insertButton");
    }

    protected void initArrayAssignment(FxRobot robot, String varType, String varName, String valueName, int size){
        robot.clickOn("#addVarOpButton");
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Assignment");
        robot.clickOn("#arrayRadioButton");
        robot
                .clickOn("#varNameTextField")
                .write(varName);
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", varType);
        robot
                .clickOn("#sizeTextField")
                .write(Integer.toString(size))
                .clickOn("#initArrayButton");
        initArrayItems(robot, valueName, size);
    }

    protected void insertArrayAssignment(FxRobot robot, String varType, String varName, String valueName, int size){
        initArrayAssignment(robot, varType, varName, valueName, size);
        robot.clickOn("#insertButton");
    }

    protected void initArrayItemAssignment(FxRobot robot, String varName, String varValue, String itemIndex){
        robot.clickOn("#addVarOpButton");
        chooseChoiceBoxItem(robot, "#operationTypeChoiceBox", "Assignment");
        robot
                .clickOn("#arrayRadioButton")
                .clickOn("#varNameTextField")
                .write(varName)
                .clickOn("#oneItemRadioButton")
                .clickOn("#varValueTextField")
                .write(varValue)
                .clickOn("#itemIndexTextField")
                .write(itemIndex);
    }

    protected void insertArrayItemAssignment(FxRobot robot, String varName, String varValue, String itemIndex){
        initArrayItemAssignment(robot, varName, varValue, itemIndex);
        robot.clickOn("#insertButton");
    }

    protected void initForLoop(FxRobot robot, String loopVarName, String start, String end){
        robot
                .clickOn("#addLoopButton")
                .clickOn("#varNameTextField")
                .write(loopVarName)
                .clickOn("#startIntTextField")
                .write(start)
                .clickOn("#endIntTextField")
                .write(end);
    }

    protected void insertForLoop(FxRobot robot, String loopVarName, String start, String end){
        initForLoop(robot, loopVarName, start, end);
        robot.clickOn("#insertButton");
    }

    protected void initForEachLoop(FxRobot robot, String type, String actualItemName, String arrayVarName){
        robot
                .clickOn("#addLoopButton");
        chooseChoiceBoxItem(robot, "#loopTypeChoiceBox", "for-each");
        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", type);
        robot
                .clickOn("#actualItemTextField")
                .write(actualItemName)
                .clickOn("#arrayVarTextField")
                .write(arrayVarName);
    }

    protected void insertForEachLoop(FxRobot robot, String type, String actualItemName, String arrayVarName){
        initForEachLoop(robot, type, actualItemName, arrayVarName);
        robot.clickOn("#insertButton");
    }

    protected void initWhileLoop(FxRobot robot, String condition){
        robot
                .clickOn("#addLoopButton");
        chooseChoiceBoxItem(robot, "#loopTypeChoiceBox", "while");
        robot
                .clickOn("#conditionTextField")
                .write(condition);
    }

    protected void insertWhileLoop(FxRobot robot, String condition){
        initWhileLoop(robot, condition);
        robot
                .clickOn("#whileRadioButton")
                .clickOn("#insertButton");
    }

    protected void insertDoWhileLoop(FxRobot robot, String condition){
        initWhileLoop(robot, condition);
        robot
                .clickOn("#doWhileRadioButton")
                .clickOn("#insertButton");
    }

    protected void initIfWithoutAnyBranches(FxRobot robot, String condition){
        robot
                .clickOn("#addCondStatButton")
                .clickOn("#conditionTextField")
                .write(condition);
    }

    protected void initIfWithElseBranch(FxRobot robot, String condition){
        robot
                .clickOn("#addCondStatButton")
                .clickOn("#conditionTextField")
                .write(condition)
                .clickOn("#elseBranchCheckBox");
    }

    protected void initIfWithElseIfBranches(FxRobot robot, String condition, String elseIfCondition, int numberOfElifs){
        robot
                .clickOn("#addCondStatButton")
                .clickOn("#conditionTextField")
                .write(condition);
        for(int i = 0; i < numberOfElifs; i++){
            robot
                    .clickOn("#addElseIfButton")
                    .clickOn("#optParamNameTextField" + (i + 1))
                    .write(elseIfCondition + (i + 1));
        }
    }

    protected void initIfWithElseIfAndElseBranches(FxRobot robot, String condition, String elseIfCondition, int numberOfElifs){
        robot
                .clickOn("#addCondStatButton")
                .clickOn("#conditionTextField")
                .write(condition)
                .clickOn("#elseBranchCheckBox");
        for(int i = 0; i < numberOfElifs; i++){
            robot
                    .clickOn("#addElseIfButton")
                    .clickOn("#optParamNameTextField" + (i + 1))
                    .write(elseIfCondition + (i + 1));
        }
    }

    protected void insertIfWithoutAnyBranches(FxRobot robot, String condition){
        initIfWithoutAnyBranches(robot, condition);
        robot.clickOn("#insertButton");
    }

    protected void insertIfWithElseBranch(FxRobot robot, String condition){
        initIfWithElseBranch(robot, condition);
        robot.clickOn("#insertButton");
    }

    protected void insertIfWithElseIfBranches(FxRobot robot, String condition, String elseIfCondition, int numberOfElifs){
        initIfWithElseIfBranches(robot, condition, elseIfCondition, numberOfElifs);
        robot.clickOn("#insertButton");
    }

    protected void insertIfWithElseIfAndElseBranches(FxRobot robot, String condition, String elseIfCondition, int numberOfElifs){
        initIfWithElseIfAndElseBranches(robot, condition, elseIfCondition, numberOfElifs);
        robot.clickOn("#insertButton");
    }

    protected void initSwitchCase(FxRobot robot, String expression, String caseValue, int numberOfCases, boolean isDefaultValue){
        robot.clickOn("#addCondStatButton");
        chooseChoiceBoxItem(robot, "#condStatementChoiceBox", "switch");
        robot
                .clickOn("#switchExprTextField")
                .write(expression);

        if (isDefaultValue){
            robot.clickOn("#defaultCaseCheckBox");
        }
        for (int i = 0; i < numberOfCases; i++){
            robot
                    .clickOn("#addCaseExprButton")
                    .clickOn("#optParamNameTextField" + (i + 1))
                    .write(caseValue + (i + 1));
        }
    }

    protected void insertSwitchCase(FxRobot robot, String expression, String caseValue, int numberOfCases, boolean isDefaultValue){
        initSwitchCase(robot, expression, caseValue, numberOfCases, isDefaultValue);
        robot.clickOn("#insertButton");
    }

    protected void initBreak(FxRobot robot){
        robot.clickOn("#addJumpStatButton");
        chooseChoiceBoxItem(robot,"#jumpStatTypeChoiceBox", "break");
    }

    protected void insertBreak(FxRobot robot){
        initBreak(robot);
        robot.clickOn("#insertButton");
    }

    protected void initContinue(FxRobot robot){
        robot.clickOn("#addJumpStatButton");
        chooseChoiceBoxItem(robot,"#jumpStatTypeChoiceBox", "continue");
    }

    protected void insertContinue(FxRobot robot){
        initContinue(robot);
        robot.clickOn("#insertButton");
    }

    protected void initReturn(FxRobot robot, String value, boolean isPostCheck){
        robot.clickOn("#addJumpStatButton");
        chooseChoiceBoxItem(robot,"#jumpStatTypeChoiceBox", "return");
        robot
                .clickOn("#expressionTextField")
                .write(value);

        if (isPostCheck){
            robot.clickOn("#checkPostCondCheckBox");
        }
    }

    protected void insertReturn(FxRobot robot, String value, boolean isPostCheck){
        initReturn(robot, value, isPostCheck);
        robot.clickOn("#insertButton");
    }
}
