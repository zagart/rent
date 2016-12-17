package rent.application.utils;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Utility class with methods for serving JavaFX-specific actions.
 *
 * @author zagart
 */
public class JavaFxUtil {
    private static final int COLUMN_WIDTH = 120;

    public static TableView createTable(final String... pFields) {
        final TableView<TableColumn> tableView = new TableView<>();
        for (final String field : pFields) {
            final TableColumn<TableColumn, Object> column = new TableColumn<>(field);
            column.setPrefWidth(COLUMN_WIDTH);
            tableView.getColumns().add(column);
        }
        return tableView;
    }
}
