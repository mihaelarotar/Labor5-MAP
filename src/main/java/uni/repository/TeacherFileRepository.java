package uni.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import uni.entities.Teacher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeacherFileRepository extends TeacherRepository implements FileRepository<Teacher> {

    private static final String PATHNAME = "teachers.json";

    public TeacherFileRepository() {
        super();
        readFromFile();
    }


    /**
     * reads data from file
     */
    @Override
    public void readFromFile() {
        File file = new File(PATHNAME);

        if(!file.exists()) {
            Teacher teacher = new Teacher("Ana", "Pop", 1);
            Teacher teacher1 = new Teacher("Jane", "Smith", 2);
            Teacher teacher2 = new Teacher("John", "Smith", 3);

            repoList.add(teacher);
            repoList.add(teacher1);
            repoList.add(teacher2);

            writeToFile();
        } else {
            ObjectMapper mapper = new ObjectMapper();

            try {
                List<Teacher> teachers = new ArrayList<>(Arrays.asList(mapper.readValue(new File(PATHNAME), Teacher[].class)));
                repoList.addAll(teachers);
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
    public Teacher save(Teacher entity) {
        Teacher savedTeacher = super.save(entity);
        if (savedTeacher != null) {
            writeToFile();
        }
        return savedTeacher;
    }

    /**
     * {@inheritDoc}
     *
     * @param entity
     */
    @Override
    public Teacher delete(Teacher entity) {
        Teacher deletedTeacher = super.delete(entity);
        if (deletedTeacher != null) {
            writeToFile();
        }
        return deletedTeacher;
    }

    /**
     * deletes the teacher with the given ID from the list
     *
     * @param teacherID int, representing the ID of the teacher to be removed
     */
    @Override
    public void deleteByID(int teacherID) {
        super.deleteByID(teacherID);
        writeToFile();
    }

    /**
     * updates a teacher in list
     * @param entity entity must not be null
     * @return null - if the entity is updated,
     * otherwise returns the entity (if the entity with the given ID does not exist)
     */
    @Override
    public Teacher update(Teacher entity) {
        Teacher updatedTeacher = super.update(entity);
        if (updatedTeacher == null) {
            writeToFile();
        }
        return updatedTeacher;
    }
}
