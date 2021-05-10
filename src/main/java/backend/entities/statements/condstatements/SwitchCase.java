package backend.entities.statements.condstatements;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representation of the Switch statement.
 */
public class SwitchCase extends ConditionalStatement {
    private String expression;

    /**
     * Constructor for SwitchCase entity.
     * @param expression representation of switch expression
     * @param caseExpressions contains the string representation of case values
     * @param isDefaultCase shows that is there a default branch
     */
    public SwitchCase(String expression, ArrayList<String> caseExpressions, boolean isDefaultCase) {
        this.expression = expression;
        this.branches = new HashMap<>();

        for (String caseExpression : caseExpressions){
            branches.put(caseExpression, new ArrayList<>());
        }

        if (isDefaultCase){
            this.falseBranchStatementList = new ArrayList<>();
        }
    }

    /**
     * Gets the expression.
     * @return current expression value
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Sets the expression.
     * @param expression value to set
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * Overridden equals() method for SwitchCase entity.
     * @param o Object to check.
     * @return result of checking
     */
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof SwitchCase)) {
            return false;
        }

        SwitchCase sc = (SwitchCase) o;

        return  sc.branches.equals(this.branches) && sc.falseBranchStatementList.equals(this.falseBranchStatementList) &&
                sc.expression.equals(this.expression);
    }
}
