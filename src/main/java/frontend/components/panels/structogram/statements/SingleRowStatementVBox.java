package frontend.components.panels.structogram.statements;

import backend.entities.statements.Statement;
import backend.entities.statements.functionoperations.FunctionAssignment;
import backend.entities.statements.functionoperations.FunctionCall;
import backend.entities.statements.functionoperations.FunctionDefinition;
import backend.entities.statements.jumpstatements.Break;
import backend.entities.statements.jumpstatements.Continue;
import backend.entities.statements.jumpstatements.Return;
import backend.entities.statements.varoperations.assignment.ArrayAssignment;
import backend.entities.statements.varoperations.assignment.ArrayItemAssignment;
import backend.entities.statements.varoperations.assignment.SimpleAssignment;
import backend.entities.statements.varoperations.declaration.ArrayDeclarationWithSize;
import backend.entities.statements.varoperations.declaration.SimpleDeclaration;
import backend.entities.statements.varoperations.definition.ArrayDefinition;
import backend.entities.statements.varoperations.definition.SimpleDefinition;
import frontend.GuiManager;
import frontend.components.factorys.FxComponentFactory;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Single row statement panel for structogram figure panel.
 */
public class SingleRowStatementVBox extends StatementVBox {

    private Label statementLabel;
    private String statementString;

    /**
     * Constructor for SingleRowStatementVBox.
     * @param statementEntity related statement entity
     */
    public SingleRowStatementVBox(Statement statementEntity){
        super(statementEntity);
        setAlignment(Pos.TOP_LEFT);
        VBox.setVgrow(this, Priority.ALWAYS);

        initStatementLabel();
    }

    /**
     * Initialization of statement label, depending on the current statement kind.
     */
    private void initStatementLabel(){
        if (statementEntity instanceof SimpleDefinition){
            setSimpleDefinitionString();

        } else if (statementEntity instanceof SimpleDeclaration){
            setSimpleDeclarationString();

        } else if (statementEntity instanceof ArrayItemAssignment){
            setArrayItemAssignmentString();

        } else if (statementEntity instanceof SimpleAssignment){
            setSimpleAssignmentString();

        } else if (statementEntity instanceof ArrayDefinition){
            setArrayDefinitionString();

        } else if (statementEntity instanceof ArrayDeclarationWithSize){
            setArrayDeclarationWithSizeString();

        } else if (statementEntity instanceof ArrayAssignment) {
            setArrayAssignmentString();

        } else if (statementEntity instanceof FunctionDefinition){
            setFunctionDefinitionString();

        } else if (statementEntity instanceof FunctionAssignment){
            setFunctionAssignmentString();

        } else if (statementEntity instanceof FunctionCall){
            setFunctionCallString();
        } else if (statementEntity instanceof Return){
            setReturnString();

        } else if (statementEntity instanceof Break){
            setBreakString();

        } else if (statementEntity instanceof Continue){
            setContinueString();
        }
        statementLabel = FxComponentFactory.generateLabel(this, statementString, "statementLabel");
    }

    private void setSimpleDeclarationString() {
        statementString = ((SimpleDeclaration) statementEntity).getVarType().toString().toLowerCase() + " "
                + ((SimpleDeclaration) statementEntity).getVarName();
    }

    /**
     * Sets the text of a simple definition
     */
    private void setSimpleDefinitionString() {
        statementString = ((SimpleDefinition) statementEntity).getVarType().toString() + " "
                + ((SimpleDefinition) statementEntity).getVarName() + " = "
                + ((SimpleDefinition) statementEntity).getVarValue();
    }

    /**
     * Sets the text of a simple definition
     */
    private void setArrayItemAssignmentString() {
        statementString = ((ArrayItemAssignment) statementEntity).getVarName() + "["
                + ((ArrayItemAssignment) statementEntity).getIndex() + "] = "
                + ((ArrayItemAssignment) statementEntity).getVarValue();
    }

    /**
     * Sets the text of a simple definition
     */
    private void setSimpleAssignmentString() {
        statementString = ((SimpleAssignment) statementEntity).getVarName() + " = "
                + ((SimpleAssignment) statementEntity).getVarValue();
    }

    /**
     * Sets the text of a simple definition
     */
    private void setArrayDefinitionString() {
        statementString = ((ArrayDefinition) statementEntity).getVarType().toString() + "["
                + ((ArrayDefinition) statementEntity).getListSize() + "] "
                + ((ArrayDefinition) statementEntity).getVarName() + " = "
                + generateArrayString(((ArrayDefinition) statementEntity).getValueList());
    }

