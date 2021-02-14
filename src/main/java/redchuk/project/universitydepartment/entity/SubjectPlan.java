package redchuk.project.universitydepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import redchuk.project.universitydepartment.entity.enums.SubjectTypes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SubjectPlan {
    @Id
    private Long id;
    private int year;
    @ManyToOne
    private Groups group;
    private SubjectTypes type;
    @ManyToOne
    private Tutor tutor;
    @OneToOne
    private SubjectSummary subjectSummary;
}
