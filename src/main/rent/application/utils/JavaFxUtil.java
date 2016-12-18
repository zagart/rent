package rent.application.utils;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rent.model.entities.Customer;
import rent.model.entities.Payment;

import java.util.ArrayList;

/**
 * Utility class with methods for serving JavaFX-specific actions.
 *
 * @author zagart
 */
public class JavaFxUtil {
    public static final int TIMESTAMP_COLUMN_WIDTH = 150;
    private static final int ADDRESS_COLUMN_WIDTH = 200;
    private static final int COLUMN_WIDTH = 120;
    private static final String PARAMETERS_QUANTITY_EXCEPTION = "Parameters quantity do not match";

    @SuppressWarnings("unchecked")
    public static <T> TableView<T> createTable(final ArrayList<PropertyValueFactory> pFactories,
                                               final String... pCaptions) {
        if (pFactories.size() != pCaptions.length) {
            throw new IllegalArgumentException(PARAMETERS_QUANTITY_EXCEPTION);
        }
        final TableView<T> tableView = new TableView<>();
        tableView.setEditable(true);
        for (int i = 0; i < pCaptions.length; i++) {
            String caption = pCaptions[i];
            final TableColumn column = new TableColumn<>(caption);
            column.setCellValueFactory(pFactories.get(i));
            switch (caption) {
                case Customer.Fields.ADDRESS:
                    column.setPrefWidth(ADDRESS_COLUMN_WIDTH);
                    break;
                case Payment.Fields.TIMESTAMP:
                    column.setPrefWidth(TIMESTAMP_COLUMN_WIDTH);
                    break;
                default:
                    column.setPrefWidth(COLUMN_WIDTH);
            }
            tableView.getColumns().add(column);
        }
        return tableView;
    }
}
