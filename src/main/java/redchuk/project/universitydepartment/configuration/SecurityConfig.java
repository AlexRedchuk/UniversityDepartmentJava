package redchuk.project.universitydepartment.configuration;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import redchuk.project.universitydepartment.filter.AuthFilter;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final AuthFilter authFilter;

	private final UserDetailsService userDetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth.inMemoryAuthentication().withUser("User1")
			.password(passwordEncoder().encode("asdasd"))
			.roles("USER")
			.and()
			.withUser("admin")
			.password(passwordEncoder().encode("asdasd"))
			.roles("ADMIN");;*/

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/v2/api-docs").permitAll()
			.antMatchers("/configuration/ui").permitAll()
			.antMatchers("/swagger-resources/**").permitAll()
			.antMatchers("/configuration/security").permitAll()
			.antMatchers("/swagger-ui.html").permitAll()
			.antMatchers("/swagger-ui/*").permitAll()
			.antMatchers("/webjars/**").permitAll()
			.antMatchers("/v1/security/login").permitAll()
			.anyRequest().authenticated();
		http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

	}


	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
