package frontend.components.factorys;

import backend.entities.statements.Statement;
import backend.entities.statements.condstatements.If;
import backend.entities.statements.condstatements.SwitchCase;
import backend.entities.statements.functionoperations.FunctionAssignment;
import backend.entities.statements.functionoperations.FunctionCall;
import backend.entities.statements.functionoperations.FunctionDefinition;
import backend.entities.statements.functionoperations.FunctionOperation;
import backend.entities.statements.jumpstatements.Break;
import backend.entities.statements.jumpstatements.Continue;
import backend.entities.statements.jumpstatements.JumpStatement;
import backend.entities.statements.jumpstatements.Return;
import backend.entities.statements.loops.*;
import backend.entities.statements.varoperations.VarOperation;
import backend.entities.statements.varoperations.assignment.ArrayAssignment;
import backend.entities.statements.varoperations.assignment.ArrayItemAssignment;
import backend.entities.statements.varoperations.assignment.SimpleAssignment;
import backend.entities.statements.varoperations.declaration.ArrayDeclarationWithSize;
import backend.entities.statements.varoperations.declaration.Declaration;
import backend.entities.statements.varoperations.declaration.SimpleDeclaration;
import backend.entities.statements.varoperations.definition.ArrayDefinition;
import backend.entities.statements.varoperations.definition.SimpleDefinition;
import backend.entities.structograms.FinalStructogram;
import backend.entities.structograms.Structogram;
import backend.enums.FuncType;
import backend.enums.Language;
import backend.enums.VarType;
import backend.serialization.Serialize;
import frontend.GuiManager;
import frontend.components.managers.QueryManager;
import frontend.components.managers.SelectionManager;
import frontend.components.panels.structogram.statementlists.StatementListVBox;
import frontend.components.panels.structogram.statements.SingleRowStatementVBox;
import frontend.components.panels.structogram.statements.StatementVBox;
import frontend.components.panels.structogram.statements.condstat.IfVBox;
import frontend.components.panels.structogram.statements.condstat.SwitchCaseVBox;
import frontend.components.panels.structogram.statements.loop.AfterCheckLoopVBox;
import frontend.components.panels.structogram.statements.loop.LoopVBox;
import frontend.components.panels.structogram.statements.loop.PreCheckLoopVBox;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Factory for structogram components. Creates an entity, a visualisation object,
 * and add the entity to the structogram, according to the position of selected
 * statement in the visual object (before, after, instead of)
 */
public final class StructogramFactory {

    /**
     * Stores the currently open structogram.
     */
    private static Structogram structogram;

    /**
     * Generates a new structogram.
     * @param fileName structorgram file name
     * @param functionName structogram function name
     * @param type structogram return type
     * @return current structogram entity
     */
    public static Structogram generateNewStructogram(String fileName, String functionName, FuncType type) {
        structogram = new Structogram(fileName, functionName, type, "", "");
        return structogram;
    }
    /**
     * Generates a new structogram.
     * @param fileName structorgram file name
     * @param functionName structogram function name
     * @param type structogram return type
     * @param precondition structogram precondition
     * @param postcondition structogram postcondition
     * @return current structogram entity
     */
    public static Structogram generateNewStructogram(String fileName, String functionName, FuncType type, String precondition, String postcondition) {
        structogram = new Structogram(fileName, functionName, type, precondition, postcondition);
        return structogram;
    }

    /**
     * Generates a new structogram.
     * @param fileName structorgram file name
     * @param functionName structogram function name
     * @param type structogram return type
     * @param precondition structogram precondition
     * @param postcondition structogram postcondition
     * @param parameterList structogram parameters
     * @return current structogram entity
     */
    public static Structogram generateNewStructogram(String fileName, String functionName, FuncType type, String precondition,
                                              String postcondition, ArrayList<Declaration> parameterList) {
        structogram = new Structogram(fileName, functionName, type, precondition, postcondition, parameterList);
        return structogram;
    }

