package redchuk.project.universitydepartment.services.interfaces;

import redchuk.project.universitydepartment.dto.group.UniversityGroupRequestDTO;
import redchuk.project.universitydepartment.dto.group.UniversityGroupResponseDTO;
import redchuk.project.universitydepartment.entity.UniversityGroup;

import java.util.Set;

public interface IUniversityGroupsService {
    UniversityGroup save(UniversityGroupRequestDTO group);
    UniversityGroupResponseDTO getById(Long id);
    Set<UniversityGroupResponseDTO> getAll(int size, int page);
    UniversityGroup edit(UniversityGroupRequestDTO group, Long id);
    UniversityGroupResponseDTO getGroupByName(String name);
    void delete(Long id);
}
