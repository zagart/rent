package rent.ui.managers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import rent.application.utils.ReflectionUtil;
import rent.interfaces.IEntity;
import rent.model.entities.*;
import rent.model.services.GenericService;
import rent.ui.main.WidgetDrawer;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Random;

/**
 * Class for handling common UI operations.
 *
 * @author zagart
 */
public class UIManager {
    private static final int MAX_ANGLE = 360;
    private static final int MIN_ANGLE = 0;
    private static final int SELECTION_WINDOW_HEIGHT = 140;
    private static final int SELECTION_WINDOW_WIDTH = 100;
    private static final int TABLE_POSITION = 1;
    final private GenericService mManager = new GenericService();
    private Node mArrow;
    private WidgetDrawer mDrawer = new WidgetDrawer();
    private FlowPane mRoot = new FlowPane();
    private TableView mTableView;
    private Group mWidgetRoot;
    private String mWindowTitle = "Title";

    public UIManager(final String pWindowTitle) {
        mWindowTitle = pWindowTitle;
    }

    private Image getIcon() {
        final URL path = getClass().getClassLoader().getResource("resources/icon.png");
        final String iconPath;
        if (path != null) {
            iconPath = path.toString();
            return new Image(iconPath);
        } else {
            return null;
        }
    }

    private TableView getTableView(final Class<?> pClass) {
        final IEntity entity = (IEntity) ReflectionUtil.createGenericObject(pClass);
        return entity.createTableView();
    }

    private ListView<String> getTablesList() {
        return new ListView<String>() {
            {
                setItems(FXCollections.observableArrayList(
                        Expense.MENU_NAME,
                        Customer.MENU_NAME,
                        Bill.MENU_NAME,
                        Tariff.MENU_NAME,
                        Payment.MENU_NAME,
                        Passport.MENU_NAME));
                setPrefSize(SELECTION_WINDOW_WIDTH, SELECTION_WINDOW_HEIGHT);
                setOnMouseClicked((pEvent) -> {
                    final ObservableList<Node> children = mRoot.getChildren();
                    children.remove(mTableView);
                    children.remove(mWidgetRoot);
                    switch (getSelectionModel().getSelectedIndex()) {
                        case 0:
                            loadTableData(Expense.class);
                            showMeter();
                            break;
                        case 1:
                            loadTableData(Customer.class);
                            break;
                        case 2:
                            loadTableData(Bill.class);
                            break;
                        case 3:
                            loadTableData(Tariff.class);
                            break;
                        case 4:
                            loadTableData(Payment.class);
                            break;
                        case 5:
                            loadTableData(Passport.class);
                            break;
                    }
                });
            }
        };
    }

    private void loadStage(final Stage pStage) {
        Scene scene = new Scene(mRoot);
        pStage.setScene(scene);
        pStage.setResizable(false);
        pStage.setTitle(mWindowTitle);
        pStage.show();
    }

    @SuppressWarnings("unchecked")
    private <E extends IEntity> void loadTableData(final Class<E> pClass) {
        final ObservableList observableList = mManager.getObservableList(pClass);
        mTableView = getTableView(pClass);
        mTableView.setItems(observableList);
        mRoot.getChildren().add(TABLE_POSITION, mTableView);
    }

    private void redrawArrow(final int pAngle) {
        mWidgetRoot.getChildren().remove(mArrow);
        mArrow = mDrawer.getArrowNode(pAngle);
        mWidgetRoot.getChildren().add(mArrow);
    }

    private FlowPane setUpGroup() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        mRoot.setOrientation(Orientation.VERTICAL);
        mArrow = mDrawer.getArrowNode(MIN_ANGLE);
        mRoot.getChildren().add(getTablesList());
        loadTableData(Expense.class);
        showMeter();
        return mRoot;
    }

    public void setUpStage(final Stage pStage)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        setUpGroup();
        pStage.getIcons().add(getIcon());
        loadStage(pStage);
    }

    private void showMeter() {
        mWidgetRoot = new Group(mDrawer.outerRim(), mArrow, mDrawer.marks(), mDrawer.centerPoint());
        mWidgetRoot.setOnMouseClicked(pEvent -> redrawArrow(new Random().nextInt(MAX_ANGLE)));
        mRoot.getChildren().add(mWidgetRoot);
    }
}
