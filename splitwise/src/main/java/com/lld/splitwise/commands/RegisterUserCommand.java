package com.lld.splitwise.commands;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.lld.splitwise.controllers.UserController;
import com.lld.splitwise.dtos.RegisterUserRequestDto;

@Controller
public class RegisterUserCommand implements Command {
	
	private UserController userController;
	
	@Autowired
	public RegisterUserCommand(UserController userController) {
		this.userController = userController;
	}

	@Override
	public boolean matches(String input) {
		// TODO Auto-generated method stub
		List<String> inpWords = Arrays.stream(input.split(" ")).toList();
		if(inpWords.size() != 4 || !inpWords.get(0).equals(CommandKeywords.REGISTER_USER))
			return false;
		
		return true;
	}

	@Override
	public void execute(String input) {
		// TODO Auto-generated method stub
		List<String> inpWords = Arrays.stream(input.split(" ")).toList();
		String password = inpWords.get(1);
		String phoneNumber = inpWords.get(2);
		String username = inpWords.get(3);
		
		RegisterUserRequestDto request = new RegisterUserRequestDto();
		request.setPassword(password);
		request.setPhoneNumber(phoneNumber);
		request.setUserName(username);
		
		userController.registerUser(request);
		
	}

}
