package testclasses.backend;

import backend.entities.statements.Statement;
import backend.entities.statements.condstatements.If;
import backend.entities.statements.condstatements.SwitchCase;
import backend.entities.statements.functionoperations.FunctionAssignment;
import backend.entities.statements.functionoperations.FunctionCall;
import backend.entities.statements.functionoperations.FunctionDefinition;
import backend.entities.statements.jumpstatements.Break;
import backend.entities.statements.jumpstatements.Continue;
import backend.entities.statements.jumpstatements.Return;
import backend.entities.statements.loops.DoWhileLoop;
import backend.entities.statements.loops.ForEachLoop;
import backend.entities.statements.loops.ForLoop;
import backend.entities.statements.loops.WhileLoop;
import backend.entities.statements.varoperations.assignment.ArrayAssignment;
import backend.entities.statements.varoperations.assignment.ArrayItemAssignment;
import backend.entities.statements.varoperations.assignment.SimpleAssignment;
import backend.entities.statements.varoperations.declaration.ArrayDeclaration;
import backend.entities.statements.varoperations.declaration.ArrayDeclarationWithSize;
import backend.entities.statements.varoperations.declaration.Declaration;
import backend.entities.statements.varoperations.declaration.SimpleDeclaration;
import backend.entities.statements.varoperations.definition.ArrayDefinition;
import backend.entities.statements.varoperations.definition.SimpleDefinition;
import backend.entities.structograms.Structogram;
import backend.enums.FuncType;
import backend.enums.VarType;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.*;

public class SerializeEntityTests {

    private void compareStructogramParameters(Structogram expected, Structogram actual){
        assertEquals(actual.getFileName(), expected.getFileName());
        assertEquals(actual.getFuncName(), expected.getFuncName());
        assertEquals(actual.getFuncType(), expected.getFuncType());
        assertNotSame(actual.getFileName(), expected.getFileName());
        assertNotSame(actual.getFuncName(), expected.getFuncName());
        assertNotSame(actual.getParameterList(), expected.getParameterList());
        assertNotSame(actual.getStatementList(), expected.getStatementList());
        assertSame(actual.getFuncType(), expected.getFuncType());
        assertEquals(actual.getPrecondition(), expected.getPrecondition());
        assertNotSame(actual.getPrecondition(), expected.getPrecondition());
        assertEquals(actual.getPostcondition(), expected.getPostcondition());
        assertNotSame(actual.getPostcondition(), expected.getPostcondition());

        ArrayList<Declaration> originalFuncParamList = actual.getParameterList();
        ArrayList<Declaration> copiedFuncParamList = expected.getParameterList();
        if (originalFuncParamList.size() == copiedFuncParamList.size()){
            for (int i = 0; i < originalFuncParamList.size(); i++){
                assertEquals(originalFuncParamList.get(i).getVarType(), copiedFuncParamList.get(i).getVarType());
                assertEquals(originalFuncParamList.get(i).getVarName(), copiedFuncParamList.get(i).getVarName());
                assertSame(originalFuncParamList.get(i).getVarType(), copiedFuncParamList.get(i).getVarType());
                assertNotSame(originalFuncParamList.get(i).getVarName(), copiedFuncParamList.get(i).getVarName());
            }
        } else {
            fail("The lists have different size.");
        }
    }

    private void compareStructogramParametersWithOverriddenEquals(Structogram expected, Structogram actual){
        assertEquals(actual.getFileName(), expected.getFileName());
        assertEquals(actual.getFuncName(), expected.getFuncName());
        assertEquals(actual.getFuncType(), expected.getFuncType());
        assertEquals(actual.getParameterList(), expected.getParameterList());
        assertEquals(actual.getStatementList(), expected.getStatementList());
        assertNotSame(actual.getFileName(), expected.getFileName());
        assertNotSame(actual.getFuncName(), expected.getFuncName());
        assertNotSame(actual.getParameterList(), expected.getParameterList());
        assertNotSame(actual.getStatementList(), expected.getStatementList());
        assertSame(actual.getFuncType(), expected.getFuncType());
        assertEquals(actual.getPostcondition(), expected.getPostcondition());
        assertNotSame(actual.getPostcondition(), expected.getPostcondition());
    }

