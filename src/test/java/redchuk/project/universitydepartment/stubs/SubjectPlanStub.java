package redchuk.project.universitydepartment.stubs;

import redchuk.project.universitydepartment.dto.subjectPlan.SubjectPlanRequestDTO;
import redchuk.project.universitydepartment.entity.SubjectPlan;

public final class SubjectPlanStub {
    public static final Long ID = 1L;
    public static SubjectPlan getRandomSubjectPlan() {
        return SubjectPlan
                .builder()
                .id(ID)
                .group(GroupStub.getRandomGroup())
                .tutor(TutorStub.getRandomTutor())
                .type("Test")
                .year(2020)
                .build();
    }

    public static SubjectPlanRequestDTO getSubjectPlanRequestDTO () {
        return SubjectPlanRequestDTO
                .builder()
                .id(ID)
                .groupId(GroupStub.getRandomGroup().getId())
                .tutorId(TutorStub.getRandomTutor().getId())
                .type("Test")
                .year(2020)
                .build();
    }
}
