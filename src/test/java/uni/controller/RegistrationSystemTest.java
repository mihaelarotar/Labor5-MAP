package uni.controller;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uni.entities.Course;
import uni.entities.Student;
import uni.entities.Teacher;
import uni.exceptions.ExceededValueException;
import uni.exceptions.NonExistingDataException;
import uni.repository.CourseRepository;
import uni.repository.StudentRepository;
import uni.repository.TeacherRepository;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationSystemTest {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private RegistrationSystem registrationSystem;

    @BeforeEach
    void setup() {
        courseRepository = new CourseRepository();
        studentRepository = new StudentRepository();
        TeacherRepository teacherRepository = new TeacherRepository();
        StudentController studentController = new StudentController(studentRepository);
        TeacherController teacherController = new TeacherController(teacherRepository);
        CourseController courseController = new CourseController(courseRepository);
        registrationSystem = new RegistrationSystem(studentController, courseController, teacherController);
        Teacher teacher = new Teacher("Ana", "Pop", 1);
        Teacher teacher1 = new Teacher("John", "Smith", 2);
        teacherRepository.save(teacher);
        teacherRepository.save(teacher1);
        Student student = new Student("Vlad", "Pop", 1);
        Student student1 = new Student("Maria", "Popa", 2);
        Student student2 = new Student("Jane", "Smith", 3);
        studentRepository.save(student);
        studentRepository.save(student1);
        studentRepository.save(student2);
        Course databases = new Course("DB", 1,2,4);
        Course oop = new Course("OOP", 2, 50, 5);
        courseRepository.save(databases);
        courseRepository.save(oop);
    }

    @Test
    void register() throws Exception {
        Course databases = courseRepository.getAll().get(0);
        Student student = studentRepository.getAll().get(0);

        assertTrue(registrationSystem.register(databases, student));
    }

    @Test
    @Description("checks if the function returns false when trying to register a student who is already registered")
    void registerAlreadyRegisteredStudent() throws Exception {
        Course databases = courseRepository.getAll().get(0);
        Student student = studentRepository.getAll().get(0);

        registrationSystem.register(databases, student);
        assertFalse(registrationSystem.register(databases, student));
    }

    @Test
    @Description("checks if the function returns false when trying to register a student to a course with no more free places")
    void registerToACourseWithNoFreePlaces() throws Exception {
        Course databases = courseRepository.getAll().get(0);
        Student student = studentRepository.getAll().get(0);
        Student student1 = studentRepository.getAll().get(1);
        Student student2 = studentRepository.getAll().get(2);

        registrationSystem.register(databases, student);
        registrationSystem.register(databases, student1);
        assertFalse(registrationSystem.register(databases, student2));
    }

    /*@Test
    @Description("check if an exception is thrown if the given course or student are not in the list")
    void registerNonExistingData() {
        Teacher teacher = new Teacher("Ana", "Pop", 1);
        Student student = new Student("Vlad", "Pop", 1);
        Course databases = new Course("DB", teacher,2,4);
        assertThrows(NonExistingDataException.class, () -> registrationSystem.register(databases, student));
    }*/

    @Test
    @Description("checks if an exception is thrown when the total number of credits for a students exceeds 30")
    void registerExceededValue() throws NonExistingDataException, ExceededValueException {
        Course databases = courseRepository.getAll().get(0);
        Student student = studentRepository.getAll().get(0);
        student.setTotalCredits(27);
        assertFalse(registrationSystem.register(databases, student));
    }

    @Test
    void retrieveCoursesWithFreePlaces() throws Exception {
        Course databases = courseRepository.getAll().get(0);
        Course oop = courseRepository.getAll().get(1);
        Student student = studentRepository.getAll().get(0);
        Student student1 = studentRepository.getAll().get(1);
        registrationSystem.register(databases,student);
        registrationSystem.register(databases,student1);

        assertEquals(registrationSystem.retrieveCoursesWithFreePlaces().size(),1);
        assertEquals(registrationSystem.retrieveCoursesWithFreePlaces().get(0), oop);
    }

    @Test
    void retrieveStudentsEnrolledForACourse() throws Exception {
        Course databases = courseRepository.getAll().get(0);
        Course oop = courseRepository.getAll().get(1);
        Student student = studentRepository.getAll().get(0);
        Student student1 = studentRepository.getAll().get(1);
        registrationSystem.register(databases,student);
        registrationSystem.register(databases,student1);

        assertEquals(registrationSystem.retrieveStudentsEnrolledForACourse(databases).size(),2);
        assertEquals(registrationSystem.retrieveStudentsEnrolledForACourse(oop).size(),0);
        assertEquals(registrationSystem.retrieveStudentsEnrolledForACourse(databases).get(0), student);
        // assertEquals(registrationSystem.retrieveStudentsEnrolledForACourse(databases).get(1), student1);
    }

    @Test
    void getAllCourses() {
        assertEquals(registrationSystem.getAllCourses().size(), 2);
        assertEquals(registrationSystem.getAllCourses(), courseRepository.getAll());
    }

}