package redchuk.project.universitydepartment.services.interfaces;

import redchuk.project.universitydepartment.dto.speciality.SpecialityRequestDTO;
import redchuk.project.universitydepartment.dto.speciality.SpecialityResponseDTO;
import redchuk.project.universitydepartment.entity.Speciality;

import java.util.Set;

public interface ISpecialityService {
    Speciality save(SpecialityRequestDTO speciality);
    SpecialityResponseDTO getById(Long id);
    Set<SpecialityResponseDTO> getAll(int size, int page);
    Speciality edit(SpecialityRequestDTO speciality, Long id);
    SpecialityResponseDTO  getSpecialityByCode(int code);
    void delete(Long id);
}
