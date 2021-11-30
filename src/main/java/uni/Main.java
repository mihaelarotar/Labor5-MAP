package uni;

import uni.controller.CourseController;
import uni.controller.RegistrationSystem;
import uni.controller.StudentController;
import uni.controller.TeacherController;
import uni.entities.Course;
import uni.entities.Student;
import uni.entities.Teacher;
import uni.repository.CourseFileRepository;
import uni.repository.StudentFileRepository;
import uni.repository.TeacherFileRepository;
import uni.view.ConsoleMenu;


public class Main {

    public static void main(String[] args) throws Exception {
        StudentFileRepository studentFileRepository = new StudentFileRepository();
        StudentController studentController = new StudentController(studentFileRepository);
        for (Student myStudent : studentController.getAll()) {
            System.out.println(myStudent);
       }

        System.out.println("-------------------------------");

        TeacherFileRepository teacherFileRepository = new TeacherFileRepository();
        TeacherController teacherController = new TeacherController(teacherFileRepository);
        for (Teacher teacher : teacherController.getAll()) {
            System.out.println(teacher);
        }

        System.out.println("-------------------------------");

        CourseFileRepository courseFileRepository = new CourseFileRepository();
        CourseController courseController = new CourseController(courseFileRepository);
        for (Course course : courseController.getAll()) {
            System.out.println(course);
        }

        RegistrationSystem registrationSystem = new RegistrationSystem(studentController, courseController, teacherController);
        ConsoleMenu consoleMenu = new ConsoleMenu(registrationSystem);
        consoleMenu.startConsole();


//        StudentRepository studentRepository = new StudentRepository();
//        CourseRepository courseRepository = new CourseRepository();
//        TeacherRepository teacherRepository = new TeacherRepository();
//        RegistrationSystem myRegistrationSystem = new RegistrationSystem(studentRepository, teacherRepository, courseRepository);
//        try {
//            Student student = new Student("Vlad", "Popa", 2);
//            studentRepository.save(student);
//
//            Student student1 = new Student("Vlad", "Apopei", 3);
//            studentRepository.save(student1);
//            Teacher teacher = new Teacher("John", "Smith", 3);
//            Course databases = new Course("DB", teacher,30,4);
//            System.out.println(teacher);
//
//            courseRepository.save(databases);
//            System.out.println(myRegistrationSystem.register(databases, student));
//            System.out.println(databases);
//
//            System.out.println(teacher);
//        } catch (InvalidDataException exception) {
//            System.out.println(exception.getMessage());
//        }
//        CourseController courseController = new CourseController(courseRepository);
//
//        System.out.println("-----------------------");
//        for (Course course : courseController.getAll()) {
//            System.out.println(course);
//        }
//        //courseController.deleteByName("DB");
//        System.out.println("-----------------------");
//        for (Course course : courseController.getAll()) {
//            System.out.println(course);
//        }
//
//        for (Course course : courseController.filterByCredits(5)) {
//            System.out.println(course);
//        }
//        System.out.println("-----------------------");
//
//        StudentController studentController = new StudentController(studentRepository);
//        for (Student myStudent : studentController.getAll()) {
//            System.out.println(myStudent);
//        }
//        studentController.sortByName();
//        for (Student myStudent : studentController.getAll()) {
//            System.out.println(myStudent);
//        }
    }
}
