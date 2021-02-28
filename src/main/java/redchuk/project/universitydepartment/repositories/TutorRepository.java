package redchuk.project.universitydepartment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redchuk.project.universitydepartment.entity.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
}
