package com.qa.intro_project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.intro_project.data.entity.Post;
import com.qa.intro_project.data.entity.User;
import com.qa.intro_project.data.repository.UserRepository;
import com.qa.intro_project.dto.NewUserDTO;
import com.qa.intro_project.dto.UserDTO;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private ModelMapper modelMapper;

	@Autowired
	public UserService(UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	public List<UserDTO> getUsers() {
		List<User> users = userRepository.findAll();
		List<UserDTO> dtos = new ArrayList<>();
		
		for (User user : users) {
			dtos.add(this.toDTO(user));
		}
		return dtos;
//		return userRepository.findAll()
//							 .stream()
//							 .map(this::toDTO)
//							 .collect(Collectors.toList());
	}
	
	public UserDTO getUser(int id) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isPresent()) {
			return this.toDTO(user.get());
		}
		throw new EntityNotFoundException("User not found with id " + id);
//		return this.toDTO(user.orElseThrow(() -> new EntityNotFoundException("User not found with id " + id)));
	}
	
	// TODO: 5. Implement PostDTO, convert this method to use it
	public List<Post> getUserPosts(int userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return user.get().getPosts();
		}
		throw new EntityNotFoundException("User not found with id " + userId);
//		return user.orElseThrow(() -> new EntityNotFoundException("Posts not found with user id " + userId)).getPosts();
	}
	
	public UserDTO createUser(NewUserDTO user) {
		User toSave = this.modelMapper.map(user, User.class);
		User newUser = userRepository.save(toSave);
		return this.toDTO(newUser);
	}
	
	public UserDTO updateUser(User user, int id) {
		// TODO: 1. Implement me
		return null;
	}
	
	public void deleteUser(int id) {
		// TODO: 2. Implement me
	}
	
	private UserDTO toDTO(User user) {		
		return this.modelMapper.map(user, UserDTO.class);
		
		// ModelMapper will create an instance of UserDTO
		// - it will then assign the values of all fields in `user`, which have the same name
		//   as the fields in `UserDTO.class`, to that new instance of UserDTO
	}
	
}
