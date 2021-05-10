package backend.entities.statements.varoperations.definition;

import backend.entities.statements.varoperations.VarOperation;
import backend.enums.VarType;

/**
 * Abstract superclass for definitions.
 */
public abstract class Definition extends VarOperation {

    protected VarType varType;

    /**
     * Constructor for Definition entity.
     * @param variableName name of the variable
     * @param varType type of the variable
     */
    protected Definition(String variableName, VarType varType) {
        super(variableName);
        this.varType = varType;
    }

    /**
     * Gets variable type.
     * @return current variable type
     */
    public VarType getVarType() {
        return varType;
    }

    /**
     * Sets variable type.
     * @param varType current variable type
     */
    public void setVarType(VarType varType) {
        this.varType = varType;
    }
}
