package uni.repository;

import org.junit.jupiter.api.Test;
import uni.entities.Teacher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TeacherRepositoryTest {

    @Test
    void save() {
        TeacherRepository teacherRepository = new TeacherRepository();
        assertEquals(teacherRepository.getAll().size(),0);
        teacherRepository.save(new Teacher("Ana", "Pop", 1));

        Teacher teacher = new Teacher("John", "Smith", 3);
        assertEquals(teacherRepository.save(teacher), teacher);
        assertNull(teacherRepository.save(teacher));
        assertEquals(teacherRepository.getAll().size(),2);
    }


    @Test
    void delete() {
        TeacherRepository teacherRepository = new TeacherRepository();
        Teacher teacher = new Teacher("John", "Smith", 3);
        teacherRepository.save(teacher);
        Teacher teacher1 = new Teacher("Ana", "Pop", 1);
        teacherRepository.save(teacher1);
        assertEquals(teacherRepository.delete(teacher1), teacher1);
        assertNull(teacherRepository.delete(teacher1));
        assertEquals(teacherRepository.delete(teacher),teacher);
        assertEquals(teacherRepository.getAll().size(),0);
    }

    @Test
    void update() {
        TeacherRepository teacherRepository = new TeacherRepository();
        Teacher teacher = new Teacher("John", "Smith", 3);
        teacherRepository.save(teacher);
        Teacher teacher1 = new Teacher("John", "Brown", 3);
        assertNull(teacherRepository.update(teacher1));
        assertEquals(teacherRepository.getAll().get(0).getLastName(), "Brown");
    }

    @Test
    void deleteByID() {
        TeacherRepository teacherRepository = new TeacherRepository();
        teacherRepository.save(new Teacher("John", "Smith", 3));
        teacherRepository.deleteByID(3);
        assertEquals(teacherRepository.getAll().size(),0);
    }
}