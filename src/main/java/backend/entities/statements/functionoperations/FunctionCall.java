package backend.entities.statements.functionoperations;

import backend.entities.structograms.Structogram;

import java.util.ArrayList;

/**
 * Representation of a simple function call, without any assignment.
 */
public class FunctionCall extends FunctionOperation {

    /**
     * Constructor method for FunctionCall class.
     * @param function actual function
     * @param parameterList list of actual parameters in the chosen function
     */
    public FunctionCall(Structogram function, ArrayList<String> parameterList) {
        super(function, parameterList);
    }

    /**
     * Overridden equals() method for FunctionCall entity.
     * @param o Object to check
     * @return result of checking
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof FunctionCall)) {
            return false;
        }

        FunctionCall fc = (FunctionCall) o;

        return  fc.function.equals(this.function) && fc.parameterList.equals(this.parameterList);
    }
}