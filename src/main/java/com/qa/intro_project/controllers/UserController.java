package com.qa.intro_project.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.intro_project.data.entity.Post;
import com.qa.intro_project.data.entity.User;
import com.qa.intro_project.data.repository.UserRepository;
import com.qa.intro_project.service.UserService;

@RestController
@RequestMapping(path = "/user") // accepts requests at localhost:8080/user
public class UserController {
	
	private UserService userService;
	
	@Autowired // Instructs the Spring IoC container to inject the required dependency
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(userService.readAll());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<User> getUser(@PathVariable(name = "id") int id) {
		User user = userService.readById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}/posts")
	public ResponseEntity<List<Post>> getUserPosts(@PathVariable(name = "id") int userId) {
		List<Post> posts = userService.readPostsByUserId(userId);
		return ResponseEntity.ok(posts);
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User newUser = userService.create(user);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/user/" + newUser.getId());

		return new ResponseEntity<User>(newUser, headers, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable(name = "id") int id) {
		// TODO: Put your implementation here
		return null;
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(name = "id") int id) {
		// TODO: Put your implementation here
		return null;
	}
}
