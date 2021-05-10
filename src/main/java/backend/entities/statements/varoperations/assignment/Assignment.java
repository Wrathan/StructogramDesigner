package backend.entities.statements.varoperations.assignment;

import backend.entities.statements.varoperations.VarOperation;

/**
 * Abstract superclass for assignments.
 */
public abstract class Assignment extends VarOperation {

    /**
     * Constructor for Assignment entity.
     * @param varName name of the variable
     */
    protected Assignment(String varName) {
        super(varName);
    }
}
