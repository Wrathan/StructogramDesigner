package backend.entities.statements.functionoperations;

import backend.entities.structograms.Structogram;
import backend.entities.statements.Statement;

import java.util.ArrayList;

/**
 * Abstract superclass for function operations (e.g function call).
 */
public abstract class FunctionOperation extends Statement {

    protected Structogram function;
    protected ArrayList<String> parameterList;

    /**
     * COnstructor for FunctionOperation entity.
     * @param function actual function
     * @param parameterList list of actual parameters in the chosen function
     */
    protected FunctionOperation(Structogram function, ArrayList<String> parameterList) {
        this.function = function;
        this.parameterList = parameterList;
    }

    /**
     * Converts parameterList into a string, separate the values with commas.
     * @return String representation of parameterList
     */
    public String parameterListToString(){
        StringBuilder sb = new StringBuilder();
        for (String parameter : parameterList){
            sb.append(parameter);
            if (!parameter.equals(parameterList.get(parameterList.size() - 1))){
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Gets function.
     * @return current function value
     */
    public Structogram getFunction() {
        return function;
    }

    /**
     * Sets function.
     * @param function current function value
     */
    public void setFunction(Structogram function) {
        this.function = function;
    }

    /**
     * Gets actual parameters.
     * @return current parameters for function
     */
    public ArrayList<String> getParameterList() {
        return parameterList;
    }
}
