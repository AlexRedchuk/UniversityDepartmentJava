package redchuk.project.universitydepartment.services.impls;

import java.util.Arrays;
import java.util.HashSet;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.entity.security.UserDetailsImpl;
import redchuk.project.universitydepartment.repositories.security.UserRepo;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return UserDetailsImpl.fromUser(userRepo.findByUsername(username).orElseThrow());
	}


}
