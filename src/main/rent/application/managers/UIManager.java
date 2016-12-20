package rent.application.managers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import rent.application.DatabaseLoader;
import rent.application.utils.ReflectionUtil;
import rent.interfaces.IEntity;
import rent.model.entities.*;
import rent.model.services.GenericService;
import rent.ui.entities.UiExpense;
import rent.ui.main.WidgetDrawer;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;

/**
 * Class for handling common UI operations.
 *
 * @author zagart
 */
public class UIManager {
    private static final int SELECTION_WINDOW_HEIGHT = 140;
    private static final int SELECTION_WINDOW_WIDTH = 100;
    private static final int TABLE_POSITION = 1;
    final private GenericService mManager = new GenericService();
    private Node mArrow;
    private WidgetDrawer mDrawer = new WidgetDrawer();
    private FlowPane mRoot = new FlowPane();
    private TableManager mTableManager;
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

    private <L extends Event> EventHandler<L> getTableListener() {
        return pEvent -> {
            final UiExpense selectedExpense = (UiExpense) mTableManager.getSelectionModel().getSelectedItem();
            redrawArrow(Math.toIntExact(selectedExpense.getGazExpense()));
        };
    }

    private TableManager getTableManager(final Class<?> pClass) {
        final IEntity entity = (IEntity) ReflectionUtil.createGenericObject(pClass);
        return entity.createTableManager();
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
                    children.remove(mTableManager);
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
        mTableManager = getTableManager(pClass);
        mTableManager.setItems(observableList);
        mRoot.getChildren().add(TABLE_POSITION, mTableManager);
    }

    private void redrawArrow(final int pValue) {
        mWidgetRoot.getChildren().remove(mArrow);
        mArrow = mDrawer.getArrowNode(pValue);
        mWidgetRoot.getChildren().add(mArrow);
    }

    private FlowPane setUpGroup() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        mRoot.setOrientation(Orientation.VERTICAL);
        mArrow = mDrawer.getArrowNode(WidgetDrawer.MIN_ANGLE);
        mRoot.getChildren().add(getTablesList());
        loadTableData(Expense.class);
        showMeter();
        return mRoot;
    }

    public void setUpStage(final Stage pStage)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        mDrawer.setMaxValue(DatabaseLoader.EXPENSE_BOUND);
        setUpGroup();
        pStage.getIcons().add(getIcon());
        loadStage(pStage);
    }

    private void showMeter() {
        mWidgetRoot = new Group(mDrawer.outerRim(), mArrow, mDrawer.marks(), mDrawer.centerPoint());
        final EventHandler<Event> tableListener = getTableListener();
        mTableManager.setOnMouseClicked(tableListener);
        mTableManager.setOnKeyReleased(tableListener);
        mRoot.getChildren().add(mWidgetRoot);
    }
}
