package backend.entities.statements.loops;

import backend.enums.VarType;

/**
 * Representation of for-each loop.
 */
public class ForEachLoop extends Loop {
    private VarType itemType;
    private String actualItemName, arrayVarName;

    /**
     * Constructor for ForEachLoop entity.
     * @param itemType loop variable type
     * @param actualItemName variable, which goes through the array items, getting
     *                       it's values, one by one
     * @param arrayVarName loop array variable name
     */
    public ForEachLoop(VarType itemType, String actualItemName, String arrayVarName) {
        this.itemType = itemType;
        this.actualItemName = actualItemName;
        this.arrayVarName = arrayVarName;
    }

    /**
     * Gets array item type.
     * @return current item type
     */
    public VarType getItemType() {
        return itemType;
    }

    /**
     * Sets array item type.
     * @param itemType set loop item variable type
     */
    public void setItemType(VarType itemType) {
        this.itemType = itemType;
    }

    /**
     * Gets loop variable name.
     * @return current variable name
     */
    public String getActualItemName() {
        return actualItemName;
    }

    /**
     * Sets loop variable name.
     * @param actualItemName current variable name
     */
    public void setActualItemName(String actualItemName) {
        this.actualItemName = actualItemName;
    }

    /**
     * Gets array name.
     * @return current array name
     */
    public String getArrayVarName() {
        return arrayVarName;
    }

    /**
     * Sets array name.
     * @param arrayVariableName current array name
     */
    public void setArrayVarName(String arrayVariableName) {
        this.arrayVarName = arrayVariableName;
    }

    /**
     * Overridden equals() method for ForEachLoop entity.
     * @param o Object to check
     * @return result of checking
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof ForEachLoop)) {
            return false;
        }

        ForEachLoop fel = (ForEachLoop) o;

        return  fel.itemType == this.itemType && fel.actualItemName.equals(this.actualItemName) &&
                fel.arrayVarName.equals(this.arrayVarName) && fel.statementList.equals(this.statementList);
    }
}
