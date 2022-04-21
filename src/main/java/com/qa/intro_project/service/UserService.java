package com.qa.intro_project.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qa.intro_project.data.entity.Post;
import com.qa.intro_project.data.entity.User;
import com.qa.intro_project.data.repository.UserRepository;

@Service // specialisation of @Component, registers this class as a bean
public class UserService {

	private UserRepository userRepository;
	
	@Autowired // Instructs the Spring IoC container to inject the required dependency
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> readAll() {
		return userRepository.findAll();
	}
	
	public User readById(int id) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isPresent()) {
			return user.get();
		}
		throw new EntityNotFoundException("User with id " + id + " was not found");
	}
	
	public List<Post> readPostsByUserId(int id) {
		User user = this.readById(id);
		return user.getPosts();
	}
	
	public User create(User user) {
		return userRepository.save(user);
	}
	
}
