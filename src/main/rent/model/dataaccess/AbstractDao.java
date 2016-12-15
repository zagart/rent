package rent.model.dataaccess;

import org.hibernate.query.Query;
import rent.interfaces.IDao;
import rent.interfaces.IEntity;
import rent.utils.ReflectionUtil;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static rent.utils.HibernateUtil.getCurrentSession;

/**
 * Abstract Hibernate DAO class.
 *
 * @param <T>
 * @param <PK>
 */
@SuppressWarnings("unchecked")
public abstract class AbstractDao<T extends IEntity, PK extends Serializable> implements IDao<T, PK> {
    private final T entityObj;

    {
        entityObj = (T) ReflectionUtil.getGenericObject(this, 0);
    }

    @Override
    public void delete(final PK pId) {
        T obj = (T) getCurrentSession().load(entityObj.getClass(), pId);
        getCurrentSession().delete(obj);
    }

    @Override
    public void delete(final T pEntity) {
        getCurrentSession().delete(pEntity);
    }

    @Override
    public int executeQuery(final String pHql, final Map<String, Object> pParameters) {
        Query query = getCurrentSession().createQuery(pHql);
        query.setProperties(pParameters);
        return query.executeUpdate();
    }

    @Override
    public List<T> getAll() {
        String hql = String.format("select target from %s target", entityObj.getClass().getName());
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public T getById(final PK pId) {
        String hql = String.format("select target from %s target where id = :targetId", entityObj.getClass().getName());
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("targetId", pId);
        return (T) query.uniqueResult();
    }

    @Override
    public List<T> getListByQuery(final String pHql) {
        return (List<T>) getCurrentSession().createQuery(pHql).list();
    }

    @Override
    public Set<PK> getPkSetByQuery(final String pHql) {
        return new HashSet<>(getCurrentSession().createQuery(pHql).list());
    }

    public PK save(final T pEntity) {
        getCurrentSession().save(pEntity);
        return (PK) pEntity.getId();
    }

    @Override
    public void update(final T pEntity) {
        getCurrentSession().update(pEntity);
    }
}