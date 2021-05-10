package frontend.components.tableviews.cells;

import backend.entities.structograms.Structogram;
import backend.serialization.Serialize;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Cell for removing current row.
 * @param <T> cell property type
 */
public class RemoveCell<T> extends TableCell<Structogram, Hyperlink> {

    private final Hyperlink link;

    /**
     *Constructor for RemoveCell.
     */
    public RemoveCell() {
        link = new Hyperlink("Remove");
        link.setStyle(
                "-fx-text-fill: #a61c1c"
        );
        link.setOnAction(evt -> {
            try {
                removeStructogram();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Removes current row's file.
     * @throws IOException when cant find file
     */
    private void removeStructogram() throws IOException {
        Files.delete(Paths.get(Serialize.getConfigHomePath() + "/saves/" + getTableView().getItems().get(getTableRow().getIndex()).getFileName() + ".ser"));
        Files.delete(Paths.get(Serialize.getConfigHomePath() + "/saves/images/" + getTableView().getItems().get(getTableRow().getIndex()).getFileName() + ".png"));
        getTableView().getItems().remove(getTableRow().getIndex());
        ((VBox) (getTableView().getParent().getChildrenUnmodifiable().get(1))).getChildren().clear();
    }

    /**
     * Overridden method for updating current item.
     * @param item current item
     * @param empty emptiness state
     */
    @Override
    public void updateItem(Hyperlink item, boolean empty) {
        super.updateItem(item, empty);

        setGraphic(empty ? null : link);
    }
}
