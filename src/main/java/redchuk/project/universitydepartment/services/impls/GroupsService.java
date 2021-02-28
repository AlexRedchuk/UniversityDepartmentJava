package redchuk.project.universitydepartment.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.entity.Groups;
import redchuk.project.universitydepartment.repositories.GroupsRepository;
import redchuk.project.universitydepartment.services.interfaces.IGroupsService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GroupsService implements IGroupsService {

    private final GroupsRepository repo;

    @Override
    public Groups save(Groups group) {
        return repo.save(group);
    }

    @Override
    public Groups getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Set<Groups> getAll() {
        List<Groups> list = repo.findAll();
        return new HashSet<>(list);
    }

    @Override
    public Groups edit(Groups group) {
        return repo.save(group);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
