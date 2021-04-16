package redchuk.project.universitydepartment.dto.subjectSummary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectSummaryResponseDTO {
    private Long id;
    private String studentFullName;
    private Integer mark;
    private String subjectName;
    private String tutorFullName;
    private Integer semester;
    private Integer year;
}
