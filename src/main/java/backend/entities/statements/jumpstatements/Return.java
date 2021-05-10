package backend.entities.statements.jumpstatements;

/**
 * Representation of return statement.
 */
public class Return extends JumpStatement {

    private String expression;
    private boolean isCheckPostCond;

    /**
     * Constructor for Return entity.
     * @param expression actual expression value
     * @param isCheckPostCond shows, if we need to check postcondition before return
     */
    public Return(String expression, boolean isCheckPostCond) {
        this.expression = expression;
        this.isCheckPostCond = isCheckPostCond;
    }

    /**
     * Gets the expression
     * @return current expression
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Sets the expression
     * @param expression current expression
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * Gets the postcondition checking state
     * @return current state
     */
    public boolean isCheckPostCond() {
        return isCheckPostCond;
    }

    /**
     * Sets the postcondition checking state
     * @param checkPostCond current state
     */
    public void setCheckPostCond(boolean checkPostCond) {
        this.isCheckPostCond = checkPostCond;
    }

    /**
     * Overridden equals() method for Return entity.
     * @param o Object to check
     * @return result of checking
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof Return)) {
            return false;
        }

        Return r = (Return) o;

        return  r.expression.equals(this.expression) && r.isCheckPostCond == this.isCheckPostCond;
    }
}
