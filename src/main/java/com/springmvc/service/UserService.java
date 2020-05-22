package com.springmvc.service;

import java.util.List;

import com.springmvc.model.User;

public interface UserService {
	void addUser(User user);
	void updateUser(User user);
	void deleteUser(long id);
	User getUserById(long id);
	List <User> getAllUsers();
}
