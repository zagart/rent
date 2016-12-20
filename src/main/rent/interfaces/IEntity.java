package rent.interfaces;

import javafx.stage.Stage;
import rent.application.managers.TableManager;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Interface for Entity classes.
 *
 * @param <T> Table model class of entity
 * @author zagart
 */
public interface IEntity<T extends ITableModel> extends Serializable {
    List<String> createFieldsList();

    Stage createModelEditStage();

    TableManager<T> createTableManager();

    T createTableModel();

    IEntity<T> extractEntityFromMap(final Map<String, String> pFields);

    Long getId();
}
