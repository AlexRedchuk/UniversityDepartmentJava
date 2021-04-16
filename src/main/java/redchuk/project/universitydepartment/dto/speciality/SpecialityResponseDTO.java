package redchuk.project.universitydepartment.dto.speciality;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityResponseDTO {
    private Long id;
    private Integer code;
    private String name;
    private Integer yearOfAdding;
    private Integer numberOfCredits;
}
