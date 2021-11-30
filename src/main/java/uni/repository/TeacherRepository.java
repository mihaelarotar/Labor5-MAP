package uni.repository;

import uni.entities.Teacher;


public class TeacherRepository extends InMemoryRepository<Teacher> {
    public TeacherRepository() {
        super();
    }

    /**
     * deletes the teacher with the given ID from the list
     * @param teacherID int, representing the ID of the teacher to be removed
     */
    public void deleteByID(int teacherID) {
        for (Teacher teacher : repoList)
            if (teacher.getTeacherID() == teacherID) {
                repoList.remove(teacher);
                return;
            }
    }

    /**
     * updates a teacher in list
     * @param entity entity must not be null
     * @return null - if the entity is updated,
     * otherwise returns the entity (if the entity with the given ID does not exist)
     */
    @Override
    public Teacher update(Teacher entity) {
        for (Teacher teacher : repoList)
            if(teacher.equals(entity)) {
                teacher.setLastName(entity.getLastName());
                teacher.setFirstName(entity.getFirstName());
                teacher.setCourses(entity.getCourses());
                return null;
            }
        return entity;
    }
}
