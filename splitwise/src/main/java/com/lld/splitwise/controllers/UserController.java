package com.lld.splitwise.controllers;

import org.springframework.stereotype.Controller;

import com.lld.splitwise.dtos.RegisterUserRequestDto;
import com.lld.splitwise.dtos.RegisterUserResponseDto;
import com.lld.splitwise.exceptions.UserAlreadyExistsException;
import com.lld.splitwise.models.User;
import com.lld.splitwise.services.UserService;

@Controller
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	public RegisterUserResponseDto registerUser(RegisterUserRequestDto request) {
		User user;
		RegisterUserResponseDto response = new RegisterUserResponseDto();
		try {
			user = userService.registerUser(request.getUserName(),
					request.getPhoneNumber(),
					request.getPassword());
			
			response.setUserId(user.getId());
			response.setStatus("SUCCESS");
		} catch (UserAlreadyExistsException exception) {
			response.setStatus("FAILURE");
			response.setMessage(exception.getMessage());
		}
		
		return response;
		
	}

}
