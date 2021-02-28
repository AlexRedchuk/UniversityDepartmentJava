package redchuk.project.universitydepartment.services.interfaces;

import redchuk.project.universitydepartment.entity.Subject;

import java.util.Set;

public interface ISubjectService {
    Subject save(Subject subject);
    Subject getById(Long id);
    Set<Subject> getAll();
    Subject edit(Subject subject);
    void delete(Long id);
}
