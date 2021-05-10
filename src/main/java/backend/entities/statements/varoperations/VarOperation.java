package backend.entities.statements.varoperations;

import backend.entities.statements.Statement;

/**
 * Abstract superclass for variable operations (e.g. definition, declaration, assignment).
 */
public abstract class VarOperation extends Statement {
    protected String varName;

    /**
     * Constructor for VarOperation entity.
     * @param varName nam of variable
     */
    protected VarOperation(String varName) {
        this.varName = varName;
    }

    /**
     * Gets variable name.
     * @return current variable name
     */
    public String getVarName() {
        return varName;
    }

    /**
     * Sets variable name.
     * @param varName current variable name
     */
    public void setVarName(String varName) {
        this.varName = varName;
    }
}
