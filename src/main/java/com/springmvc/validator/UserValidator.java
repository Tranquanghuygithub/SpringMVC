package com.springmvc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.springmvc.model.User;
@Component
//ddee ko can tao bean bang tay mam no se tao 1 cach tu dong
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// Kiem tra doi tuog truyen vao la user
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// Error chua cac loi
		User user= (User) target; 
		
		if(user.getName()== null || user.getName().length() ==0) {
			errors.rejectValue("name", "field.required"); 
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password" ,"field.required");
		
		if( user.getPassword().length()< 6 || user.getPassword().length() >12 ) {
			errors.rejectValue("password","password.invalid");
		}
	}

}
