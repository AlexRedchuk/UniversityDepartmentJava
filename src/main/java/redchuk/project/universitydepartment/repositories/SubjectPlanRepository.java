package redchuk.project.universitydepartment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redchuk.project.universitydepartment.entity.SubjectPlan;

import java.util.List;

@Repository
public interface SubjectPlanRepository extends JpaRepository<SubjectPlan, Long> {
    List<SubjectPlan> getSubjectPlansByYearAndUniversityGroup_Id(Integer year, Long groupId);
}
