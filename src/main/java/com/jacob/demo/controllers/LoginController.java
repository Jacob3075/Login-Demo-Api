package com.jacob.demo.controllers;

import com.jacob.demo.models.User;
import com.jacob.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RestController("/")
public class LoginController {

	@Autowired
	UserRepository userRepository;

	@PostConstruct
	private void dummyUsers() {
		User user1 = new User();
		User user2 = new User();
		user1 = user1
				.setUsername("User1")
				.setPassword("123")
				.setLoggedIn(false);
		user2 = user2
				.setUsername("User2")
				.setPassword("1234")
				.setLoggedIn(false);
		userRepository.save(user1);
		userRepository.save(user2);
	}


	@GetMapping("login")
	public boolean login(User user) {
		Optional<User> optionalUser = userRepository.findById(user.getUsername());
		boolean isValidUser = optionalUser
				.filter(value -> user.getPassword().equals(value.getPassword()))
				.isPresent();
		if (isValidUser) {
			userRepository.deleteById(user.getUsername());
			User newUser = optionalUser
					.get()
					.setLoggedIn(true);
			userRepository.save(newUser);
			return true;
		}
		return false;
	}
}
