package uni.repository;

import uni.entities.Student;
import uni.exceptions.ExceededValueException;


public class StudentRepository extends InMemoryRepository<Student> {
    public StudentRepository() {
        super();
    }

    /**
     * deletes the student with the given ID from the list
     * @param studentID long, representing the ID of the student to be removed
     */
    public void deleteByID(long studentID) {
        for (Student student : repoList)
            if (student.getStudentID() == studentID) {
                repoList.remove(student);
                return;
            }
    }

    /**
     * updates a student in list
     * @param entity entity must not be null
     * @return null - if the entity is updated,
     * otherwise returns the entity (if the entity with the given ID does not exist)
     */
    @Override
    public Student update(Student entity) {
        for (Student student : repoList)
            if(student.getStudentID() == entity.getStudentID()) {
                student.setLastName(entity.getLastName());
                student.setFirstName(entity.getFirstName());
                try {
                    student.setTotalCredits(entity.getTotalCredits());
                } catch (ExceededValueException exception) {
                    System.out.println(exception.getMessage());
                    return entity;
                }
                student.setEnrolledCourses(entity.getEnrolledCourses());
                return null;
            }
        return entity;
    }
}
