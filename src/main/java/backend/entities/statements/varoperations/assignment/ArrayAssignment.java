package backend.entities.statements.varoperations.assignment;

import backend.enums.VarType;

import java.util.ArrayList;

/**
 * Representation of an array' assignment operation.
 */
public class ArrayAssignment extends Assignment {

    private VarType varType;
    private ArrayList<String> valueList;

    /**
     * Constructor for ArrayAsignment entity.
     * @param varName array's name
     * @param varType array's type
     * @param valueList array values
     */
    public ArrayAssignment(String varName, VarType varType, ArrayList<String> valueList) {
        super(varName);
        this.varType = varType;
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
     * Gets value list.
     * @return current value list
     */
    public ArrayList<String> getValueList() {
        return valueList;
    }

    /**
     * Sets value list.
     * @param valueList current value lsit
     */
    public void setValueList(ArrayList<String> valueList) {
        this.valueList = valueList;
    }

    /**
     * Gets array type.
     * @return current array type
     */
    public VarType getVarType() {
        return varType;
    }

    /**
     * Sets array type.
     * @param varType current array type
     */
    public void setVarType(VarType varType) {
        this.varType = varType;
    }

    /**
     * Gets the number of array values.
     * @return number of array values
     */
    public int getListSize(){
        return valueList.size();
    }

    /**
     * Overridden equals() method for ArrayAssignment entity.
     * @param o Object to check
     * @return result of checking
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof ArrayAssignment)) {
            return false;
        }

        ArrayAssignment aa = (ArrayAssignment) o;

        return aa.varType == this.varType && aa.varName.equals(this.varName) && aa.valueList.equals(this.valueList);
    }
}
