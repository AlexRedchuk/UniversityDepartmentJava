package redchuk.project.universitydepartment.services.interfaces;

import redchuk.project.universitydepartment.entity.Student;

import java.util.Set;

public interface IStudentService {
    Student save(Student student);
    Student getById(Long id);
    Set<Student> getAll();
    Student edit(Student student);
    void delete(Long id);
}
