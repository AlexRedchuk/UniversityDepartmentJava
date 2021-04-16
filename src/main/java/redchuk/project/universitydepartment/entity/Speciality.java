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
public class Speciality {
    @Id
//    @SequenceGenerator(name= "VOCABULARY_SEQUENCE", sequenceName = "VOCABULARY_SEQUENCE_ID", initialValue=1, allocationSize = 1)
//    @GeneratedValue(strategy=GenerationType.AUTO, generator="VOCABULARY_SEQUENCE")
    private Long id;
    private Integer code;
    private String name;
    private Integer yearOfAdding;
    private Integer numberOfCredits;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "speciality")
    @Fetch(value= FetchMode.SELECT)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<UniversityGroup> universityGroups;
}
