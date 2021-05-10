package frontend.components.tableviews.cells;

import backend.entities.structograms.FinalStructogram;
import backend.entities.structograms.Structogram;
import backend.serialization.Serialize;
import frontend.GuiManager;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Cell for shoving current image of the element,
 * which belongs to the actual row.
 * @param <T> cell property type
 */
public class ShowImageCell<T> extends TableCell<Structogram, Hyperlink> {

    private final Hyperlink link;
    private String path;

    /**
     * Constructor for ShowImageCell.
     */
    public ShowImageCell(){

        link = new Hyperlink("Show");
        link.setStyle(
                "-fx-text-fill: #a61c1c"
        );
        link.setOnAction(e -> showImage());
    }

    /**
     * Shows current structogram's image.
     */
    private void showImage() {
        Structogram rowObject = getTableView().getItems().get(getTableRow().getIndex());
        String imagePath;
        if (rowObject instanceof FinalStructogram){
            imagePath =  Serialize.getConfigHomePath() + "/basicfunctions/images/";
        } else {
            imagePath = Serialize.getConfigHomePath() + "/saves/images/";
        }
        File file = new File(imagePath + rowObject.getFileName() + ".png");
        ImageView imageView = new ImageView(new Image(file.toURI().toString()));
        GuiManager.openImageStage(imageView);
    }

    /**
     * Overridden method for updating current item.
     * @param item current item
     * @param empty emptiness state
     */
    @Override
    protected void updateItem(Hyperlink item, boolean empty){
        super.updateItem(item, empty);

        setGraphic(empty ? null : link);
    }
}
