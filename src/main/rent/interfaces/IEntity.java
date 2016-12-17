package rent.interfaces;

import javafx.scene.control.TableView;

import java.io.Serializable;

/**
 * Interface for Entity classes.
 *
 * @author zagart
 */
public interface IEntity extends Serializable {
    Long getId();

    TableView getTableView();
}
