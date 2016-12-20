package rent.interfaces;

import java.io.Serializable;

/**
 * Table model of {@link IEntity} class.
 *
 * @author zagart
 */
public interface ITableModel<PK extends Serializable, E extends IEntity> {
    E extractEntity();

    PK getId();
}
