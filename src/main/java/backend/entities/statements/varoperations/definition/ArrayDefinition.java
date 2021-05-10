package backend.entities.statements.varoperations.definition;

import backend.enums.VarType;

import java.util.ArrayList;

/**
 * Representation of an array's definition.
 */
public class ArrayDefinition extends Definition {

    ArrayList<String> valueList;

    /**
     * Constructor for ArrayDefinition entity.
     * @param variableName name of the array
     * @param varType type of the array
     * @param valueList values of the array
     */
    public ArrayDefinition(String variableName, VarType varType, ArrayList<String> valueList) {
        super(variableName, varType);
        this.valueList = valueList;
    }

    /**
     * Converts valueList into a string, separate the values with commas.
     * @return String representation of parameterList
     */
    public String valueListToString(){
        StringBuilder sb = new StringBuilder();
        for (String value : valueList){
            sb.append(value);
            if (!value.equals(valueList.get(valueList.size() - 1))){
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Gets array value list.
     * @return current array value list
     */
    public ArrayList<String> getValueList() {
        return valueList;
    }

    /**
     * Sets array value list.
     * @param valueList current array value list
     */
    public void setValueList(ArrayList<String> valueList) {
        this.valueList = valueList;
    }

    /**
     * Gets the number of array values.
     * @return current number of array values
     */
    public int getListSize(){
        return valueList.size();
    }

    /**
     * Overridden equals() method for ArrayDefinition entity.
     * @param o Object to check
     * @return result of checking
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof ArrayDefinition)) {
            return false;
        }

        ArrayDefinition ad = (ArrayDefinition) o;

        return ad.varType == this.varType && ad.varName.equals(this.varName) && ad.valueList.equals(this.valueList);
    }
}
