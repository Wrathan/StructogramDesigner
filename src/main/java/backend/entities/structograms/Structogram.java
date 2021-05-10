package backend.entities.structograms;

import backend.entities.statements.Statement;
import backend.entities.statements.condstatements.ConditionalStatement;
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
import backend.entities.statements.varoperations.assignment.Assignment;
import backend.entities.statements.varoperations.assignment.SimpleAssignment;
import backend.entities.statements.varoperations.declaration.ArrayDeclaration;
import backend.entities.statements.varoperations.declaration.ArrayDeclarationWithSize;
import backend.entities.statements.varoperations.declaration.Declaration;
import backend.entities.statements.varoperations.declaration.SimpleDeclaration;
import backend.entities.statements.varoperations.definition.ArrayDefinition;
import backend.entities.statements.varoperations.definition.Definition;
import backend.entities.statements.varoperations.definition.SimpleDefinition;
import backend.enums.FuncType;
import backend.enums.Language;
import backend.enums.VarType;
import backend.serialization.Serialize;

import java.io.File;
import java.io.Serializable;
import java.util.*;

/**
 * Representation of a structogram
 */
public class Structogram implements Serializable {
    private String fileName, functionName, precondition, postcondition;
    private FuncType funcType;
    private ArrayList<Declaration> parameterList;
    private ArrayList<Statement> statementList;

    /**
     * Constructor for Structogram entity
     * @param fileName name of the file
     * @param functionName name of the structogram
     * @param funcType type of the structogram
     */
    public Structogram(String fileName, String functionName, FuncType funcType) {
        this.fileName = fileName;
        this.functionName = functionName;
        this.funcType = funcType;
        this.parameterList = new ArrayList<>();
        this.statementList = new ArrayList<>();
        this.precondition = "";
        this.postcondition = "";
    }

    /**
     * Constructor for Structogram entity
     * @param fileName name of the file
     * @param functionName name of the structogram
     * @param funcType type of the structogram
     * @param parameterList parameter's of the structogram
     */
    public Structogram(String fileName, String functionName, FuncType funcType, ArrayList<Declaration> parameterList) {
        this.fileName = fileName;
        this.functionName = functionName;
        this.funcType = funcType;
        this.parameterList = parameterList;
        this.statementList = new ArrayList<>();
        this.precondition = "";
        this.postcondition = "";
    }

    /**
     * Constructor for Structogram entity
     * @param fileName name of the file
     * @param functionName name of the structogram
     * @param funcType type of the structogram
     * @param precondition that condition must be true at the beginning of the function
     * @param postcondition that condition must be true at the, when the program exits from the function
     */
    public Structogram(String fileName, String functionName, FuncType funcType, String precondition, String postcondition) {
        this.fileName = fileName;
        this.functionName = functionName;
        this.funcType = funcType;
        this.precondition = precondition;
        this.postcondition = postcondition;
        this.parameterList = new ArrayList<>();
        this.statementList = new ArrayList<>();
    }

    /**
     * Constructor for Structogram entity
     * @param fileName name of the file
     * @param functionName name of the structogram
     * @param funcType type of the structogram
     * @param precondition that condition must be true at the beginning of the function
     * @param postcondition that condition what must be true at the, when the program exits from the function
     * @param parameterList parameter's of the function structogram
     */
    public Structogram(String fileName, String functionName, FuncType funcType, String precondition, String postcondition, ArrayList<Declaration> parameterList) {
        this.fileName = fileName;
        this.functionName = functionName;
        this.funcType = funcType;
        this.precondition = precondition;
        this.postcondition = postcondition;
        this.parameterList = parameterList;
        this.statementList = new ArrayList<>();
    }

    /**
     * Gets file name.
     * @return current file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets file name.
     * @param fileName current file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets function name.
     * @return current function name
     */
    public String getFuncName() {
        return functionName;
    }

    /**
     * Sets function name.
     * @param functionName current function name
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    /**
     * Gets statements in the structogram.
     * @return statments in the srtuctogram
     */
    public ArrayList<Statement> getStatementList() {
        return statementList;
    }

    /**
     * Sets statements in the structogram.
     * @param statementList statments in the srtuctogram
     */
    public void setStatementList(ArrayList<Statement> statementList) {
        this.statementList = statementList;
    }

    /**
     * Gets function (structogram) type.
     * @return current function type
     */
    public FuncType getFuncType() {
        return funcType;
    }

    /**
     * Sets function (structogram) type.
     * @param funcType current function type
     */
    public void setFuncType(FuncType funcType) {
        this.funcType = funcType;
    }

    /**
     * Gets precondition.
     * @return current precondition
     */
    public String getPrecondition() {
        return precondition;
    }

    /**
     * Sets precondition.
     * @param precondition current precondition
     */
    public void setPrecondition(String precondition) {
        this.precondition = precondition;
    }

    /**
     * Gets postcondition.
     * @return current postcondition
     */
    public String getPostcondition() {
        return postcondition;
    }

    /**
     * Sets postcondition.
     * @param postcondition current postcondition
     */
    public void setPostcondition(String postcondition) {
        this.postcondition = postcondition;
    }

    /**
     * Get list of the parameters.
     * @return current parameters
     */
    public ArrayList<Declaration> getParameterList() {
        return parameterList;
    }

