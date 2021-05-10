package backend.entities.statements.loops;

/**
 * Representation of while loop.
 */
public class WhileLoop extends Loop {
    private String condition;

    /**
     * Constructor for WhileLoop entity.
     * @param condition represent the loop condition
     */
    public WhileLoop(String condition) {
        this.condition = condition;
    }

    /**
     * Gets condition
     * @return current condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets condition.
     * @param condition current condition
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Overridden equals() method for WhileLoop entity.
     * @param o Object to check
     * @return result of checking
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof WhileLoop)) {
            return false;
        }

        WhileLoop wl = (WhileLoop) o;

        return  wl.condition.equals(this.condition) && wl.statementList.equals(this.statementList);
    }
}
