package redchuk.project.universitydepartment.stubs;

import redchuk.project.universitydepartment.dto.subject.SubjectRequestDTO;
import redchuk.project.universitydepartment.dto.subject.SubjectResponseDTO;
import redchuk.project.universitydepartment.entity.Subject;

import java.util.HashSet;

public final class SubjectStub {
    public static final Long ID = 1L;
    public static Subject getRandomSubject() {
        return Subject
                .builder()
                .id(ID)
                .code(1223)
                .name("Java Programming")
                .tutors(new HashSet<>())
                .build();
    }

    public static SubjectRequestDTO getSubjectRequestDTO() {
        return SubjectRequestDTO
                .builder()
                .id(ID)
                .code(1223)
                .name("Java Programming")
                .build();
    }

    public static SubjectResponseDTO getSubjectResponseDTO() {
        return SubjectResponseDTO
                .builder()
                .id(ID)
                .code(1223)
                .name("Java Programming")
                .build();
    }
}
