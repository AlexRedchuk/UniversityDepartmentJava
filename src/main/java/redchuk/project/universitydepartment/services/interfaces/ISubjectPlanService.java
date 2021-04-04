package redchuk.project.universitydepartment.services.interfaces;

import redchuk.project.universitydepartment.dto.subjectPlan.SubjectPlanRequestDTO;
import redchuk.project.universitydepartment.dto.subjectPlan.SubjectPlanResponseDTO;
import redchuk.project.universitydepartment.entity.SubjectPlan;

import java.util.Set;

public interface ISubjectPlanService {
    SubjectPlan save(SubjectPlanRequestDTO subjectPlan);
    SubjectPlanResponseDTO getById(Long id);
    Set<SubjectPlanResponseDTO> getAll(int size, int page);
    SubjectPlan edit(SubjectPlanRequestDTO subjectPlan, Long id);
    Set<SubjectPlanResponseDTO> getSubjectPlansByYearAndGroup_Id(int year, Long groupId);
    void delete(Long id);
}
