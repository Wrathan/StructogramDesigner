package backend.entities.statements.loops;

/**
 * Representation of for loop.
 */
public class ForLoop extends Loop {
    private String varName, start, end;

    /**
     * Constructor for ForLoop entity.
     * @param varName loop variable name
     * @param start value, where the loop starts
     * @param end value, where the loop ends
     */
    public ForLoop(String varName, String start, String end) {
        this.varName = varName;
        this.start = start;
        this.end = end;
    }

    /**
     * Gets loop start value.
     * @return current start value
     */
    public String getStart() {
        return start;
    }

    /**
     * Sets loop start value.
     * @param start current start value
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Gets loop end value.
     * @return current end value
     */
    public String getEnd() {
        return end;
    }

    /**
     * Sets loop end value.
     * @param end current end value
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * Gets loop variable name.
     * @return current start value
     */
    public String getVarName() {
        return varName;
    }

    /**
     * Sets loop variable name.
     * @param varName current variable name
     */
    public void setVarName(String varName) {
        this.varName = varName;
    }

    /**
     * Overridden equals() method for ForLoop entity.
     * @param o Object to check
     * @return result of checking
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof ForLoop)) {
            return false;
        }

        ForLoop fl = (ForLoop) o;

        return  fl.start.equals(this.start) &&
                fl.end.equals(this.end) && fl.statementList.equals(this.statementList);
    }
}
