package backend.entities.statements.functionoperations;

import backend.entities.structograms.Structogram;

import java.util.ArrayList;

/**
 * Representation of an assignment, where we give the variable a function's value
 */
public class FunctionAssignment extends FunctionCall {

    protected String variableName;

    /**
     * Constructor for FunctionAssignment entity.
     * @param function actual function
     * @param parameterList list of actual parameters in the chosen function
     * @param variableName variable, which gets the function's values
     */
    public FunctionAssignment(Structogram function, ArrayList<String> parameterList, String variableName) {
        super(function, parameterList);
        this.variableName = variableName;
    }

    /**
     * Gets the variable name.
     * @return current variable name
     */
    public String getVariableName() {
        return variableName;
    }

    /**
     * Sets the variable name.
     * @param variableName current variable name
     */
    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    /**
     * Overridden equals() method for FunctionAssignment entity.
     * @param o Object to check
     * @return result of checking
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof FunctionAssignment)) {
            return false;
        }

        FunctionAssignment fa = (FunctionAssignment) o;

        return  fa.function.equals(this.function) && fa.parameterList.equals(this.parameterList) && fa.variableName.equals(this.variableName);
    }
}
