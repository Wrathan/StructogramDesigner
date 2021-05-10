package frontend.components.factorys;

import backend.entities.structograms.Structogram;
import backend.interfaces.Type;
import frontend.components.tableviews.FunctionTableView;
import frontend.components.tableviews.SerFunctionTableView;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

/**
 * Factory for JavaFX components
 */
public final class FxComponentFactory {

    /**
     * Creates a button.
     * @param parent parent node
     * @param id JavaFX object id
     * @return created object
     */
    public static Button generateButton(Pane parent, String id){
        Button button = new Button();
        button.setId(id);
        button.setPrefSize(100, 20);
        button.setTextAlignment(TextAlignment.CENTER);
        parent.getChildren().add(button);
        return button;
    }

    /**
     * Creates a button.
     * @param parent Parent node
     * @param text text on object
     * @param id JavaFX object id
     * @return created object
     */
    public static Button generateButton(Pane parent, String text, String id){
        Button button = new Button(text);
        button.setId(id);
        button.setPrefSize(100, 20);
        button.setTextAlignment(TextAlignment.CENTER);
        parent.getChildren().add(button);
        return button;
    }

    /**
     * Creates a button.
     * @param parent parent node
     * @param text text on object
     * @param id JavaFX object id
     * @param x width
     * @param y height
     * @return created object
     */
    public static Button generateButton(Pane parent, String text, String id, double x, double y){
        Button button = new Button(text);
        button.setId(id);
        button.setPrefSize(x, y);
        button.setTextAlignment(TextAlignment.CENTER);
        parent.getChildren().add(button);
        return button;
    }

    /**
     * Creates a menu.
     * @param menuBar parent node
     * @param text text on object
     * @param id JavaFX object id
     * @return created object
     */
    public static Menu generateMenu(MenuBar menuBar, String text, String id){
        Menu menu = new Menu(text);
        menu.setId(id);
        menuBar.getMenus().add(menu);
        return menu;
    }

    /**
     * Creates a menu item.
     * @param menu parent node
     * @param text text on object
     * @param id JavaFX object id
     * @return created object
     */
    public static MenuItem generateMenuItem(Menu menu, String text, String id){
        MenuItem menuItem = new MenuItem(text);
        menuItem.setId(id);
        menu.getItems().add(menuItem);
        return menuItem;
    }

    /**
     * Creates a label.
     * @param parent parent node
     * @param id JavaFX object id
     * @return created object
     */
    public static Label generateLabel(Pane parent, String id){
        Label label = new Label();
        label.setId(id);
        parent.getChildren().add(label);
        return label;
    }

    /**
     * Creates a label.
     * @param parent parent node
     * @param text text on object
     * @param id JavaFX object id
     * @return created object
     */
    public static Label generateLabel(Pane parent, String text, String id){
        Label label = new Label(text);
        label.setId(id);
        parent.getChildren().add(label);
        return label;
    }

    /**
     * Creates a textfield.
     * @param parent parent node
     * @param id JavaFX object id
     * @return created object
     */
    public static TextField generateTextField(Pane parent, String id){
        TextField textField = new TextField();
        textField.setId(id);
        textField.setPrefSize(50, 20);
        parent.getChildren().add(textField);
        return textField;
    }

    /**
     * Creates a textfield.
     * @param parent parent node
     * @param id JavaFX object id
     * @param x width
     * @param y height
     * @return created object
     */
    public static TextField generateTextField(Pane parent, String id, double x, double y){
        TextField textField = new TextField();
        textField.setId(id);
        textField.setPrefSize(x, y);
        parent.getChildren().add(textField);
        return textField;
    }

    /**
     * Creates a choice box.
     * @param parent parent node
     * @param id JavaFX object id
     * @return created object
     */
    public static ChoiceBox generateChoiceBox(Pane parent, String id){
        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.setId(id);
        choiceBox.setPrefSize(100, 20);
        parent.getChildren().add(choiceBox);
        return choiceBox;
    }

    /**
     * Creates a choice box.
     * @param parent parent node
     * @param id JavaFX object id
     * @param x width
     * @param y height
     * @return created object
     */
    public static ChoiceBox generateChoiceBox(Pane parent, String id, double x, double y){
        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.setId(id);
        choiceBox.setPrefSize(x, y);
        parent.getChildren().add(choiceBox);
        return choiceBox;
    }

    /**
     * Creates a choice box.
     * @param parent parent node
     * @param stringArray values of choice box
     * @param id JavaFX object id
     * @return created object
     */
    public static ChoiceBox<String> generateChoiceBox(Pane parent, String[] stringArray, String id){
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setId(id);
        choiceBox.setPrefSize(100, 20);
        for (String string : stringArray){
            choiceBox.getItems().add(string);
        }
        parent.getChildren().add(choiceBox);
        return choiceBox;
    }

    /**
     * Creates a choice box.
     * @param parent parent node
     * @param id JavaFX object id
     * @param enumArrayList values of choice box in enum format
     * @return created object
     */
    public static ChoiceBox<String> generateChoiceBox(Pane parent, String id, ObservableList<Type> enumArrayList){
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setId(id);
        choiceBox.setPrefSize(100, 20);
        for (Object object : enumArrayList){
            choiceBox.getItems().add(object.toString());
        }
        parent.getChildren().add(choiceBox);
        return choiceBox;
    }

