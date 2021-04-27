package redchuk.project.universitydepartment.services.impls;

import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.components.JwtUtils;
import redchuk.project.universitydepartment.dto.user.UserRequestDTO;
import redchuk.project.universitydepartment.entity.security.Role;
import redchuk.project.universitydepartment.entity.security.User;
import redchuk.project.universitydepartment.repositories.security.RoleRepo;
import redchuk.project.universitydepartment.repositories.security.UserRepo;

@Service
@RequiredArgsConstructor
public class SecurityService {
	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtils jwtUtils;
	private final AuthenticationManager authenticationManager;


	public User createUser(UserRequestDTO userRequest, String role) {
		var roles = new HashSet<Role>();
		var build = roleRepo.findByName(role);
		roles.add(build.orElseThrow());
		var user = User.builder()
					   .username(userRequest
							   .getUsername())
					   .password(passwordEncoder.encode(userRequest.getPassword()))
					   .roles(roles)
					   .build();
		return userRepo.save(user);
	}

	public String createToken(UserRequestDTO request) {
		var authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);

		return jwtUtils.generateJwtToken(authenticate);
	}
}