    /**
     * Generates a new final structogram.
     * @param fileName structorgram file name
     * @param functionName structogram function name
     * @param type structogram return type
     * @return current structogram entity
     */
    public static Structogram generateNewFinalStructogram(String fileName, String functionName, FuncType type) {
        structogram = new FinalStructogram(fileName, functionName, type, "", "");
        return structogram;
    }
    /**
     * Generates a new final structogram.
     * @param fileName structorgram file name
     * @param functionName structogram function name
     * @param type structogram return type
     * @param precondition structogram precondition
     * @param postcondition structogram postcondition
     * @return current structogram entity
     */
    public static Structogram generateNewFinalStructogram(String fileName, String functionName, FuncType type, String precondition, String postcondition) {
        structogram = new FinalStructogram(fileName, functionName, type, precondition, postcondition);
        return structogram;
    }

    /**
     * Generates a new final structogram.
     * @param fileName structorgram file name
     * @param functionName structogram function name
     * @param type structogram return type
     * @param precondition structogram precondition
     * @param postcondition structogram postcondition
     * @param parameterList structogram parameters
     * @return current structogram entity
     */
    public static Structogram generateNewFinalStructogram(String fileName, String functionName, FuncType type, String precondition,
                                                     String postcondition, ArrayList<Declaration> parameterList) {
        structogram = new FinalStructogram(fileName, functionName, type, precondition, postcondition, parameterList);
        return structogram;
    }

    /**
     * Creates a simple definition.
     * @param position position, according to selected statement in the visual object
     * @param varName name of variable
     * @param varTypeString string representation of variable type
     * @param varValue type of variable
     */
    public static void generateSimpleDefinition(String position, String varName, String varTypeString, String varValue){
        VarType varType = VarType.valueOf(varTypeString.toUpperCase());

        SimpleDefinition simpleDefinitionEntity = new SimpleDefinition(varName, varType, varValue);
        SingleRowStatementVBox simpleDefinitionVBox = new SingleRowStatementVBox(simpleDefinitionEntity);

        insertToStructogram(position, simpleDefinitionVBox);
    }

    /**
     * Creates a simple declaration.
     * @param position position, according to selected statement in the visual object
     * @param varName name of variable
     * @param varTypeString string representation of variable type
     */
    public static void generateSimpleDeclaration(String position, String varName, String varTypeString){
        VarType varType = VarType.valueOf(varTypeString.toUpperCase());

        SimpleDeclaration simpleDeclarationEntity = new SimpleDeclaration(varName, varType);
        SingleRowStatementVBox simpleDeclarationVBox = new SingleRowStatementVBox(simpleDeclarationEntity);

        insertToStructogram(position, simpleDeclarationVBox);
    }

    /**
     * Creates a simple assignment.
     * @param position position, according to selected statement in the visual object
     * @param varName name of variable
     * @param varValue type of variable
     */
    public static void generateSimpleAssignment(String position, String varName, String varValue){
        SimpleAssignment simpleAssignmentEntity = new SimpleAssignment(varName, varValue);
        SingleRowStatementVBox simpleAssignmentVBox = new SingleRowStatementVBox(simpleAssignmentEntity);

        insertToStructogram(position, simpleAssignmentVBox);
    }

    /**
     * Creates an assignment of an array element.
     * @param position position, according to selected statement in the visual object
     * @param index index of array
     * @param varName name of array
     * @param varValue type of array
     */
    public static void generateArrayItemAssignment(String position, String index, String varName, String varValue){
        ArrayItemAssignment arrayItemAssignmentEntity = new ArrayItemAssignment(index, varName, varValue);
        SingleRowStatementVBox arrayItemAssignmentVBox = new SingleRowStatementVBox(arrayItemAssignmentEntity);

        insertToStructogram(position, arrayItemAssignmentVBox);
    }

    /**
     * Creates a definition of an array.
     * @param position position, according to selected statement in the visual object
     * @param varName name of array
     * @param varTypeString string representation of array type
     * @param varValueList list of array values
     */
    public static void generateArrayDefinition(String position, String varName, String varTypeString, ArrayList<String> varValueList){
        VarType varType = VarType.valueOf(varTypeString.toUpperCase());

        ArrayDefinition arrayDefinitionEntity = new ArrayDefinition(varName, varType, varValueList);
        SingleRowStatementVBox arrayDefinitionVBox = new SingleRowStatementVBox(arrayDefinitionEntity);

        insertToStructogram(position, arrayDefinitionVBox);
    }

