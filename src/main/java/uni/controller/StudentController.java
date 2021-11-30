package uni.controller;

import uni.entities.Student;
import uni.repository.InMemoryRepository;
import uni.repository.StudentRepository;

import java.util.Comparator;
import java.util.List;

public class StudentController extends Controller<Student> {
    public StudentController(InMemoryRepository<Student> repository) {
        super(repository);
    }

    /**
     * deletes the student with the given ID from the list
     * @param studentID long, representing the ID of the student to be removed
     */
    public void deleteByID(long studentID) {
        StudentRepository studentRepository = (StudentRepository) repository;
        studentRepository.deleteByID(studentID);
    }

    /**
     * filters students by the number of total credits
     * @param totalCredits the number of total credits of the students to be shown
     * @return the students with the number of total credits equal to the parameter
     */
    public List<Student> filterByTotalCredits(int totalCredits) {
        return filter( student -> student.getTotalCredits() == totalCredits);
    }

    /**
     * sorts students alphabetically by name
     */
    public void sortByName() {
        Comparator<Student> compareByName = Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName);
        sort(compareByName);
    }

    /**
     * sorts students descending by the number of credits
     */
    public void sortByCreditsDescending() {
        Comparator<Student> compareByCredits = Comparator.comparingInt(Student::getTotalCredits);
        sort(compareByCredits.reversed());
    }
}
