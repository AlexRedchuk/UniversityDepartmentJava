package redchuk.project.universitydepartment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redchuk.project.universitydepartment.entity.Speciality;

import java.util.Optional;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
    Optional<Speciality> getSpecialityByCode(Integer code);
}
