package redchuk.project.universitydepartment.services.interfaces;

import redchuk.project.universitydepartment.dto.subject.SubjectRequestDTO;
import redchuk.project.universitydepartment.dto.subject.SubjectResponseDTO;
import redchuk.project.universitydepartment.entity.Subject;

import java.util.Optional;
import java.util.Set;

public interface ISubjectService {
    Subject save(SubjectRequestDTO subject);
    SubjectResponseDTO getById(Long id);
    Set<SubjectResponseDTO> getAll(int size, int page);
    Subject edit(SubjectRequestDTO subject, Long id);
    SubjectResponseDTO getSubjectByCode(int code);
    void delete(Long id);
}
