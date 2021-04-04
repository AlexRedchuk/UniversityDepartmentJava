package redchuk.project.universitydepartment.services.interfaces;

import redchuk.project.universitydepartment.dto.tutor.TutorRequestDTO;
import redchuk.project.universitydepartment.dto.tutor.TutorResponseDTO;
import redchuk.project.universitydepartment.entity.Tutor;

import java.util.Optional;
import java.util.Set;

public interface ITutorService {
    Tutor save(TutorRequestDTO tutor);
    TutorResponseDTO getById(Long id);
    Set<TutorResponseDTO> getAll(int size, int page);
    Tutor edit(TutorRequestDTO tutor, Long id);
    Set<TutorResponseDTO> getTutorsByPosition(String position);
    void delete(Long id);
}
