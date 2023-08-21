package com.lld.splitwise.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lld.splitwise.exceptions.UserAlreadyExistsException;
import com.lld.splitwise.models.User;
import com.lld.splitwise.models.UserStatus;
import com.lld.splitwise.repositories.UserRepository;

@Service
public class UserService {
	
	UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User registerUser(String userName, String phoneNumber, String password) throws UserAlreadyExistsException {
		
		Optional<User> userOptional = this.userRepository.findByPhone(phoneNumber);
		if(userOptional.isPresent()) {
			if(userOptional.get().getUserStatus().equals(UserStatus.ACTIVE)) {
				throw new UserAlreadyExistsException();
			} else {
				User user = userOptional.get();
				user.setUserStatus(UserStatus.ACTIVE);
				user.setName(userName);
				user.setName(userName);
				userRepository.save(user);
			}
		}
		
		User user = new User();
		user.setPhone(phoneNumber);
		user.setName(userName);
		user.setPassword(password);
		user.setUserStatus(UserStatus.ACTIVE);
		
		return userRepository.save(user);
	}

}
