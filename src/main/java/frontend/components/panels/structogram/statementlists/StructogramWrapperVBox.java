package frontend.components.panels.structogram.statementlists;

import backend.entities.statements.Statement;
import frontend.components.managers.QueryManager;
import frontend.components.panels.structogram.statements.StatementVBox;

import java.util.ArrayList;

/**
 * Wrapper object, which contains all structogram elements
 * and add an outer border to the figure
 */
public class StructogramWrapperVBox extends StatementListVBox {

    /**
     * Constructor of StructogramWrapperVBox.
     * @param structogramList current structogram list
     */
    public StructogramWrapperVBox(ArrayList<Statement> structogramList){
        super(structogramList);
        setStyle(
                "-fx-border-color: black;" +
                "-fx-border-width: 1 1 1 1"
        );
    }

    /**
     * Overridden method, which add a statement panel.
     * @param statementVBox current statement panel
     */
    @Override
    public void addChild(StatementVBox statementVBox){
        getChildren().add(statementVBox);

        if (getChildren().size() > 1){
            statementVBox.setStyle(
                    "-fx-border-color: black;" +
                    "-fx-border-width: 1 0 0 0"
            );
        }
        if (getChildren().size() > 0){
            QueryManager.getStructogramFigureVBox().setVisible(true);
        }
    }

    /**
     * Overridden method, which add a statement panel.
     * @param statementVBox current statement panel
     */
    @Override
    public void removeChild(int index, StatementVBox statementVBox){
        getChildren().remove(statementVBox);

        if (getChildren().size() > 0 && index == 0){
            getChildren().get(0).setStyle(
                    "-fx-border-width: 0 0 0 0"
            );
        }
        if (getChildren().size() == 0){
            QueryManager.getStructogramFigureVBox().setVisible(false);
        }
    }
}
