package frontend.components.panels.structogram.statements;

import backend.entities.statements.Statement;
import frontend.components.panels.structogram.StructogramElementVBox;

/**
 * Abstract superclass of statement panels.
 */
public abstract class StatementVBox extends StructogramElementVBox {

    protected Statement statementEntity;

    /**
     * Constructor for StatementVBox.
     * @param statementEntity related statement entity
     */
    protected StatementVBox(Statement statementEntity){
        this.statementEntity = statementEntity;
    }

    /**
     * Gets related statement entity
     * @return related statement entity.
     */
    public Statement getStatementEntity() {
        return statementEntity;
    }
}

