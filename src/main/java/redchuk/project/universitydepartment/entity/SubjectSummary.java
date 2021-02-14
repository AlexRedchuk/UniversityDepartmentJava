package redchuk.project.universitydepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SubjectSummary {
    @Id
    private Long id;
    @ManyToOne
    private Student student;
    private int mark;
    @ManyToOne
    private Subject subject;
    @ManyToOne
    private Tutor tutor;
    private int semester;
    private int year;
}
