package testclasses.frontend.javafx.editorpanel.generationpanel;

import backend.enums.FuncType;
import backend.serialization.Serialize;
import frontend.GuiManager;
import frontend.components.factorys.StructogramFactory;
import frontend.components.panels.editor.propertiespanel.FunctionPropertiesPanel;
import frontend.components.panels.structogram.statements.condstat.IfVBox;
import frontend.components.panels.structogram.statements.loop.PreCheckLoopVBox;
import frontend.components.scenes.EditorScene;
import frontend.components.tableviews.FunctionTableView;
import javafx.scene.control.TableRow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import testclasses.frontend.javafx.editorpanel.EditorPanelTests;

import java.io.File;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodeGenerationTests extends EditorPanelTests {

    private void createEuclideanAlgorithm(FxRobot robot){
        robot
                .clickOn("#modifyParamsButton")
                .clickOn("#precondTextField")
                .write("a > 0 AND b > 0")
                .clickOn("#postcondTextField")
                .write("x > 0")
                .clickOn("#addParameterButton")
                .clickOn("#addParameterButton");

        chooseChoiceBoxItem(robot, "#paramTypeChoiceBox1", "int");
        chooseChoiceBoxItem(robot, "#paramTypeChoiceBox2", "int");

        robot
                .clickOn("#paramTextField1")
                .write("a")
                .clickOn("#paramTextField2")
                .write("b")
                .clickOn("#okButton");

        insertSimpleDefinition(robot, "int", "x", "a");
        insertSimpleDefinition(robot, "int", "y", "b");
        insertWhileLoop(robot, "x != y");

        PreCheckLoopVBox whileLoopVBox = (PreCheckLoopVBox) structogramPanel.getStructogramWrapperVBox().getChildren().get(2);

        robot
                .moveTo(whileLoopVBox)
                .moveBy(0, whileLoopVBox.getHeight()/2 - 5)
                .clickOn(MouseButton.PRIMARY);

        insertIfWithElseBranch(robot, "x > y");

        IfVBox ifVBox = (IfVBox) whileLoopVBox.getStatementListVBox().getChildren().get(0);

        robot
                .moveTo(ifVBox)
                .moveBy(-20, ifVBox.getHeight()/2 - 5)
                .clickOn(MouseButton.PRIMARY);

        insertSimpleAssignment(robot, "x", "x - y");

        robot
                .moveTo(ifVBox)
                .moveBy(20, ifVBox.getHeight()/2 - 5)
                .clickOn(MouseButton.PRIMARY);

        insertSimpleAssignment(robot, "y", "y - x");

        robot
                .moveTo(whileLoopVBox)
                .moveBy(0, -(whileLoopVBox.getHeight()/2 - 5))
                .clickOn(MouseButton.PRIMARY);

        insertReturn(robot, "x", true);
    }

    @Start
    private void start(Stage stage) {
        StructogramFactory.generateNewStructogram("a", "a", FuncType.INT);
        stage = GuiManager.openEditorStage();
        statementPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStatementPanel();
        structogramPanel = ((EditorScene) GuiManager.getScreen().getScene()).getStructogramPanel();
        stage.show();
    }

    @Test
    public void generateEuclideanAlgorithm(FxRobot robot){
        createEuclideanAlgorithm(robot);

        String javaResult = "int a(int a, int b) {\n" +
                "\tif (!(a > 0 && b > 0)){\n" +
                "\t\tthrow new IllegalArgumentException(\"Precondition failed.\");\n" +
                "\t}\n" +
                "\tint x = a;\n" +
                "\tint y = b;\n" +
                "\twhile (x != y) {\n" +
                "\t\tif (x > y) {\n" +
                "\t\t\tx = x - y;\n" +
                "\t\t} else {\n" +
                "\t\t\ty = y - x;\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\tif (x > 0){\n" +
                "\t\treturn x;\n" +
                "\t} else {\n" +
                "\t\tthrow new IllegalArgumentException(\"Postcondition failed.\")\n" +
                "\t}\n" +
                "}";

        String cppResult = "int a(int a, int b) {\n" +
                "\tif (!(a > 0 && b > 0)){\n" +
                "\t\tstd::invalid_argument(\"Precondition failed.\");\n" +
                "\t}\n" +
                "\tint x = a;\n" +
                "\tint y = b;\n" +
                "\twhile (x != y) {\n" +
                "\t\tif (x > y) {\n" +
                "\t\t\tx = x - y;\n" +
                "\t\t} else {\n" +
                "\t\t\ty = y - x;\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\tif (x > 0){\n" +
                "\t\treturn x;\n" +
                "\t} else {\n" +
                "\t\tstd::invalid_argument(\"Postcondition failed.\")\n" +
                "\t}\n" +
                "}";

        String pythonResult = "def a(a, b):\n" +
                "\tif not (a > 0 and b > 0):\n" +
                "\t\traise ValueError(\"Precondition failed.\")\n" +
                "\tx = a\n" +
                "\ty = b\n" +
                "\twhile x != y:\n" +
                "\t\tif x > y:\n" +
                "\t\t\tx = x - y\n" +
                "\t\telse:\n" +
                "\t\t\ty = y - x\n" +
                "\tif x > 0:\n" +
                "\t\treturn x\n" +
                "\telse:\n" +
                "\t\traise ValueError(\"Postcondition failed.\")\n";

        robot
                .clickOn("#generateButton")
                .clickOn("#javaButton");

        assertEquals(javaResult, robot.lookup("#codeArea").queryAs(CodeArea.class).getText());

        robot.clickOn("#cppButton");

        assertEquals(cppResult, robot.lookup("#codeArea").queryAs(CodeArea.class).getText());

        robot.clickOn("#pythonButton");

        assertEquals(pythonResult, robot.lookup("#codeArea").queryAs(CodeArea.class).getText());
    }

    @Test
    public void generateInnerFunctionCalls(FxRobot robot){
        createEuclideanAlgorithm(robot);

        robot
                .clickOn("#fileMenu")
                .clickOn("#saveMenuItem")
                .clickOn("#serializationButton")
                .clickOn("#fileMenu")
                .clickOn("#newMenuItem");

        addMandatoryFuncPropertiesToOpenPropertiesPanel(robot, "aa", "aa", FuncType.VOID);

        robot
                .clickOn("#okButton")
                .clickOn("#addFuncButton");

        FunctionTableView tableView = ((FunctionPropertiesPanel)((EditorScene) GuiManager.getScreen().getScene()).getStatementPanel().getStatementPropertiesPanel()).getFunctionTableView();

        robot
                .clickOn(tableView.lookupAll(".table-row-cell").iterator().next())
                .clickOn("#funcVarTextField1")
                .write("320")
                .clickOn("#funcVarTextField2")
                .write("125")
                .clickOn("#insertButton")
                .clickOn("#insertTypeChoiceBox")
                .type(KeyCode.DOWN, KeyCode.ENTER)
                .clickOn("#varNameTextField")
                .write("test_var1");

        chooseChoiceBoxItem(robot, "#varTypeChoiceBox", "int");

        robot
                .clickOn("#insertButton")
                .clickOn("#insertTypeChoiceBox")
                .type(KeyCode.DOWN, KeyCode.ENTER)
                .clickOn("#varNameTextField")
                .write("test_var2")
                .clickOn("#insertButton")
                .clickOn("#fileMenu")
                .clickOn("#saveMenuItem")
                .clickOn("#serializationButton")
                .clickOn("#addJumpStatButton")
                .clickOn("#addFuncButton");

        tableView = ((FunctionPropertiesPanel)((EditorScene) GuiManager.getScreen().getScene()).getStatementPanel().getStatementPropertiesPanel()).getFunctionTableView();

        Iterator iterator = tableView.lookupAll(".table-row-cell").iterator();
        iterator.next();

        robot
                .clickOn((TableRow) iterator.next())
                .clickOn("#insertButton");

        String javaResult = "int a(int a, int b) {\n" +
                "\tif (!(a > 0 && b > 0)){\n" +
                "\t\tthrow new IllegalArgumentException(\"Precondition failed.\");\n" +
                "\t}\n" +
                "\tint x = a;\n" +
                "\tint y = b;\n" +
                "\twhile (x != y) {\n" +
                "\t\tif (x > y) {\n" +
                "\t\t\tx = x - y;\n" +
                "\t\t} else {\n" +
                "\t\t\ty = y - x;\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\tif (x > 0){\n" +
                "\t\treturn x;\n" +
                "\t} else {\n" +
                "\t\tthrow new IllegalArgumentException(\"Postcondition failed.\")\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "void aa() {\n" +
                "\ta(320, 125);\n" +
                "\tint test_var1 = a(320, 125);\n" +
                "\ttest_var2 = a(320, 125);\n" +
                "\taa();\n" +
                "}";

        String cppResult = "int a(int a, int b) {\n" +
                "\tif (!(a > 0 && b > 0)){\n" +
                "\t\tstd::invalid_argument(\"Precondition failed.\");\n" +
                "\t}\n" +
                "\tint x = a;\n" +
                "\tint y = b;\n" +
                "\twhile (x != y) {\n" +
                "\t\tif (x > y) {\n" +
                "\t\t\tx = x - y;\n" +
                "\t\t} else {\n" +
                "\t\t\ty = y - x;\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\tif (x > 0){\n" +
                "\t\treturn x;\n" +
                "\t} else {\n" +
                "\t\tstd::invalid_argument(\"Postcondition failed.\")\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "void aa() {\n" +
                "\ta(320, 125);\n" +
                "\tint test_var1 = a(320, 125);\n" +
                "\ttest_var2 = a(320, 125);\n" +
                "\taa();\n" +
                "}";

        String pythonResult = "def a(a, b):\n" +
                "\tif not (a > 0 and b > 0):\n" +
                "\t\traise ValueError(\"Precondition failed.\")\n" +
                "\tx = a\n" +
                "\ty = b\n" +
                "\twhile x != y:\n" +
                "\t\tif x > y:\n" +
                "\t\t\tx = x - y\n" +
                "\t\telse:\n" +
                "\t\t\ty = y - x\n" +
                "\tif x > 0:\n" +
                "\t\treturn x\n" +
                "\telse:\n" +
                "\t\traise ValueError(\"Postcondition failed.\")\n" +
                "\n" +
                "\n" +
                "def aa():\n" +
                "\ta(320, 125)\n" +
                "\ttest_var1 = a(320, 125)\n" +
                "\ttest_var2 = a(320, 125)\n" +
                "\taa()\n";

        robot
                .clickOn("#generateButton")
                .clickOn("#javaButton");

        assertEquals(javaResult, robot.lookup("#codeArea").queryAs(CodeArea.class).getText());

        robot.clickOn("#cppButton");

        assertEquals(cppResult, robot.lookup("#codeArea").queryAs(CodeArea.class).getText());

        robot.clickOn("#pythonButton");

        assertEquals(pythonResult, robot.lookup("#codeArea").queryAs(CodeArea.class).getText());

        File file1 = new File(Serialize.getConfigHomePath() + "/saves/a.ser");
        File file2 = new File(Serialize.getConfigHomePath() + "/saves/aa.ser");
        File imgFile1 = new File(Serialize.getConfigHomePath() + "/saves/images/a.png");
        File imgFile2 = new File(Serialize.getConfigHomePath() + "/saves/images/aa.png");

        file1.delete();
        imgFile1.delete();
        file2.delete();
        imgFile2.delete();
    }

    @Test
    public void generateComplexStatements(FxRobot robot){
        insertSwitchCase(robot, "test_expression", "val", 3, true);
        insertIfWithElseIfAndElseBranches(robot, "test_condition", "condition", 4);
        insertForLoop(robot, "i", "0", "n");
        insertForEachLoop(robot, "string", "item", "array_var1");
        insertArrayAssignment(robot, "double", "array_var2", "array_val", 6);
        insertDoWhileLoop(robot, "condition");
        insertArrayDefinition(robot, "boolean", "array_var3", "bool_value", 3);

        String javaResult = "int a() {\n" +
                "\tswitch (test_expression) {\n" +
                "\t\tcase val3:\n" +
                "\t\t\tbreak;\n" +
                "\t\tcase val2:\n" +
                "\t\t\tbreak;\n" +
                "\t\tcase val1:\n" +
                "\t\t\tbreak;\n" +
                "\t\tdefault:\n" +
                "\t\t\tbreak;\n" +
                "\t}\n" +
                "\tif (test_condition) {\n" +
                "\t} else if (condition1) {\n" +
                "\t} else if (condition2) {\n" +
                "\t} else if (condition3) {\n" +
                "\t} else if (condition4) {\n" +
                "\t} else {\n" +
                "\t}\n" +
                "\tfor (int i = 0; i < n; ++i) {\n" +
                "\t}\n" +
                "\tfor (string item : array_var1){\n" +
                "\t}\n" +
                "\tarray_var2 = new double[]{array_val1, array_val2, array_val3, array_val4, array_val5, array_val6};\n" +
                "\tdo {\n" +
                "\t} while (condition);\n" +
                "\tboolean array_var3[] = new boolean[]{bool_value1, bool_value2, bool_value3};\n" +
                "}";

        String cppResult = "int a() {\n" +
                "\tswitch (test_expression) {\n" +
                "\t\tcase val3:\n" +
                "\t\t\tbreak;\n" +
                "\t\tcase val2:\n" +
                "\t\t\tbreak;\n" +
                "\t\tcase val1:\n" +
                "\t\t\tbreak;\n" +
                "\t\tdefault:\n" +
                "\t\t\tbreak;\n" +
                "\t}\n" +
                "\tif (test_condition) {\n" +
                "\t} else if (condition1) {\n" +
                "\t} else if (condition2) {\n" +
                "\t} else if (condition3) {\n" +
                "\t} else if (condition4) {\n" +
                "\t} else {\n" +
                "\t}\n" +
                "\tfor (int i = 0; i < n; ++i) {\n" +
                "\t}\n" +
                "\tfor (string item : array_var1){\n" +
                "\t}\n" +
                "\tarray_var2 = new double[]{array_val1, array_val2, array_val3, array_val4, array_val5, array_val6};\n" +
                "\tdo {\n" +
                "\t} while (condition);\n" +
                "\tbool array_var3[] = {bool_value1, bool_value2, bool_value3};\n" +
                "}";

        String pythonResult = "def a():\n" +
                "\tif test_expression == val3:\n" +
                "\telif test_expression == val2:\n" +
                "\telif test_expression == val1:\n" +
                "\telse:\n" +
                "\tif test_condition:\n" +
                "\telif condition1:\n" +
                "\telif condition2:\n" +
                "\telif condition3:\n" +
                "\telif condition4:\n" +
                "\telse:\n" +
                "\tfor i in range(0, n):\n" +
                "\tfor item in array_var1:\n" +
                "\tarray_var2 = [array_val1, array_val2, array_val3, array_val4, array_val5, array_val6]\n" +
                "\twhile True:\n" +
                "\t\tif condition:\n" +
                "\t\t\tbreak\n" +
                "\tarray_var3 = [bool_value1, bool_value2, bool_value3]\n";

        robot
                .clickOn("#generateButton")
                .clickOn("#javaButton");

        assertEquals(javaResult, robot.lookup("#codeArea").queryAs(CodeArea.class).getText());

        robot.clickOn("#cppButton");

        assertEquals(cppResult, robot.lookup("#codeArea").queryAs(CodeArea.class).getText());

        robot.clickOn("#pythonButton");

        assertEquals(pythonResult, robot.lookup("#codeArea").queryAs(CodeArea.class).getText());
    }
}
