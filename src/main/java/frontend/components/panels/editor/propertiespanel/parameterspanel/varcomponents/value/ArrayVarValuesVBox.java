package frontend.components.panels.editor.propertiespanel.parameterspanel.varcomponents.value;

import frontend.GuiManager;
import frontend.components.factorys.FxComponentFactory;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Panel, where the user can add values to array variables.
 */
public class ArrayVarValuesVBox extends ArrayVarSizeVBox {

    protected VBox initArrayVBox;
    protected Button initArrayButton, delArrayButton;

    /**
     * Constructor for ArrayVarValuesVBox.
     */
    public ArrayVarValuesVBox() {
        initArrayButton = FxComponentFactory.generateButton(sizeHBox, "Init", "initArrayButton", 40, 20);
        delArrayButton = FxComponentFactory.generateButton(sizeHBox, "Del", "delArrayButton", 40, 20);

        initArrayButton.setOnAction(e -> {
            try{
                initArrayPanel();
            } catch (NumberFormatException ex){
                GuiManager.openErrorStage("Please enter a number.");
            }
        });
        delArrayButton.setOnAction(e -> delArrayPanel());
    }

    /**
     * Initialize array panel with array item panels.
     * @throws NumberFormatException when the user trying to add array items,
     * with invalid size (e.g. string in the size text field, instead of number)
     */
    private void initArrayPanel() throws NumberFormatException{
        if (!sizeTextField.getText().equals("")){
            delArrayPanel();
            initArrayVBox = new VBox();
            initArrayVBox.setPadding(new Insets(5, 0, 0, 0));
            HBox arrayOperationButtonsHBox = new HBox();
            FlowPane arrayItemsFlowPane = new FlowPane(Orientation.VERTICAL);
            Button addItemButton = FxComponentFactory.generateButton(arrayOperationButtonsHBox, "+ item", "addItemButton", 55, 20);
            Button modifySizeButton = FxComponentFactory.generateButton(arrayOperationButtonsHBox, "Modify size", "modifySizeButton", 80, 20);

            initArrayVBox.setSpacing(5);
            arrayOperationButtonsHBox.setSpacing(5);
            arrayItemsFlowPane.setHgap(10);

            int arraySize =  Integer.parseInt(sizeTextField.getText());
            for (int i = 0; i < arraySize; i++){
                initItem(arrayItemsFlowPane);
            }

            addItemButton.setOnAction(e -> addItemWithAddButton(arrayItemsFlowPane));
            modifySizeButton.setOnAction(e -> modifyArraySize(arrayItemsFlowPane));

            initArrayVBox.getChildren().addAll(arrayOperationButtonsHBox, arrayItemsFlowPane);
            getChildren().add(initArrayVBox);
        } else {
            throw new NumberFormatException();
        }
    }

    /**
     * Modifying array item panels number.
     * @param arrayItemsFlowPane panel, which contains array item panels
     */
    private void modifyArraySize(FlowPane arrayItemsFlowPane) {
        if (arrayItemsFlowPane.getChildren().size() > Integer.parseInt(sizeTextField.getText())) {
            for (int i = arrayItemsFlowPane.getChildren().size() - 1; i >= Integer.parseInt(sizeTextField.getText()); i--) {
                arrayItemsFlowPane.getChildren().remove(arrayItemsFlowPane.getChildren().size() - 1);
            }
        } else if (arrayItemsFlowPane.getChildren().size() < Integer.parseInt(sizeTextField.getText())) {
            for (int i = arrayItemsFlowPane.getChildren().size() - 1; i < Integer.parseInt(sizeTextField.getText()) - 1; i++) {
                initItem(arrayItemsFlowPane);
            }
        }
    }

    /**
     * Adding a new array item panel to the parent panel.
     * @param arrayItemsFlowPane panel, which contains array item panels
     */
    private void addItemWithAddButton(FlowPane arrayItemsFlowPane){
        initItem(arrayItemsFlowPane);
        sizeTextField.setText(Integer.toString(arrayItemsFlowPane.getChildren().size()));
    }

    /**
     * Initialize array item panel.
     * @param arrayItemsFlowPane panel, which contains array item panels
     */
    private void initItem(FlowPane arrayItemsFlowPane) {
        HBox arrayItemHBox = new HBox();
        TextField arrayItemTextField = FxComponentFactory.generateTextField(arrayItemHBox, "arrayItemTextField" + (arrayItemsFlowPane.getChildren().size() + 1), 80, 20);
        Button removeArrayItem = FxComponentFactory.generateButton(arrayItemHBox, "-", "removeArrayItemButton" + (arrayItemsFlowPane.getChildren().size() + 1), 20, 20);

        arrayItemHBox.setSpacing(5);
        arrayItemHBox.setPadding(new Insets(0, 0, 5, 0));
        arrayItemTextField.setPromptText(Integer.toString(arrayItemsFlowPane.getChildren().size()));

        removeArrayItem.setOnAction(e -> removeItem(arrayItemsFlowPane, arrayItemHBox));

        arrayItemsFlowPane.getChildren().add(arrayItemHBox);
    }

    /**
     * Remove an array item panel from parent panel.
     * @param arrayItemsFlowPane panel, which contains array item panels
     * @param arrayItemHBox array item panel what will be removed
     */
    private void removeItem(FlowPane arrayItemsFlowPane, HBox arrayItemHBox) {
        int itemIndex = arrayItemsFlowPane.getChildren().indexOf(arrayItemHBox);
        int arrayFlowPaneSize = arrayItemsFlowPane.getChildren().size();

        arrayItemsFlowPane.getChildren().remove(arrayItemHBox);

        for (int i = itemIndex; i < arrayFlowPaneSize - 1; i++){
            ((TextField) ((HBox) arrayItemsFlowPane.getChildren().get(i)).getChildren().get(0)).setPromptText(Integer.toString(i));
            ((HBox) arrayItemsFlowPane.getChildren().get(i)).getChildren().get(0).setId("arrayItemTextField" + (i + 1));
            ((HBox) arrayItemsFlowPane.getChildren().get(i)).getChildren().get(1).setId("removeArrayItemButton" + (i + 1));
        }

        sizeTextField.setText(Integer.toString(arrayItemsFlowPane.getChildren().size()));

        if (arrayItemsFlowPane.getChildren().size() == 0){
            GuiManager.removePane(this, 1);
            initArrayVBox = null;
            sizeTextField.clear();
        }
    }

    /**
     * Delete all array item panels (delete parent panel).
     */
    protected void delArrayPanel() {
        if (getChildren().size() == 3){
            GuiManager.removePane(this, 2);
            initArrayVBox = null;
        }
    }

    /**
     * Get array item text fields.
     * @return current array item text fields
     */
    public ArrayList<TextField> getArrayItemTextFields(){
        if (initArrayVBox.getChildren().size() > 1){
            ArrayList<TextField> arrayItemTextFieldArrayList = new ArrayList<>();

            for (Node item : ((FlowPane) initArrayVBox.getChildren().get(1)).getChildren()){
                arrayItemTextFieldArrayList.add((TextField) ((HBox) item).getChildren().get(0));
            }

            return arrayItemTextFieldArrayList;
        } else {
            return null;
        }
    }
}
