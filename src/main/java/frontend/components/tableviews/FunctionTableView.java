package frontend.components.tableviews;


import backend.entities.statements.varoperations.declaration.ArrayDeclaration;
import backend.entities.statements.varoperations.declaration.Declaration;
import backend.entities.structograms.Structogram;
import backend.serialization.Serialize;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.tableviews.cells.ShowImageCell;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * TableView to show the available structograms.
 */
public class FunctionTableView extends TableView<Structogram> {

    protected TableColumn<Structogram, String> nameColumn, typeColumn, parametersColumn, fileNameColumn;
    protected TableColumn<Structogram, Hyperlink> showLinkColumn;
    protected ObservableList<Structogram> structogramList;

    /**
     * Constructor for FunctionTableView.
     * @param width current width
     * @param height current height
     */
    public FunctionTableView(double width, double height) {
        setMaxWidth(width);
        setMaxHeight(height);
        initColumns();
    }

    /**
     * Initialize table columns.
     */
    private void initColumns(){
        nameColumn = FxComponentFactory.generateStrTableColumn(this, "Name", "nameColumn");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("funcName"));
        typeColumn = FxComponentFactory.generateStrTableColumn(this, "Type", "typeColumn");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("funcType"));
        parametersColumn = FxComponentFactory.generateStrTableColumn(this, "Parameters", "parametersColumn");
        parametersColumn.setCellValueFactory(this::getParameterString);
        fileNameColumn = FxComponentFactory.generateStrTableColumn(this, "File", "fileNameColumn");
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        showLinkColumn = FxComponentFactory.generateLinkTableColumn(this, "Image", "showLinkColumn");
        showLinkColumn.setCellFactory(tc -> new ShowImageCell<>());
    }

    /**
     * Creates string for parameter cells
     * @param cellDataFeatures current cell data features.
     * @return current cell property
     */
    private ObservableValue<String> getParameterString(TableColumn.CellDataFeatures<Structogram, String> cellDataFeatures) {
        StringBuilder parameterSb = new StringBuilder();
        ArrayList<Declaration> parameterList = cellDataFeatures.getValue().getParameterList();
        for (Declaration parameter : parameterList){
            parameterSb.append(parameter.getVarType());
            if (parameter instanceof ArrayDeclaration){
                parameterSb.append("[]");
            }
            parameterSb.append(" ").append(parameter.getVarName());
            if (!parameterList.get(parameterList.size()-1).equals(parameter)){
                parameterSb.append(", ");
            }
        }
        return new SimpleStringProperty(parameterSb.toString());
    }

    /**
     * Loads available structograms from path.
     * @param path current path
     */
    public void loadData(String path){
        structogramList = FXCollections.observableArrayList();
        File folder = new File(path);
        for (File file : Objects.requireNonNull(folder.listFiles())){
            if (file.isFile()) {
                getItems().add(Serialize.load(file.getPath()));
            }
        }
    }
}
