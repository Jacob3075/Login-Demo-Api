package com.jacob.demo.controllers;

import com.jacob.demo.models.User;
import com.jacob.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController("/")
public class LoginController {

	@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
	@Autowired
	UserServices userServices;

	@PostConstruct
	private void dummyUsers() {
		userServices.dummyUsers();
	}

	@GetMapping("/login")
	public String login(@RequestBody User user) {
		return userServices.login(user);
	}

	@GetMapping("/dummy-{username}")
	public String dummy(@PathVariable("username") String username) {
		return userServices.dummy(username);
	}

	@PostMapping(path = "/add-user", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveUser(@RequestBody User user) {
		return userServices.saveUser(user);
	}


	@GetMapping("/logout-{username}")
	private String logout(@PathVariable("username") String username) {
		return userServices.logOut(username);
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userServices.getAllUsers();
	}
}
