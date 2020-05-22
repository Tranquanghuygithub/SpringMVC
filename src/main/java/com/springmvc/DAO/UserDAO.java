package com.springmvc.DAO;

import java.util.List;

import com.springmvc.model.User;

public interface UserDAO {
	void addUser(User user);
	void updateUser(User user);
	void deleteUser(long id);
	User getUserById(long id);
	List <User> getAllUsers();
}
