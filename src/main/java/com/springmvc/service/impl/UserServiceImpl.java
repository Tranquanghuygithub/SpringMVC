package com.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.DAO.UserDAO;
import com.springmvc.model.User;
import com.springmvc.service.UserService;

@Service
// de tao ra cac bean tuong ung
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDao; // tự động gán 1 bean  UserServiceimpl vaof userDao. tinh da hinh >

	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
		
	}

	@Override
	public void deleteUser(long id) {
		userDao.deleteUser(id);		
	}

	@Override
	public User getUserById(long id) {
		return userDao.getUserById(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

}
