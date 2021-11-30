package uni.repository;

import uni.entities.Course;
import uni.entities.Student;


public class CourseRepository extends InMemoryRepository<Course> {
    public CourseRepository() {
        super();
    }

    /*public Teacher findTeacher(int teacherID) {

    }*/

    /**
     * deletes the course with the given name from the list, as well from the teacher's list of courses
     * and the students' lists of enrolled courses
     * @param name string, representing the name of the course to be deleted
     */
    public void deleteByName(String name){
        for (Course course : repoList)
            if (course.getName().equals(name)) {
                /*Teacher teacher = course.findTeacher(getTeacherID());
                if (teacher.getCourses().contains(course)) {
                    teacher.deleteCourseFromCourses(course);
                }
                course.getTeacher().deleteCourseFromCourses(course);*/
                for (Student student : course.getStudentsEnrolled()) {
                    student.removeCourseFromEnrolledCourses(course);
                }
                repoList.remove(course);
                return;
            }
    }

    /**
     * adds a new course to the list of courses, as well to the teacher's list of courses
     * @param entity entity must be not null
     * @return the entity - if the given entity was created successfully, otherwise returns null (if the entity already exists)
     */
    @Override
    public Course save(Course entity) {
        /*if (!entity.getTeacher().getCourses().contains(entity)) {
            entity.getTeacher().addCourseToCourses(entity);
        }*/
        return super.save(entity);
    }


    /**
     * deletes the course with the given name from the list, as well from the teacher's list of courses
     * and the students' lists of enrolled courses
     * @param course must not be null
     * @return the removed entity or null if there is no such entity
     */
    @Override
    public Course delete(Course course) {
        /*if (course.getTeacher().getCourses().contains(course)) {
            course.getTeacher().deleteCourseFromCourses(course);
        }*/
        for (Student student : course.getStudentsEnrolled()) {
            student.removeCourseFromEnrolledCourses(course);
        }
        return super.delete(course);
    }

    /**
     * updates given course in list
     * @param entity entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity (if this entity does not exist)
     */
    @Override
    public Course update(Course entity) {
        for (Course course : repoList)
            if (course.equals(entity)) {
                course.setTeacherID(entity.getTeacherID());
                course.setCredits(entity.getCredits());
                course.setMaxEnrollment(entity.getMaxEnrollment());
                course.setStudentsEnrolled(entity.getStudentsEnrolled());
                return null;
            }
    return entity;
    }


}