    /**
     * Get current parameter types.
     * @return current parameter types in a list
     */
    public ArrayList<VarType> getParameterTypes(){
        ArrayList<VarType> parameterTypes = new ArrayList<>();

        for (Declaration parameter : parameterList){
            parameterTypes.add(parameter.getVarType());
        }

        return parameterTypes;
    }

    /**
     * Get list of the parameters.
     * @param parameterList current parameters
     */
    public void setParameterList(ArrayList<Declaration> parameterList) {
        this.parameterList = parameterList;
    }

    /**
     * Validates function calls in structogram.
     * @return current invalid functions
     */
    public HashSet<Structogram> functionValidation(){
        return recursiveValidationChecking(statementList);
    }

    /**
     * Recursively checks the statements for inner functions validation.
     * @param statementList current statement list
     */
    private HashSet<Structogram> recursiveValidationChecking(ArrayList<Statement> statementList){
        HashSet<Structogram> invalidFuncSet = new HashSet<>();
        for (Statement statement : statementList){

            if (statement instanceof FunctionOperation && !(((FunctionOperation) statement).getFunction() instanceof FinalStructogram) && !((FunctionOperation) statement).getFunction().equals(this)){
                boolean found = false;
                File folder = new File(Serialize.getConfigHomePath() + "/saves/");
                Structogram currentFunction = ((FunctionOperation) statement).getFunction();

                for (final File fileEntry : Objects.requireNonNull(folder.listFiles())){
                    if (fileEntry.isFile()) {
                        if (currentFunction.getFileName().equals(fileEntry.getName().split("\\.")[0])){
                            found = true;
                            Structogram newFunction = Serialize.load(fileEntry.getPath());
                            assert newFunction != null;
                            if(!currentFunction.equals(newFunction)){
                                if (currentFunction.getFuncName().equals(newFunction.getFuncName()) && areParameterFieldsEqual(currentFunction, newFunction)){
                                    ((FunctionOperation) statement).setFunction(newFunction);
                                } else {
                                    invalidFuncSet.add(((FunctionOperation) statement).getFunction());
                                }
                            }
                            break;
                        }
                    }
                }
                if (!found){
                    invalidFuncSet.add(((FunctionOperation) statement).getFunction());
                }
                HashSet<Structogram> innerSet = recursiveValidationChecking(((FunctionOperation) statement).getFunction().getStatementList());
                invalidFuncSet.addAll(innerSet);

            } else if (statement instanceof Loop){
                HashSet<Structogram> innerSet = recursiveValidationChecking(((Loop) statement).getStatementList());
                invalidFuncSet.addAll(innerSet);

            } else if (statement instanceof ConditionalStatement){

                for (Map.Entry<String, ArrayList<Statement>> entry : ((ConditionalStatement) statement).getBranches().entrySet()) {
                    HashSet<Structogram> innerSet = recursiveValidationChecking(entry.getValue());
                    invalidFuncSet.addAll(innerSet);
                }
                if (((ConditionalStatement) statement).getFalseBranchStatementList() != null) {
                    HashSet<Structogram> innerSet = recursiveValidationChecking(((ConditionalStatement) statement).getFalseBranchStatementList());
                    invalidFuncSet.addAll(innerSet);
                }
            }
        }
        return invalidFuncSet;
    }

