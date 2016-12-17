package rent.ui;

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
    private static final int TABLE_POSITION = 1;
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
        return entity.getTableView();
    }

    private ListView<String> getTablesList() {
        return new ListView<String>() {
            {
                setItems(FXCollections.observableArrayList(
                        "Показания",
                        "Заказчики",
                        "Начисления",
                        "Тарифы",
                        "Платежи"));
                setPrefSize(100, 117);
                setOnMouseClicked((pEvent) -> {
                    final ObservableList<Node> children = mRoot.getChildren();
                    children.remove(mTableView);
                    children.remove(mWidgetRoot);
                    switch (getSelectionModel().getSelectedIndex()) {
                        case 0:
                            mTableView = getTableView(Expense.class);
                            showMeter();
                            break;
                        case 1:
                            mTableView = getTableView(Customer.class);
                            break;
                        case 2:
                            mTableView = getTableView(Bill.class);
                            break;
                        case 3:
                            mTableView = getTableView(Tariff.class);
                            break;
                        case 4:
                            mTableView = getTableView(Payment.class);
                            break;
                    }
                    children.add(TABLE_POSITION, mTableView);
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

    private void redrawArrow(final int pAngle) {
        mWidgetRoot.getChildren().remove(mArrow);
        mArrow = mDrawer.getArrowNode(pAngle);
        mWidgetRoot.getChildren().add(mArrow);
    }

    private FlowPane setUpGroup() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        mRoot.setOrientation(Orientation.VERTICAL);
        mArrow = mDrawer.getArrowNode(MIN_ANGLE);
        mRoot.getChildren().add(getTablesList());
        mTableView = getTableView(Expense.class);
        mRoot.getChildren().add(mTableView);
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
