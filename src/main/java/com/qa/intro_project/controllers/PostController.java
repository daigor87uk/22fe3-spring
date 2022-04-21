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
import com.qa.intro_project.data.repository.PostRepository;
import com.qa.intro_project.data.repository.UserRepository;

@RestController
@RequestMapping(path = "/post")
public class PostController {

	private PostRepository postRepository;
	private UserRepository userRepository;
	
	@Autowired
	public PostController(PostRepository postRepository, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<Post>> getPosts() {
		return ResponseEntity.ok(postRepository.findAll());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Post> getPost(@PathVariable(name = "id") int id) {
		Optional<Post> post = postRepository.findById(id);
		
		if (post.isPresent()) {
			return new ResponseEntity<>(post.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// When I create a post, it is required that the user id also be provided
	// of the user that is creating the post.
	// - if that user doesn't exist, we will return a 404 - user not found
	@PostMapping(path = "/{userId}")
	public ResponseEntity<Post> createPost(@Valid @RequestBody Post post, @PathVariable(name = "userId") int userId) {
		Optional<User> user = userRepository.findById(userId);
		
		if (user.isPresent()) {
			post.setUser(user.get());
			Post newPost = postRepository.save(post);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", "http://localhost:8080/post/" + newPost.getId());
			
			return new ResponseEntity<>(newPost, headers, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(path = "/{id}")
	public Post updatePost(@RequestBody Post post, @PathVariable(name = "id") int id) {
		// TODO: 2. Implement me
		return null;
	}
	
	@DeleteMapping(path = "/{id}")
	public Post deletePost(@PathVariable(name = "id") int id) {
		// TODO: 3. Implement me
		return null;
	}
}