    /**
     * Creates a declaration of an array.
     * @param position position, according to selected statement in the visual object
     * @param varName name of array
     * @param varTypeString string representation of array type
     * @param size size of array
     */
    public static void generateArrayDeclarationWithSize(String position, String varName, String varTypeString, String size){
        VarType varType = VarType.valueOf(varTypeString.toUpperCase());

        ArrayDeclarationWithSize arrayDeclarationWithSizeEntity = new ArrayDeclarationWithSize(varName, varType, size);
        SingleRowStatementVBox arrayDeclarationVBox = new SingleRowStatementVBox(arrayDeclarationWithSizeEntity);

        insertToStructogram(position, arrayDeclarationVBox);
    }

    /**
     * Creates an assignment of an array.
     * @param position position, according to selected statement in the visual object
     * @param varName name of array
     * @param varTypeString string representation of array type
     * @param varValueList list of array values
     */
    public static void generateArrayAssignment(String position, String varName, String varTypeString, ArrayList<String> varValueList){
        VarType varType = VarType.valueOf(varTypeString.toUpperCase());

        ArrayAssignment arrayAssignmentEntity = new ArrayAssignment(varName, varType, varValueList);
        SingleRowStatementVBox arrayAssignmentVBox = new SingleRowStatementVBox(arrayAssignmentEntity);

        insertToStructogram(position, arrayAssignmentVBox);
    }

    /**
     * Creates an if.
     * @param position position, according to selected statement in the visual object
     * @param branchConditions list of conditions
     * @param isElseBranch shows that is there else branch
     */
    public static void generateIf(String position, ArrayList<String> branchConditions, boolean isElseBranch){
        If ifEntity = new If(branchConditions, isElseBranch);
        IfVBox ifVBox = new IfVBox(ifEntity);

        insertToStructogram(position, ifVBox);
    }

    /**
     * Creates a switch.
     * @param position position, according to selected statement in the visual object
     * @param expression expression of switch
     * @param caseExpressions list of expression values
     * @param isDefaultCase shows that is there default case
     */
    public static void generateSwitchCase(String position, String expression, ArrayList<String> caseExpressions, boolean isDefaultCase){
        SwitchCase switchCaseEntity = new SwitchCase(expression, caseExpressions, isDefaultCase);
        SwitchCaseVBox switchCaseVBox = new SwitchCaseVBox(switchCaseEntity);

        insertToStructogram(position, switchCaseVBox);
    }

    /**
     * Creates a for loop.
     * @param position position, according to selected statement in the visual object
     * @param varName name of variable
     * @param start start value
     * @param end end value
     */
    public static void generateForLoop(String position, String varName, String start, String end){
        ForLoop forLoopEntity = new ForLoop(varName, start, end);
        LoopVBox forLoopVBox = new PreCheckLoopVBox(forLoopEntity);

        insertToStructogram(position, forLoopVBox);
    }

    /**
     * Creates a for-each loop.
     * @param position position, according to selected statement in the visual object
     * @param varTypeString string representation of array type
     * @param itemName array element value
     * @param arrayVarName name of array
     */
    public static void generateForEachLoop(String position, String varTypeString, String itemName, String arrayVarName){
        VarType varType = VarType.valueOf(varTypeString.toUpperCase());

        ForEachLoop forEachLoopEntity = new ForEachLoop(varType, itemName, arrayVarName);
        LoopVBox forEachLoopVBox = new PreCheckLoopVBox(forEachLoopEntity);

        insertToStructogram(position, forEachLoopVBox);
    }

    /**
     * Creates a while loop.
     * @param position position, according to selected statement in the visual object
     * @param condition loop condition
     */
    public static void generateWhileLoop(String position, String condition){
        WhileLoop whileLoopEntity = new WhileLoop(condition);
        LoopVBox WhileLoopVBox = new PreCheckLoopVBox(whileLoopEntity);

        insertToStructogram(position, WhileLoopVBox);
    }

    /**
     * Creates a do-while loop.
     * @param position position, according to selected statement in the visual object
     * @param condition loop condition
     */
    public static void generateDoWhileLoop(String position, String condition){
        DoWhileLoop doWhileLoopEntity = new DoWhileLoop(condition);
        LoopVBox doWhileLoopVBox = new AfterCheckLoopVBox(doWhileLoopEntity);

        insertToStructogram(position, doWhileLoopVBox);
    }

