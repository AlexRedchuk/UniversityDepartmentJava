package redchuk.project.universitydepartment.stubs;

import redchuk.project.universitydepartment.dto.speciality.SpecialityRequestDTO;
import redchuk.project.universitydepartment.dto.speciality.SpecialityResponseDTO;
import redchuk.project.universitydepartment.entity.Speciality;

public final class SpecialityStub {
    public static final Long ID = 1L;
    public static Speciality getRandomSpeciality() {
        return Speciality
                .builder()
                .id(ID)
                .code(121)
                .name("Software Engeneering")
                .numberOfCredits(90)
                .yearOfAdding(2001)
                .build();
    }
    public static SpecialityRequestDTO getSpecialityRequestDTO() {
        return SpecialityRequestDTO
                .builder()
                .id(ID)
                .code(121)
                .name("Software Engeneering")
                .numberOfCredits(90)
                .yearOfAdding(2001)
                .build();
    }

    public static SpecialityResponseDTO getSpecialityResponseDTO() {
        return SpecialityResponseDTO
                .builder()
                .id(ID)
                .code(121)
                .name("Software Engeneering")
                .numberOfCredits(90)
                .yearOfAdding(2001)
                .build();
    }
}
