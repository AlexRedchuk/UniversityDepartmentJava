package redchuk.project.universitydepartment.stubs;


import redchuk.project.universitydepartment.dto.tutor.TutorRequestDTO;
import redchuk.project.universitydepartment.dto.tutor.TutorResponseDTO;
import redchuk.project.universitydepartment.entity.Tutor;

import java.math.BigDecimal;
import java.util.Date;

public final class TutorStub {
    public static final Long ID = 1L;
    public static Tutor getRandomTutor() {
        return Tutor
                .builder()
                .id(ID)
                .tabNumber(12342)
                .fullName("Valeriy Georgiyevich Prohorov")
                .dateOfBirth(new Date())
                .degree("Professor")
                .position("Tutor")
                .salary(BigDecimal.valueOf(10000))
                .build();
    }
    public static TutorRequestDTO getTutorRequestDTO() {
        return TutorRequestDTO
                .builder()
                .id(ID)
                .tabNumber(12342)
                .fullName("Valeriy Georgiyevich Prohorov")
                .dateOfBirth(new Date())
                .degree("Professor")
                .position("Tutor")
                .salary(BigDecimal.valueOf(10000))
                .build();
    }
}
