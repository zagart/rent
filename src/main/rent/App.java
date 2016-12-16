package rent;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rent.application.DatabaseLoader;
import rent.ui.WidgetDrawer;

import java.util.Random;

@SuppressWarnings("deprecation")
public class App extends Application {
    private static final String WINDOW_TITLE = "Rent";
    private static WidgetDrawer sDrawer = new WidgetDrawer();
    private Node mArrow;
    private Group mRoot;

    public static void main(String[] args) {
        new Thread(DatabaseLoader::activate).start();
        launch(args);
    }

    private void onWidgetChanged(final int pAngle) {
        mRoot.getChildren().remove(mArrow);
        mArrow = App.sDrawer.getArrowNode(pAngle);
        mRoot.getChildren().add(mArrow);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        mArrow = sDrawer.getArrowNode(150);
        mRoot = GroupBuilder.create()
                .children(
                        sDrawer.outerRim(),
                        mArrow,
                        sDrawer.marks(),
                        sDrawer.centerPoint()
                )
                .build();
        mRoot.setOnMouseClicked(pEvent -> onWidgetChanged(new Random().nextInt(360)));
        sDrawer.setUpMouseForScaleAndMove(stage, mRoot);
        stage.setScene(new Scene(mRoot, 500, 200));
        stage.setTitle(WINDOW_TITLE);
        stage.show();
    }
}
