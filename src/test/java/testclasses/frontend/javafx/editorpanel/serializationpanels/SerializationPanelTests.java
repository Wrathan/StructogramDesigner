package testclasses.frontend.javafx.editorpanel.serializationpanels;

import backend.serialization.Serialize;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.api.FxRobot;
import testclasses.frontend.javafx.editorpanel.EditorPanelTests;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public abstract class SerializationPanelTests extends EditorPanelTests {

    protected File file, imgFile;

    @BeforeEach
    private void saveStructogram(FxRobot robot) {
        ArrayList<File> files = saveBasicStructogram(robot);
        file = files.get(0);
        imgFile = files.get(1);
    }

    @AfterEach
    private void deleteFiles(){
        file.delete();
        imgFile.delete();
    }

    protected int countFiles(){
        int number = 0;
        File folder = new File(Serialize.getConfigHomePath() + "/saves/");
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isFile()) {
                number++;
            }
        }
        return number;
    }
}
