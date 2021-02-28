package redchuk.project.universitydepartment.services.interfaces;

import redchuk.project.universitydepartment.entity.Groups;

import java.util.Set;

public interface IGroupsService {
    Groups save(Groups group);
    Groups getById(Long id);
    Set<Groups> getAll();
    Groups edit(Groups group);
    void delete(Long id);
}
