package frontend.stages.popup;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Abstract superclass of popup windows.
 */
public abstract class PopUpStage extends Stage {

    /**
     * Constructor for PopUpStage.
     */
    protected PopUpStage() {
        initModality(Modality.APPLICATION_MODAL);
        initStyle(StageStyle.UTILITY);
        setResizable(false);
    }
}
