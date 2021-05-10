package testclasses.frontend.factorys;

import backend.entities.structograms.Structogram;
import backend.entities.statements.varoperations.declaration.ArrayDeclaration;
import backend.entities.statements.varoperations.declaration.Declaration;
import backend.entities.statements.varoperations.declaration.SimpleDeclaration;
import backend.enums.FuncType;
import backend.enums.VarType;
import frontend.components.factorys.StructogramFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StructogramGenerationTests {

    private Structogram initStructogram(){
        String filename = "file_name";
        String funcName = "func_name";
        String precondition = "test_precond";
        String postcondition = "test_postcond";
        FuncType funcType = FuncType.STRING;
        ArrayList<Declaration> parameterList = new ArrayList<>();
        Declaration firstParameter = new SimpleDeclaration("test_simple_declaration", VarType.INT);
        Declaration secondParameter = new ArrayDeclaration("test_array_declaration", VarType.CHAR);
        parameterList.add(firstParameter);
        parameterList.add(secondParameter);

        StructogramFactory.generateNewStructogram(filename, funcName, funcType, precondition, postcondition, parameterList);

        return StructogramFactory.getStructogram();
    }

    @Test
    void generateNewStructogram() {
        String filename = "file_name";
        String funcName = "func_name";
        FuncType funcType = FuncType.STRING;
        StructogramFactory.generateNewStructogram(filename, funcName, funcType);

        Structogram structogram = StructogramFactory.getStructogram();
        assertEquals(structogram.getFileName(), filename);
        assertEquals(structogram.getFuncName(), funcName);
        assertEquals(structogram.getFuncType(), funcType);
        assertEquals(structogram.getPrecondition(), "");
        assertEquals(structogram.getPostcondition(), "");
        assertEquals(structogram.getParameterList().size(), 0);
        assertEquals(structogram.getStatementList().size(), 0);
    }

    @Test
    void testGenerateNewStructogram() {
        String filename = "file_name";
        String funcName = "func_name";
        String precondition = "test_precond";
        String postcondition = "test_postcond";
        FuncType funcType = FuncType.STRING;
        StructogramFactory.generateNewStructogram(filename, funcName, funcType, precondition, postcondition);

        Structogram structogram = StructogramFactory.getStructogram();
        assertEquals(structogram.getFileName(), filename);
        assertEquals(structogram.getFuncName(), funcName);
        assertEquals(structogram.getFuncType(), funcType);
        assertEquals(structogram.getPrecondition(), precondition);
        assertEquals(structogram.getPostcondition(), postcondition);
        assertEquals(structogram.getParameterList().size(), 0);
        assertEquals(structogram.getStatementList().size(), 0);
    }

    @Test
    void testGenerateNewStructogram1() {
        String filename = "file_name";
        String funcName = "func_name";
        String precondition = "test_precond";
        String postcondition = "test_postcond";
        FuncType funcType = FuncType.STRING;
        ArrayList<Declaration> parameterList = new ArrayList<>();
        Declaration firstParameter = new SimpleDeclaration("test_simple_declaration", VarType.INT);
        Declaration secondParameter = new ArrayDeclaration("test_array_declaration", VarType.CHAR);
        parameterList.add(firstParameter);
        parameterList.add(secondParameter);

        StructogramFactory.generateNewStructogram(filename, funcName, funcType, precondition, postcondition, parameterList);

        Structogram structogram = StructogramFactory.getStructogram();
        assertEquals(structogram.getFileName(), filename);
        assertEquals(structogram.getFuncName(), funcName);
        assertEquals(structogram.getFuncType(), funcType);
        assertEquals(structogram.getPrecondition(), precondition);
        assertEquals(structogram.getPostcondition(), postcondition);
        assertEquals(structogram.getParameterList().size(), 2);

        assertEquals(structogram.getParameterList().get(0).getVarType(), firstParameter.getVarType());
        assertEquals(structogram.getParameterList().get(0).getVarName(), firstParameter.getVarName());
        assertEquals(structogram.getParameterList().get(0), firstParameter);
        assertSame(structogram.getParameterList().get(0), firstParameter);
        assertTrue(structogram.getParameterList().get(0) instanceof SimpleDeclaration);

        assertEquals(structogram.getParameterList().get(1).getVarType(), secondParameter.getVarType());
        assertEquals(structogram.getParameterList().get(1).getVarName(), secondParameter.getVarName());
        assertEquals(structogram.getParameterList().get(1), secondParameter);
        assertSame(structogram.getParameterList().get(0), firstParameter);
        assertTrue(structogram.getParameterList().get(1) instanceof ArrayDeclaration);

        assertEquals(structogram.getStatementList().size(), 0);
    }

    //We test the other methods included the insertion testings, because for that, we need the whole GUI.
}