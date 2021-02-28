package redchuk.project.universitydepartment.services.interfaces;

import redchuk.project.universitydepartment.entity.SubjectPlan;

import java.util.Set;

public interface ISubjectPlanService {
    SubjectPlan save(SubjectPlan subjectPlan);
    SubjectPlan getById(Long id);
    Set<SubjectPlan> getAll();
    SubjectPlan edit(SubjectPlan subjectPlan);
    void delete(Long id);
}
