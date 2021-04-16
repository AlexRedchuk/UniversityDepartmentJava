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
public class UniversityGroup {
    @Id
//    @SequenceGenerator(name= "VOCABULARY_SEQUENCE", sequenceName = "VOCABULARY_SEQUENCE_ID", initialValue=1, allocationSize = 1)
//    @GeneratedValue(strategy=GenerationType.AUTO, generator="VOCABULARY_SEQUENCE")
    private Long id;
    private String name;
    @ManyToOne(fetch=FetchType.EAGER, optional=true, cascade=CascadeType.PERSIST)
    @JoinColumn(name = "speciality_id")
    @EqualsAndHashCode.Exclude
    private Speciality speciality;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "universityGroup")
    @Fetch(value= FetchMode.SELECT)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Student> students;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "universityGroup")
    @Fetch(value= FetchMode.SELECT)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<SubjectPlan> subjectPlans;
}
