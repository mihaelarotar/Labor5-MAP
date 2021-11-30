package uni.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import uni.entities.Student;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentFileRepository extends StudentRepository implements FileRepository<Student> {

    private static final String PATHNAME = "students.json";

    public StudentFileRepository() {
        super();
        readFromFile();
    }


    /**
     * reads data from file
     */
    @Override
    public void readFromFile() {
        File file = new File(PATHNAME);

        if (!file.exists()) {
            Student student = new Student("Maria", "Pop", 100);
            Student student1 = new Student("Vlad", "Popa", 101);
            Student student2 = new Student("Marian", "Popa", 102);
            Student student3 = new Student("Vlad", "Apopei", 104);
            Student student4 = new Student("Jane", "Doe", 105);

            repoList.add(student);
            repoList.add(student1);
            repoList.add(student2);
            repoList.add(student3);
            repoList.add(student4);

            writeToFile();

        } else {
            ObjectMapper mapper = new ObjectMapper();

            try {
                List<Student> students = new ArrayList<>(Arrays.asList(mapper.readValue(new File(PATHNAME), Student[].class)));
                repoList.addAll(students);
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


    /**
     * {@inheritDoc}
     *
     * @param entity
     */
    @Override
    public Student save(Student entity) {
        Student savedStudent = super.save(entity);
        if (savedStudent != null) {
            writeToFile();
        }
        return savedStudent;
    }

    /**
     * {@inheritDoc}
     *
     * @param entity
     */
    @Override
    public Student delete(Student entity) {
        Student deletedStudent = super.delete(entity);
        if (deletedStudent != null) {
            writeToFile();
        }
        return deletedStudent;
    }

    /**
     * deletes the student with the given ID from the list
     *
     * @param studentID long, representing the ID of the student to be removed
     */
    @Override
    public void deleteByID(long studentID) {
        super.deleteByID(studentID);
        writeToFile();
    }

    /**
     * updates a student in list
     * @param entity entity must not be null
     * @return null - if the entity is updated,
     * otherwise returns the entity (if the entity with the given ID does not exist)
     */
    @Override
    public Student update(Student entity) {
        Student updatedStudent = super.update(entity);
        if (updatedStudent == null) {
            writeToFile();
        }
        return updatedStudent;
    }
}
