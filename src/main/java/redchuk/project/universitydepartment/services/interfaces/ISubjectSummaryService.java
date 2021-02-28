package redchuk.project.universitydepartment.services.interfaces;

import redchuk.project.universitydepartment.entity.SubjectSummary;

import java.util.Set;

public interface ISubjectSummaryService {
    SubjectSummary save(SubjectSummary subjectSummary);
    SubjectSummary getById(Long id);
    Set<SubjectSummary> getAll();
    SubjectSummary edit(SubjectSummary subjectSummary);
    void delete(Long id);
}
