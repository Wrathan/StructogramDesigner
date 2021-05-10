package backend.entities.statements.varoperations.declaration;

import backend.enums.VarType;

/**
 * Representation of a simple variable's declaration.
 */
public class SimpleDeclaration extends Declaration {

    /**
     * Constructor fot SimpleDeclaration entity.
     * @param variableName name of variable
     * @param varType type of variable
     */
    public SimpleDeclaration(String variableName, VarType varType){
        super(variableName, varType);
    }

    /**
     * Overridden equals() method for Declaration entity.
     * @param o Object to check
     * @return result of checking
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof SimpleDeclaration)) {
            return false;
        }

        SimpleDeclaration d = (SimpleDeclaration) o;

        return d.varType == this.varType && d.varName.equals(this.varName);
    }
}
