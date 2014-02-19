package fsoft.fsu11.bu11.tcs.core.daos;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/17/14
 * Time: 1:37 PM
 * GenericDAO is the interface to be used by all DAO.
 */
public interface GenericDAO<E> {

    /**
     * Add the Entity passed as arg.
     * @param instance the object to persist.
     * @return instance persisted
     */
    E add(E instance);

    /**
     * Update the Entity passed as arg.
     * @param instance the object to persist.
     * @return instance persisted
     */
    E update(E instance);

    /**
     * Remove the Entity passed as arg;
     * @param entity the entity to remove
     */
    void remove(E entity);

    /**
     * Finds the item having the key passed on arg.
     * @param id the value of the key to find
     * @return the object
     */
    E findById(Integer id);

}
