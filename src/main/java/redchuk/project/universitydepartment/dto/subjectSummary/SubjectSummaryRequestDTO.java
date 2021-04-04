package redchuk.project.universitydepartment.dto.subjectSummary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectSummaryRequestDTO {
    private Long id;
    private Long studentId;
    private int mark;
    private Long subjectId;
    private Long tutorId;
    private int semester;
    private int year;
}
