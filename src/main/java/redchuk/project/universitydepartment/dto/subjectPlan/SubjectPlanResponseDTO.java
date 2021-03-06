package redchuk.project.universitydepartment.dto.subjectPlan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectPlanResponseDTO {
    private Long id;
    private Integer year;
    private String universityGroupName;
    private String type;
    private String tutorFullName;
    private String subjectName;
}
