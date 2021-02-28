package redchuk.project.universitydepartment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redchuk.project.universitydepartment.entity.SubjectSummary;

@Repository
public interface SubjectSummaryRepository extends JpaRepository<SubjectSummary, Long> {
}
