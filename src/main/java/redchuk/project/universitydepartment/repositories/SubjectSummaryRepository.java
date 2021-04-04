package redchuk.project.universitydepartment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redchuk.project.universitydepartment.entity.SubjectSummary;

import java.util.List;

@Repository
public interface SubjectSummaryRepository extends JpaRepository<SubjectSummary, Long> {
    List<SubjectSummary> getSubjectSummariesByStudent_IdAndYearAndSemester(Long studentId, int semester, int year);
}
