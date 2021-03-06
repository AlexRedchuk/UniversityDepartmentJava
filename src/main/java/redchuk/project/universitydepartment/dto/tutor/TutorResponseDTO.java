package redchuk.project.universitydepartment.dto.tutor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TutorResponseDTO {
    private Long id;
    private Integer tabNumber;
    private String fullName;
    private Date dateOfBirth;
    private String degree;
    private String position;
    private BigDecimal salary;
}
