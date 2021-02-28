package redchuk.project.universitydepartment.services.interfaces;

import redchuk.project.universitydepartment.entity.Tutor;

import java.util.Set;

public interface ITutorService {
    Tutor save(Tutor tutor);
    Tutor getById(Long id);
    Set<Tutor> getAll();
    Tutor edit(Tutor tutor);
    void delete(Long id);
}