    @Test
    public void SimpleAssignmentSerialization(){
        SimpleAssignment original = new SimpleAssignment("test_assignment", "test_value");
        SimpleAssignment copy = SerializationUtils.clone(original);

        assertEquals(original.getVarValue(), copy.getVarValue());
        assertEquals(original.getVarName(), copy.getVarName());
        assertNotSame(original.getVarValue(), copy.getVarValue());
        assertNotSame(original.getVarName(), copy.getVarName());
    }

    @Test
    public void SimpleDeclarationSerialization(){
        SimpleDeclaration original = new SimpleDeclaration("test_declaration", VarType.BOOLEAN);
        SimpleDeclaration copy = SerializationUtils.clone(original);

        assertEquals(original.getVarName(), copy.getVarName());
        assertEquals(original.getVarType(), copy.getVarType());
        assertNotSame(original.getVarName(), copy.getVarName());
        assertSame(original.getVarType(), copy.getVarType());
    }
    @Test
    public void SimpleDefinitionSerialization(){
        SimpleDefinition original = new SimpleDefinition("test_definition", VarType.INT, "10");
        SimpleDefinition copy = SerializationUtils.clone(original);

        assertEquals(original.getVarName(), copy.getVarName());
        assertEquals(original.getVarType(), copy.getVarType());
        assertEquals(original.getVarValue(), copy.getVarValue());
        assertNotSame(original.getVarName(), copy.getVarName());
        assertNotSame(original.getVarValue(), copy.getVarValue());
        assertSame(original.getVarType(), copy.getVarType());
    }


    @Test
    public void ArrayItemAssignmentSerialization(){
        ArrayItemAssignment original = new ArrayItemAssignment("test_index", "test_item", "test_value");
        ArrayItemAssignment copy = SerializationUtils.clone(original);

        assertEquals(original.getIndex(), copy.getIndex());
        assertEquals(original.getVarName(), copy.getVarName());
        assertEquals(original.getVarValue(), copy.getVarValue());
        assertNotSame(original.getIndex(), copy.getIndex());
        assertNotSame(original.getVarName(), copy.getVarName());
        assertNotSame(original.getVarValue(), copy.getVarValue());
    }

    @Test
    public void ArrayAssignmentSerialization(){
        ArrayList<String> valueList = new ArrayList<>();
        valueList.add("a");
        valueList.add("b");
        valueList.add("c");

        ArrayAssignment original = new ArrayAssignment("test_array", VarType.CHAR, valueList);
        ArrayAssignment copy = SerializationUtils.clone(original);

        assertEquals(original.getVarName(), copy.getVarName());
        assertEquals(original.getVarType(), copy.getVarType());
        assertEquals(original.getValueList(), copy.getValueList());
        assertNotSame(original.getVarName(), copy.getVarName());
        assertNotSame(original.getValueList(), copy.getValueList());
        assertSame(original.getVarType(), copy.getVarType());
    }

    @Test
    public void ArrayDeclarationSerialization(){
        ArrayDeclaration original = new ArrayDeclaration("test_array", VarType.DOUBLE);
        ArrayDeclaration copy = SerializationUtils.clone(original);

        assertEquals(original.getVarName(), copy.getVarName());
        assertEquals(original.getVarType(), copy.getVarType());
        assertNotSame(original.getVarName(), copy.getVarName());
        assertSame(original.getVarType(), copy.getVarType());
    }

    @Test
    public void ArrayDeclarationWithSizeSerialization(){
        ArrayDeclarationWithSize original = new ArrayDeclarationWithSize("test_array", VarType.DOUBLE, "5");
        ArrayDeclarationWithSize copy = SerializationUtils.clone(original);

        assertEquals(original.getVarName(), copy.getVarName());
        assertEquals(original.getVarType(), copy.getVarType());
        assertEquals(original.getSize(), copy.getSize());
        assertNotSame(original.getVarName(), copy.getVarName());
        assertNotSame(original.getSize(), copy.getSize());
        assertSame(original.getVarType(), copy.getVarType());
    }

    @Test
    public void ArrayDefinitionSerialization(){
        ArrayList<String> valueList = new ArrayList<>();
        valueList.add("test1");
        valueList.add("test2");
        valueList.add("test3");

        ArrayDefinition original = new ArrayDefinition("test_array", VarType.STRING, valueList);
        ArrayDefinition copy = SerializationUtils.clone(original);


        assertEquals(original.getVarName(), copy.getVarName());
        assertEquals(original.getVarType(), copy.getVarType());
        assertEquals(original.getValueList(), copy.getValueList());
        assertNotSame(original.getVarName(), copy.getVarName());
        assertNotSame(original.getValueList(), copy.getValueList());
        assertSame(original.getVarType(), copy.getVarType());
    }

