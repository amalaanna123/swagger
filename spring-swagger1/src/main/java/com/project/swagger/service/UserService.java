package com.project.swagger.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.project.swagger.repository.UserRepository;
import com.project.swagger.model.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {
		Optional<User> Useroptional = userRepository.findUserByEmail(user.getEmailid());
		if(Useroptional.isPresent()) {
			throw new IllegalStateException("emailid is already taken");
		}
		return userRepository.save(user);	
	}

	public List<User> createUsers(List<User> users) {
		return userRepository.saveAll(users);
	}

	public User getUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}


	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public User updateUser(User user) {
		User oldUser=null;
		Optional<User> optionaluser=userRepository.findById(user.getId());
		if(optionaluser.isPresent()) {
			oldUser=optionaluser.get();
			oldUser.setFirstName(user.getFirstName());
			oldUser.setLastName(user.getLastname());
			oldUser.setEmailId(user.getEmailid());
		
			userRepository.save(oldUser);
		}else {
			return new User();
		}
		return oldUser;
	}
	
	public String deleteUserById(int id) {
		
		
		{
			boolean exists = userRepository.existsById(id);
			if(!exists) {
				throw new IllegalStateException("user with id "  +id+   " does not exist");
			}
			userRepository.deleteById(id);
			return "User got deleted";
}
		
	}
}
