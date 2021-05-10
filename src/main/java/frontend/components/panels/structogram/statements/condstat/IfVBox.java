package frontend.components.panels.structogram.statements.condstat;

import backend.entities.statements.Statement;
import backend.entities.statements.condstatements.If;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.panels.structogram.statementlists.StatementListVBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Map;

/**
 * If panel for structogram figure panel.
 */
public class IfVBox extends CondStatVBox {

    /**
     * Constructor for IfVBox
     * @param ifEntity related statement entity
     */
    public IfVBox(If ifEntity) {
        super(ifEntity);

        statementListVBoxArrayList = new ArrayList<>();

        setAlignment(Pos.TOP_CENTER);

        if (((If) statementEntity).getBranches().size() != 1){
            initIfWithMoreConditions();
        } else {
            initIfWithOneCondition();
        }
        VBox.setVgrow(this, Priority.ALWAYS);
    }

    /**
     * Initialize if panel, if it has else if conditions.
     */
    private void initIfWithMoreConditions(){
        HBox branchesHBox = new HBox();
        VBox.setVgrow(branchesHBox, Priority.ALWAYS);

        for (Map.Entry<String, ArrayList<Statement>> entry : ((If) statementEntity).getBranches().entrySet()){
            initBranch(entry.getKey(), entry.getValue(), branchesHBox);
        }

        if (((If) statementEntity).getFalseBranchStatementList() != null){
            initBranch("else", ((If) statementEntity).getFalseBranchStatementList(), branchesHBox);
        }

        getChildren().add(branchesHBox);
    }

    /**
     * Initialize branch for a condition.
     * @param condition current condition
     * @param statementList current statement list
     * @param parentHBox current parent panel
     */
    private void initBranch(String condition, ArrayList<Statement> statementList, HBox parentHBox){
        VBox branchVBox = new VBox();
        VBox branchConditionVBox = new VBox();
        StatementListVBox statementListVBox = new StatementListVBox(statementList);
        Label conditionLabel = FxComponentFactory.generateLabel(branchConditionVBox, condition, "conditionLabel");

        branchVBox.setAlignment(Pos.TOP_CENTER);
        branchConditionVBox.setAlignment(Pos.CENTER);

        conditionLabel.setStyle(
                "-fx-font-style: italic"
        );
        branchConditionVBox.setStyle(
                "-fx-border-color: black;" +
                "-fx-border-width: 0 0 1 0"
        );

        statementListVBoxArrayList.add(statementListVBox);

        branchVBox.getChildren().addAll(branchConditionVBox, statementListVBox);
        parentHBox.getChildren().add(branchVBox);

        if (parentHBox.getChildren().size() > 1){
            branchVBox.setStyle(
                    "-fx-border-color: black;" +
                    "-fx-border-width: 0 0 0 1"
            );
        }

        HBox.setHgrow(branchVBox, Priority.ALWAYS);
    }

    /**
     * Initialize if panel, if it has only one condition.
     */
    private void initIfWithOneCondition(){
        Label conditionLabel = FxComponentFactory.generateLabel(this, ((If) statementEntity).getBranches().entrySet().iterator().next().getKey(), "conditionLabel");
        StatementListVBox ifStatementListVBox = new StatementListVBox(((If) statementEntity).getBranches().entrySet().iterator().next().getValue());

        conditionLabel.setStyle("-fx-font-style: italic");

        statementListVBoxArrayList.add(ifStatementListVBox);

        if (((If) statementEntity).getFalseBranchStatementList() != null){
            HBox twoBranchHBox = new HBox();
            StatementListVBox elseStatementListVBox = new StatementListVBox(((If) statementEntity).getFalseBranchStatementList());

            twoBranchHBox.setStyle(
                    "-fx-border-color: black;" +
                    "-fx-border-width: 1 0 0 0"
            );
            elseStatementListVBox.setStyle(
                    "-fx-border-color: black;" +
                    "-fx-border-width: 0 0 0 1"
            );

            statementListVBoxArrayList.add(elseStatementListVBox);
            twoBranchHBox.getChildren().addAll(ifStatementListVBox, elseStatementListVBox);
            getChildren().add(twoBranchHBox);

            VBox.setVgrow(twoBranchHBox, Priority.ALWAYS);
            HBox.setHgrow(ifStatementListVBox, Priority.ALWAYS);
            HBox.setHgrow(elseStatementListVBox, Priority.ALWAYS);
        }
        else {
            ifStatementListVBox.setStyle(
                    "-fx-border-color: black;" +
                    "-fx-border-width: 1 0 0 0"
            );
            getChildren().add(ifStatementListVBox);
        }
    }
}