    @Test
    public void forLoopSerialization(){
        ForLoop original = new ForLoop("test_i", "0", "n");
        ForLoop copy = SerializationUtils.clone(original);

        assertEquals(original.getVarName(), copy.getVarName());
        assertEquals(original.getStart(), copy.getStart());
        assertEquals(original.getEnd(), original.getEnd());
        assertEquals(original.getStatementList(), copy.getStatementList());
        assertNotSame(original.getVarName(), copy.getVarName());
        assertNotSame(original.getStart(), copy.getStart());
        assertNotSame(original.getEnd(), copy.getEnd());
        assertNotSame(original.getStatementList(), copy.getStatementList());
    }

    @Test
    public void forEachLoopSerialization(){
        ForEachLoop original = new ForEachLoop(VarType.STRING, "test_item", "item_array");
        ForEachLoop copy = SerializationUtils.clone(original);

        assertEquals(original.getItemType(), copy.getItemType());
        assertEquals(original.getActualItemName(), copy.getActualItemName());
        assertEquals(original.getArrayVarName(), copy.getArrayVarName());
        assertSame(original.getItemType(), copy.getItemType());
        assertNotSame(original.getActualItemName(), copy.getActualItemName());
        assertNotSame(original.getArrayVarName(), copy.getArrayVarName());
    }

    @Test
    public void whileLoopSerialization(){
        WhileLoop original = new WhileLoop("test_condition_string");
        WhileLoop copy = SerializationUtils.clone(original);

        assertEquals(original.getCondition(), copy.getCondition());
        assertEquals(original.getStatementList(), copy.getStatementList());
        assertNotSame(original.getCondition(), copy.getCondition());
        assertNotSame(original.getStatementList(), copy.getStatementList());
    }

    @Test
    public void doWhileLoopSerialization(){
        DoWhileLoop original = new DoWhileLoop("test_condition_string");
        DoWhileLoop copy = SerializationUtils.clone(original);

        assertEquals(original.getCondition(), copy.getCondition());
        assertEquals(original.getStatementList(), copy.getStatementList());
        assertNotSame(original.getCondition(), copy.getCondition());
        assertNotSame(original.getStatementList(), copy.getStatementList());
    }

    @Test
    public void breakSerialization(){
        Break original = new Break();
        Break copy = SerializationUtils.clone(original);

        assertNotSame(original, copy);
    }

    @Test
    public void continueSerialization(){
        Continue original = new Continue();
        Continue copy = SerializationUtils.clone(original);

        assertNotSame(original, copy);
    }

    @Test
    public void returnsSerialization(){
        Return original = new Return("test_return_value", false);
        Return copy = SerializationUtils.clone(original);

        assertEquals(original.getExpression(), copy.getExpression());
        assertNotSame(original.getExpression(), copy.getExpression());
        assertEquals(original.isCheckPostCond(), copy.isCheckPostCond());
        assertSame(original.isCheckPostCond(), copy.isCheckPostCond());
    }

