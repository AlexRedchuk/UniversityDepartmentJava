package redchuk.project.universitydepartment.repositories.security;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redchuk.project.universitydepartment.entity.security.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
		Optional<User> findByUsername(String username);
}
