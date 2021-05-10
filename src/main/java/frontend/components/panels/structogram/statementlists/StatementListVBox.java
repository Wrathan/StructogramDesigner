package frontend.components.panels.structogram.statementlists;

import backend.entities.statements.Statement;
import frontend.components.panels.structogram.StructogramElementVBox;
import frontend.components.panels.structogram.statements.StatementVBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Structogram figure panel, what contains statement panels.
 */
public class StatementListVBox extends StructogramElementVBox {

    protected ArrayList<Statement> entityStatementList;

    /**
     * Constructor for StatementListVBox.
     * @param entityStatementList statement list of current entity
     */
    public StatementListVBox (ArrayList<Statement> entityStatementList){
        this.entityStatementList = entityStatementList;

        setMinSize(50, 15);
        VBox.setVgrow(this, Priority.ALWAYS);
    }

    /**
     * Add statement panel.
     * @param statementVBox current statement panel
     */
    public void addChild(StatementVBox statementVBox){
        getChildren().add(statementVBox);

        if (getChildren().size() > 1){
            statementVBox.setStyle(
                    "-fx-border-color: black;" +
                    "-fx-border-width: 1 0 0 0"
            );
        }
    }

    /**
     * Add statement panel to specified index
     * @param index current index
     * @param statementVBox current statement panel
     */
    public void addChild(int index, StatementVBox statementVBox){
        if (index == 0){
            getChildren().get(0).setStyle(
                    "-fx-border-color: black;" +
                    "-fx-border-width: 1 0 0 0"
            );
        }

        getChildren().add(index, statementVBox);

        if (getChildren().size() > 1 && index != 0){
            statementVBox.setStyle(
                    "-fx-border-color: black;" +
                    "-fx-border-width: 1 0 0 0"
            );
        }
    }

    /**
     * Add statement panel instead of another
     * @param index current index
     * @param statementVBox current statement panel
     */
    public void replaceChild(int index, StatementVBox statementVBox){
        getChildren().set(index, statementVBox);

        if (getChildren().size() > 1 && index != 0){
            statementVBox.setStyle(
                    "-fx-border-color: black;" +
                    "-fx-border-width: 1 0 0 0"
            );
        }
    }

    /**
     * Remove statement panel
     * @param index current index
     * @param statementVBox current statement panel
     */
    public void removeChild(int index, StatementVBox statementVBox){
        getChildren().remove(statementVBox);

        if (getChildren().size() > 0 && index == 0){
            getChildren().get(0).setStyle(
                    "-fx-border-width: 0 0 0 0"
            );
        }
    }

    /**
     * Gets statement list of actual entity.
     * @return current statement list of actual entity
     */
    public ArrayList<Statement> getEntityStatementList() {
        return entityStatementList;
    }

    /**
     * Sets statement list of actual entity.
     * @param entityStatementList current statement list of actual entity
     */
    public void setEntityStatementList(ArrayList<Statement> entityStatementList) {
        this.entityStatementList = entityStatementList;
    }
}
