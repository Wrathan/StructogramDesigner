package frontend.components.panels.editor.propertiespanel.parameterspanel.loop;

import frontend.components.factorys.FxComponentFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Panel, where the user can add for loop parameters.
 */
public class ForParametersPanel extends LoopParametersPanel {

    private final VBox varNameVBox, startIntVBox, endIntVBox;
    private final HBox intervalHBox;
    private final Label varNameLabel, startIntLabel, endIntLabel;
    private final TextField varNameTextField, startIntTextField, endIntTextField;

    /**
     * COnstructor for ForParametersPanel.
     */
    public ForParametersPanel(){
        varNameVBox = new VBox();
        intervalHBox = new HBox();
        startIntVBox = new VBox();
        endIntVBox = new VBox();
        intervalHBox.setSpacing(10);

        varNameLabel = FxComponentFactory.generateLabel(varNameVBox, "Loop variable name:", "varNameLabel");
        varNameTextField = FxComponentFactory.generateTextField(varNameVBox, "varNameTextField");
        startIntLabel = FxComponentFactory.generateLabel(startIntVBox, "\nFrom:", "startIntLabel");
        startIntTextField = FxComponentFactory.generateTextField(startIntVBox, "startIntTextField");
        endIntLabel = FxComponentFactory.generateLabel(endIntVBox, "Until\nless than:", "endIntLabel");
        endIntTextField = FxComponentFactory.generateTextField(endIntVBox, "endIntTextField");

        intervalHBox.getChildren().addAll(startIntVBox, endIntVBox);

        getChildren().addAll(varNameVBox, intervalHBox);
    }

    /**
     * Gets loop variable name text field.
     * @return current loop variable name text field
     */
    public TextField getVarNameTextField() {
        return varNameTextField;
    }

    /**
     * Gets start value text field.
     * @return current start value text field
     */
    public TextField getStartIntTextField() {
        return startIntTextField;
    }

    /**
     * Gets end value text field.
     * @return current end value text field
     */
    public TextField getEndIntTextField() {
        return endIntTextField;
    }
}
