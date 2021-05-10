package backend.entities.statements.varoperations.assignment;

/**
 * Representation of simple variable assignment.
 */
public class SimpleAssignment extends Assignment {

    protected String value;

    /**
     * Constructor for SimpleAssignment entity.
     * @param varName name of the variable
     * @param value value of the actual variable
     */
    public SimpleAssignment(String varName, String value) {
        super(varName);
        this.value = value;
    }

    /**
     * Gets variable value.
     * @return current value
     */
    public String getVarValue() {
        return value;
    }

    /**
     * Sets the variable value.
     * @param value current value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Overridden equals() method for SimpleAssignment entity.
     * @param o Object to check
     * @return result of checking
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof SimpleAssignment)) {
            return false;
        }

        SimpleAssignment sa = (SimpleAssignment) o;

        return  sa.varName.equals(this.varName) && sa.value.equals(this.value);
    }
}
