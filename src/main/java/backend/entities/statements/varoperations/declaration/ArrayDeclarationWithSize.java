package backend.entities.statements.varoperations.declaration;

import backend.enums.VarType;

/**
 * Representation of an array's declaration with size.
 */
public class ArrayDeclarationWithSize extends ArrayDeclaration {

    String size;

    /**
     * Constructor for ArrayDeclaration entity.
     *
     * @param varName name of the array
     * @param varType type of the array
     * @param size size of the array
     */
    public ArrayDeclarationWithSize(String varName, VarType varType, String size) {
        super(varName, varType);
        this.size = size;
    }

    /**
     * Gets current size.
     * @return current size
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets current size.
     * @param size current size
     */
    public void setSize(String size) {
        this.size = size;
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

        ArrayDeclarationWithSize d = (ArrayDeclarationWithSize) o;

        return d.varType == this.varType && d.varName.equals(this.varName) && d.size.equals(this.size);
    }
}
