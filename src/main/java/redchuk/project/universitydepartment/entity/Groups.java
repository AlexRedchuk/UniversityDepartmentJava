package redchuk.project.universitydepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Groups {
    @Id
    private Long id;
    private String name;
    @OneToOne
    private Student monitor;
    @OneToMany
    private Set<Student> students;
    @ManyToOne
    private Specialty specialty;
}
