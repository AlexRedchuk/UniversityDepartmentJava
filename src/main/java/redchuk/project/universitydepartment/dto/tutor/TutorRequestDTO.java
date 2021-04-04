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
public class TutorRequestDTO {
    private Long id;
    private int tabNumber;
    private String fullName;
    private Date dateOfBirth;
    private String degree;
    private String position;
    private BigDecimal salary;
}
