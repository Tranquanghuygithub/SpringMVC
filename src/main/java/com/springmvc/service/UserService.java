package com.springmvc.service;

import java.util.List;

import com.springmvc.model.UserDTO;

public interface UserService {
	void addUser(UserDTO user);
	void updateUser(UserDTO user);
	void deleteUser(long id);
	UserDTO getUserById(long id);
	List <UserDTO> getAllUsers();
}
