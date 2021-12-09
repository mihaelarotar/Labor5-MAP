package uni.controller;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import uni.entities.Course;
import uni.entities.Student;
import uni.entities.Teacher;
import uni.exceptions.ExceededValueException;
import uni.exceptions.NonExistingDataException;
import uni.repository.CourseJdbcRepository;
import uni.repository.StudentJdbcRepository;
import uni.repository.TeacherJdbcRepository;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationSystemTest {

    private RegistrationSystem registrationSystem;

    @BeforeEach
    void setup() throws SQLException {
        CourseJdbcRepository courseRepository = Mockito.mock(CourseJdbcRepository.class);
        StudentJdbcRepository studentRepository = Mockito.mock(StudentJdbcRepository.class);
        TeacherJdbcRepository teacherRepository = Mockito.mock(TeacherJdbcRepository.class);
        StudentController studentController = new StudentController(studentRepository);
        TeacherController teacherController = new TeacherController(teacherRepository);
        CourseController courseController = new CourseController(courseRepository);
        registrationSystem = new RegistrationSystem(studentController, courseController, teacherController);
        Teacher teacher = new Teacher("Ana", "Pop", 1);
        Teacher teacher1 = new Teacher("John", "Smith", 2);
        teacherController.add(teacher);
        teacherController.add(teacher1);

        List<Teacher> teachers = new ArrayList<>(Arrays.asList(teacher, teacher1));
        Mockito.when(teacherRepository.getAll()).thenReturn(teachers);
        Mockito.when(teacherRepository.findByID(1)).thenReturn(teacher);
        Mockito.when(teacherRepository.findByID(2)).thenReturn(teacher1);

        Student student = new Student("Vlad", "Pop", 1);
        Student student1 = new Student("Maria", "Popa", 2);
        Student student2 = new Student("Jane", "Smith", 3);
        studentController.add(student);
        studentController.add(student1);
        studentController.add(student2);
        Course databases = new Course("DB", 1, 2, 4);
        Course oop = new Course("OOP", 2, 50, 5);
        // todo mocks and tests
        registrationSystem.addCourse(databases);
        registrationSystem.addCourse(oop);

        List<Student> students = new ArrayList<>(Arrays.asList(student, student1, student2));
        Mockito.when(studentRepository.getAll()).thenReturn(students);
        Mockito.when(studentRepository.findByID(1)).thenReturn(student);
        Mockito.when(studentRepository.findByID(2)).thenReturn(student1);
        Mockito.when(studentRepository.findByID(3)).thenReturn(student2);

        List<Course> courses = new ArrayList<>(Arrays.asList(databases, oop));
        Mockito.when(courseRepository.getAll()).thenReturn(courses);
        Mockito.when(courseRepository.findByName("DB")).thenReturn(databases);
        Mockito.when(courseRepository.findByName("OOP")).thenReturn(oop);
    }


    /*@Test
    void register() throws Exception {
        Course databases = registrationSystem.getCourseController().getAll().get(0);
        Student student = registrationSystem.getStudentController().getAll().get(0);

        assertTrue(registrationSystem.register("DB", 1));
    }

    @Test
    @Description("checks if the function returns false when trying to register a student who is already registered")
    void registerAlreadyRegisteredStudent() throws Exception {
        Course databases = registrationSystem.getCourseController().getAll().get(0);
        Student student = registrationSystem.getStudentController().getAll().get(0);

        registrationSystem.register(databases.getName(), student.getStudentID());
        assertFalse(registrationSystem.register(databases.getName(), student.getStudentID()));
    }

    @Test
    @Description("checks if the function returns false when trying to register a student to a course with no more free places")
    void registerToACourseWithNoFreePlaces() throws Exception {
        Course databases = registrationSystem.getCourseController().getAll().get(0);
        Student student = registrationSystem.getStudentController().getAll().get(0);
        Student student1 = registrationSystem.getStudentController().getAll().get(1);
        Student student2 = registrationSystem.getStudentController().getAll().get(2);

        registrationSystem.register(databases.getName(), student.getStudentID());
        registrationSystem.register(databases.getName(), student1.getStudentID());
        assertFalse(registrationSystem.register(databases.getName(), student2.getStudentID()));
    }

    @Test
    @Description("check if an exception is thrown if the given course or student are not in the list")
    void registerNonExistingData() {
        Student student = new Student("Vlad", "Pop", 1111);
        Course databases = new Course("DB", 1,2,4);
        assertThrows(NonExistingDataException.class, () -> registrationSystem.register(databases.getName(), student.getStudentID()));
    }

    @Test
    @Description("checks if an exception is thrown when the total number of credits for a students exceeds 30")
    void registerExceededValue() throws Exception {
        Course databases = registrationSystem.getCourseController().getAll().get(0);
        Student student = registrationSystem.getStudentController().getAll().get(0);
        student.setTotalCredits(27);
        assertThrows(ExceededValueException.class, () -> registrationSystem.register(databases.getName(), student.getStudentID()));
    }

    @Test
    void retrieveCoursesWithFreePlaces() throws Exception {
        Course databases = registrationSystem.getCourseController().getAll().get(0);
        Course oop = registrationSystem.getCourseController().getAll().get(1);
        Student student = registrationSystem.getStudentController().getAll().get(0);
        Student student1 = registrationSystem.getStudentController().getAll().get(1);
        registrationSystem.register(databases.getName(),student.getStudentID());
        registrationSystem.register(databases.getName(),student1.getStudentID());

        assertEquals(registrationSystem.retrieveCoursesWithFreePlaces().size(),1);
        assertEquals(registrationSystem.retrieveCoursesWithFreePlaces().get(0), oop);
    }

    @Test
    void retrieveStudentsEnrolledForACourse() throws Exception {
        Course databases = registrationSystem.getCourseController().getAll().get(0);
        Course oop = registrationSystem.getCourseController().getAll().get(1);
        Student student = registrationSystem.getStudentController().getAll().get(0);
        Student student1 = registrationSystem.getStudentController().getAll().get(1);
        registrationSystem.register(databases.getName(),student.getStudentID());
        registrationSystem.register(databases.getName(),student1.getStudentID());

        assertEquals(registrationSystem.retrieveStudentsEnrolledForACourse(databases).size(),2);
        assertEquals(registrationSystem.retrieveStudentsEnrolledForACourse(oop).size(),0);
        assertEquals(registrationSystem.retrieveStudentsEnrolledForACourse(databases).get(0), student);
        // assertEquals(registrationSystem.retrieveStudentsEnrolledForACourse(databases).get(1), student1);
    }*/

    @Test
    void getAllCourses() throws Exception{
        assertEquals(registrationSystem.getAllCourses().size(), 2);
        assertEquals(registrationSystem.getAllCourses(), registrationSystem.getCourseController().getAll());
    }

    @Test
    @Description("checks if the deleted course was also removed from the teacher's list of courses")
    void deleteCourseFromTeachersList() throws Exception{
        Course databases = registrationSystem.getCourseController().getAll().get(0);
        Teacher teacher = registrationSystem.getTeacherController().getAll().get(0);
        registrationSystem.deleteCourse("DB");
        assertFalse(teacher.getCourses().contains(databases));
    }


    @Test
    @Description("checks if the added course was also added to the teacher's list of courses")
    void saveCourseInTeachersList() throws Exception{
        Course databases = registrationSystem.getCourseController().findByName("DB");
        Teacher teacher = registrationSystem.getTeacherController().findByID(1);
        assertTrue(teacher.getCourses().contains(databases));
    }
}