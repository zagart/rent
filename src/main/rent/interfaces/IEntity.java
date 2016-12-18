package rent.interfaces;

import javafx.scene.control.TableView;

import java.io.Serializable;

/**
 * Interface for Entity classes.
 *
 * @param <T> Table model class of entity
 * @author zagart
 */
public interface IEntity<T extends ITableModel> extends Serializable {
    T createTableModel();

    TableView<T> createTableView();

    Long getId();
}
