package uni.repository;

import uni.entities.Student;
import uni.exceptions.ExceededValueException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    /**
     * maps each row of data in ResultSet
     *
     * @param resultSet ResultSet, the ResultSet to be mapped
     * @return the result object for the current row
     * @throws SQLException for database access errors
     */
    @Override
    public Student mapRow(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setStudentID(resultSet.getLong("studentId"));
        student.setFirstName(resultSet.getString("firstName"));
        student.setLastName(resultSet.getString("lastName"));
        try {
            student.setTotalCredits(resultSet.getInt("totalCredits"));
        } catch (ExceededValueException e) {
            e.printStackTrace();
        }
        String sql = String.format("select * from Course C inner join Enrolled E " +
                "on C.name = E.courseName " +
                "where E.studentId= '%s'",  student.getStudentID());

        student.setEnrolledCourses(new CourseJdbcRepository().getRows(sql, new CourseRowMapper()));
        return student;
    }
}
