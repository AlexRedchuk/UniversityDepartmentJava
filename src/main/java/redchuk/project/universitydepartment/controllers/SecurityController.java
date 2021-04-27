package redchuk.project.universitydepartment.controllers;

import javax.annotation.PostConstruct;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import redchuk.project.universitydepartment.dto.user.UserRequestDTO;
import redchuk.project.universitydepartment.entity.security.User;
import redchuk.project.universitydepartment.services.impls.SecurityService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/security")
public class SecurityController {
	private final SecurityService service;
	public void createUser(){
		var adminRequest = new UserRequestDTO();
		adminRequest.setUsername("admin");
		adminRequest.setPassword("admin");
		service.createUser(adminRequest, "ROLE_ADMIN");
	}

	//@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	@PostMapping("/createUser")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> registerUser(@RequestBody UserRequestDTO userDto){
		return  ResponseEntity.ok(service.createUser(userDto, "ROLE_USER"));
	}

	//@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	@PostMapping("/createAdmin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> registerAdmin(@RequestBody UserRequestDTO userDto){
		return  ResponseEntity.ok(service.createUser(userDto, "ROLE_ADMIN"));
	}

	//@ApiOperation(value = "", authorizations = { @Authorization(value="JWT") })
	@PostMapping("/login")
	@PreAuthorize("isAnonymous()")
	public ResponseEntity<String> login(@RequestBody UserRequestDTO userRequest){
		return ResponseEntity.ok(service.createToken(userRequest));
	}


}
