package redchuk.project.universitydepartment.dto.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UniversityGroupResponseDTO {
    private Long id;
    private String name;
    private String specialityName;
}
