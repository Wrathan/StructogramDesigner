package frontend.components.panels.structogram.statements.loop;

import backend.entities.statements.loops.Loop;
import frontend.components.factorys.FxComponentFactory;

/**
 * After check loop panel for structogram figure.
 */
public class AfterCheckLoopVBox extends LoopVBox {

    /**
     * Constructor for AfterCheckLoopVBox.
     * @param loopEntity related loop entity
     */
    public AfterCheckLoopVBox(Loop loopEntity) {
        super(loopEntity);

        statementListVBox.setStyle(
                "-fx-border-color: black;" +
                "-fx-border-width: 0 0 1 1"
        );

        getChildren().add(statementListVBox);

        conditionLabel = FxComponentFactory.generateLabel(this, conditionString, "conditionLabel");
        conditionLabel.setStyle("-fx-font-style: italic");
    }
}
