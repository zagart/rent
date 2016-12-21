package rent.application.managers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import rent.application.singletons.Context;
import rent.constants.ExceptionConstants;
import rent.constants.UiConstants;
import rent.interfaces.IEntity;
import rent.interfaces.ITableModel;
import rent.model.entities.Customer;
import rent.model.entities.Payment;
import rent.ui.custom.CustomStage;
import rent.ui.custom.CustomTextField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class for managing table data.
 *
 * @author zagart
 */
public class TableManager<M extends ITableModel> extends TableView<M> {
    private static final int ADDRESS_COLUMN_WIDTH = 200;
    private static final int COLUMN_WIDTH = 120;
    private static final String CUSTOMER_ID_FIELD = "ID заказчика";
    private static final int FIRST_ITEM = 0;
    private static final String ID_FIELD = "ID";
    private static final String TARIFF_ID_TARIFF = "ID тарифа";
    private static final int TIMESTAMP_COLUMN_WIDTH = 150;
    private CustomStage mSecondaryStage;
    private UIManager mUIManager;

    public TableManager(final ArrayList<PropertyValueFactory> pFactories,
                        final String... pCaptions) {
        setUpTable(pFactories, pCaptions);
    }

    @SuppressWarnings("unchecked")
    void addRow() {
        openSecondaryStage(UiConstants.ADD);
    }

    private void customizeColumnWidth(final TableColumn pColumn, final String pCaption) {
        switch (pCaption) {
            case Customer.Fields.ADDRESS:
                pColumn.setPrefWidth(ADDRESS_COLUMN_WIDTH);
                break;
            case Payment.Fields.TIMESTAMP:
                pColumn.setPrefWidth(TIMESTAMP_COLUMN_WIDTH);
                break;
            default:
                pColumn.setPrefWidth(COLUMN_WIDTH);
        }
    }

    @SuppressWarnings("unchecked")
    void deleteRow() {
        final M model = getSelectionModel().getSelectedItem();
        final IEntity entity = model.extractEntity();
        if (entity != null) {
            Context.getService(entity.getClass()).delete(entity);
            updateTable(entity.getClass());
        }
    }

    void editRow() {
        openSecondaryStage(UiConstants.EDIT);
    }

    public <E extends IEntity> ChangeListener getSearchListener(final Class<E> pClass) {
        return (ObservableValue pObservableValue, Object pOld, Object pNew) -> {
            if (Customer.class == pClass) {
                final String text = mUIManager.getSearch().getText();
                final ObservableList<M> items = getItems();
                for (int i = items.size() - 1; i >= 0; i--) {
                    final Customer customer = (Customer) items.get(i).extractEntity();
                    if (customer.getLastName().toLowerCase().contains(text.toLowerCase())) {
                        getSelectionModel().select(items.get(i));
                    }
                }
            }
        };
    }

    private void openSecondaryStage(final String pConstant) {
        if (getItems().size() > 0) {
            final M model = getItems().get(FIRST_ITEM);
            final IEntity entity = model.extractEntity();
            if (entity != null) {
                mSecondaryStage = (CustomStage) entity.createModelEditStage();
                switch (pConstant) {
                    case UiConstants.EDIT:
                        //TODO editing selected row
                    case UiConstants.ADD:
                        mSecondaryStage.setTitle(pConstant);
                        final Button cancelButton = mSecondaryStage.getCancelButton();
                        final Button okButton = mSecondaryStage.getOkButton();
                        cancelButton.setOnMouseClicked((pEvent) -> mSecondaryStage.hide());
                        okButton.setOnMouseClicked((pEvent) -> {
                            saveStageResult(mSecondaryStage.getFieldsLayout(), entity);
                            mSecondaryStage.hide();
                            updateTable(getItems().get(FIRST_ITEM).extractEntity().getClass());
                        });
                        mSecondaryStage.show();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void saveStageResult(final VBox pFieldsLayout, final IEntity pEntity) {
        final List fields = pEntity.createFieldsList();
        final HashMap<String, Object> entityContentMap = new HashMap<>();
        for (final Node node : pFieldsLayout.getChildren()) {
            if (node instanceof CustomTextField) {
                final CustomTextField field = (CustomTextField) node;
                final String tag = field.getTag();
                if (tag != null && fields.contains(tag)) {
                    entityContentMap.put(tag, field.getText());
                }
            }
        }
        final IEntity entity = pEntity.extractEntityFromMap(entityContentMap);
        Context.getService(entity.getClass()).save(entity);
    }

    TableManager setUiManager(final UIManager pUiManager) {
        mUIManager = pUiManager;
        return this;
    }

    @SuppressWarnings("unchecked")
    private void setUpTable(ArrayList<PropertyValueFactory> pFactories, String[] pCaptions) {
        if (pFactories.size() != pCaptions.length) {
            throw new IllegalArgumentException(ExceptionConstants.PARAMETERS_QUANTITY_EXCEPTION);
        }
        setEditable(true);
        for (int i = 0; i < pCaptions.length; i++) {
            String caption = pCaptions[i];
            if (!caption.equals(ID_FIELD) && !caption.equals(CUSTOMER_ID_FIELD) && !caption.equals(TARIFF_ID_TARIFF)) {
                final TableColumn column = new TableColumn<>(caption);
                column.setCellValueFactory(pFactories.get(i));
                customizeColumnWidth(column, caption);
                getColumns().add(column);
            }
        }
    }

    private <E extends IEntity> void updateTable(final Class<E> pClass) {
        mUIManager.reloadTableData(pClass);
    }
}
