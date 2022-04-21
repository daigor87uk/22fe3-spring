package com.qa.intro_project.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.intro_project.data.entity.User;

@Repository // Marks this as a repository bean
public interface UserRepository extends JpaRepository<User, Integer> {
	// User is the type of data being stored in the database
	// Integer is the type of the User entities id

	// The JpaRepository interface provides a set of default actions that can be
	// performed against the database. Spring and Hibernate from the `spring-data-jpa` module, amongst others,
	// will provide the implementations and so no concrete class needs to inherit this.
	
	// Repositories must be defined as interfaces.
	
	// Instance methods that can be used with a user repository include but are not limited to:
	// - findAll()            returns a list of all saved entities
	// - findById(id)         returns an optional of the entity, or an empty optional
	// - save(entity)         saves the specified entity to the database, returns the new saved entity (use this)
	// - existsById()         returns a boolean, true if the entity exists, false otherwise
	// - deleteById()         deletes the specified entity by its id, no return type
}