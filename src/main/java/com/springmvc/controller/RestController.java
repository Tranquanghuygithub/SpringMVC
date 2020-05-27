package com.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.springmvc.model.UserDTO;
import com.springmvc.service.UserService;
@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	UserService userService;

	
	@RequestMapping(value="/danh-sach")
	public  List<UserDTO> getListUser(){
		List<UserDTO> list= userService.getAllUsers();
		return list;	
	}
	// guwir thong bao tra ve cua http laf 201: da tao than cong
	@ResponseStatus(code=HttpStatus.CREATED)
	
	
	@RequestMapping(value="/them-nguoi-dung", method= RequestMethod.POST)
	public   void  themUser(@RequestBody UserDTO user) {
		userService.addUser(user);
		//return "redirect:/list-user";
	}
	
	
	@RequestMapping(value="user/{id}")
	public UserDTO get( @PathVariable(name = "id") long id ) {
		return userService.getUserById(id);
	}
}
