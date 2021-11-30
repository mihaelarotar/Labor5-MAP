package uni.repository;

import java.util.List;

/**
 * CRUD operations repository interface
 */
public interface ICrudRepository<E> {
    /**
     * searches for the index of the entity in the list
     * @param entity -the entity to be searched
     * @return the index of the given entity or -1 if there is no such entity
     */
    int findIndex(E entity);

    /**
     * @return all entities
     */
    List<E> getAll();

    /**
     * saves given entity
     * @param entity entity must be not null
     * @return the entity - if the given entity was created successfully, otherwise returns null (if the entity already exists)
     */
    E save(E entity);

    /**
     * removes the entity with the specified id
     * @param entity entity must not be null
     * @return the removed entity or null if there is no such entity
     *
     */
    E delete(E entity);

    /**
     * updates given entity
     * @param entity entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity (if this entity does not exist)
     */
    E update(E entity);

}
