package redchuk.project.universitydepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SubjectSummary {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    private int mark;
    @ManyToOne
    private Subject subject;
    @ManyToOne
    private Tutor tutor;
    private int semester;
    private int year;
}
