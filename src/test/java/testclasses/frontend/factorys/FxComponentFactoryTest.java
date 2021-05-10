package testclasses.frontend.factorys;

import backend.entities.structograms.Structogram;
import backend.enums.VarType;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.tableviews.FunctionTableView;
import frontend.components.tableviews.SerFunctionTableView;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.fxmisc.richtext.CodeArea;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FxComponentFactoryTest extends ApplicationTest {

    @Test
    void generateButton() {
        VBox vBox = new VBox();
        String buttonId = "button";
        Button button = FxComponentFactory.generateButton(vBox, buttonId);

        assertEquals(vBox.getChildren().size(), 1);
        assertEquals(vBox.getChildren().get(0), button);
        assertEquals(button.getId(), buttonId);
        assertEquals(button.getPrefWidth(), 100);
        assertEquals(button.getPrefHeight(), 20);
    }

    @Test
    void testGenerateButton() {
        VBox vBox = new VBox();
        String buttonText = "text";
        String buttonId = "button";
        Button button = FxComponentFactory.generateButton(vBox, buttonText, buttonId);

        assertEquals(vBox.getChildren().size(), 1);
        assertEquals(vBox.getChildren().get(0), button);
        assertEquals(button.getText(), buttonText);
        assertEquals(button.getId(), buttonId);
        assertEquals(button.getPrefWidth(), 100);
        assertEquals(button.getPrefHeight(), 20);
    }

    @Test
    void testGenerateButton1() {
        VBox vBox = new VBox();
        String buttonText = "text";
        String buttonId = "button";
        double width = 200;
        double height = 50;

        Button button = FxComponentFactory.generateButton(vBox, buttonText, buttonId, width, height);

        assertEquals(vBox.getChildren().size(), 1);
        assertEquals(vBox.getChildren().get(0), button);
        assertEquals(button.getText(), buttonText);
        assertEquals(button.getId(), buttonId);
        assertEquals(button.getPrefWidth(), width);
        assertEquals(button.getPrefHeight(), height);
    }

    @Test
    void generateMenu() {
        MenuBar menuBar = new MenuBar();
        String menuText = "text";
        String menuId = "menu";

        Menu menu = FxComponentFactory.generateMenu(menuBar, menuText, menuId);

        assertEquals(menuBar.getMenus().size(), 1);
        assertEquals(menuBar.getMenus().get(0), menu);
        assertEquals(menu.getText(), menuText);
        assertEquals(menu.getId(), menuId);
    }

    @Test
    void generateMenuItem() {
        Menu menu = new Menu();
        String menuText = "text";
        String menuId = "menuItem";

        MenuItem menuItem = FxComponentFactory.generateMenuItem(menu, menuText, menuId);

        assertEquals(menu.getItems().size(), 1);
        assertEquals(menu.getItems().get(0), menuItem);
        assertEquals(menuItem.getText(), menuText);
        assertEquals(menuItem.getId(), menuId);
    }

    @Test
    void generateLabel() {
        VBox pane = new VBox();
        String labelId = "label";

        Label label = FxComponentFactory.generateLabel(pane, labelId);

        assertEquals(pane.getChildren().size(), 1);
        assertEquals(pane.getChildren().get(0), label);
        assertEquals(label.getId(), labelId);
    }

    @Test
    void testGenerateLabel() {
        VBox pane = new VBox();
        String labelText = "text";
        String labelId = "label";

        Label label = FxComponentFactory.generateLabel(pane, labelText, labelId);

        assertEquals(pane.getChildren().size(), 1);
        assertEquals(pane.getChildren().get(0), label);
        assertEquals(label.getText(), labelText);
        assertEquals(label.getId(), labelId);
    }

    @Test
    void generateTextField() {
        VBox pane = new VBox();
        String textFieldId = "textField";

        TextField textField = FxComponentFactory.generateTextField(pane, textFieldId);

        assertEquals(pane.getChildren().size(), 1);
        assertEquals(pane.getChildren().get(0), textField);
        assertEquals(textField.getId(), textFieldId);
    }

    @Test
    void testGenerateTextField() {
        VBox pane = new VBox();
        String textFieldId = "textField";
        double width = 200;
        double height = 50;

        TextField textField = FxComponentFactory.generateTextField(pane, textFieldId, width, height);

        assertEquals(pane.getChildren().size(), 1);
        assertEquals(pane.getChildren().get(0), textField);
        assertEquals(textField.getId(), textFieldId);
        assertEquals(textField.getPrefWidth(), width);
        assertEquals(textField.getPrefHeight(), height);
    }

    @Test
    void generateChoiceBox() {
        VBox pane = new VBox();
        String choiceBoxId = "choiceBox";

        ChoiceBox choiceBox = FxComponentFactory.generateChoiceBox(pane, choiceBoxId);

        assertEquals(pane.getChildren().size(), 1);
        assertEquals(pane.getChildren().get(0), choiceBox);
        assertEquals(choiceBox.getId(), choiceBoxId);
    }

    @Test
    void testGenerateChoiceBox() {
        VBox pane = new VBox();
        String choiceBoxId = "choiceBox";
        double width = 200;
        double height = 50;

        ChoiceBox choiceBox = FxComponentFactory.generateChoiceBox(pane, choiceBoxId, width, height);

        assertEquals(pane.getChildren().size(), 1);
        assertEquals(pane.getChildren().get(0), choiceBox);
        assertEquals(choiceBox.getId(), choiceBoxId);
        assertEquals(choiceBox.getPrefWidth(), width);
        assertEquals(choiceBox.getPrefHeight(), height);
    }

    @Test
    void testGenerateChoiceBox1() {
        VBox pane = new VBox();
        String[] stringArray = {"A", "B", "C"};
        String choiceBoxId = "choiceBox";

        ChoiceBox choiceBox = FxComponentFactory.generateChoiceBox(pane, stringArray, choiceBoxId);

        assertEquals(pane.getChildren().size(), 1);
        assertEquals(pane.getChildren().get(0), choiceBox);
        assertEquals(choiceBox.getId(), choiceBoxId);
        assertArrayEquals(choiceBox.getItems().toArray(), stringArray);
    }

    @Test
    void testGenerateChoiceBox2() {
        VBox pane = new VBox();
        String choiceBoxId = "choiceBox";
        ArrayList<String> stringArrayList = new ArrayList<>();

        ChoiceBox choiceBox = FxComponentFactory.generateChoiceBox(pane, choiceBoxId, FXCollections.observableArrayList(VarType.values()));

        for (VarType varType : FXCollections.observableArrayList(VarType.values())){
            stringArrayList.add(varType.toString());
        }

        assertEquals(pane.getChildren().size(), 1);
        assertEquals(pane.getChildren().get(0), choiceBox);
        assertEquals(choiceBox.getId(), choiceBoxId);
        assertArrayEquals(choiceBox.getItems().toArray(), stringArrayList.toArray());
    }

    @Test
    void testGenerateChoiceBox3() {
        VBox pane = new VBox();
        String choiceBoxId = "choiceBox";
        ArrayList<String> stringArrayList = new ArrayList<>();
        double width = 200;
        double height = 50;

        ChoiceBox choiceBox = FxComponentFactory.generateChoiceBox(pane, FXCollections.observableArrayList(VarType.values()), choiceBoxId, width, height);

        for (VarType varType : FXCollections.observableArrayList(VarType.values())){
            stringArrayList.add(varType.toString());
        }

        assertEquals(pane.getChildren().size(), 1);
        assertEquals(pane.getChildren().get(0), choiceBox);
        assertEquals(choiceBox.getId(), choiceBoxId);
        assertArrayEquals(choiceBox.getItems().toArray(), stringArrayList.toArray());
        assertEquals(choiceBox.getPrefWidth(), width);
        assertEquals(choiceBox.getPrefHeight(), height);
    }

    @Test
    void testGenerateChoiceBox4() {
        VBox pane = new VBox();
        String[] stringArray = {"A", "B", "C"};
        String choiceBoxId = "choiceBox";

        ChoiceBox choiceBox = FxComponentFactory.generateChoiceBox(pane, stringArray, stringArray[0], choiceBoxId);

        assertEquals(pane.getChildren().size(), 1);
        assertEquals(pane.getChildren().get(0), choiceBox);
        assertEquals(choiceBox.getId(), choiceBoxId);
        assertEquals(choiceBox.getValue().toString(), stringArray[0]);
        assertArrayEquals(choiceBox.getItems().toArray(), stringArray);
    }

    @Test
    void testGenerateChoiceBox5() {
        VBox pane = new VBox();
        String[] stringArray = {"A", "B", "C"};
        String choiceBoxId = "choiceBox";
        double width = 200;
        double height = 50;

        ChoiceBox choiceBox = FxComponentFactory.generateChoiceBox(pane, stringArray, choiceBoxId, width, height);

        assertEquals(pane.getChildren().size(), 1);
        assertEquals(pane.getChildren().get(0), choiceBox);
        assertEquals(choiceBox.getId(), choiceBoxId);
        assertArrayEquals(choiceBox.getItems().toArray(), stringArray);
    }

    @Test
    void generateRadioButton() {
        VBox pane = new VBox();
        String radioButtonText = "text";
        String radioButtonId = "radioButton";
        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton radioButton = FxComponentFactory.generateRadioButton(pane, radioButtonText, radioButtonId, toggleGroup);

        assertEquals(pane.getChildren().size(), 1);
        assertEquals(pane.getChildren().get(0), radioButton);
        assertEquals(radioButton.getText(), radioButtonText);
        assertEquals(radioButton.getId(), radioButtonId);
        assertEquals(radioButton.getToggleGroup(), toggleGroup);
    }

    @Test
    void generateCheckBox() {
        VBox pane = new VBox();
        String checkBoxText = "text";
        String checkBoxId = "radioButton";

        CheckBox checkBox = FxComponentFactory.generateCheckBox(pane, checkBoxText, checkBoxId);

        assertEquals(pane.getChildren().size(), 1);
        assertEquals(pane.getChildren().get(0), checkBox);
        assertEquals(checkBox.getText(), checkBoxText);
        assertEquals(checkBox.getId(), checkBoxId);
    }

    @Test
    void generateFunctionTableView() {
        VBox pane = new VBox();
        double width = 200;
        double height = 50;
        String functionTableViewId = "functionTableView";

        FunctionTableView functionTableView = FxComponentFactory.generateFunctionTableView(pane, width, height, functionTableViewId);

        assertEquals(pane.getChildren().size(), 1);
        assertEquals(pane.getChildren().get(0), functionTableView);
        assertEquals(functionTableView.getId(), functionTableViewId);
    }

    @Test
    void generateSerFunctionTableView() {
        VBox pane = new VBox();
        double width = 200;
        double height = 50;
        String serFunctionTableViewId = "serFunctionTableView";

        SerFunctionTableView serFunctionTableView = FxComponentFactory.generateSerFunctionTableView(pane, width, height, serFunctionTableViewId);

        assertEquals(pane.getChildren().size(), 1);
        assertEquals(pane.getChildren().get(0), serFunctionTableView);
        assertEquals(serFunctionTableView.getId(), serFunctionTableViewId);
    }

    @Test
    void generateStrTableColumn() {
        TableView<Structogram> tableView = new TableView<Structogram>();
        String strTableColumnText = "text";
        String strTableColumnId = "functionTableView";

        TableColumn<Structogram, String> strTableColumn = FxComponentFactory.generateStrTableColumn(tableView, strTableColumnText, strTableColumnId);

        assertEquals(tableView.getColumns().size(), 1);
        assertEquals(tableView.getColumns().get(0), strTableColumn);
        assertEquals(strTableColumn.getText(), strTableColumnText);
        assertEquals(strTableColumn.getId(), strTableColumnId);
    }

    @Test
    void generateLinkTableColumn() {
        TableView<Structogram> tableView = new TableView<Structogram>();
        String linkTableColumnText = "text";
        String linkTableColumnId = "functionTableView";

        TableColumn<Structogram, Hyperlink> strTableColumn = FxComponentFactory.generateLinkTableColumn(tableView, linkTableColumnText, linkTableColumnId);

        assertEquals(tableView.getColumns().size(), 1);
        assertEquals(tableView.getColumns().get(0), strTableColumn);
        assertEquals(strTableColumn.getText(), linkTableColumnText);
        assertEquals(strTableColumn.getId(), linkTableColumnId);
    }

    @Test
    void generateCodeArea() {
        VBox pane = new VBox();
        String codeAreaId = "codeArea";
        double width = 200;
        double height = 50;

        CodeArea codeArea = FxComponentFactory.generateCodeArea(pane, codeAreaId, width, height);

        assertEquals(pane.getChildren().size(), 1);
        assertEquals(pane.getChildren().get(0), codeArea);
        assertEquals(codeArea.getId(), codeAreaId);
        assertEquals(codeArea.getPrefWidth(), width);
        assertEquals(codeArea.getPrefHeight(), height);
    }
}
