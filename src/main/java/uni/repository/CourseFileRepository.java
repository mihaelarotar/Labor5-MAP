package uni.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import uni.entities.Course;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseFileRepository extends CourseRepository implements FileRepository<Course> {

    private static final String PATHNAME = "courses.json";

    public CourseFileRepository() {
        super();
        readFromFile();
    }


    /**
     * deletes the course with the given name from the list, as well from the teacher's list of courses
     * and the students' lists of enrolled courses
     *
     * @param name string, representing the name of the course to be deleted
     */
    @Override
    public void deleteByName(String name) {
        super.deleteByName(name);
        writeToFile();
    }

    /**
     * adds a new course to the list of courses, as well to the teacher's list of courses
     *
     * @param entity entity must be not null
     * @return the entity - if the given entity was created successfully, otherwise returns null (if the entity already exists)
     */
    @Override
    public Course save(Course entity) {
        Course savedCourse = super.save(entity);
        if (savedCourse != null) {
            writeToFile();
        }
        return savedCourse;
    }

    /**
     * deletes the course with the given name from the list, as well from the teacher's list of courses
     * and the students' lists of enrolled courses
     *
     * @param course must not be null
     * @return the removed entity or null if there is no such entity
     */
    @Override
    public Course delete(Course course) {
        Course deletedCourse = super.delete(course);
        if (deletedCourse != null) {
            writeToFile();
        }
        return deletedCourse;
    }

    /**
     * updates given course in list
     *
     * @param entity entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity (if this entity does not exist)
     */
    @Override
    public Course update(Course entity) {
        Course updatedCourse = super.update(entity);
        if (updatedCourse == null) {
            writeToFile();
        }
        return updatedCourse;
    }

    /**
     * reads data from file
     */
    @Override
    public void readFromFile() {
        File file = new File(PATHNAME);

        if(!file.exists()) {
            /*Teacher teacher = new Teacher("Ana", "Pop", 1);
            Teacher teacher1 = new Teacher("Jane", "Smith",2);*/
            Course databases = new Course("DB", 1, 80, 4);
            Course oop = new Course("OOP", 2, 100, 6);
            Course map = new Course("MAP", 2, 50, 6);
            Course algebra = new Course("Algebra", 1, 60,5);

            repoList.add(databases);
            repoList.add(oop);
            repoList.add(map);
            repoList.add(algebra);

            writeToFile();

        } else {
            ObjectMapper mapper = new ObjectMapper();

            try {
                List<Course> courses = new ArrayList<>(Arrays.asList(mapper.readValue(new File(PATHNAME), Course[].class)));
                repoList.addAll(courses);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * writes data to file
     */
    @Override
    public void writeToFile() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(new File(PATHNAME), getAll());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
