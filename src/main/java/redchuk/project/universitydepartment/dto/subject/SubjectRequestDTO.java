package redchuk.project.universitydepartment.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectRequestDTO {
    private Long id;
    private int code;
    private String name;
}
