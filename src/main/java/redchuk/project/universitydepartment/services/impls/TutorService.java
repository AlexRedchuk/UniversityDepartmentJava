package redchuk.project.universitydepartment.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.entity.Tutor;
import redchuk.project.universitydepartment.repositories.TutorRepository;
import redchuk.project.universitydepartment.services.interfaces.ITutorService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TutorService implements ITutorService {

    private final TutorRepository repo;

    @Override
    public Tutor save(Tutor tutor) {
        return repo.save(tutor);
    }

    @Override
    public Tutor getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Set<Tutor> getAll() {
        List<Tutor> list = repo.findAll();
        return new HashSet<>(list);
    }

    @Override
    public Tutor edit(Tutor tutor) {
        return repo.save(tutor);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
