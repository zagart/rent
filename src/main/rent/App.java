package rent;

import javafx.application.Application;
import javafx.stage.Stage;
import rent.ui.UIManager;

import java.lang.reflect.InvocationTargetException;

public class App extends Application {
    private static final String WINDOW_TITLE = "Rent";
    final private UIManager mManager = new UIManager(WINDOW_TITLE);

    public static void main(String[] args) {
//        new Thread(DatabaseLoader::activate).start();
        launch(args);
    }

    @Override
    public void start(final Stage pStage) {
        try {
            mManager.setUpStage(pStage);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException pEx) {
            pEx.printStackTrace();
        }
    }
}