    /**
     * Decides that the two parameter list has same size, and same
     * type of parameters (type, and array variable, or not)
     * @param old last known parameter list
     * @param current current parameter list
     * @return equality state
     */
    private boolean areParameterFieldsEqual(Structogram old, Structogram current){
        ArrayList<Declaration> oldParameters = old.getParameterList();
        ArrayList<Declaration>  currentParameters = current.getParameterList();
        if (oldParameters.size() != currentParameters.size()){
            return false;
        }

        for(int i = 0; i < oldParameters.size(); i++){
            if ((oldParameters.get(i) instanceof SimpleDeclaration && currentParameters.get(i) instanceof  ArrayDeclaration) ||
                    (oldParameters.get(i) instanceof ArrayDeclaration && currentParameters.get(i) instanceof SimpleDeclaration)){
                return false;
            }

            if (oldParameters.get(i).getVarType() != currentParameters.get(i).getVarType()){
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the transformed, string version of parameters, according to
     * Java syntax, separated with commas.
     * @return String representation of parameters
     */
    public String parameterListToJavaString(){
        StringBuilder sb = new StringBuilder();
        for (Declaration parameter : parameterList){
            sb.append(parameter.getVarType().getJava());
            if (parameter instanceof ArrayDeclaration){
                sb.append("[]");
            }
            sb.append(" ").append(parameter.getVarName());
            if (!parameter.equals(parameterList.get(parameterList.size() - 1))){
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Gets the transformed, string version of parameters, according to
     * C++ syntax, separated with commas.
     * @return String representation of parameters
     */
    private String parameterListToCppString(){
        StringBuilder sb = new StringBuilder();
        for (Declaration parameter : parameterList){
            sb.append(parameter.getVarType().getCpp()).append(" ").append(parameter.getVarName());
            if (parameter instanceof ArrayDeclaration){
                sb.append("[]");
            }
            if (!parameter.equals(parameterList.get(parameterList.size() - 1))){
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Gets the transformed, string version of parameters, according to
     * Python syntax, separated with commas.
     * @return String representation of parameters
     */
    private String parameterListToPythonString(){
        StringBuilder sb = new StringBuilder();
        for (Declaration parameter : parameterList){
            sb.append(parameter.getVarName());
            if (!parameter.equals(parameterList.get(parameterList.size() - 1))){
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Formats conditions (e.. loop condition, if condition)
     * to the right format, according to current programming language.
     * @param condition current condition
     * @param language current language
     * @return transformed version of condition
     */
    private String formatCondition(String condition, Language language){
        String formattedCondition;
        switch (language){
            case JAVA:
            case CPP:
                formattedCondition = condition
                        .replaceAll("\\bAND\\b", "&&")
                        .replaceAll("\\bOR\\b", "||")
                        .replaceAll("\\bNOT\\b", "!")
                        .replaceAll("\\bTRUE\\b", "true")
                        .replaceAll("\\bFALSE\\b", "false");
                break;
            case PYTHON:
                formattedCondition = condition
                        .replaceAll("\\bAND\\b", "and")
                        .replaceAll("\\bOR\\b", "or")
                        .replaceAll("\\bNOT\\b", "not")
                        .replaceAll("\\bTRUE\\b", "True")
                        .replaceAll("\\bFALSE\\b", "False");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + language);
        }
        return formattedCondition;
    }

    private String getTabString(int tabCounter){
        StringBuilder tabSb = new StringBuilder();
        for(int i = 0; i<tabCounter; ++i ){
            tabSb.append("\t");
        }
        return tabSb.toString();
    }

    /**
     * Generates precondition the right format, according to current programming language.
     * @param language current language
     * @return generated code lines
     */
    private String generatePrecondition(Language language){
        if (!precondition.equals("")){
            StringBuilder sb = new StringBuilder();
            switch (language){
                case JAVA:
                    sb
                            .append(System.lineSeparator())
                            .append(getTabString(1))
                            .append("if (!(")
                            .append(formatCondition(precondition, language))
                            .append(")){")
                            .append(System.lineSeparator())
                            .append(getTabString(2))
                            .append("throw new IllegalArgumentException(\"Precondition failed.\");")
                            .append(System.lineSeparator())
                            .append(getTabString(1))
                            .append("}");
                    break;
                case CPP:
                    sb
                            .append(System.lineSeparator())
                            .append(getTabString(1))
                            .append("if (!(")
                            .append(formatCondition(precondition, language))
                            .append(")){")
                            .append(System.lineSeparator())
                            .append(getTabString(2))
                            .append("std::invalid_argument(\"Precondition failed.\");")
                            .append(System.lineSeparator())
                            .append(getTabString(1))
                            .append("}");
                    break;
                case PYTHON:
                    sb
                            .append(System.lineSeparator())
                            .append(getTabString(1))
                            .append("if not (")
                            .append(formatCondition(precondition, language))
                            .append("):")
                            .append(System.lineSeparator())
                            .append(getTabString(2))
                            .append("raise ValueError(\"Precondition failed.\")");
                    break;
            }
            return sb.toString();
        }
        return "";
    }

    /**
     * Generates precondition the right format, according to current programming language.
     * @param language current language
     * @param returnValue current return value
     * @param tabCounter number of tabs
     * @return generated code lines
     */
    private String generatePostcondition(Language language, String returnValue, int tabCounter){
        if(!postcondition.equals("")){
            StringBuilder sb = new StringBuilder();

            switch (language){
                case JAVA:
                    sb
                            .append("if (")
                            .append(formatCondition(postcondition, language))
                            .append("){")
                            .append(System.lineSeparator())
                            .append(getTabString(tabCounter + 1))
                            .append("return ")
                            .append(formatCondition(returnValue, language))
                            .append(";")
                            .append(System.lineSeparator())
                            .append(getTabString(tabCounter))
                            .append("} else {")
                            .append(System.lineSeparator())
                            .append(getTabString(tabCounter + 1))
                            .append("throw new IllegalArgumentException(\"Postcondition failed.\")")
                            .append(System.lineSeparator())
                            .append(getTabString(tabCounter))
                            .append("}");
                    break;
                case CPP:
                    sb
                            .append("if (")
                            .append(formatCondition(postcondition, language))
                            .append("){")
                            .append(System.lineSeparator())
                            .append(getTabString(tabCounter + 1))
                            .append("return ")
                            .append(formatCondition(returnValue, language))
                            .append(";")
                            .append(System.lineSeparator())
                            .append(getTabString(tabCounter))
                            .append("} else {")
                            .append(System.lineSeparator())
                            .append(getTabString(tabCounter + 1))
                            .append("std::invalid_argument(\"Postcondition failed.\")")
                            .append(System.lineSeparator())
                            .append(getTabString(tabCounter))
                            .append("}");
                    break;
                case PYTHON:
                    sb
                            .append("if ")
                            .append(formatCondition(postcondition, language))
                            .append(":")
                            .append(System.lineSeparator())
                            .append(getTabString(tabCounter + 1))
                            .append("return ")
                            .append(formatCondition(returnValue, language))
                            .append(System.lineSeparator())
                            .append(getTabString(tabCounter))
                            .append("else:")
                            .append(System.lineSeparator())
                            .append(getTabString(tabCounter + 1))
                            .append("raise ValueError(\"Postcondition failed.\")");
            }
            return sb.toString();
        } else {
            switch (language){
                case JAVA:
                case CPP:
                    return "return " + returnValue + ";";
                case PYTHON:
                    return "return " + returnValue;
            }
        }
        return "";
    }

    /**
     * Genetares code, according to the current language syntax.
     * @param language current language
     * @return generated code
     * @throws IllegalStateException when switch gets unknown programming language
     */
    public String generateCode(Language language) throws IllegalStateException {
        StringBuilder sb = new StringBuilder();
        int tabCounter = 0;
        LinkedHashSet<Structogram> innerFunctionsSet = new LinkedHashSet<>();

        switch(language){
            case JAVA:
                sb
                        .append(funcType.getJava())
                        .append(" ").append(functionName)
                        .append("(")
                        .append(parameterListToJavaString())
                        .append(") {")
                        .append(generatePrecondition(language));
                toJava(sb, statementList, tabCounter, innerFunctionsSet);
                sb.append("}");
                break;
            case CPP:
                sb
                        .append(funcType.getCpp())
                        .append(" ")
                        .append(functionName)
                        .append("(")
                        .append(parameterListToCppString())
                        .append(") {")
                        .append(generatePrecondition(language));
                toCpp(sb, statementList, tabCounter, innerFunctionsSet);
                sb.append("}");
                break;
            case PYTHON:
                sb
                        .append("def ")
                        .append(functionName)
                        .append("(")
                        .append(parameterListToPythonString())
                        .append("):")
                        .append(generatePrecondition(language));
                toPython(sb, statementList, tabCounter, innerFunctionsSet);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + language);
        }

        for (Structogram function : innerFunctionsSet){
            sb
                    .insert(0, function.generateCode(language) +
                            System.lineSeparator() +
                            System.lineSeparator());
        }

        return sb.toString();
    }

    /**
     * Recursive generator function for Java syntax
     * @param sb adding the code to this
     * @param statementList list of statements(if one statement has other
     *                      statement list, it will call this function recursively)
     * @param tabCounter number of tabs
     */
    private void toJava(StringBuilder sb, ArrayList<Statement> statementList, int tabCounter, LinkedHashSet<Structogram> innerFunctionsSet) {
        sb.
                append(System.lineSeparator());

        tabCounter++;

        for (Statement statement : statementList) {
            sb.
                    append(getTabString(tabCounter));
            if (statement instanceof VarOperation){
                if (statement instanceof Definition){
                    sb
                            .append(((Definition) statement).getVarType().getJava())
                            .append(" ")
                            .append(((Definition) statement).getVarName());

                    if (statement instanceof ArrayDefinition){
                        sb
                                .append("[]");
                    }
                    sb
                            .append(" = ");

                    if (statement instanceof ArrayDefinition){
                        sb
                                .append("new ")
                                .append(((ArrayDefinition) statement).getVarType().getJava())
                                .append("[]{")
                                .append(((ArrayDefinition) statement).valueListToString())
                                .append("}");

                    } else if (statement instanceof SimpleDefinition){
                        if(((SimpleDefinition) statement).getVarType() == VarType.BOOLEAN){
                            sb
                                    .append(formatCondition(((SimpleDefinition) statement).getVarValue(), Language.JAVA));
                        } else {
                            sb
                                    .append(((SimpleDefinition) statement).getVarValue());
                        }
                    }

                     sb
                             .append(";");
                } else if (statement instanceof Declaration){
                    sb
                            .append(((Declaration) statement).getVarType().getJava())
                            .append(" ")
                            .append(((Declaration) statement).getVarName());

                    if (statement instanceof ArrayDeclarationWithSize) {
                        sb
                                .append("[] = new ")
                                .append(((ArrayDeclarationWithSize) statement).getVarType())
                                .append("[")
                                .append(((ArrayDeclarationWithSize) statement).getSize())
                                .append("]");
                    }

                    sb
                            .append(";");

                } else if (statement instanceof Assignment){
                    sb
                            .append(((Assignment) statement).getVarName());

                    if (statement instanceof SimpleAssignment) {

                        if (statement instanceof ArrayItemAssignment){
                            sb
                                    .append("[")
                                    .append(((ArrayItemAssignment) statement).getIndex())
                                    .append("]");
                        }
                    }
                    sb
                            .append(" = ");

                    if (statement instanceof ArrayAssignment){
                        sb
                                .append("new ")
                                .append(((ArrayAssignment) statement).getVarType().getJava())
                                .append("[]{")
                                .append(((ArrayAssignment) statement).valueListToString())
                                .append("}");

                    } else if (statement instanceof SimpleAssignment){
                        if(((SimpleAssignment) statement).getVarValue().equals(formatCondition(((SimpleAssignment) statement).getVarValue(), Language.JAVA))){
                            sb
                                    .append(((SimpleAssignment) statement).getVarValue());
                        } else {
                            sb
                                    .append(formatCondition(((SimpleAssignment) statement).getVarValue(), Language.JAVA));
                        }
                    }
                    sb
                            .append(";");
                }

            } else if (statement instanceof Loop) {

                if (statement instanceof ForLoop) {
                    sb
                            .append("for (int ")
                            .append(((ForLoop) statement).getVarName())
                            .append(" = ")
                            .append(((ForLoop) statement).getStart())
                            .append("; ")
                            .append(((ForLoop) statement).getVarName())
                            .append(" < ")
                            .append(((ForLoop) statement).getEnd())
                            .append("; ++")
                            .append(((ForLoop) statement).getVarName())
                            .append(") {");

                } else if (statement instanceof ForEachLoop) {
                    sb
                            .append("for (")
                            .append(((ForEachLoop) statement).getItemType())
                            .append(" ")
                            .append(((ForEachLoop) statement).getActualItemName())
                            .append(" : ")
                            .append(((ForEachLoop) statement).getArrayVarName())
                            .append("){");

                } else if (statement instanceof WhileLoop) {

                    if (statement instanceof DoWhileLoop) {
                        sb
                                .append("do {");

                    } else {
                        sb
                                .append("while (")
                                .append(formatCondition(((WhileLoop) statement).getCondition(), Language.JAVA))
                                .append(") {");
                    }
                }
                toJava(sb, ((Loop) statement).getStatementList(), tabCounter, innerFunctionsSet);

                if (statement instanceof DoWhileLoop) {
                    sb
                            .append(getTabString(tabCounter))
                            .append("} while (")
                            .append(formatCondition(((DoWhileLoop) statement).getCondition(), Language.JAVA))
                            .append(");");

                } else {
                    sb
                            .append(getTabString(tabCounter))
                            .append("}");
                }

            } else if (statement instanceof ConditionalStatement) {
                if (statement instanceof If) {
                    boolean firstElem = false;
                    for (Map.Entry<String, ArrayList<Statement>> entry : ((If) statement).getBranches().entrySet()) {
                        if (!firstElem) {
                            firstElem = true;
                            sb
                                    .append("if (")
                                    .append(formatCondition(entry.getKey(), Language.JAVA))
                                    .append(") {");
                            toJava(sb, entry.getValue(), tabCounter, innerFunctionsSet);
                        } else {
                            sb
                                    .append(getTabString(tabCounter))
                                    .append("} else if (")
                                    .append(formatCondition(entry.getKey(), Language.JAVA))
                                    .append(") {");
                            toJava(sb, entry.getValue(), tabCounter, innerFunctionsSet);
                        }
                    }
                    if (((If) statement).getFalseBranchStatementList() != null) {
                        sb
                                .append(getTabString(tabCounter))
                                .append("} else {");
                        toJava(sb, ((If) statement).getFalseBranchStatementList(), tabCounter, innerFunctionsSet);
                    }
                    sb
                            .append(getTabString(tabCounter))
                            .append("}");

                } else if (statement instanceof SwitchCase) {
                    sb
                            .append("switch (")
                            .append(((SwitchCase) statement).getExpression())
                            .append(") {")
                            .append(System.lineSeparator());

                    for (Map.Entry<String, ArrayList<Statement>> entry : ((SwitchCase) statement).getBranches().entrySet()) {
                        sb
                                .append(getTabString(tabCounter + 1))
                                .append("case ")
                                .append(entry.getKey())
                                .append(":");
                        toJava(sb, entry.getValue(), tabCounter + 1, innerFunctionsSet);
                        sb
                                .append(getTabString(tabCounter + 2))
                                .append("break;")
                                .append(System.lineSeparator());
                    }

                    if (((SwitchCase) statement).getFalseBranchStatementList() != null) {
                        sb
                                .append(getTabString(tabCounter + 1))
                                .append("default:");
                        toJava(sb, ((SwitchCase) statement).getFalseBranchStatementList(), tabCounter + 1, innerFunctionsSet);
                        sb
                                .append(getTabString(tabCounter + 2))
                                .append("break;")
                                .append(System.lineSeparator());
                    }
                    sb
                            .append(getTabString(tabCounter))
                            .append("}");
                }

            } else if (statement instanceof FunctionOperation){

                if (statement instanceof FunctionCall){

                    if (statement instanceof FunctionDefinition) {
                        sb
                                .append(((FunctionDefinition) statement).getVarType().getJava())
                                .append(" ");
                    }
                    if (statement instanceof FunctionAssignment){
                        sb
                                .append(((FunctionAssignment) statement).getVariableName())
                                .append(" = ");
                    }
                    sb
                            .append(((FunctionCall) statement).getFunction().getFuncName())
                            .append("(")
                            .append(((FunctionCall) statement).parameterListToString())
                            .append(");");

                    if (!((FunctionOperation) statement).getFunction().equals(this)){
                        innerFunctionsSet.add(((FunctionOperation) statement).getFunction());
                    }
                }

            } else if (statement instanceof JumpStatement) {
                if (statement instanceof Return) {
                    if (((Return) statement).isCheckPostCond()) {
                        sb
                                .append(generatePostcondition(Language.JAVA, ((Return) statement).getExpression(), tabCounter));
                    } else {
                        sb
                                .append("return");
                        if(!((Return) statement).getExpression().equals("")){
                            sb
                                    .append(" ")
                                    .append(formatCondition(((Return) statement).getExpression(), Language.JAVA));
                        }
                        sb
                                .append(";");
                    }
                } else if (statement instanceof Break){
                    sb
                            .append("break;");
                } else if (statement instanceof Continue){
                    sb
                            .append("continue;");
                }
            }
            sb
                    .append(System.lineSeparator());
        }
    }

    /**
     * Recursive generator function for C++ syntax
     * @param sb adding the code to this
     * @param statementList list of statements(if one statement has other
     *                      statement list, it will call this function recursively)
     * @param tabCounter number of tabs
     */
    private void toCpp(StringBuilder sb, ArrayList<Statement> statementList, int tabCounter, LinkedHashSet<Structogram> innerFunctionsSet) {
        sb.
                append(System.lineSeparator());

        tabCounter++;

        for (Statement statement : statementList) {
            sb.
                    append(getTabString(tabCounter));
            if (statement instanceof VarOperation){
                if (statement instanceof Definition){
                    sb
                            .append(((Definition) statement).getVarType().getCpp())
                            .append(" ")
                            .append(((Definition) statement).getVarName());

                    if (statement instanceof ArrayDefinition){
                        sb
                                .append("[]");
                    }
                    sb
                            .append(" = ");

                    if (statement instanceof ArrayDefinition){
                        sb
                                .append("{")
                                .append(((ArrayDefinition) statement).valueListToString())
                                .append("}");
                    } else if (statement instanceof SimpleDefinition){
                        if(((SimpleDefinition) statement).getVarType() == VarType.BOOLEAN){
                            sb
                                    .append(formatCondition(((SimpleDefinition) statement).getVarValue(), Language.CPP));
                        } else {
                            sb
                                    .append(((SimpleDefinition) statement).getVarValue());
                        }
                    }

                    sb
                            .append(";");
                } else if (statement instanceof Declaration){
                    sb
                            .append(((Declaration) statement).getVarType().getCpp())
                            .append(" ")
                            .append(((Declaration) statement).getVarName());

                    if (statement instanceof ArrayDeclarationWithSize) {
                        sb
                                .append("[")
                                .append(((ArrayDeclarationWithSize) statement).getSize())
                                .append("]");
                    }

                    sb
                            .append(";");

                } else if (statement instanceof Assignment){
                    sb
                            .append(((Assignment) statement).getVarName());

                    if (statement instanceof SimpleAssignment) {

                        if (statement instanceof ArrayItemAssignment){
                            sb
                                    .append("[")
                                    .append(((ArrayItemAssignment) statement).getIndex())
                                    .append("]");
                        }
                    }
                    sb
                            .append(" = ");

                    if (statement instanceof ArrayAssignment){
                        sb
                                .append("new ")
                                .append(((ArrayAssignment) statement).getVarType().getCpp())
                                .append("[]{")
                                .append(((ArrayAssignment) statement).valueListToString())
                                .append("}");
                    } else if (statement instanceof SimpleAssignment){
                        if(((SimpleAssignment) statement).getVarValue().equals(formatCondition(((SimpleAssignment) statement).getVarValue(), Language.CPP))){
                            sb
                                    .append(((SimpleAssignment) statement).getVarValue());
                        } else {
                            sb
                                    .append(formatCondition(((SimpleAssignment) statement).getVarValue(), Language.CPP));
                        }
                    }
                    sb
                            .append(";");
                }

            } else if (statement instanceof Loop) {

                if (statement instanceof ForLoop) {
                    sb
                            .append("for (int ")
                            .append(((ForLoop) statement).getVarName())
                            .append(" = ")
                            .append(((ForLoop) statement).getStart())
                            .append("; ")
                            .append(((ForLoop) statement).getVarName())
                            .append(" < ")
                            .append(((ForLoop) statement).getEnd())
                            .append("; ++")
                            .append(((ForLoop) statement).getVarName())
                            .append(") {");

                } else if (statement instanceof ForEachLoop) {
                    sb
                            .append("for (")
                            .append(((ForEachLoop) statement).getItemType())
                            .append(" ")
                            .append(((ForEachLoop) statement).getActualItemName())
                            .append(" : ")
                            .append(((ForEachLoop) statement).getArrayVarName())
                            .append("){");

                } else if (statement instanceof WhileLoop) {

                    if (statement instanceof DoWhileLoop) {
                        sb
                                .append("do {");

                    } else {
                        sb
                                .append("while (")
                                .append(formatCondition(((WhileLoop) statement).getCondition(), Language.CPP))
                                .append(") {");
                    }
                }
                toCpp(sb, ((Loop) statement).getStatementList(), tabCounter, innerFunctionsSet);

                if (statement instanceof DoWhileLoop) {
                    sb
                            .append(getTabString(tabCounter))
                            .append("} while (")
                            .append(formatCondition(((DoWhileLoop) statement).getCondition(), Language.CPP))
                            .append(");");

                } else {
                    sb
                            .append(getTabString(tabCounter))
                            .append("}");
                }

            } else if (statement instanceof ConditionalStatement) {
                if (statement instanceof If) {
                    boolean firstElem = false;
                    for (Map.Entry<String, ArrayList<Statement>> entry : ((If) statement).getBranches().entrySet()) {
                        if (!firstElem) {
                            firstElem = true;
                            sb
                                    .append("if (")
                                    .append(formatCondition(entry.getKey(), Language.CPP))
                                    .append(") {");
                            toCpp(sb, entry.getValue(), tabCounter, innerFunctionsSet);
                        } else {
                            sb
                                    .append(getTabString(tabCounter))
                                    .append("} else if (")
                                    .append(formatCondition(entry.getKey(), Language.CPP))
                                    .append(") {");
                            toCpp(sb, entry.getValue(), tabCounter, innerFunctionsSet);
                        }
                    }
                    if (((If) statement).getFalseBranchStatementList() != null) {
                        sb
                                .append(getTabString(tabCounter))
                                .append("} else {");
                        toCpp(sb, ((If) statement).getFalseBranchStatementList(), tabCounter, innerFunctionsSet);
                    }
                    sb
                            .append(getTabString(tabCounter))
                            .append("}");

                } else if (statement instanceof SwitchCase) {
                    sb
                            .append("switch (")
                            .append(((SwitchCase) statement).getExpression())
                            .append(") {")
                            .append(System.lineSeparator());

                    for (Map.Entry<String, ArrayList<Statement>> entry : ((SwitchCase) statement).getBranches().entrySet()) {
                        sb
                                .append(getTabString(tabCounter + 1))
                                .append("case ")
                                .append(entry.getKey())
                                .append(":");
                        toCpp(sb, entry.getValue(), tabCounter + 1, innerFunctionsSet);
                        sb
                                .append(getTabString(tabCounter + 2))
                                .append("break;")
                                .append(System.lineSeparator());
                    }

                    if (((SwitchCase) statement).getFalseBranchStatementList() != null) {
                        sb
                                .append(getTabString(tabCounter + 1))
                                .append("default:");
                        toCpp(sb, ((SwitchCase) statement).getFalseBranchStatementList(), tabCounter + 1, innerFunctionsSet);
                        sb
                                .append(getTabString(tabCounter + 2))
                                .append("break;")
                                .append(System.lineSeparator());
                    }
                    sb
                            .append(getTabString(tabCounter))
                            .append("}");
                }

            } else if (statement instanceof FunctionOperation){

                if (statement instanceof FunctionCall){

                    if (statement instanceof FunctionDefinition) {
                        sb
                                .append(((FunctionDefinition) statement).getVarType().getCpp())
                                .append(" ");
                    }
                    if (statement instanceof FunctionAssignment){
                        sb
                                .append(((FunctionAssignment) statement).getVariableName())
                                .append(" = ");
                    }
                    sb
                            .append(((FunctionCall) statement).getFunction().getFuncName())
                            .append("(")
                            .append(((FunctionCall) statement).parameterListToString())
                            .append(");");

                    if (!((FunctionOperation) statement).getFunction().equals(this)){
                        innerFunctionsSet.add(((FunctionOperation) statement).getFunction());
                    }
                }

            } else if (statement instanceof JumpStatement) {
                if (statement instanceof Return) {
                    if (((Return) statement).isCheckPostCond()) {
                        sb
                                .append(generatePostcondition(Language.CPP, ((Return) statement).getExpression(), tabCounter));
                    } else {
                        sb
                                .append("return");
                        if(!((Return) statement).getExpression().equals("")){
                            sb
                                    .append(" ")
                                    .append(formatCondition(((Return) statement).getExpression(), Language.CPP));
                        }
                        sb
                                .append(";");
                    }
                } else if (statement instanceof Break){
                    sb
                            .append("break;");
                } else if (statement instanceof Continue){
                    sb
                            .append("continue;");
                }
            }
            sb
                    .append(System.lineSeparator());
        }
    }

    /**
     * Recursive generator function for Python syntax
     * @param sb adding the code to this
     * @param statementList list of statements(if one statement has other
     *                      statement list, it will call this function recursively)
     * @param tabCounter number of tabs
     */
    private void toPython(StringBuilder sb, ArrayList<Statement> statementList, int tabCounter, LinkedHashSet<Structogram> innerFunctionsSet) {
        sb
                .append(System.lineSeparator());

        tabCounter++;

        for(Statement statement : statementList) {
            sb.
                    append(getTabString(tabCounter));
            if (statement instanceof VarOperation){
                if (statement instanceof Definition){
                    sb
                            .append(((Definition) statement).getVarName())
                            .append(" = ");

                    if (statement instanceof ArrayDefinition){
                        sb
                                .append("[")
                                .append(((ArrayDefinition) statement).valueListToString())
                                .append("]");

                    } else if (statement instanceof SimpleDefinition){
                        if(((SimpleDefinition) statement).getVarType() == VarType.BOOLEAN){
                            sb
                                    .append(formatCondition(((SimpleDefinition) statement).getVarValue(), Language.PYTHON));
                        } else {
                            sb
                                    .append(((SimpleDefinition) statement).getVarValue());
                        }
                    }
                } else if (statement instanceof Declaration){
                    sb
                            .append(((Declaration) statement).getVarName())
                            .append(" = ");

                    if (statement instanceof SimpleDeclaration){
                        if (((Declaration) statement).getVarType() == VarType.STRING || ((Declaration) statement).getVarType() == VarType.CHAR) {
                            sb
                                    .append("\"\"");
                        } else if (((Declaration) statement).getVarType() == VarType.INT){
                            sb
                                    .append("0");
                        } else if (((Declaration) statement).getVarType() == VarType.DOUBLE){
                            sb
                                    .append("0.0");
                        } else if (((Declaration) statement).getVarType() == VarType.BOOLEAN){
                            sb
                                    .append("False");
                        }
                    } else if (statement instanceof ArrayDeclarationWithSize){
                        sb
                                .append("[]");
                    }


                } else if (statement instanceof Assignment){
                    sb
                            .append(((Assignment) statement).getVarName());

                    if (statement instanceof SimpleAssignment) {

                        if (statement instanceof ArrayItemAssignment){
                            sb
                                    .append("[")
                                    .append(((ArrayItemAssignment) statement).getIndex())
                                    .append("]");
                        }
                    }
                    sb
                            .append(" = ");

                    if (statement instanceof ArrayAssignment){
                        sb
                                .append("[")
                                .append(((ArrayAssignment) statement).valueListToString())
                                .append("]");
                    } else if (statement instanceof SimpleAssignment){
                        if(((SimpleAssignment) statement).getVarValue().equals(formatCondition(((SimpleAssignment) statement).getVarValue(), Language.PYTHON))){
                            sb
                                    .append(((SimpleAssignment) statement).getVarValue());
                        } else {
                            sb
                                    .append(formatCondition(((SimpleAssignment) statement).getVarValue(), Language.PYTHON));
                        }
                    }
                }
                sb
                        .append(System.lineSeparator());

            } else if (statement instanceof Loop) {

                if (statement instanceof ForLoop) {
                    sb
                            .append("for ")
                            .append(((ForLoop) statement).getVarName())
                            .append(" in range(")
                            .append(((ForLoop) statement).getStart())
                            .append(", ")
                            .append(((ForLoop) statement).getEnd())
                            .append("):");

                } else if (statement instanceof ForEachLoop) {
                    sb
                            .append("for ")
                            .append(((ForEachLoop) statement).getActualItemName())
                            .append(" in ")
                            .append(((ForEachLoop) statement).getArrayVarName())
                            .append(":");
                } else if (statement instanceof WhileLoop) {

                    if (statement instanceof DoWhileLoop) {
                        sb
                                .append("while True:");

                    } else {
                        sb
                                .append("while ")
                                .append(formatCondition(((WhileLoop) statement).getCondition(), Language.PYTHON))
                                .append(":");
                    }
                }
                toPython(sb, ((Loop) statement).getStatementList(), tabCounter, innerFunctionsSet);

                if (statement instanceof DoWhileLoop) {
                    sb
                            .append(getTabString(tabCounter + 1))
                            .append("if ")
                            .append(formatCondition(((DoWhileLoop) statement).getCondition(), Language.PYTHON))
                            .append(":")
                            .append(System.lineSeparator())
                            .append(getTabString(tabCounter + 2))
                            .append("break")
                            .append(System.lineSeparator());
                }

            } else if (statement instanceof ConditionalStatement) {
                if (statement instanceof If) {
                    boolean firstElem = false;
                    for (Map.Entry<String, ArrayList<Statement>> entry : ((If) statement).getBranches().entrySet()) {
                        if (!firstElem) {
                            firstElem = true;
                            sb
                                    .append("if ")
                                    .append(formatCondition(entry.getKey(), Language.PYTHON))
                                    .append(":");
                            toPython(sb, entry.getValue(), tabCounter, innerFunctionsSet);
                        } else {
                            sb
                                    .append(getTabString(tabCounter))
                                    .append("elif ")
                                    .append(formatCondition(entry.getKey(), Language.PYTHON))
                                    .append(":");
                            toPython(sb, entry.getValue(), tabCounter, innerFunctionsSet);
                        }
                    }
                    if (((If) statement).getFalseBranchStatementList() != null) {
                        sb
                                .append(getTabString(tabCounter))
                                .append("else:");
                        toPython(sb, ((If) statement).getFalseBranchStatementList(), tabCounter, innerFunctionsSet);
                    }

                } else if (statement instanceof SwitchCase) {
                    boolean isFirst = true;
                    for (Map.Entry<String, ArrayList<Statement>> entry : ((SwitchCase) statement).getBranches().entrySet()) {
                        if (isFirst){
                            sb
                                    .append("if ");
                            isFirst = false;
                        } else {
                            sb
                                    .append(getTabString(tabCounter))
                                    .append("elif ");
                        }
                        sb
                                .append(((SwitchCase) statement).getExpression())
                                .append(" == ")
                                .append(entry.getKey())
                                .append(":");
                        toPython(sb, entry.getValue(), tabCounter, innerFunctionsSet);
                    }

                    if (((SwitchCase) statement).getFalseBranchStatementList() != null) {
                        sb
                                .append(getTabString(tabCounter))
                                .append("else:");
                        toPython(sb, ((SwitchCase) statement).getFalseBranchStatementList(), tabCounter, innerFunctionsSet);
                    }
                }

            } else if (statement instanceof FunctionOperation){

                if (statement instanceof FunctionCall){

                    if (statement instanceof FunctionAssignment){
                        sb
                                .append(((FunctionAssignment) statement).getVariableName())
                                .append(" = ");
                    }
                    sb
                            .append(((FunctionCall) statement).getFunction().getFuncName())
                            .append("(")
                            .append(((FunctionCall) statement).parameterListToString())
                            .append(")");
                }
                sb
                        .append(System.lineSeparator());

                if (!((FunctionOperation) statement).getFunction().equals(this)){
                    innerFunctionsSet.add(((FunctionOperation) statement).getFunction());
                }

            } else if (statement instanceof JumpStatement) {
                if (statement instanceof Return) {
                    if (((Return) statement).isCheckPostCond()) {
                        sb
                                .append(generatePostcondition(Language.PYTHON, ((Return) statement).getExpression(), tabCounter));
                    } else {
                        sb
                                .append("return");
                        if(!((Return) statement).getExpression().equals("")){
                            sb
                                    .append(" ")
                                    .append(formatCondition(((Return) statement).getExpression(), Language.PYTHON));
                        }
                    }

                } else if (statement instanceof Break){
                    sb
                            .append("break");
                } else if (statement instanceof Continue){
                    sb
                            .append("continue");
                }
                sb
                        .append(System.lineSeparator());
            }
        }
    }

    /**
     * Overridden equals() method for Structogram entity.
     * @param o Object to check
     * @return result of checking
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof Structogram)) {
            return false;
        }

        Structogram s = (Structogram) o;

        return s.fileName.equals(this.fileName) &&
                s.functionName.equals(this.functionName) &&
                s.precondition.equals(this.precondition) &&
                s.postcondition.equals(this.postcondition) &&
                s.parameterList.equals(this.parameterList) &&
                s.statementList.equals(this.statementList);
    }
}