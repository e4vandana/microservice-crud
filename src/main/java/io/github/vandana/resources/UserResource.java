package io.github.vandana.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.vandana.exception.UserNotFoundException;
import io.github.vandana.model.UserRequest;
import io.github.vandana.model.UserResponse;
import io.github.vandana.service.UsersService;

@RestController
@RequestMapping(value = "/userInfo/api/v1")
public class UserResource {

	@Autowired
	UsersService usersService;

	@GetMapping("/users")
	public UserResponse getAllUsers() {
		return usersService.getAllUsers();
	}

	@GetMapping("/users/{id}")
	public UserResponse getUserById(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
		return usersService.getUserById(userId);
	}

	@PostMapping("/users")
	public UserResponse createUser(@RequestBody UserRequest userRequest) {
		return usersService.createUser(userRequest);
	}

	@DeleteMapping("/users/{id}")
	public UserResponse deleteUser(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
		return usersService.deleteUser(userId);
	}

	@PutMapping("/users/{id}")
	public UserResponse updateUser(@PathVariable(value = "id") Long userId, @RequestBody UserRequest userRequest)
			throws UserNotFoundException {
		return usersService.updateUser(userId, userRequest);
	}

}
