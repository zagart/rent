package rent.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Service interface.
 *
 * @author zagart
 */
public interface IService<T extends IEntity, PK extends Serializable, DAO extends IDao> {
    int batchSave(final List<T> pEntities);

    void delete(final PK pId);

    void delete(final T pEntity);

    int executeQuery(final String pHql, final Map<String, Object> pParameters);

    List<T> getAll();

    T getById(final PK pId);

    List<T> getListByQuery(final String pHql);

    Set<PK> getPkSetByQuery(final String pHql);

    PK save(final T pEntity);

    void update(final T pEntity);
}
