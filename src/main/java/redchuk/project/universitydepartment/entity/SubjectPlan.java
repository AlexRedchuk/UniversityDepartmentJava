package redchuk.project.universitydepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int year;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private UniversityGroup group;
    private String type;
    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
