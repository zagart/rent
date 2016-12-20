package rent.interfaces;

import rent.application.managers.TableManager;

import java.io.Serializable;

/**
 * Interface for Entity classes.
 *
 * @param <T> Table model class of entity
 * @author zagart
 */
public interface IEntity<T extends ITableModel> extends Serializable {
    TableManager<T> createTableManager();

    T createTableModel();

    Long getId();
}
