package rent;

import javafx.application.Application;
import javafx.stage.Stage;
import rent.application.DatabaseLoader;
import rent.application.managers.ThreadManager;
import rent.application.managers.UIManager;
import rent.application.singletons.Context;
import rent.constants.Services;

import java.lang.reflect.InvocationTargetException;

public class App extends Application {
    private static final String WINDOW_TITLE = "Rent";
    final private ThreadManager mThreadManager = (ThreadManager) Context.getSystemService(Services.THREAD_MANAGER);
    final private UIManager mUiManager = new UIManager(WINDOW_TITLE);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage pStage)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        mUiManager.setUpStage(pStage);
        mThreadManager.execute(DatabaseLoader::activate);
    }
}
