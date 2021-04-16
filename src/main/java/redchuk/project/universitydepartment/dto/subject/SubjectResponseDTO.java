package redchuk.project.universitydepartment.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponseDTO {
    private Long id;
    private Integer code;
    private String name;
}
