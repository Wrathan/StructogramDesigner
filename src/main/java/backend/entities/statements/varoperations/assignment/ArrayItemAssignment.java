package backend.entities.statements.varoperations.assignment;

/**
 * Representation of assignment of an array's element.
 */
public class ArrayItemAssignment extends SimpleAssignment {

    private String index;

    /**
     * Constructor for ArrayItemAssignment entity.
     * @param index array index
     * @param varName array name
     * @param value array value
     */
    public ArrayItemAssignment(String index, String varName, String value) {
        super(varName, value);
        this.index = index;
    }

    /**
     * Gets array index.
     * @return current index
     */
    public String getIndex() {
        return index;
    }

    /**
     * Sets array index.
     * @param index current index
     */
    public void setIndex(String index) {
        this.index = index;
    }

    /**
     * Overridden equals() method for ArrayItemAssignment entity.
     * @param o Object to check
     * @return result of checking
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof ArrayItemAssignment)) {
            return false;
        }

        ArrayItemAssignment aia = (ArrayItemAssignment) o;

        return  aia.varName.equals(this.varName) && aia.value.equals(this.value) && aia.index == this.index;
    }
}
