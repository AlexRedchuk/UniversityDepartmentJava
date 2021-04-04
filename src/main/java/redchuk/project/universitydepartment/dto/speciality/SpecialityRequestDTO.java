package redchuk.project.universitydepartment.dto.speciality;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityRequestDTO {
    private Long id;
    private int code;
    private String name;
    private int yearOfAdding;
    private int numberOfCredits;
}