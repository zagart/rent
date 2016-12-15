package rent.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * DAO interface.
 *
 * @param <T>
 * @param <PK>
 */
public interface IDao<T extends IEntity, PK extends Serializable> {
    void delete(final PK pId);

    void delete(final T pEntity);

    int executeQuery(final String pHql, final Map<String, Object> pParameters);

    List<T> getAll();

    T getById(final PK pId);

    List<T> getListByQuery(String pHql);

    Set<PK> getPkSetByQuery(String pHql);

    PK save(final T pEntity);

    void update(final T pEntity);
}
