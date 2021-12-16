package uni;

import uni.controller.CourseController;
import uni.controller.RegistrationSystem;
import uni.controller.StudentController;
import uni.controller.TeacherController;

import uni.entities.Course;
import uni.repository.*;
import uni.view.ConsoleMenu;

import java.sql.*;


public class Main {

    public static void main(String[] args) throws SQLException {
        CourseJdbcRepository courseRepository = new CourseJdbcRepository();
        CourseController courseController = new CourseController(courseRepository);

        StudentJdbcRepository studentRepository = new StudentJdbcRepository();
        StudentController studentController = new StudentController(studentRepository);

        TeacherJdbcRepository teacherRepository = new TeacherJdbcRepository();
        TeacherController teacherController = new TeacherController(teacherRepository);

        RegistrationSystem registrationSystem = new RegistrationSystem(studentController, courseController, teacherController);
        ConsoleMenu consoleMenu = new ConsoleMenu(registrationSystem);
        consoleMenu.startConsole();

        /*for (Course course : courseController.getAll()) {
            System.out.println(course);
        }

        System.out.println("------------");
        System.out.println(courseController.findByName("DB"));*/

        /*try {
            registrationSystem.register("DB",100);
        } catch (NonExistingDataException e) {
            e.printStackTrace();
        }

        for (Course course : courseController.getAll()) {
            System.out.println(course);
        }*/

    }
}