    /**
     * Creates a return.
     * @param position position, according to selected statement in the visual object
     * @param expression expression value
     * @param isCheckPostCond shows that is there default case
     */
    public static void generateReturn(String position, String expression, boolean isCheckPostCond){
        Return returnEntity = new Return(expression, isCheckPostCond);
        SingleRowStatementVBox returnVBox = new SingleRowStatementVBox(returnEntity);

        insertToStructogram(position, returnVBox);
    }

    /**
     * Creates a break.
     * @param position position, according to selected statement in the visual object
     */
    public static void generateBreak(String position){
        Break breakEntity = new Break();
        SingleRowStatementVBox breakVBox = new SingleRowStatementVBox(breakEntity);

        insertToStructogram(position, breakVBox);
    }

    /**
     * Creates a continue.
     * @param position position, according to selected statement in the visual object
     */
    public static void generateContinue(String position){
        Continue continueEntity = new Continue();
        SingleRowStatementVBox continueVBox = new SingleRowStatementVBox(continueEntity);

        insertToStructogram(position, continueVBox);
    }

    /**
     * Creates a simple function call.
     * @param position position, according to selected statement in the visual object
     * @param function structogram object
     * @param parameterList list of actual given parameters to the function
     */
    public static void insertFunctionCall(String position, Structogram function, ArrayList<String> parameterList) {
        FunctionCall functionCallEntity;

        if (structogram.getFileName().equals(function.getFileName())){
            functionCallEntity = new FunctionCall(structogram, parameterList);
        } else {
            functionCallEntity = new FunctionCall(function, parameterList);
        }

        functionValidation(functionCallEntity.getFunction());

        SingleRowStatementVBox functionCallVBox = new SingleRowStatementVBox(functionCallEntity);

        insertToStructogram(position, functionCallVBox);
    }

    /**
     * Creates a definition of a value, which has a function value.
     * @param position position, according to selected statement in the visual object
     * @param function structogram object
     * @param parameterList list of actual given parameters to the function
     * @param functionVarName name of variable
     * @param functionVarTypeString string representation of var type
     */
    public static void insertFunctionDefinition(String position, Structogram function, ArrayList<String> parameterList, String functionVarName, String functionVarTypeString) {
        VarType functionVarType = VarType.valueOf(functionVarTypeString.toUpperCase());
        FunctionDefinition functionDefinitionEntity;

        if (structogram.getFileName().equals(function.getFileName())){
            functionDefinitionEntity = new FunctionDefinition(structogram, parameterList, functionVarName, functionVarType);
        } else {
            functionDefinitionEntity = new FunctionDefinition(function, parameterList, functionVarName, functionVarType);
        }

        SingleRowStatementVBox functionDefinitionVBox = new SingleRowStatementVBox(functionDefinitionEntity);

        insertToStructogram(position, functionDefinitionVBox);
    }

    /**
     * Creates an assignment of a value, which has a function value.
     * @param position position, according to selected statement in the visual object
     * @param function structogram object
     * @param parameterList list of actual given parameters to the function
     * @param functionVarName name of variable
     */
    public static void insertFunctionAssignment(String position, Structogram function, ArrayList<String> parameterList, String functionVarName) {
        FunctionAssignment functionAssignmentEntity;

        if (structogram.getFileName().equals(function.getFileName())){
            functionAssignmentEntity = new FunctionAssignment(structogram, parameterList, functionVarName);
        } else {
            functionAssignmentEntity = new FunctionAssignment(function, parameterList, functionVarName);
        }

        SingleRowStatementVBox functionAssignmentVBox = new SingleRowStatementVBox(functionAssignmentEntity);

        insertToStructogram(position, functionAssignmentVBox);
    }

    /**
     * Generates code to the given language.
     * @param language programming language
     * @return generated code as a string
     */
    public static String generateCode(Language language) {
        return structogram.generateCode(language);
    }

    /**
     * Gets structogram.
     * @return current structogram
     */
    public static Structogram getStructogram() {
        return structogram;
    }

    /**
     * Loads structogram.
     * @param name filename of structogram
     */
    public static void loadStructogram(String name) {
        structogram = Serialize.load(name);

        functionValidation(structogram);

        GuiManager.openEditorStage();
        generatePanes(QueryManager.getStructogramWrapperVBox());
        SelectionManager.setSelectedList(null);
    }

    private static void functionValidation(Structogram structogram) {
        assert structogram != null;
        HashSet<Structogram> invalidFuncList = structogram.functionValidation();

        if (invalidFuncList.size() > 0){
            invalidFuncToErrorStage(invalidFuncList);
        }
    }

