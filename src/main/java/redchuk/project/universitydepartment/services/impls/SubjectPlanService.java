package redchuk.project.universitydepartment.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.entity.SubjectPlan;
import redchuk.project.universitydepartment.repositories.SubjectPlanRepository;
import redchuk.project.universitydepartment.services.interfaces.ISubjectPlanService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubjectPlanService implements ISubjectPlanService {

    private final SubjectPlanRepository repo;

    @Override
    public SubjectPlan save(SubjectPlan subjectPlan) {
        return repo.save(subjectPlan);
    }

    @Override
    public SubjectPlan getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Set<SubjectPlan> getAll() {
        List<SubjectPlan> list = repo.findAll();
        return new HashSet<>(list);
    }

    @Override
    public SubjectPlan edit(SubjectPlan subjectPlan) {
        return repo.save(subjectPlan);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