    @Test
    public void FunctionCallSerialization(){
        ArrayList<Declaration> declarationList = new ArrayList<>();
        ArrayList<String> parameterList = new ArrayList<>();

        declarationList.add(new SimpleDeclaration("test_Var_decl", VarType.BOOLEAN));
        declarationList.add(new ArrayDeclaration("test_array", VarType.STRING));
        parameterList.add("n");
        parameterList.add("test");

        Structogram structogram = new Structogram("test_function", "test_function", FuncType.VOID,
                "test_precondition", "test_postcondition", declarationList);

        structogram.getStatementList().add(new SimpleDeclaration("test_name", VarType.STRING));
        structogram.getStatementList().add(new ForLoop("i", "0", "n-1"));

        FunctionCall original = new FunctionCall(structogram, parameterList);
        FunctionCall copy = SerializationUtils.clone(original);

        assertEquals(original.getParameterList(), copy.getParameterList());
        assertNotSame(original.getParameterList(), copy.getParameterList());
        compareStructogramParameters(original.getFunction(), copy.getFunction());

        ArrayList<Statement> originalStatList = original.getFunction().getStatementList();
        ArrayList<Statement> copiedStatList = copy.getFunction().getStatementList();
        if (originalStatList.size() == copiedStatList.size()){

            assertEquals(((SimpleDeclaration) originalStatList.get(0)).getVarType(),
                    ((SimpleDeclaration) copiedStatList.get(0)).getVarType());
            assertEquals(((SimpleDeclaration) originalStatList.get(0)).getVarName(),
                    ((SimpleDeclaration) copiedStatList.get(0)).getVarName());

            assertEquals(((ForLoop) originalStatList.get(1)).getVarName(),
                    ((ForLoop) copiedStatList.get(1)).getVarName());
            assertEquals(((ForLoop) originalStatList.get(1)).getStart(),
                    ((ForLoop) copiedStatList.get(1)).getStart());
            assertEquals(((ForLoop) originalStatList.get(1)).getEnd(),
                    ((ForLoop) copiedStatList.get(1)).getEnd());

            assertSame(((SimpleDeclaration) originalStatList.get(0)).getVarType(),
                    ((SimpleDeclaration) copiedStatList.get(0)).getVarType());
            assertNotSame(((SimpleDeclaration) originalStatList.get(0)).getVarName(),
                    ((SimpleDeclaration) copiedStatList.get(0)).getVarName());

            assertNotSame(((ForLoop) originalStatList.get(1)).getVarName(),
                    ((ForLoop) copiedStatList.get(1)).getVarName());
            assertNotSame(((ForLoop) originalStatList.get(1)).getStart(),
                    ((ForLoop) copiedStatList.get(1)).getStart());
            assertNotSame(((ForLoop) originalStatList.get(1)).getEnd(),
                    ((ForLoop) copiedStatList.get(1)).getEnd());
        } else {
            fail("The lists have different size.");
        }
    }

    @Test
    public void FunctionAssignmentSerialization(){
        ArrayList<Declaration> declarationList = new ArrayList<>();
        ArrayList<String> parameterList = new ArrayList<>();

        Structogram structogram = new Structogram("test_function", "test_function", FuncType.VOID,
                "test_precondition", "test_postcondition", declarationList);

        FunctionAssignment original = new FunctionAssignment(structogram, parameterList, "test_variable_name");
        FunctionAssignment copy = SerializationUtils.clone(original);

        assertEquals(original.getVariableName(), copy.getVariableName());
        assertEquals(original.getParameterList(), copy.getParameterList());
        assertNotSame(original.getVariableName(), copy.getVariableName());
        assertNotSame(original.getParameterList(), copy.getParameterList());
        compareStructogramParameters(original.getFunction(), copy.getFunction());
    }

    @Test
    public void FunctionDefinitionSerialization(){
        ArrayList<Declaration> declarationList = new ArrayList<>();
        ArrayList<String> parameterList = new ArrayList<>();

        declarationList.add(new SimpleDeclaration("test_Var_decl", VarType.BOOLEAN));
        declarationList.add(new ArrayDeclaration("test_array", VarType.STRING));
        parameterList.add("n");
        parameterList.add("test");

        Structogram structogram = new Structogram("test_function", "test_function", FuncType.VOID,
                "test_precondition", "test_postcondition", declarationList);

        FunctionDefinition original = new FunctionDefinition(structogram, parameterList, "test_variable_name", VarType.STRING);
        FunctionDefinition copy = SerializationUtils.clone(original);

        assertEquals(original.getVariableName(), copy.getVariableName());
        assertEquals(original.getVarType(), copy.getVarType());
        assertEquals(original.getParameterList(), copy.getParameterList());
        assertNotSame(original.getVariableName(), copy.getVariableName());
        assertNotSame(original.getParameterList(), copy.getParameterList());
        assertSame(original.getVarType(), copy.getVarType());
        compareStructogramParameters(original.getFunction(), copy.getFunction());
    }

    @Test
    public void ifSerialization(){
        ArrayList<String> branchConditionsList = new ArrayList<>();
        branchConditionsList.add("n > 1 OR text == \"test\"");
        branchConditionsList.add("NOT TRUE");
        branchConditionsList.add("");

        If original = new If(branchConditionsList, true);
        If copy = SerializationUtils.clone(original);

        assertEquals(original.getBranches(), copy.getBranches());
        assertEquals(original.getFalseBranchStatementList(), copy.getFalseBranchStatementList());
        assertNotSame(original.getBranches(), copy.getBranches());
        assertNotSame(original.getFalseBranchStatementList(), copy.getFalseBranchStatementList());
    }

