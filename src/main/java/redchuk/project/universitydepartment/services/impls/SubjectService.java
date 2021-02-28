package redchuk.project.universitydepartment.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.entity.Subject;
import redchuk.project.universitydepartment.repositories.SubjectRepository;
import redchuk.project.universitydepartment.services.interfaces.ISubjectService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubjectService implements ISubjectService {

    private final SubjectRepository repo;

    @Override
    public Subject save(Subject subject) {
        return repo.save(subject);
    }

    @Override
    public Subject getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Set<Subject> getAll() {
        List<Subject> list = repo.findAll();
        return new HashSet<>(list);
    }

    @Override
    public Subject edit(Subject subject) {
        return repo.save(subject);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
