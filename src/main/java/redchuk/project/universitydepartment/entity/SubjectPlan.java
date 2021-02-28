package redchuk.project.universitydepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import redchuk.project.universitydepartment.entity.enums.SubjectTypes;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SubjectPlan {
    @Id
    private Long id;
    private int year;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups group;
    private SubjectTypes type;
    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
    @OneToOne
    private SubjectSummary subjectSummary;
}
