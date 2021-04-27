package redchuk.project.universitydepartment.entity.security;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
	@SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
	private long id;
	@Enumerated(EnumType.STRING)
	private ERole name;

	public enum ERole{
		ROLE_USER,
		ROLE_ADMIN
	}
}

