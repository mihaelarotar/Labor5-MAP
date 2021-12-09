package uni.repository;

import uni.entities.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentJdbcRepository extends JdbcRepository<Student> {
    /**
     * @return all entities
     */
    @Override
    public List<Student> getAll() throws SQLException {
        return null;
    }

    /**
     * returns the student with the given ID
     * @param studentID long int, representing the ID of the student to be returned
     * @return the student with the given ID
     */
    public Student findByID (long studentID) {
        return null;
    }

    /**
     * saves given entity
     *
     * @param entity entity must be not null
     * @return the entity - if the given entity was created successfully, otherwise returns null (if the entity already exists)
     */
    @Override
    public Student save(Student entity) {
        return null;
    }

    /**
     * removes the entity with the specified id
     *
     * @param entity entity must not be null
     * @return the removed entity or null if there is no such entity
     */
    @Override
    public Student delete(Student entity) {
        return null;
    }

    /**
     * deletes the student with the given ID from the list
     * @param studentID long, representing the ID of the student to be removed
     */
    public void deleteByID(long studentID) {
    }

    /**
     * updates given entity
     *
     * @param entity entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity (if this entity does not exist)
     */
    @Override
    public Student update(Student entity) {
        return null;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }
}
