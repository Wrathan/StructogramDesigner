package frontend.components.panels.editor.propertiespanel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * Abstract superclass of statement properties panels.
 */
public abstract class StatementPropertiesPanel extends VBox {

    /**
     * Constructor for StatementPropertiesPanel.
     */
    protected StatementPropertiesPanel(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(0, 20, 40, 20));
        setSpacing(10);
    }
}