    @Test
    public void switchCaseSerialization(){
        ArrayList<String> caseExpressionsList = new ArrayList<>();
        caseExpressionsList.add("TEST1");
        caseExpressionsList.add("\"TEST2\"");
        caseExpressionsList.add("");

        SwitchCase original = new SwitchCase("test_string", caseExpressionsList, false);
        SwitchCase copy = SerializationUtils.clone(original);

        assertEquals(original.getExpression(), copy.getExpression());
        assertEquals(original.getBranches(), copy.getBranches());
        assertEquals(original.getFalseBranchStatementList(), copy.getFalseBranchStatementList());
        assertNotSame(original.getExpression(), copy.getExpression());
        assertNotSame(original.getBranches(), copy.getBranches());
        assertNull(original.getFalseBranchStatementList());
        assertNull(copy.getFalseBranchStatementList());
    }

    @Test
    public void structogramSerializationWithoutStatementList(){
        ArrayList<Declaration> declarationList = new ArrayList<>();
        declarationList.add(new SimpleDeclaration("test_Var_decl", VarType.BOOLEAN));
        declarationList.add(new ArrayDeclaration("test_array", VarType.STRING));

        Structogram original = new Structogram("test_function", "test_function", FuncType.VOID,
                "test_precondition", "test_postcondition", declarationList);
        Structogram copy = SerializationUtils.clone(original);

        compareStructogramParameters(original, copy);
    }

    @Test
    public void structogramSerializationWithStatementList(){
        ArrayList<Declaration> declarationList = new ArrayList<>();

        declarationList.add(new SimpleDeclaration("test_Var_decl", VarType.BOOLEAN));
        declarationList.add(new ArrayDeclaration("test_array", VarType.STRING));

        Structogram original = new Structogram("test_function", "test_function", FuncType.VOID,
                "test_precondition", "test_postcondition", declarationList);
        original.getStatementList().add(new SimpleDeclaration("test_name", VarType.STRING));
        original.getStatementList().add(new FunctionCall(new Structogram("test_function", "test_function", FuncType.STRING), new ArrayList<>()));

        Structogram copy = SerializationUtils.clone(original);

        compareStructogramParameters(original, copy);

        ArrayList<Statement> originalStatList = original.getStatementList();
        ArrayList<Statement> copiedStatList = copy.getStatementList();
        if (originalStatList.size() == copiedStatList.size()){

            assertEquals(((SimpleDeclaration) originalStatList.get(0)).getVarType(),
                    ((SimpleDeclaration) copiedStatList.get(0)).getVarType());
            assertEquals(((SimpleDeclaration) originalStatList.get(0)).getVarName(),
                    ((SimpleDeclaration) copiedStatList.get(0)).getVarName());

            assertSame(((SimpleDeclaration) originalStatList.get(0)).getVarType(),
                    ((SimpleDeclaration) copiedStatList.get(0)).getVarType());
            assertNotSame(((SimpleDeclaration) originalStatList.get(0)).getVarName(),
                    ((SimpleDeclaration) copiedStatList.get(0)).getVarName());

            compareStructogramParameters(((FunctionCall) originalStatList.get(1)).getFunction(), ((FunctionCall) copiedStatList.get(1)).getFunction());

        } else {
            fail("The lists have different size.");
        }
    }

    @Test
    public void structogramSerializationWithStatementListEqualMethodTest(){
        ArrayList<Declaration> declarationList = new ArrayList<>();

        declarationList.add(new SimpleDeclaration("test_Var_decl", VarType.BOOLEAN));
        declarationList.add(new ArrayDeclaration("test_array", VarType.STRING));

        Structogram original = new Structogram("test_function", "test_function", FuncType.VOID,
                "test_precondition", "test_postcondition", declarationList);
        original.getStatementList().add(new SimpleDeclaration("test_name", VarType.STRING));
        original.getStatementList().add(new FunctionCall(new Structogram("test_function", "test_function", FuncType.STRING), new ArrayList<>()));

        Structogram copy = SerializationUtils.clone(original);

        compareStructogramParametersWithOverriddenEquals(original, copy);

        assertEquals(copy, original);
        assertNotSame(copy, original);
    }
}