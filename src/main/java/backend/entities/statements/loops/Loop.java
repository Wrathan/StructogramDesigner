package backend.entities.statements.loops;

import backend.entities.statements.Statement;

import java.util.ArrayList;

/**
 * Abstract superclass for loops (e.g. for, while...)
 */
public abstract class Loop extends Statement {
    protected ArrayList<Statement> statementList;

    /**
     *Constructor for Loop entity
     */
    protected Loop() {
        this.statementList = new ArrayList<Statement>();
    }

    /**
     * Gets the list of inner statements
     * @return current list of inner statements
     */
    public ArrayList<Statement> getStatementList() {
        return statementList;
    }

    /**
     * Sets the list of inner statements
     * @param statementList current list of inner statements
     */
    public void setStatementList(ArrayList<Statement> statementList) {
        this.statementList = statementList;
    }
}
