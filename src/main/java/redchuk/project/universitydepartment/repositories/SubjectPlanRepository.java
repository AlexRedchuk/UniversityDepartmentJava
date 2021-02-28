package redchuk.project.universitydepartment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redchuk.project.universitydepartment.entity.SubjectPlan;

@Repository
public interface SubjectPlanRepository extends JpaRepository<SubjectPlan, Long> {
}
