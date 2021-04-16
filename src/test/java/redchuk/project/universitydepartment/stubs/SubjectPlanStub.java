package redchuk.project.universitydepartment.stubs;

import redchuk.project.universitydepartment.dto.subjectPlan.SubjectPlanRequestDTO;
import redchuk.project.universitydepartment.dto.subjectPlan.SubjectPlanResponseDTO;
import redchuk.project.universitydepartment.entity.SubjectPlan;

public final class SubjectPlanStub {
    public static final Long ID = 1L;
    public static SubjectPlan getRandomSubjectPlan() {
        return SubjectPlan
                .builder()
                .id(ID)
                .universityGroup(GroupStub.getRandomGroup())
                .subject(SubjectStub.getRandomSubject())
                .tutor(TutorStub.getRandomTutor())
                .type("Test")
                .year(2020)
                .build();
    }

    public static SubjectPlanRequestDTO getSubjectPlanRequestDTO () {
        return SubjectPlanRequestDTO
                .builder()
                .id(ID)
                .universityGroupId(GroupStub.getRandomGroup().getId())
                .tutorId(TutorStub.getRandomTutor().getId())
                .subjectId(SubjectStub.getRandomSubject().getId())
                .type("Test")
                .year(2020)
                .build();
    }

    public static SubjectPlanResponseDTO getSubjectPlanResponseDTO () {
        return SubjectPlanResponseDTO
                .builder()
                .id(ID)
                .universityGroupName(GroupStub.getRandomGroup().getName())
                .tutorFullName(TutorStub.getRandomTutor().getFullName())
                .subjectName(SubjectStub.getRandomSubject().getName())
                .type("Test")
                .year(2020)
                .build();
    }
}
