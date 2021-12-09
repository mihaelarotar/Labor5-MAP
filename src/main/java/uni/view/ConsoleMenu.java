package uni.view;

import uni.controller.RegistrationSystem;
import uni.entities.Course;
import uni.entities.Student;
import uni.entities.Teacher;
import uni.exceptions.NonExistingDataException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final RegistrationSystem control;

    public ConsoleMenu(RegistrationSystem control) {

        this.control = control;
    }

    /**
     * prints the menu with all the possible options for the user
     */
    public void showMenu() {
        System.out.println("""
                    What would you like to do?
                    1. add student
                    2. add teacher
                    3. add course
                    4. delete student
                    5. delete teacher
                    6. delete course
                    7. update student
                    8. update teacher
                    9. update course
                    10. register
                    11. retrieve courses with free places
                    12. retrieve students enrolled for a course
                    13. get all courses
                    14. sort students by name
                    15. sort students by credits descending
                    16. filter students by credits
                    17. filter courses by credits
                    18. sort courses by credits
                    19. sort courses by name
                    20. get all students
                    21. exit""");
    }

    /**
     * switches through all the possible options
     */
    public void startConsole() throws SQLException {
        showMenu();
        while(true) {
            Scanner myInput = new Scanner(System.in);
            int option = myInput.nextInt();

            switch (option) {
                case 0:
                    showMenu();
                    break;
                case 1:
                    Student student = createStudent();
                    control.getStudentController().add(student);
                    break;
                case 2:
                    Teacher teacher = createTeacher();
                    control.getTeacherController().add(teacher);
                    break;
                case 3:
                    Course course = createCourse();
                    control.addCourse(course);
                    break;
                case 4:
                    myInput.nextLine();
                    System.out.println("Give student ID: ");
                    long id = myInput.nextInt();
                    control.getStudentController().deleteByID(id);
                    break;
                case 5:
                    myInput.nextLine();
                    System.out.println("Give teacher ID: ");
                    int idTeacher = myInput.nextInt();
                    control.getTeacherController().deleteByID(idTeacher);
                    break;
                case 6:
                    myInput.nextLine();
                    System.out.println("Give name: ");
                    String name = myInput.nextLine();
                    control.deleteCourse(name);
                    break;
                case 7:
                    control.getStudentController().update(createStudent());
                    break;
                case 8:
                    control.getTeacherController().update(createTeacher());
                    break;
                case 9:
                    control.getCourseController().update(createCourse());
                    break;
                case 10:
                    myInput.nextLine();
                    System.out.println("Give course name: ");
                    String courseName = myInput.nextLine();
                    myInput.nextLine();
                    System.out.println("Give student ID: ");
                    int studentID = myInput.nextInt();
                    try {
                        control.register(courseName, studentID);
                    } catch (NonExistingDataException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 11:
                    printCourses(control.retrieveCoursesWithFreePlaces());
                    break;
                case 12:
                    printStudents(control.retrieveStudentsEnrolledForACourse(createCourse()));
                    break;
                case 13:
                    printCourses(control.getAllCourses());
                    break;
                case 14:
                    control.getStudentController().sortByName();
                    printStudents(control.getStudentController().getAll());
                    break;
                case 15:
                    control.getStudentController().sortByCreditsDescending();
                    printStudents(control.getStudentController().getAll());
                    break;
                case 16:
                    myInput.nextLine();
                    System.out.println("Give number of total credits: ");
                    int totalCredits = myInput.nextInt();
                    printStudents(control.getStudentController().filterByTotalCredits(totalCredits));
                    break;
                case 17:
                    myInput.nextLine();
                    System.out.println("Give number of credits: ");
                    int credits = myInput.nextInt();
                    printCourses(control.getCourseController().filterByCredits(credits));
                    break;
                case 18:
                    control.getCourseController().sortByCredits();
                    printCourses(control.getCourseController().getAll());
                    break;
                case 19:
                    control.getCourseController().sortByName();
                    printCourses(control.getCourseController().getAll());
                    break;
                case 20:
                    printStudents(control.getStudentController().getAll());
                    break;
                case 21:
                    return;
                default:
                    System.out.println("Option not valid");
                    startConsole();
            }
        }
    }

    /**
     * creates a teacher with the attributes given by user
     * @return a new teacher with the given parameter
     */
    public Teacher createTeacher() {
        Scanner in = new Scanner(System.in);
        System.out.println("Teacher first name: ");
        String firstName = in.nextLine();
        System.out.println("Teacher last name");
        String lastName = in.nextLine();
        System.out.println("Teacher ID: ");
        int teacherID = in.nextInt();
        return new Teacher(firstName, lastName, teacherID);
    }


    /**
     * creates a course with the attributes given by user
     * @return a new course with the given parameter
     */
    public Course createCourse() {
        Scanner in = new Scanner(System.in);
        System.out.println("Give course title: ");
        String name = in.nextLine();
        System.out.println("Give teacher ID: ");
        int teacherID = in.nextInt();
        System.out.println("Give max number of enrolled students: ");
        int maxEnrollment = in.nextInt();
        System.out.println("Give number of credits: ");
        int credits = in.nextInt();
        return new Course(name, teacherID, maxEnrollment, credits);
    }

    /**
     * creates a student with the attributes given by user
     * @return a new student with the given parameter
     */
    public Student createStudent() {
        Scanner in = new Scanner(System.in);
        System.out.println("Give first name: ");
        String firstName = in.nextLine();
        System.out.println("Give last name: ");
        String lastName = in.nextLine();
        System.out.println("Give ID: ");
        int studentID = in.nextInt();
        return new Student(firstName, lastName, studentID);

    }

    /**
     * shows all the courses in the list given as parameter
     * @param courses list of courses to be shown
     */
    public void printCourses(List<Course> courses) {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    /**
     * shows all the students in the list given as parameter
     * @param students list of students to be shown
     */
    public void printStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
