package frontend.components.panels.structogram;

import frontend.components.managers.SelectionManager;
import javafx.scene.layout.VBox;

/**
 * Abstract superclass of structogram figure elements.
 */
public abstract class StructogramElementVBox extends VBox {

    /**
     * Constructor of StructogramElementVBox.
     */
    public StructogramElementVBox() {
        setMouseEvents();
    }

    /**
     * Set mouse events.
     */
    private void setMouseEvents(){
        setOnMouseEntered(e -> mouseEntered());
        setOnMouseExited(e -> mouseExited());
        setOnMouseClicked(e -> mouseClicked());
    }

    /**
     * Event when mouse enter to a panel.
     */
    private void mouseEntered() {
        SelectionManager.mouseEntered(this);
    }

    /**
     * Event when mouse exits from a panel.
     */
    private void mouseExited(){
        SelectionManager.mouseExited();
    }

    /**
     * Event when mouse clicks on a panel.
     */
    private void mouseClicked(){
        SelectionManager.mouseClicked(this);
    }
}
