package redchuk.project.universitydepartment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import redchuk.project.universitydepartment.entity.UniversityGroup;

import java.util.Optional;

@Repository
public interface UniversityGroupRepository extends JpaRepository<UniversityGroup, Long> {
    @Query(value = "SELECT * FROM University_group as c where c.name = :name ", nativeQuery = true)
    Optional<UniversityGroup> getUniversityGroupByName(String name);
}
