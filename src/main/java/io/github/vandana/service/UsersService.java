package io.github.vandana.service;

import org.springframework.stereotype.Service;

import io.github.vandana.exception.UserNotFoundException;
import io.github.vandana.model.UserRequest;
import io.github.vandana.model.UserResponse;

@Service
public interface UsersService {

	UserResponse createUser(UserRequest userRequest);

	UserResponse deleteUser(Long userId) throws UserNotFoundException;

	UserResponse getUserById(Long userId) throws UserNotFoundException;

	UserResponse getAllUsers();

	UserResponse updateUser(Long userId, UserRequest userRequest) throws UserNotFoundException;

}
