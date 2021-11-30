package uni.controller;

import uni.entities.Course;
import uni.repository.CourseRepository;

import java.util.Comparator;
import java.util.List;


public class CourseController extends Controller<Course> {

    public CourseController(CourseRepository courseRepository) {
        super(courseRepository);
    }

    /**
     * deletes the course with the given name from the list, as well from the teacher's list of courses
     * and the students' lists of enrolled courses
     * @param name string, representing the name of the course to be deleted
     */
    public void deleteByName(String name) {
        CourseRepository courseRepository = (CourseRepository) repository;
        courseRepository.deleteByName(name);
    }

    /**
     * filters courses by the number of credits
     * @param credits the number of credits of the courses to be shown
     * @return the courses with the number of credits equal to the parameter
     */
    public List<Course> filterByCredits(int credits) {
        return filter( course -> course.getCredits() == credits);
    }

    /**
     * sorts courses alphabetically by name
     */
    public void sortByName() {
        Comparator<Course> compareByName = Comparator.comparing(Course::getName);
        sort(compareByName);
    }

    /**
     * sorts courses ascending by the number of credits
     */
    public void sortByCredits() {
        Comparator<Course> compareByCredits = Comparator.comparingInt(Course::getCredits);
        sort(compareByCredits);
    }
}