    /**
     * Creates a choice box.
     * @param parent parent node
     * @param enumArrayList values of choice box in enum format
     * @param id JavaFX object id
     * @param x width
     * @param y height
     * @return created object
     */
    public static ChoiceBox<String> generateChoiceBox(Pane parent, ObservableList enumArrayList, String id, double x, double y){
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setId(id);
        choiceBox.setPrefSize(x, y);
        for (Object object : enumArrayList){
            choiceBox.getItems().add(object.toString());
        }
        parent.getChildren().add(choiceBox);
        return choiceBox;
    }

    /**
     * Creates a choice box.
     * @param parent parent node
     * @param stringArray values of choice box
     * @param value default value of choice box
     * @param id JavaFX object id
     * @return created object
     */
    public static ChoiceBox<String> generateChoiceBox(Pane parent, String[] stringArray, String value, String id){
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setId(id);
        choiceBox.setPrefSize(100, 20);
        choiceBox.setValue(value);
        for (String string : stringArray){
            choiceBox.getItems().add(string);
        }
        parent.getChildren().add(choiceBox);
        return choiceBox;
    }

    /**
     * Creates a choice box.
     * @param parent parent node
     * @param stringArray values of choice box
     * @param id JavaFX object id
     * @param x width
     * @param y height
     * @return created object
     */
    public static ChoiceBox<String> generateChoiceBox(Pane parent, String[] stringArray, String id, double x, double y){
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setId(id);
        choiceBox.setPrefSize(x, y);
        for (String string : stringArray){
            choiceBox.getItems().add(string);
        }
        parent.getChildren().add(choiceBox);
        return choiceBox;
    }

    /**
     * Creates a radiobutton.
     * @param parent parent node
     * @param text text on object
     * @param id JavaFX object id
     * @param toggleGroup radio button's toggle group
     * @return created object
     */
    public static RadioButton generateRadioButton(Pane parent, String text, String id, ToggleGroup toggleGroup){
        RadioButton radioButton = new RadioButton(text);
        radioButton.setId(id);
        radioButton.setToggleGroup(toggleGroup);
        parent.getChildren().add(radioButton);
        return radioButton;
    }

    /**
     * Creates a checkbox.
     * @param parent parent node
     * @param text text on object
     * @param id JavaFX object id
     * @return created object
     */
    public static CheckBox generateCheckBox(Pane parent, String text, String id){
        CheckBox checkBox = new CheckBox(text);
        checkBox.setId(id);
        parent.getChildren().add(checkBox);
        return checkBox;
    }

    /**
     * Creates a table view of all structograms.
     * @param parent parent node
     * @param x witdh
     * @param y height
     * @param id JavaFX object id
     * @return current object
     */
    public static FunctionTableView generateFunctionTableView(Pane parent, double x, double y, String id){
        FunctionTableView functionTableView = new FunctionTableView(x, y);
        functionTableView.setId(id);
        parent.getChildren().add(functionTableView);
        return functionTableView;
    }

    /**
     * Creates a table view of structograms, created by user.
     * @param parent parent node
     * @param x width
     * @param y height
     * @param id JavaFX object id
     * @return current object
     */
    public static SerFunctionTableView generateSerFunctionTableView(Pane parent, double x, double y, String id){
        SerFunctionTableView serFunctionTableView = new SerFunctionTableView(x, y);
        serFunctionTableView.setId(id);
        parent.getChildren().add(serFunctionTableView);
        return serFunctionTableView;
    }

    /**
     * Creates a string table column for a table of structograms.
     * @param parent parent node
     * @param text text on object
     * @param id JavaFX object id
     * @return created object
     */
    public static TableColumn<Structogram, String> generateStrTableColumn(TableView<Structogram> parent, String text, String id){
        TableColumn<Structogram, String> tableColumn = new TableColumn<>(text);
        tableColumn.setId(id);
        parent.getColumns().add(tableColumn);
        return tableColumn;
    }

    /**
     * Creates a hyperlink table column for a table of structograms.
     * @param parent parent node
     * @param text text on object
     * @param id JavaFX object id
     * @return created object
     */
    public static TableColumn<Structogram, Hyperlink> generateLinkTableColumn(TableView<Structogram> parent, String text, String id){
        TableColumn<Structogram, Hyperlink> tableColumn = new TableColumn<>(text);
        tableColumn.setId(id);
        parent.getColumns().add(tableColumn);
        return tableColumn;
    }

    /**
     * Creates a code area (specific text area).
     * @param parent parent node
     * @param id JavaFX object id
     * @param x width
     * @param y height
     * @return created object
     */
    public static CodeArea generateCodeArea(Pane parent, String id, double x, double y){
        CodeArea codeArea = new CodeArea();
        codeArea.setId(id);
        codeArea.setPrefWidth(x);
        codeArea.setPrefHeight(y);
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        parent.getChildren().add(codeArea);
        return codeArea;
    }
}
