package uni.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uni.entities.Course;
import uni.repository.CourseJdbcRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseControllerTest {

    private CourseController courseController;

    @BeforeEach
    void setup() {
        CourseJdbcRepository courseRepository = new CourseJdbcRepository();
        courseController = new CourseController(courseRepository);

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
    void filterByCredits() throws Exception {
        List<Course> filteredCourses = courseController.filterByCredits(6);
        assertEquals(filteredCourses, new ArrayList<>(Arrays.asList(courseController.findByName("OOP"), courseController.findByName("MAP"))));

    }

    @Test
    void sortByName() throws Exception{
        courseController.sortByName();
        assertEquals(courseController.getAll(), new ArrayList<>(Arrays.asList(courseController.findByName("Algebra"), courseController.findByName("DB"),
                courseController.findByName("MAP"), courseController.findByName("OOP"))));
    }

    @Test
    void sortByCredits() throws Exception{
        courseController.sortByCredits();
        assertEquals(courseController.getAll(), new ArrayList<>(Arrays.asList(courseController.findByName("DB"), courseController.findByName("Algebra"),
                courseController.findByName("OOP"), courseController.findByName("MAP"))));
    }
}