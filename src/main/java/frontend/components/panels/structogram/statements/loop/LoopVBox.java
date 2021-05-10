package frontend.components.panels.structogram.statements.loop;

import backend.entities.statements.loops.*;
import frontend.components.panels.structogram.statementlists.StatementListVBox;
import frontend.components.panels.structogram.statements.StatementVBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

/**
 * Loop panel for structogram figure panel.
 */
public abstract class LoopVBox extends StatementVBox {

    protected Label conditionLabel;
    protected StatementListVBox statementListVBox;
    protected String conditionString;

    /**
     * Constructor for LoopVBox.
     * @param loopEntity related loop entity
     */
    protected LoopVBox(Loop loopEntity){
        super(loopEntity);
        setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(this, Priority.ALWAYS);

        initLoopStatementVBox();
        initConditionLabel();
    }

    /**
     * Initialize loop panel.
     */
    private void initLoopStatementVBox(){
        this.statementListVBox = new StatementListVBox(((Loop) statementEntity).getStatementList());

        statementListVBox.setMinHeight(15);
        VBox.setMargin(statementListVBox, new Insets(0, 0, 0, 10));
    }

    /**
     * Initialize loop condition label.
     */
    private void initConditionLabel(){
        if (statementEntity instanceof ForLoop){
            conditionString = ((ForLoop) statementEntity).getVarName() + ": " + ((ForLoop) statementEntity).getStart() + ".." + ((ForLoop) statementEntity).getEnd() + "-1";
        } else if (statementEntity instanceof ForEachLoop){
            conditionString = ((ForEachLoop) statementEntity).getActualItemName() + " in " + ((ForEachLoop) statementEntity).getArrayVarName();
        } else if (statementEntity instanceof  WhileLoop){
            conditionString = ((WhileLoop) statementEntity).getCondition();
        }
    }

    /**
     * Gets loop condition label.
     * @return current loop condition label
     */
    public Label getConditionLabel() {
        return conditionLabel;
    }

    /**
     * Sets loop condition label.
     * @param conditionLabel current loop condition label
     */
    public void setConditionLabel(Label conditionLabel) {
        this.conditionLabel = conditionLabel;
    }

    /**
     * Gets statement list panel, which contains this panel.
     * @return current statement list panel
     */
    public StatementListVBox getStatementListVBox() {
        return statementListVBox;
    }

    /**
     * Sets statement list panel, which contains this panel.
     * @param statementListVBox current statement list panel
     */
    public void setStatementListVBox(StatementListVBox statementListVBox) {
        this.statementListVBox = statementListVBox;
    }
}
