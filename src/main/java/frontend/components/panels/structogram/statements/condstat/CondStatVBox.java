package frontend.components.panels.structogram.statements.condstat;

import backend.entities.statements.Statement;
import frontend.components.panels.structogram.statementlists.StatementListVBox;
import frontend.components.panels.structogram.statements.StatementVBox;

import java.util.ArrayList;

/**
 * Abstract superclass for conditional statement panels, for structogram panel.
 */
public abstract class CondStatVBox extends StatementVBox {

    protected ArrayList<StatementListVBox> statementListVBoxArrayList;

    /**
     * Constructor for StatementVBox.
     * @param statementEntity related statement entity
     */
    protected CondStatVBox(Statement statementEntity) {
        super(statementEntity);
    }

    /**
     * Gets statement list panels.
     * @return current statement list panels
     */
    public ArrayList<StatementListVBox> getStatementListVBoxArrayList() {
        return statementListVBoxArrayList;
    }
}
