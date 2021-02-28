package redchuk.project.universitydepartment.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.entity.Speciality;
import redchuk.project.universitydepartment.repositories.SpecialityRepository;
import redchuk.project.universitydepartment.services.interfaces.ISpecialityService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SpecialityService implements ISpecialityService {

    private final SpecialityRepository repo;

    @Override
    public Speciality save(Speciality speciality) {
        return repo.save(speciality);
    }

    @Override
    public Speciality getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Set<Speciality> getAll() {
        List<Speciality> list = repo.findAll();
        return new HashSet<>(list);
    }

    @Override
    public Speciality edit(Speciality speciality) {
        return repo.save(speciality);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
