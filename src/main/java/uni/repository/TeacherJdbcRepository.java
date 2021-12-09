package uni.repository;

import uni.entities.Teacher;

import java.sql.SQLException;
import java.util.List;

public class TeacherJdbcRepository extends JdbcRepository<Teacher> {
    /**
     * removes the entity with the specified id
     *
     * @param entity entity must not be null
     * @return the removed entity or null if there is no such entity
     */
    @Override
    public Teacher delete(Teacher entity) {
        return null;
    }

    /**
     * deletes the teacher with the given ID from the list
     * @param teacherID int, representing the ID of the teacher to be removed
     */
    public void deleteByID(int teacherID) {

    }

    /**
     * returns the teacher with the given ID
     * @param teacherID int, representing the ID of the teacher to be returned
     * @return the teacher with the given ID
     */
    public Teacher findByID(int teacherID) {
        return null;
    }

    /**
     * @return all entities
     */
    @Override
    public List<Teacher> getAll() throws SQLException {
        return null;
    }

    /**
     * saves given entity
     *
     * @param entity entity must be not null
     * @return the entity - if the given entity was created successfully, otherwise returns null (if the entity already exists)
     */
    @Override
    public Teacher save(Teacher entity) {
        return null;
    }

    /**
     * updates given entity
     *
     * @param entity entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity (if this entity does not exist)
     */
    @Override
    public Teacher update(Teacher entity) {
        return null;
    }
}
