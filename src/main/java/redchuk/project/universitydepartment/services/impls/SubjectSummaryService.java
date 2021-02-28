package redchuk.project.universitydepartment.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.entity.SubjectSummary;
import redchuk.project.universitydepartment.repositories.SubjectSummaryRepository;
import redchuk.project.universitydepartment.services.interfaces.ISubjectSummaryService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubjectSummaryService implements ISubjectSummaryService {

    private final SubjectSummaryRepository repo;

    @Override
    public SubjectSummary save(SubjectSummary subjectSummary) {
        return repo.save(subjectSummary);
    }

    @Override
    public SubjectSummary getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Set<SubjectSummary> getAll() {
        List<SubjectSummary> list = repo.findAll();
        return new HashSet<>(list);
    }

    @Override
    public SubjectSummary edit(SubjectSummary subjectSummary) {
        return repo.save(subjectSummary);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
