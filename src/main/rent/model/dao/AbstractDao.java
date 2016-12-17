package rent.model.dao;

import org.hibernate.query.Query;
import rent.interfaces.IDao;
import rent.interfaces.IEntity;
import rent.application.utils.ReflectionUtil;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static rent.application.utils.HibernateUtil.getCurrentSession;

/**
 * Abstract Hibernate DAO class implementation.
 *
 * @param <T>
 * @param <PK>
 */
@SuppressWarnings("unchecked")
public abstract class AbstractDao<T extends IEntity, PK extends Serializable> implements IDao<T, PK> {
    private final T mEntity = (T) ReflectionUtil.getGenericObject(this, 0);
    private String TABLE_NAME = mEntity.getClass().getSimpleName();

    @Override
    public void delete(final PK pId) {
        T entity = (T) getCurrentSession().load(mEntity.getClass(), pId);
        getCurrentSession().delete(entity);
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
        String hql = String.format("select target from %s target", TABLE_NAME);
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public T getById(final PK pId) {
        String hql = String.format("select target from %s target where id = :targetId", TABLE_NAME);
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