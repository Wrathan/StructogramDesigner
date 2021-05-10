package backend.entities.statements.functionoperations;

import backend.entities.structograms.Structogram;
import backend.enums.VarType;

import java.util.ArrayList;

/**
 * Representation of a variable definition, which gets a function's value.
 */
public class FunctionDefinition extends FunctionAssignment {

    private VarType varType;

    /**
     * Constructor for FunctionDefinition entity.
     * @param function actual function
     * @param parameterList list of actual parameters in the chosen function
     * @param variableName variable, which gets the function's values
     * @param varType type of the variable
     */
    public FunctionDefinition(Structogram function, ArrayList<String> parameterList, String variableName, VarType varType) {
        super(function, parameterList, variableName);
        this.varType = varType;
    }

    /**
     * Gets the type of variable.
     * @return current variable type
     */
    public VarType getVarType() {
        return varType;
    }

    /**
     * Sets the variable type
     * @param varType current variable type
     */
    public void setVarType(VarType varType) {
        this.varType = varType;
    }

    /**
     * Overridden equals() method for FunctionDefinition entity.
     * @param o Object to check
     * @return result of checking
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof FunctionDefinition)) {
            return false;
        }

        FunctionDefinition fd = (FunctionDefinition) o;

        return  fd.function.equals(this.function) && fd.parameterList.equals(this.parameterList)
                && fd.variableName.equals(this.variableName) && fd.varType == this.varType;
    }
}
