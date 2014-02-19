package fsoft.fsu11.bu11.tcs.core.daos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/17/14
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class GenericJpaDAO<E> implements GenericDAO<E> {

    private static final Logger log = LoggerFactory.getLogger(GenericJpaDAO.class);

    private Class<E> entityClass;

    protected EntityManager entityManager;

    @PersistenceContext(unitName = "tcsPersistenceUnit")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        entityManager.setFlushMode(FlushModeType.COMMIT);
    }

    @SuppressWarnings("unchecked")
    public GenericJpaDAO() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }

    public void flush()  {
        entityManager.flush();
    }

    public void refresh(E entity)  {
        entityManager.refresh(entity);
    }

    @Override
    public E add(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public E update(E entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public void remove(final E entity) {
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public E findById(final Integer id) {
        return entityManager.find(entityClass, id);
    }

    /**
     * Execute the query and return the query results as a List in transaction.
     * @param query query
     * @return a list of the results
     */
    @SuppressWarnings("unchecked")
    protected List<E> executeList(Query query) {
        return query.getResultList();
    }

    /**
     * Execute a SELECT query that returns a single result in transaction.
     * @param query query
     * @return the result or null if not found
     */
    protected E executeSingle(Query query) {
        List<E> resultList = executeList(query);
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    public void clear() {
        entityManager.clear();
    }

}