    private static void invalidFuncToErrorStage(HashSet<Structogram> invalidFuncList) {
        StringBuilder sb = new StringBuilder();
        for (Structogram function : invalidFuncList){
            sb
                    .append(function.getFuncName())
                    .append("(")
                    .append(function.parameterListToJavaString())
                    .append(")");
        }
        GuiManager.openErrorStage("Inner function not found!\nCode generation will generate the last\nknown form of the following function(s):\n" + sb.toString());
    }

    /**
     * Insert currently generated element to the structogram.
     * @param position position, according to selected statement in the visual object
     * @param statementVBox object of visual element
     */
    private static void insertToStructogram(String position, StatementVBox statementVBox){
        insertToStructogramFigure(position, statementVBox);
        insertToStructogramEntity(position, statementVBox.getStatementEntity());
    }

    /**
     * Insert currently generated visual element to the visual object.
     * @param position position, according to selected statement in the visual object
     * @param statementVBox object of visual element
     */
    private static void insertToStructogramFigure(String position, StatementVBox statementVBox) {
        if (SelectionManager.getSelectedStatement() == null) {
            if (SelectionManager.getSelectedList() != null){
                SelectionManager.getSelectedList().addChild(statementVBox);
            }
        } else {
            if (position.equals("instead of")) {
                SelectionManager.getSelectedList().replaceChild(SelectionManager.getSelectedIndex(), statementVBox);

            } else if (position.equals("before")) {
                SelectionManager.getSelectedList().addChild(SelectionManager.getSelectedIndex(), statementVBox);

            } else if (position.equals("after")) {
                SelectionManager.getSelectedList().addChild(SelectionManager.getSelectedIndex() + 1, statementVBox);
            }
        }
        SelectionManager.setSelectedStatement(statementVBox);
        SelectionManager.setSelectedIndex(SelectionManager.getSelectedList().getChildren().indexOf(SelectionManager.getSelectedStatement()));
    }

    /**
     * Insert currently generated backend element to the backend object.
     * @param statement statement entity
     */
    private static void insertToStructogramEntity(String position, Statement statement){
        if (position.equals("instead of") && SelectionManager.getSelectedList().getChildren().size() > 1) {
            SelectionManager.getSelectedList().getEntityStatementList().set(SelectionManager.getSelectedIndex(), statement);
        } else {
            SelectionManager.getSelectedList().getEntityStatementList().add(SelectionManager.getSelectedIndex(), statement);
        }
    }

    /**
     * Creates visual object from recently loaded structogram recursively.
     * @param statementListVBox object of visual element
     */
    private static void generatePanes(StatementListVBox statementListVBox){
        ArrayList<Statement> statementList = statementListVBox.getEntityStatementList();

        for (Statement statement : statementList){
            if (statement instanceof VarOperation || statement instanceof FunctionOperation || statement instanceof JumpStatement){
                statementListVBox.addChild(new SingleRowStatementVBox(statement));

            } else if (statement instanceof DoWhileLoop){
                AfterCheckLoopVBox afterCheckLoopVBox = new AfterCheckLoopVBox((Loop) statement);
                statementListVBox.addChild(afterCheckLoopVBox);
                generatePanes(afterCheckLoopVBox.getStatementListVBox());

            } else if (statement instanceof Loop){
                PreCheckLoopVBox preCheckLoopVBox = new PreCheckLoopVBox((Loop) statement);
                statementListVBox.addChild(preCheckLoopVBox);
                generatePanes(preCheckLoopVBox.getStatementListVBox());

            } else if (statement instanceof If) {
                IfVBox ifVBox = new IfVBox((If) statement);
                statementListVBox.addChild(ifVBox);

                for (StatementListVBox ifStatementListVBox : ifVBox.getStatementListVBoxArrayList()){
                    generatePanes(ifStatementListVBox);
                }

            } else if (statement instanceof SwitchCase){
                SwitchCaseVBox switchCaseVBox = new SwitchCaseVBox((SwitchCase) statement);
                statementListVBox.addChild(switchCaseVBox);
                for (StatementListVBox scStatementListVBox : switchCaseVBox.getStatementListVBoxArrayList()){
                    generatePanes(scStatementListVBox);
                }
            }
        }
    }
}
