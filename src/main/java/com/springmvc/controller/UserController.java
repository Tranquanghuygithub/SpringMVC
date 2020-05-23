package com.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvc.model.UserDTO;
import com.springmvc.service.UserService;
import com.springmvc.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired 
	private UserValidator userValidator;
	
	@RequestMapping(value="/list-user", method= RequestMethod.GET)
	public String getAllUsers(HttpServletRequest request) {
		List<UserDTO> users= userService.getAllUsers();
		request.setAttribute("users", users);
		return "user/userList";
		
	}
	@RequestMapping(value="/detail-user/{userId}", method= RequestMethod.GET)
	public String  detailUser(HttpServletRequest request, @PathVariable(name = "userId") long userId) {
		UserDTO user = userService.getUserById(userId);
		request.setAttribute("user", user);
		return "user/detailUser";
	}
	@RequestMapping(value="/delete-user/{userId}", method= RequestMethod.GET)
	public String  deleteUser(HttpServletRequest request, @PathVariable(name = "userId") long userId) {
		userService.deleteUser(userId);
		List<UserDTO> users= userService.getAllUsers();
		//update list customer
		request.setAttribute("users", users);
		return "user/userList";
	}
	@RequestMapping(value="/update-user/{userId}", method= RequestMethod.GET)
	public String  updateUser(HttpServletRequest request, @PathVariable(name = "userId") long userId) {
		UserDTO user= userService.getUserById(userId);
		System.out.println(user.getName()+" "+user.getPhone());
		request.setAttribute("user", user);
		return "user/update";
	}
	@RequestMapping(value="/update-user", method= RequestMethod.POST)
	public String  updateUser(HttpServletRequest request,@ModelAttribute(name = "user") UserDTO user, BindingResult bindingResult) {
		System.out.println("A");
		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {		
			System.out.println("Có lỗi là: "+ bindingResult.getFieldError());
			return "update";
		}
		userService.updateUser(user);
		return "redirect:/list-user";
	}
	@RequestMapping(value="/add-user", method= RequestMethod.GET)
	public String  addUser(HttpServletRequest request) {
		UserDTO user= new UserDTO();
		request.setAttribute("user", user);
		return "user/addUser";
	}
	@RequestMapping(value="/add-user", method= RequestMethod.POST)
	public String  addUser(HttpServletRequest request, @ModelAttribute(name="user") UserDTO user) {
		userService.addUser(user);
		userService.updateUser(user);
		return "redirect:/list-user";
	}
}
