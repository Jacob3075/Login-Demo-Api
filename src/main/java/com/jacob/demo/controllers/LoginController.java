package com.jacob.demo.controllers;

import com.jacob.demo.models.User;
import com.jacob.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController("/")
public class LoginController {

	@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
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


	@GetMapping("/login")
	public String login(@RequestBody User user) {
		Optional<User> optionalUser = userRepository.findById(user.getUsername());
		boolean isValidUser = optionalUser
				.filter(value -> user.getPassword().equals(value.getPassword()))
				.isPresent();
		if (isValidUser) {
			User updatedUser = optionalUser
					.get()
					.setLoggedIn(true);
			userRepository.save(updatedUser);
			return "Logged in";
		}
		return "No User found";
	}

	@GetMapping("/dummy-{username}")
	public String dummy(@PathVariable("username") String username) {
		var ref = new Object() {
			String message;
		};
		Optional<User> optionalUser = userRepository.findById(username);
		optionalUser
				.filter(User::isLoggedIn)
				.ifPresentOrElse(
						value -> {
							ref.message = "Welcome " + value.getUsername();
							new java.util.Timer().schedule(
									new java.util.TimerTask() {
										@Override
										public void run() {
											logout(value.getUsername());
										}
									},
									60000
							);
						},
						() -> ref.message = "Not Logged in");
		return ref.message;
	}

	@PostMapping(path = "/add-user", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveUser(@RequestBody User user) {
		if (!isPresent(user)) {
			if ((user.getUsername() != null) && (user.getPassword() != null)) {
				userRepository.save(user);
				return "Saved";
			} else {
				return "Invalid parameters";
			}
		}
		return "Already present";
	}

	public boolean isPresent(User user) {
		return userRepository.findById(user.getUsername()).isPresent();
	}

	@GetMapping("/logout-{username}")
	private String logout(@PathVariable("username") String username) {
		Optional<User> optionalUser = userRepository.findById(username);
		if (optionalUser.isPresent()) {
			if (optionalUser.get().isLoggedIn()) {
				User updatedUser = optionalUser
						.get()
						.setLoggedIn(false);
				userRepository.save(updatedUser);
				return "Logged Out";
			} else {
				return "Not Logged In";
			}
		} else {
			return "No User found";
		}
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
