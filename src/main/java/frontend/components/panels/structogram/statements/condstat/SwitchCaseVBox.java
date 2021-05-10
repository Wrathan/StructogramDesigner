package frontend.components.panels.structogram.statements.condstat;

import backend.entities.statements.Statement;
import backend.entities.statements.condstatements.SwitchCase;
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
 * Switch panel for structogram figure panel.
 */
public class SwitchCaseVBox extends CondStatVBox {

    private Label expressionLabel;
    private HBox caseHBox;

    /**
     * Constructor for SwitchCaseVBox.
     * @param switchCaseEntity related switch entity
     */
    public SwitchCaseVBox(SwitchCase switchCaseEntity){
        super(switchCaseEntity);

        statementListVBoxArrayList = new ArrayList<>();

        initCaseHBox();
        initCaseVBoxes();
        setAlignment(Pos.TOP_CENTER);

        expressionLabel = FxComponentFactory.generateLabel(this, ((SwitchCase) statementEntity).getExpression(), "expressionLabel");

        expressionLabel.setStyle(
                "-fx-font-style: italic"
        );

        getChildren().add(caseHBox);
        VBox.setVgrow(this, Priority.ALWAYS);
    }

    /**
     * Initialization the panel, which contains case panels.
     */
    private void initCaseHBox() {
        caseHBox = new HBox();
        VBox.setVgrow(caseHBox, Priority.ALWAYS);
    }

    /**
     * Initialize case panels.
     */
    private void initCaseVBoxes(){
        for (Map.Entry<String, ArrayList<Statement>> entry : ((SwitchCase) statementEntity).getBranches().entrySet()){
            initCaseVBox(entry.getKey(), entry.getValue());
        }

        if (((SwitchCase) statementEntity).getFalseBranchStatementList() != null){
            initCaseVBox("default", ((SwitchCase) statementEntity).getFalseBranchStatementList());
        }
    }

    /**
     * Initialize case panel.
     * @param caseValue current case value
     * @param entityStatementList related entity statement list
     */
    private void initCaseVBox(String caseValue, ArrayList<Statement> entityStatementList){
        VBox caseVBox = new VBox();
        VBox caseValueVBox = new VBox();
        StatementListVBox caseStatementListVBox = new StatementListVBox(entityStatementList);
        Label caseValueLabel = FxComponentFactory.generateLabel(caseValueVBox, caseValue, "caseValueLabel");

        caseValueVBox.setMaxHeight(15);
        caseValueVBox.setAlignment(Pos.BOTTOM_CENTER);
        caseValueVBox.setStyle(
                "-fx-border-color: black;" +
                "-fx-border-width: 0 0 1 0"
        );

        statementListVBoxArrayList.add(caseStatementListVBox);

        caseVBox.getChildren().addAll(caseValueVBox, caseStatementListVBox);
        caseHBox.getChildren().add(caseVBox);

        if (caseHBox.getChildren().size() > 1){
            caseVBox.setStyle(
                    "-fx-border-color: black;" +
                    "-fx-border-width: 0 0 0 1"
            );
        }

        HBox.setHgrow(caseVBox, Priority.ALWAYS);
    }
}
