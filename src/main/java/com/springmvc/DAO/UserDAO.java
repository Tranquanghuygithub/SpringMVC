package com.springmvc.DAO;

import java.util.List;

import com.springmvc.entity.UserEntity;

public interface UserDAO {
	void addUser(UserEntity user);
	void updateUser(UserEntity user);
	void deleteUser(long id);
	UserEntity getUserById(long id);
	List <UserEntity> getAllUsers();
}
