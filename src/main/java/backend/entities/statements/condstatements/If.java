package backend.entities.statements.condstatements;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Representation of the if statement.
 */
public class If extends ConditionalStatement {

    /**
     * Constructor for If entity
     * @param branchConditions keys for branches LinkedHashMap
     * @param isElseBranch shows that is there a false branch in the if statement
     */
    public If(ArrayList<String> branchConditions, boolean isElseBranch) {
        this.branches = new LinkedHashMap<>();

        for (String branchCondition : branchConditions){
            branches.put(branchCondition, new ArrayList<>());
        }

        if (isElseBranch){
            this.falseBranchStatementList = new ArrayList<>();
        }
    }

    /**
     * Overridden equals() method for If entity.
     * @param o Object to check
     * @return result of checking
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof If)) {
            return false;
        }

        If i = (If) o;

        return  i.branches.equals(this.branches) && i.falseBranchStatementList.equals(this.falseBranchStatementList);
    }
}
