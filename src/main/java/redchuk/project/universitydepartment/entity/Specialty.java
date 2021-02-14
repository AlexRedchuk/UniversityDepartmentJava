package redchuk.project.universitydepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Specialty {
    @Id
    private Long id;
    private String code;
    private String name;
    private int yearOfAdding;
    private int NumberOfCredits;
}
