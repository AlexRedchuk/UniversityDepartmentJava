package redchuk.project.universitydepartment.stubs;

import redchuk.project.universitydepartment.dto.subjectSummary.SubjectSummaryRequestDTO;
import redchuk.project.universitydepartment.dto.subjectSummary.SubjectSummaryResponseDTO;
import redchuk.project.universitydepartment.entity.Speciality;
import redchuk.project.universitydepartment.entity.Subject;
import redchuk.project.universitydepartment.entity.SubjectSummary;

public final class SubjectSummaryStub {
    public static final Long ID = 1L;
    public static SubjectSummary getRandomSubjectSummary() {
        return SubjectSummary
                .builder()
                .id(ID)
                .mark(100)
                .semester(2)
                .student(StudentStub.getRandomStudent())
                .subject(SubjectStub.getRandomSubject())
                .tutor(TutorStub.getRandomTutor())
                .year(2020)
                .build();

    }
    public static SubjectSummaryRequestDTO getSubjectSummaryRequestDTO () {
        return SubjectSummaryRequestDTO
                .builder()
                .id(ID)
                .mark(100)
                .semester(2)
                .studentId(StudentStub.getRandomStudent().getId())
                .subjectId(SubjectStub.getRandomSubject().getId())
                .tutorId(TutorStub.getRandomTutor().getId())
                .year(2020)
                .build();
    }

    public static SubjectSummaryResponseDTO getSubjectSummaryResponseDTO () {
        return SubjectSummaryResponseDTO
                .builder()
                .id(ID)
                .mark(100)
                .semester(2)
                .studentFullName(StudentStub.getRandomStudent().getFullName())
                .subjectName(SubjectStub.getRandomSubject().getName())
                .tutorFullName(TutorStub.getRandomTutor().getFullName())
                .year(2020)
                .build();
    }
}
