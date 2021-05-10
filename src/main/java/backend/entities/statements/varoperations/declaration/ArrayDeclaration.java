package backend.entities.statements.varoperations.declaration;

import backend.enums.VarType;

/**
 * Representation of an array's declaration.
 */
public class ArrayDeclaration extends Declaration {

    /**
     * Constructor for ArrayDeclaration entity.
     * @param varName name of the array
     * @param varType type of the array
     */
    public ArrayDeclaration(String varName, VarType varType) {
        super(varName, varType);
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

        if (!(o instanceof ArrayDeclaration)) {
            return false;
        }

        ArrayDeclaration d = (ArrayDeclaration) o;

        return d.varType == this.varType && d.varName.equals(this.varName);
    }
}
