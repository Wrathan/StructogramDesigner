package backend.serialization;

import backend.entities.structograms.Structogram;
import frontend.components.factorys.StructogramFactory;
import frontend.components.managers.QueryManager;
import frontend.components.managers.SelectionManager;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.*;

/**
 * Serialization methods.
 */
public final class Serialize {

    private static File CONFIG_HOME;

    /**
     * Sets the home folder for structogram files.
     */
    public static void setHome(){
        String home = System.getProperty("user.home");
        CONFIG_HOME = new File(home, ".structogramDesigner").getAbsoluteFile();
        CONFIG_HOME.mkdirs();
    }

    /**
     * Save structogram to file.
     * @param name name of the .ser file
     */
    public static void save(String name) {
        try {
            generateFile(name);
            generateImage(name);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Load structogram from file.
     * @param path path for the .ser file
     * @return generated object
     */
    public static Structogram load(String path){
        try {
            Structogram structogram;
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            structogram = (Structogram) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return structogram;
        } catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Generates file from Structogram.
     * @param name name of the .ser file
     * @throws IOException FileOutputStream, and ObjectOutputStream throws this
     */
    private static void generateFile(String name) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(Serialize.getConfigHomePath() + "/saves/" + name + ".ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(StructogramFactory.getStructogram());
        objectOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * Generates an image from the structogram, which is always has 1000x1000 size.
     * @param name name of the .png file
     * @throws IOException IOImage.write() throws this
     */
    private static void generateImage(String name) throws IOException{
        SelectionManager.clearBackground();

        final int OUTPUT_SIZE = 600;
        double figureWidth = QueryManager.getStructogramFigureVBox().getWidth();
        double figureHeight = QueryManager.getStructogramFigureVBox().getHeight();
        File file = new File(Serialize.getConfigHomePath() + "/saves/images/" + name + ".png");
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        RenderedImage renderedImage;

        if (figureWidth >= figureHeight){
            snapshotParameters.setTransform(new Scale(OUTPUT_SIZE/figureWidth, OUTPUT_SIZE/figureWidth));
        } else {
            snapshotParameters.setTransform(new Scale(OUTPUT_SIZE/figureHeight, OUTPUT_SIZE/figureHeight));
        }

        WritableImage snapshot = QueryManager.getStructogramFigureVBox().snapshot(snapshotParameters, null);

        if (snapshot.getHeight() == OUTPUT_SIZE && snapshot.getWidth() == OUTPUT_SIZE){
            renderedImage = SwingFXUtils.fromFXImage(snapshot, null);
        } else {
            WritableImage outputImage = new WritableImage(OUTPUT_SIZE, OUTPUT_SIZE);
            PixelWriter writablePixelWriter = outputImage.getPixelWriter();
            PixelReader snapshotPixelReader = snapshot.getPixelReader();

            int horizontalMargin = (int) Math.ceil((OUTPUT_SIZE - snapshot.getWidth())/2);
            int verticalMargin = (int) Math.ceil((OUTPUT_SIZE - snapshot.getHeight())/2);

            for (int x = 0; x < OUTPUT_SIZE; x++){
                for (int y = 0; y < OUTPUT_SIZE; y++){
                    if (horizontalMargin > 0) {
                        if (x < horizontalMargin || x >= OUTPUT_SIZE - horizontalMargin){
                            writablePixelWriter.setColor(x, y, Color.WHITE);
                        } else {
                            writablePixelWriter.setColor(x, y, snapshotPixelReader.getColor(x - horizontalMargin, y));
                        }

                    } else if (verticalMargin > 0) {
                        if (y < verticalMargin || y >= OUTPUT_SIZE - verticalMargin) {
                            writablePixelWriter.setColor(x, y, Color.WHITE);
                        } else {
                            writablePixelWriter.setColor(x, y, snapshotPixelReader.getColor(x, y - verticalMargin));
                        }
                    }
                }
            }
            renderedImage = SwingFXUtils.fromFXImage(outputImage, null);
        }

        ImageIO.write(renderedImage, "png", file);
        SelectionManager.refillBackground();
    }

    public static String getConfigHomePath() {
        return CONFIG_HOME.getAbsolutePath();
    }
}
