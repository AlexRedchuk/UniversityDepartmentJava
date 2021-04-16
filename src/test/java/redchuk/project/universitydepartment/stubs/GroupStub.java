package redchuk.project.universitydepartment.stubs;

import redchuk.project.universitydepartment.dto.group.UniversityGroupRequestDTO;
import redchuk.project.universitydepartment.dto.group.UniversityGroupResponseDTO;
import redchuk.project.universitydepartment.entity.UniversityGroup;

public final class GroupStub {
    public static final Long ID = 1L;
    public static UniversityGroup getRandomGroup() {
        return UniversityGroup
                .builder()
                .id(ID)
                .name("343(3)")
                .speciality(SpecialityStub.getRandomSpeciality())
                .build();

    }

    public static UniversityGroupRequestDTO getGroupRequestDTO() {
        return UniversityGroupRequestDTO
                .builder()
                .id(ID)
                .name("343(3)")
                .specialityId(SpecialityStub.getRandomSpeciality().getId())
                .build();
    }

    public static UniversityGroupResponseDTO getGroupResponseDTO() {
        return UniversityGroupResponseDTO
                .builder()
                .id(ID)
                .name("343(3)")
                .specialityName(SpecialityStub.getRandomSpeciality().getName())
                .build();
    }
}
