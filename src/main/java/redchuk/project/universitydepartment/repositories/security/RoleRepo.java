package redchuk.project.universitydepartment.repositories.security;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redchuk.project.universitydepartment.entity.security.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
