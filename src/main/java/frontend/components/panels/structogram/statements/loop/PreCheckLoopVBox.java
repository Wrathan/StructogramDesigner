package frontend.components.panels.structogram.statements.loop;

import backend.entities.statements.loops.Loop;
import frontend.components.factorys.FxComponentFactory;

/**
 * Previous check loop panel for structogram figure panel.
 */
public class PreCheckLoopVBox extends LoopVBox {

    /**
     * Constructor for PreCheckLoopVBox.
     * @param loopEntity related loop entity
     */
    public PreCheckLoopVBox(Loop loopEntity) {
        super(loopEntity);

        statementListVBox.setStyle(
                "-fx-border-color: black;" +
                "-fx-border-width: 1 0 0 1"
        );

        conditionLabel = FxComponentFactory.generateLabel(this, conditionString, "conditionLabel");
        conditionLabel.setStyle("-fx-font-style: italic");
        getChildren().add(statementListVBox);
    }
}
