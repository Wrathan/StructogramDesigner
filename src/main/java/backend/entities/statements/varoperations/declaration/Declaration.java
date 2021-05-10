package backend.entities.statements.varoperations.declaration;

import backend.entities.statements.varoperations.VarOperation;
import backend.enums.VarType;

/**
 * Abstract superclass for declarations.
 */
public abstract class Declaration extends VarOperation {

    protected VarType varType;

    /**
     * Constructor for Declaration entity.
     * @param varName name of the variable
     * @param varType type of the variable
     */
    protected Declaration(String varName, VarType varType) {
        super(varName);
        this.varType = varType;
    }

    /**
     * Gets variable type.
     * @return current type
     */
    public VarType getVarType() {
        return varType;
    }

    /**
     * Sets variable type.
     * @param varType current type
     */
    public void setVarType(VarType varType) {
        this.varType = varType;
    }
}
