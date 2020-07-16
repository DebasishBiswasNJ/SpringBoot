package com.deb.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * 
 * @author ARIES_PC
 *
 */
@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;

	// GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	// GET /users/{id}
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id- "+id);
		}
		//return user;
		
		/**
		 * HATEOAS Implementation :
		 * 
		 * "all-users", SERVER_PATH + "/users"
		 * retrieveAllUsers
		 */
		Resource<User> resource = new Resource<>(user);
		
		//static import is done for ControllerLinkBuilder class
		// This is link to retrieveAllUsers()
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		//  This is link to deleteAdvUser()
		ControllerLinkBuilder linkTo1 = linkTo(methodOn(this.getClass()).deleteAdvUser(id));
		resource.add(linkTo1.withRel("delete-users"));
		
		return resource;
	}
	
	/**
	 * Old deleteUser() method
	 */
	/*@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if (user == null)
			throw new UserNotFoundException("id- "+id);
	}*/
	
	/**
	 * New deleteAdvUser() method
	 */
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteAdvUser(@PathVariable int id) {
		User deleteUser = service.deleteById(id);
		if (deleteUser == null) 
			throw new UserNotFoundException("id- "+id);	//will invoke the UserNotFoundException class, which is throwing NOT_FOUND --> status : 404
		else 
			return ResponseEntity.noContent().build();	// no-content --> status : 204
	}

	/**
	 * Old createUser() Method
	 * 
	 * input - details of user
	 * output - CREATED & return the created URI
	 * @RequestBody will map the Request Body which is user fields[i.e. "name",
	 * birthDate" automatically]
	 */
	/*@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User saveduser = service.save(user);

		// CREATED
		// /user/{id} savedUser.getId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}*/
	
	/**
	 * New createAdvUser() method
	 * 
	 * @Valid : use 2.1.3.RELEASE springboot parent version, else will not work,
	 * If validation fails, handleMethodArgumentNotValid() of CustomisedResponseEntityExceptionhandler class will be triggered
	 * 
	 * <artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.3.RELEASE</version>
	 */
	@PostMapping("/users")
	public ResponseEntity<Object> createAdvUser(@Valid @RequestBody User user) {
		User saveduser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	

}
