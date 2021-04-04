package redchuk.project.universitydepartment.services.interfaces;

import redchuk.project.universitydepartment.dto.subjectSummary.SubjectSummaryRequestDTO;
import redchuk.project.universitydepartment.dto.subjectSummary.SubjectSummaryResponseDTO;
import redchuk.project.universitydepartment.entity.SubjectSummary;

import java.util.Set;

public interface ISubjectSummaryService {
    SubjectSummary save(SubjectSummaryRequestDTO subjectSummary);
    SubjectSummaryResponseDTO getById(Long id);
    Set<SubjectSummaryResponseDTO> getAll(int size, int page);
    SubjectSummary edit(SubjectSummaryRequestDTO subjectSummary, Long id);
    Set<SubjectSummaryResponseDTO> getSubjectSummariesByStudent_IdAndYearAndSemester(Long studentId, int semester, int year);
    void delete(Long id);
}
