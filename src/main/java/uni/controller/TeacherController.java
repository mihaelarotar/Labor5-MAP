package uni.controller;

import uni.entities.Teacher;
import uni.repository.InMemoryRepository;
import uni.repository.TeacherRepository;

public class TeacherController extends Controller<Teacher> {
    public TeacherController(InMemoryRepository<Teacher> repository) {
        super(repository);
    }

    /**
     * deletes the teacher with the given ID from the list
     * @param teacherID int, representing the ID of the teacher to be removed
     */
    public void deleteByID(int teacherID) {
        TeacherRepository teacherRepository = (TeacherRepository) repository;
        teacherRepository.deleteByID(teacherID);
    }
}
