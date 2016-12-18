package rent;

import javafx.application.Application;
import javafx.stage.Stage;
import rent.application.DatabaseLoader;
import rent.ui.managers.UIManager;

import java.lang.reflect.InvocationTargetException;

public class App extends Application {
    private static final String WINDOW_TITLE = "Rent";
    final private UIManager mManager = new UIManager(WINDOW_TITLE);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage pStage)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        mManager.setUpStage(pStage);
        new Thread(DatabaseLoader::activate).start();
    }
}
