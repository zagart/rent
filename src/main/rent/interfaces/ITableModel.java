package rent.interfaces;

import java.io.Serializable;

/**
 * @author zagart
 */
public interface ITableModel<PK extends Serializable> {
    PK getId();
}
