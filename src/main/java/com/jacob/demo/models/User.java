package com.jacob.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private String username;
	private String password;
	private boolean loggedIn;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public User setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", loggedIn=" + loggedIn +
				'}';
	}
}
