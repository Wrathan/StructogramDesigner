package testclasses.frontend.javafx.dashboardpanel;

import frontend.GuiManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.api.FxToolkit;
import testclasses.frontend.javafx.TestFXBase;

import java.util.concurrent.TimeoutException;

public abstract class DashboardPanelTests extends TestFXBase {

    @BeforeEach
    private void setUpClass() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(GuiManager::new);
    }

    @AfterEach
    private void cleanUpStages() throws TimeoutException {
        FxToolkit.cleanupStages();
    }
}
