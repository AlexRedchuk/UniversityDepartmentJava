package redchuk.project.universitydepartment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tutor {
    @Id
//    @SequenceGenerator(name= "VOCABULARY_SEQUENCE", sequenceName = "VOCABULARY_SEQUENCE_ID", initialValue=1, allocationSize = 1)
//    @GeneratedValue(strategy=GenerationType.AUTO, generator="VOCABULARY_SEQUENCE")
    private Long id;
    private Integer tabNumber;
    private String fullName;
    private Date dateOfBirth;
    private String degree;
    private String position;
    private BigDecimal salary;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tutor")
    @Fetch(value= FetchMode.SELECT)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<SubjectPlan> subjectSummaries;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tutor")
    @Fetch(value= FetchMode.SELECT)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<SubjectPlan> subjectPlans;
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value= FetchMode.SELECT)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Subject> subjects;
}