    /**
     * Sets the text of a simple definition
     */
    private void setArrayDeclarationWithSizeString() {
        statementString = ((ArrayDeclarationWithSize) statementEntity).getVarType().toString() + "["
                + ((ArrayDeclarationWithSize) statementEntity).getSize() + "] "
                + ((ArrayDeclarationWithSize) statementEntity).getVarName();
    }

    /**
     * Sets the text of a simple definition
     */
    private void setArrayAssignmentString() {
        statementString = ((ArrayAssignment) statementEntity).getVarName() + " = "
                + generateArrayString(((ArrayAssignment) statementEntity).getValueList());
    }

    /**
     * Sets the text of a simple definition
     */
    private void setFunctionDefinitionString() {
        statementString = ((FunctionDefinition) statementEntity).getVarType().toString() + " " +
                ((FunctionDefinition) statementEntity).getVariableName() + " = " +
                ((FunctionDefinition) statementEntity).getFunction().getFuncName() + "(" +
                ((FunctionDefinition) statementEntity).parameterListToString() + ")";
    }

    /**
     * Sets the text of a simple definition
     */
    private void setFunctionAssignmentString() {
        statementString = ((FunctionAssignment) statementEntity).getVariableName() + " = " +
                ((FunctionAssignment) statementEntity).getFunction().getFuncName() + "(" +
                ((FunctionAssignment) statementEntity).parameterListToString() + ")";
    }

    /**
     * Sets the text of a simple definition
     */
    private void setFunctionCallString() {
        statementString = ((FunctionCall) statementEntity).getFunction().getFuncName() + "(" +
                ((FunctionCall) statementEntity).parameterListToString() + ")";
    }

    /**
     * Sets the text of a simple definition
     */
    private void setReturnString() {
        if (((Return) statementEntity).isCheckPostCond()){
            statementString = "return " + ((Return) statementEntity).getExpression() + (" (postc. check)");
        } else {
            statementString = "return " + ((Return) statementEntity).getExpression();
        }
    }

    /**
     * Sets the text of a simple definition
     */
    private void setBreakString() {
        statementString = "break";
    }

    /**
     * Sets the text of a simple definition
     */
    private void setContinueString() {
        statementString = "continue";
    }

    /**
     * Sets the text of a function definition
     */
    public void updateFunctionDefinitionString() {
        statementString = ((FunctionDefinition) statementEntity).getVarType().toString() + " " +
                ((FunctionDefinition) statementEntity).getVariableName() + " = " +
                ((FunctionDefinition) statementEntity).getFunction().getFuncName() + "(" +
                ((FunctionDefinition) statementEntity).parameterListToString() + ")";
        GuiManager.removePane(this, 0);
        statementLabel = FxComponentFactory.generateLabel(this, statementString, "statementLabel");
    }

    /**
     * Sets the text of a function definition
     */
    public void updateFunctionAssignmentString() {
        statementString = ((FunctionAssignment) statementEntity).getVariableName() + " = " +
                ((FunctionAssignment) statementEntity).getFunction().getFuncName() + "(" +
                ((FunctionAssignment) statementEntity).parameterListToString() + ")";
        GuiManager.removePane(this, 0);
        statementLabel = FxComponentFactory.generateLabel(this, statementString, "statementLabel");
    }

    /**
     * Sets the text of a function definition
     */
    public void updateFunctionCallString() {
        statementString = ((FunctionCall) statementEntity).getFunction().getFuncName() + "(" +
                ((FunctionCall) statementEntity).parameterListToString() + ")";
        GuiManager.removePane(this, 0);
        statementLabel = FxComponentFactory.generateLabel(this, statementString, "statementLabel");
    }

    /**
     * Creates string from array variables, separated them with comma.
     * @param varValueList current array variables
     * @return current array variables transformed to string
     */
    private String generateArrayString(ArrayList<String> varValueList){
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for (int i = 0; i < varValueList.size() - 1; i++) {
            sb.append(varValueList.get(i)).append(", ");
        }
        sb.append(varValueList.get(varValueList.size() - 1)).append("]");

        return sb.toString();
    }

    /**
     * Gets statement label.
     * @return current statement label
     */
    public Label getStatementLabel() {
        return statementLabel;
    }
}
