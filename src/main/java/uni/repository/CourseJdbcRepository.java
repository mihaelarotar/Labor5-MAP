package uni.repository;

import uni.entities.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CourseJdbcRepository extends JdbcRepository<Course> {


    private Course mapRow(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setName(resultSet.getString("name"));
        course.setTeacherID(resultSet.getInt("teacherId"));
        course.setMaxEnrollment(resultSet.getInt("maxEnrollment"));
        course.setCredits(resultSet.getInt("credits"));
        // todo: course.setStudentsEnrolled(getRows(""));
        return course;
    }

    /**
     * returns the course with the given name
     * @param courseName string, representing the title of the course to be returned
     * @return the course with the given name
     */
    public Course findByName(String courseName) throws SQLException {
        String sql = "select * from Course where name=" + courseName;
        return getEntity(sql, this::mapRow);
    }

    /**
     * @return all entities
     */
    @Override
    public List<Course> getAll() throws SQLException {
        String sql = "select * from Course";
        return getRows(sql, this::mapRow);
    }

    /**
     * saves given entity
     *
     * @param entity entity must be not null
     * @return the entity - if the given entity was created successfully, otherwise returns null (if the entity already exists)
     */
    @Override
    public Course save(Course entity) {
        String sql = String.format("insert into Course values(%s, %d, %d, %d)", entity.getName(), entity.getTeacherID(), entity.getMaxEnrollment(), entity.getCredits());
        try {
            updateTable(sql);
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
        return entity;
    }

    /**
     * removes the entity with the specified id
     * @param entity entity must not be null
     * @return the removed entity or null if there is no such entity
     */
    @Override
    public Course delete(Course entity) {
        String sql = "delete from Course where name=" + entity.getName();
        try {
            updateTable(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return entity;
    }

    /**
     * deletes the course with the given name from the list, as well from the teacher's list of courses
     * and the students' lists of enrolled courses
     * @param name string, representing the name of the course to be deleted
     */
    public void deleteByName(String name) {
        String sql = "delete from Course where name=" + name;
        try {
            updateTable(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * updates given entity
     * @param entity entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity (if this entity does not exist)
     */
    @Override
    public Course update(Course entity) {
        String sql = String.format("update Course " +
                "set teacherId=%d, maxEnrollment=%d, credits=%d" +
                "where name=%s", entity.getTeacherID(), entity.getMaxEnrollment(), entity.getCredits(), entity.getName());
        try {
            updateTable(sql);
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            return entity;
        }
        return null;
    }
}
