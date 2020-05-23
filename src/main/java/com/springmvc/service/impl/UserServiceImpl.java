package com.springmvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.DAO.UserDAO;
import com.springmvc.entity.UserEntity;
import com.springmvc.model.UserDTO;
import com.springmvc.service.UserService;

@Service
//de tao cac bean tuong ung 1 cach tuu dong ma ko can tao ben configuration ???
@Transactional
// co the dat toan bo transaction cho class hoac dat cho tuwngf hamf
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDao; // tự động gán 1 bean  UserServiceimpl vaof userDao. tinh da hinh >

	@Override
	public void addUser(UserDTO userDTO) {
		// chuyen 1 user DTO phia client them vao CSDL
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userDTO.getId());
		userEntity.setName(userDTO.getName());
		userEntity.setPhone(userDTO.getPhone());
		userDao.addUser(userEntity);
		//throw new RuntimeException();// thêm cai này để xem thử xem cái transaction có giúp việc rollback data ko 
	}

	@Override
	public void updateUser(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userDTO.getId());
		userEntity.setName(userDTO.getName());
		userEntity.setPhone(userDTO.getPhone());	
		userDao.updateUser(userEntity);
		
	}

	@Override
	public void deleteUser(long id) {
		UserEntity userEntity= userDao.getUserById(id);
		if(userEntity !=null) {
			userDao.deleteUser(id);
		}
	}

	@Override
	public UserDTO getUserById(long id) {
		UserEntity userEntity =userDao.getUserById(id);
		UserDTO userDTO = new UserDTO();
		
		userDTO.setId(userEntity.getId());
		userDTO.setName(userEntity.getName());
		userDTO.setPhone(userEntity.getPhone());	
		return  userDTO;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<UserEntity> listEnity= new ArrayList<UserEntity>();
		List<UserDTO> listDTO= new ArrayList<UserDTO>();
		listEnity= userDao.getAllUsers();
		for(UserEntity userEntity:listEnity) {
			UserDTO userDTO= new UserDTO();
			userDTO.setId(userEntity.getId());
			userDTO.setName(userEntity.getName());
			userDTO.setPhone(userEntity.getPhone());
			listDTO.add(userDTO);
		}
		return listDTO;
	}

}
