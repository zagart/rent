package rent.application.managers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rent.application.DatabaseLoader;
import rent.application.utils.ReflectionUtil;
import rent.application.utils.UiUtil;
import rent.constants.UiConstants;
import rent.interfaces.IEntity;
import rent.model.entities.*;
import rent.model.services.GenericService;
import rent.ui.entities.UiExpense;
import rent.ui.main.WidgetDrawer;

import java.lang.reflect.InvocationTargetException;

/**
 * Class for handling common UI operations.
 *
 * @author zagart
 */
public class UIManager {
    private static final int BUTTON_WIDTH = 100;
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

    private VBox getCrudMenu() {
        final VBox crudMenu = new VBox();
        final Button addButton = new Button(UiConstants.ADD);
        final Button editButton = new Button(UiConstants.EDIT);
        final Button deleteButton = new Button(UiConstants.DELETE);
        addButton.setOnMouseClicked((pEvent) -> mTableManager.addRow());
        deleteButton.setOnMouseClicked((pEvent) -> mTableManager.deleteRow());
        editButton.setOnMouseClicked((pEvent) -> mTableManager.editRow());
        setUpButtons(addButton, editButton, deleteButton);
        crudMenu.getChildren().add(addButton);
        crudMenu.getChildren().add(editButton);
        crudMenu.getChildren().add(deleteButton);
        return crudMenu;
    }

    private <L extends Event> EventHandler<L> getTableListener() {
        return pEvent -> {
            final UiExpense selectedExpense = (UiExpense) mTableManager.getSelectionModel().getSelectedItem();
            redrawArrow(Math.toIntExact(selectedExpense.getGazExpense()));
        };
    }

    private TableManager getTableManager(final Class<?> pClass) {
        final IEntity entity = (IEntity) ReflectionUtil.createGenericObject(pClass);
        return entity.createTableManager().setUiManager(this);
    }

    private VBox getTablesList() {
        final ListView<String> menuItems = new ListView<String>() {
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
                    switch (getSelectionModel().getSelectedIndex()) {
                        case 0:
                            reloadTableData(Expense.class);
                            showMeter();
                            break;
                        case 1:
                            reloadTableData(Customer.class);
                            break;
                        case 2:
                            reloadTableData(Bill.class);
                            break;
                        case 3:
                            reloadTableData(Tariff.class);
                            break;
                        case 4:
                            reloadTableData(Payment.class);
                            break;
                        case 5:
                            reloadTableData(Passport.class);
                            break;
                    }
                });
            }
        };
        return new VBox(menuItems, getCrudMenu());
    }

    private void loadStage(final Stage pStage) {
        Scene scene = new Scene(mRoot);
        pStage.setScene(scene);
        pStage.setResizable(false);
        pStage.setTitle(mWindowTitle);
        pStage.show();
    }

    private void redrawArrow(final int pValue) {
        mWidgetRoot.getChildren().remove(mArrow);
        mArrow = mDrawer.getArrowNode(pValue);
        mWidgetRoot.getChildren().add(mArrow);
    }

    @SuppressWarnings("unchecked")
    public <E extends IEntity> void reloadTableData(final Class<E> pClass) {
        final ObservableList<Node> children = mRoot.getChildren();
        children.remove(mTableManager);
        children.remove(mWidgetRoot);
        final ObservableList observableList = mManager.getObservableList(pClass);
        mTableManager = getTableManager(pClass);
        mTableManager.setItems(observableList);
        children.add(TABLE_POSITION, mTableManager);
    }

    private void setUpButtons(final Button... pButtons) {
        for (final Button button : pButtons) {
            button.setPrefWidth(BUTTON_WIDTH);
        }
    }

    private FlowPane setUpGroup() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        mRoot.setOrientation(Orientation.VERTICAL);
        mArrow = mDrawer.getArrowNode(WidgetDrawer.MIN_ANGLE);
        mRoot.getChildren().add(getTablesList());
        reloadTableData(Expense.class);
        showMeter();
        return mRoot;
    }

    public void setUpStage(final Stage pStage)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        mDrawer.setMaxValue(DatabaseLoader.EXPENSE_BOUND);
        setUpGroup();
        pStage.getIcons().add(UiUtil.getIcon());
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
