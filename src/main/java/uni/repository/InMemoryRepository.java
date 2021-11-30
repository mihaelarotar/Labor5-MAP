package uni.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * In Memory Repository abstract Class
 * @param <E> template entity
 */
public abstract class InMemoryRepository<E> implements ICrudRepository<E>{
    protected List<E> repoList;

    protected InMemoryRepository() {
        this.repoList = new ArrayList<>();
    }

    /**
     * searches for the index of the entity in the list
     * {@inheritDoc}
     */
    @Override
    public int findIndex(E entity) {
        for (E object : repoList) {
            if (entity.equals(object))
                return repoList.indexOf(object);
        }
        return -1;
    }

    /**
     * @return all entities
     */
    @Override
    public List<E> getAll() {
        return this.repoList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E save(E entity) {
        if(this.repoList.contains(entity))
            return null;
        this.repoList.add(entity);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E delete(E entity) {
        if(!this.repoList.contains(entity))
            return null;
        this.repoList.remove(entity);
        return entity;
    }
}
