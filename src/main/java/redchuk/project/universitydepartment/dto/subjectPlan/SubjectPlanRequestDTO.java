package redchuk.project.universitydepartment.dto.subjectPlan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectPlanRequestDTO {
    private Long id;
    private int year;
    private Long groupId;
    private String type;
    private Long tutorId;
    private Long SubjectId;
}
