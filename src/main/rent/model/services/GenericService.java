package rent.model.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import rent.application.singletons.Context;
import rent.interfaces.IEntity;
import rent.interfaces.ITableModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for communication data and UI objects.
 *
 * @author zagart
 */
public class GenericService {
    @SuppressWarnings("unchecked")
    public <E extends IEntity<T>, T extends ITableModel> ObservableList getObservableList(final Class<E> pClass) {
        final List<E> entities = Context.getService(pClass).getAll();
        final List<T> uiTariffs = new ArrayList<>();
        for (final E entity : entities) {
            final T tableModel = entity.createTableModel();
            if (tableModel.getId() != null) {
                uiTariffs.add(tableModel);
            }
        }
        return FXCollections.observableList(uiTariffs);
    }
}
