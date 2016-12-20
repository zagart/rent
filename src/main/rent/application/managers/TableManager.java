package rent.application.managers;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rent.application.singletons.Context;
import rent.constants.ExceptionConstants;
import rent.constants.Services;
import rent.interfaces.IEntity;
import rent.interfaces.ITableModel;
import rent.model.entities.Customer;
import rent.model.entities.Payment;

import java.util.ArrayList;

/**
 * Class for managing table data.
 *
 * @author zagart
 */
public class TableManager<M extends ITableModel> extends TableView<M> {
    private static final int ADDRESS_COLUMN_WIDTH = 200;
    private static final int COLUMN_WIDTH = 120;
    private static final int TIMESTAMP_COLUMN_WIDTH = 150;
    final private ThreadManager mThreadManager = (ThreadManager) Context.getSystemService(Services.THREAD_MANAGER);

    public TableManager(final ArrayList<PropertyValueFactory> pFactories,
                        final String... pCaptions) {
        setUpTable(pFactories, pCaptions);
    }

    @SuppressWarnings("unchecked")
    public void addRow(final M pModel) {
        getItems().add(pModel);
        final IEntity entity = pModel.extractEntity();
        mThreadManager.executeInCachedPool(() -> Context.getService(entity.getClass()).save(entity));
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
    private void setUpTable(ArrayList<PropertyValueFactory> pFactories, String[] pCaptions) {
        if (pFactories.size() != pCaptions.length) {
            throw new IllegalArgumentException(ExceptionConstants.PARAMETERS_QUANTITY_EXCEPTION);
        }
        setEditable(true);
        for (int i = 0; i < pCaptions.length; i++) {
            String caption = pCaptions[i];
            final TableColumn column = new TableColumn<>(caption);
            column.setCellValueFactory(pFactories.get(i));
            customizeColumnWidth(column, caption);
            getColumns().add(column);
        }
    }
}
