package rent.model.services;

import org.apache.log4j.Logger;
import rent.constants.LoggerConstants;
import rent.interfaces.IDao;
import rent.interfaces.IEntity;
import rent.interfaces.IService;
import rent.application.utils.ReflectionUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static rent.application.utils.HibernateUtil.*;

/**
 * Abstract Service class implementation.
 *
 * @author zagart
 */
@SuppressWarnings("unchecked")
public class AbstractService<T extends IEntity, PK extends Serializable, DAO extends IDao>
        implements IService<T, PK, DAO> {
    final private IDao mDao = (IDao) ReflectionUtil.getGenericObject(this, 2);
    final private T mEntity = (T) ReflectionUtil.getGenericObject(this, 0);
    final private Logger mLogger = Logger.getLogger(mDao.getClass());
    private String TAG = mEntity.getClass().getSimpleName();

    @Override
    public int batchSave(final List<T> pEntities) {
        pEntities.forEach(this::save);
        return pEntities.size();
    }

    @SuppressWarnings("MalformedFormatString")
    @Override
    public void delete(final PK pId) {
        openCurrentSessionWithTransaction();
        mDao.delete(pId);
        closeCurrentSessionWithTransaction();
        mLogger.info(String.format(LoggerConstants.ENTITY_DELETED, TAG, pId));
    }

    @Override
    public void delete(final T pEntity) {
        openCurrentSessionWithTransaction();
        mDao.delete(pEntity);
        closeCurrentSessionWithTransaction();
        mLogger.info(String.format(LoggerConstants.ENTITY_DELETED_BY_ID, TAG, pEntity.getId()));
    }

    @Override
    public int executeQuery(final String pHql, final Map<String, Object> pParameters) {
        openCurrentSessionWithTransaction();
        int rowsAffected = mDao.executeQuery(pHql, pParameters);
        closeCurrentSessionWithTransaction();
        mLogger.info(String.format(LoggerConstants.QUERY_EXECUTED, TAG, rowsAffected));
        return rowsAffected;
    }

    @Override
    public List<T> getAll() {
        openCurrentSession();
        List<T> entities = mDao.getAll();
        closeCurrentSession();
        mLogger.info(String.format(LoggerConstants.ALL_ENTITIES_RETRIEVED, TAG, entities.size()));
        return entities;
    }

    @Override
    public T getById(final PK pId) {
        openCurrentSession();
        T entity = (T) mDao.getById(pId);
        closeCurrentSession();
        mLogger.info(String.format(LoggerConstants.ENTITY_RETRIEVED_BY_ID, TAG, entity.getId()));
        return entity;
    }

    @Override
    public List<T> getListByQuery(final String pHql) {
        openCurrentSession();
        List<T> entities = mDao.getListByQuery(pHql);
        closeCurrentSession();
        mLogger.info(String.format(LoggerConstants.ENTITY_LIST_BY_QUERY, TAG, entities.size()));
        return entities;
    }

    @Override
    public Set<PK> getPkSetByQuery(final String pHql) {
        openCurrentSession();
        Set<PK> keys = mDao.getPkSetByQuery(pHql);
        closeCurrentSession();
        mLogger.info(String.format(LoggerConstants.PK_SET_BY_QUERY, TAG, keys.size()));
        return keys;
    }

    @Override
    public PK save(final T pEntity) {
        openCurrentSessionWithTransaction();
        mDao.save(pEntity);
        closeCurrentSessionWithTransaction();
        mLogger.info(String.format(LoggerConstants.ENTITY_SAVED, TAG, pEntity.getId()));
        return (PK) pEntity.getId();
    }

    @Override
    public void update(final T pEntity) {
        openCurrentSessionWithTransaction();
        mDao.update(pEntity);
        closeCurrentSessionWithTransaction();
        mLogger.info(String.format(LoggerConstants.ENTITY_UPDATED, TAG, pEntity.getId()));
    }
}
