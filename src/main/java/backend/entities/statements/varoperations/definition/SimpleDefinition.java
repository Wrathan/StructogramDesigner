package backend.entities.statements.varoperations.definition;

import backend.enums.VarType;

/**
 * Representation of a simple value's definition.
 */
public class SimpleDefinition extends Definition {

    private String value;

    /**
     * Constructor for SimpleDefinition entity.
     * @param varName name of variable
     * @param varType type of variable
     * @param value value of variable
     */
    public SimpleDefinition(String varName, VarType varType, String value) {
        super(varName, varType);
        this.value = value;
    }

    /**
     * Gets value of the variable.
     * @return current variable value
     */
    public String getVarValue() {
        return value;
    }

    /**
     * Sets value of the variable.
     * @param value current variable value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Overridden equals() method for SimpleDefinition entity.
     * @param o Object to check
     * @return result of checking
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof SimpleDefinition)) {
            return false;
        }

        SimpleDefinition sd = (SimpleDefinition) o;

        return sd.varType == this.varType && sd.varName.equals(this.varName) && sd.value.equals(this.value);
    }
}
