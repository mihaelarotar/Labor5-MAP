package uni.controller;

import uni.entities.Course;
import uni.entities.Student;
import uni.exceptions.ExceededValueException;
import uni.exceptions.NonExistingDataException;

import java.util.ArrayList;
import java.util.List;

public class RegistrationSystem {

    private final StudentController studentController;
    private final CourseController courseController;
    private final TeacherController teacherController;

    public RegistrationSystem(StudentController studentController, CourseController courseController, TeacherController teacherController) {
        this.studentController = studentController;
        this.courseController = courseController;
        this.teacherController = teacherController;
    }

    public StudentController getStudentController() {
        return studentController;
    }

    public CourseController getCourseController() {
        return courseController;
    }

    public TeacherController getTeacherController() {
        return teacherController;
    }

    /**
     * registers a student to a given course
     * @param course the course to be enrolled in
     * @param student the student to be enrolled
     * @return true if the registration was completed successfully
     * or false if the student is already registered to this course
     * or if there are no more available places for this course
     * @throws NonExistingDataException if the given course or student are not in the list
     */
    public boolean register(Course course, Student student) throws NonExistingDataException{
        int courseIndex = courseController.findIndex(course);
        int studentIndex = studentController.findIndex(student);
        if (courseIndex == -1) {
            throw new NonExistingDataException("There is no such course in the list");
        }
        if (studentIndex == -1) {
            throw new NonExistingDataException("There is no such student in the list");
        }
        Course foundCourse = courseController.getAll().get(courseIndex);
        Student foundStudent = studentController.getAll().get(studentIndex);
        if (foundCourse.getStudentsEnrolled().size() >= foundCourse.getMaxEnrollment()) {
            System.out.println("There are no more available places for this course");
            return false;
        }

        if (foundCourse.getStudentsEnrolled().contains(student) || foundStudent.getEnrolledCourses().contains(course)) {
            return false;
        }


        try {
            foundStudent.addCourseToEnrolledCourses(course);
            foundCourse.addStudentToStudentsEnrolled(student);
        } catch (ExceededValueException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

    /**
     * retrieves the courses with available places
     * @return a new list containing all the courses with available places
     */
    public List<Course> retrieveCoursesWithFreePlaces() {
        List<Course> coursesWithFreePlaces = new ArrayList<>();
        for(Course course : courseController.getAll()) {
            if (course.getMaxEnrollment() > course.getStudentsEnrolled().size()) {
                coursesWithFreePlaces.add(course);
            }
        }
        return coursesWithFreePlaces;
    }

    /**
     * retrieves all students enrolled to a given course
     * @param course the given course
     * @return a new list containing all the enrolled students
     */
    public List<Student> retrieveStudentsEnrolledForACourse(Course course) {
        List<Student> enrolledStudents = new ArrayList<>();
        for (Student student : studentController.getAll()) {
            if (student.getEnrolledCourses().contains(course))
                enrolledStudents.add(student);
        }
        return enrolledStudents;
    }

    /**
     * retrieves all courses in the list
     * @return the list with all courses
     */
    public List<Course> getAllCourses() {
        return courseController.getAll();
    }
}
