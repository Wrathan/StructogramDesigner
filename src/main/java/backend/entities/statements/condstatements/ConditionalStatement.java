package backend.entities.statements.condstatements;

import backend.entities.statements.Statement;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstract superclass for conditional statements (e.g. if, switch)
 */
public abstract class ConditionalStatement extends Statement {
    protected HashMap<String, ArrayList<Statement>> branches;
    protected ArrayList<Statement> falseBranchStatementList;

    /**
     * Gets the branches of the statement.
     * @return current branches, except false branch, if it has
     */
    public HashMap<String, ArrayList<Statement>> getBranches() {
        return branches;
    }

    /**
     * Sets the branches of the statement.
     * @param branches value to set.
     */
    public void setBranches(HashMap<String, ArrayList<Statement>> branches) {
        this.branches = branches;
    }

    /**
     * Gets the statement list of false branch
     * @return current false branch statement list
     */
    public ArrayList<Statement> getFalseBranchStatementList() {
        return falseBranchStatementList;
    }

    /**
     *Sets the statement list of false branch
     * @param falseBranchStatementList value to set
     */
    public void setFalseBranchStatementList(ArrayList<Statement> falseBranchStatementList) {
        this.falseBranchStatementList = falseBranchStatementList;
    }
}
