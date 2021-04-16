package redchuk.project.universitydepartment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectPlan {
    @Id
//    @SequenceGenerator(name= "VOCABULARY_SEQUENCE", sequenceName = "VOCABULARY_SEQUENCE_ID", initialValue=1, allocationSize = 1)
//    @GeneratedValue(strategy=GenerationType.AUTO, generator="VOCABULARY_SEQUENCE")
    private Long id;
    private Integer year;
    private String type;
    @ManyToOne(fetch=FetchType.EAGER, optional=true, cascade=CascadeType.PERSIST)
    @JoinColumn(name = "group_id")
    @EqualsAndHashCode.Exclude
    private UniversityGroup universityGroup;
    @ManyToOne(fetch=FetchType.EAGER, optional=true, cascade=CascadeType.PERSIST)
    @JoinColumn(name = "tutor_id")
    @EqualsAndHashCode.Exclude
    private Tutor tutor;
    @ManyToOne(fetch=FetchType.EAGER, optional=true, cascade=CascadeType.PERSIST)
    @JoinColumn(name = "subject_id")
    @EqualsAndHashCode.Exclude
    private Subject subject;
}
