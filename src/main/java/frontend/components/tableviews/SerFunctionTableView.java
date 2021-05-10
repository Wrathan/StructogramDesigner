package frontend.components.tableviews;

import backend.entities.structograms.Structogram;
import frontend.components.factorys.FxComponentFactory;
import frontend.components.tableviews.cells.RemoveCell;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;

/**
 * TableView to show the serializable structograms.
 */
public class SerFunctionTableView extends FunctionTableView {

    private TableColumn<Structogram, Hyperlink> deleteLinkColumn;

    /**
     * Constructor for SerFunctionTableView.
     * @param width current width
     * @param height current height
     */
    public SerFunctionTableView(double width, double height) {
        super(width, height);
        initDeleteColumn();
    }

    /**
     * Initialize column for deleting a row.
     */
    public void initDeleteColumn(){
        deleteLinkColumn = FxComponentFactory.generateLinkTableColumn(this, "Remove", "deleteLinkColumn");
        deleteLinkColumn.setCellFactory(tc -> new RemoveCell<>());
    }
}
