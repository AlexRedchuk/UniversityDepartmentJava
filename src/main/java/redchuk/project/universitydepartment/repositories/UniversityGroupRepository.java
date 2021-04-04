package redchuk.project.universitydepartment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redchuk.project.universitydepartment.entity.UniversityGroup;

import java.util.Optional;

@Repository
public interface UniversityGroupRepository extends JpaRepository<UniversityGroup, Long> {
    //@Query(value = "SELECT * FROM GROUP u WHERE u.name = name ", nativeQuery = true)
    Optional<UniversityGroup> getGroupsByName(String name);
}
