package redchuk.project.universitydepartment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectSummary {
    @Id
//    @SequenceGenerator(name= "VOCABULARY_SEQUENCE", sequenceName = "VOCABULARY_SEQUENCE_ID", initialValue=1, allocationSize = 1)
//    @GeneratedValue(strategy=GenerationType.AUTO, generator="VOCABULARY_SEQUENCE")
    private Long id;
    @ManyToOne(fetch=FetchType.EAGER, optional=true, cascade=CascadeType.PERSIST)
    @JoinColumn(name = "student_id")
    @EqualsAndHashCode.Exclude
    private Student student;
    private Integer mark;
    @ManyToOne(fetch=FetchType.EAGER, optional=true, cascade=CascadeType.PERSIST)
    @JoinColumn(name = "subject_id")
    @EqualsAndHashCode.Exclude
    private Subject subject;
    @ManyToOne(fetch=FetchType.EAGER, optional=true, cascade=CascadeType.PERSIST)
    @JoinColumn(name = "tutor_id")
    @EqualsAndHashCode.Exclude
    private Tutor tutor;
    private Integer semester;
    private Integer year;
}
