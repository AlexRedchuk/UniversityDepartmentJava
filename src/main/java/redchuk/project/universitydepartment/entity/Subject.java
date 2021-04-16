package redchuk.project.universitydepartment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
//    @SequenceGenerator(name= "VOCABULARY_SEQUENCE", sequenceName = "VOCABULARY_SEQUENCE_ID", initialValue=1, allocationSize = 1)
//    @GeneratedValue(strategy=GenerationType.AUTO, generator="VOCABULARY_SEQUENCE")
    private Long id;
    private Integer code;
    private String name;
    @ManyToMany
    @JoinTable(name="tutor_subjects",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name="tutor_id"))
    @EqualsAndHashCode.Exclude
    private Set<Tutor> tutors;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "subject")
    @Fetch(value= FetchMode.SELECT)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<SubjectPlan> subjectSummaries;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "subject")
    @Fetch(value= FetchMode.SELECT)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<SubjectPlan> subjectPlans;
}
