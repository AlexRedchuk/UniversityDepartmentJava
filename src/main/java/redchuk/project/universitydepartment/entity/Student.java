package redchuk.project.universitydepartment.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
//    @SequenceGenerator(name= "VOCABULARY_SEQUENCE", sequenceName = "VOCABULARY_SEQUENCE_ID", initialValue=1, allocationSize = 1)
//    @GeneratedValue(strategy=GenerationType.AUTO, generator="VOCABULARY_SEQUENCE")
    private Long id;
    private String fullName;
    @ManyToOne(fetch=FetchType.EAGER, optional=true, cascade=CascadeType.PERSIST)
    @JoinColumn(name = "universityGroup_id")
    @EqualsAndHashCode.Exclude
    private UniversityGroup universityGroup;
}
