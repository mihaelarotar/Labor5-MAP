package uni.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uni.entities.Course;
import uni.repository.CourseRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseControllerTest {

    private CourseController courseController;

    @BeforeEach
    void setup() {
        CourseRepository courseRepository = new CourseRepository();
        courseController = new CourseController(courseRepository);
        /*Teacher teacher = new Teacher("Ana", "Pop", 1);
        Teacher teacher1 = new Teacher("Jane", "Smith",2);*/
        Course databases = new Course("DB", 1, 80, 4);
        Course oop = new Course("OOP", 2, 100, 6);
        Course map = new Course("MAP", 2, 50, 6);
        Course algebra = new Course("Algebra", 1, 60,5);

        courseController.add(databases);
        courseController.add(oop);
        courseController.add(map);
        courseController.add(algebra);
    }

    @Test
    void filterByCredits() {
        List<Course> filteredCourses = courseController.filterByCredits(6);
        assertEquals(filteredCourses.size(), 2);
        assertEquals(filteredCourses.get(0).getName(), "OOP");
        assertEquals(filteredCourses.get(1).getName(), "MAP");

    }

    @Test
    void sortByName() {
        courseController.sortByName();
        assertEquals(courseController.getAll().get(0).getName(), "Algebra");
    }

    @Test
    void sortByCredits() {
        courseController.sortByCredits();
        assertEquals(courseController.getAll().get(0).getName(), "DB");
        assertEquals(courseController.getAll().get(1).getName(), "Algebra");
    }
}