package redchuk.project.universitydepartment.entity.security;

import java.util.Set;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
	private Long id;
	private String username;
	private String password;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
}
