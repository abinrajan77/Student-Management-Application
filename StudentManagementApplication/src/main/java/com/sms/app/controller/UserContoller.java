package com.sms.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sms.app.entity.User;
import com.sms.app.exception.RecordNotFoundException;
import com.sms.app.service.UserService;

@RestController
@RequestMapping("/sms")
public class UserContoller {

	@Autowired
	UserService userService;

	@GetMapping("/search/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public User searchByUser(@PathVariable String username) {

		User user = userService.findByUsername(username);

		return user;
	}
	
	@GetMapping("/searchby")
	public ResponseEntity<List<User>> searchUser(@RequestParam("query") String query){
		return ResponseEntity.ok(userService.searchUser(query));
	}
	
//	@GetMapping("/searchrole/{name}")
//	@PreAuthorize("hasRole('ADMIN')")
//	public List<User> searchRole(@PathVariable String name) {
//
//		return userService.findAllByRolename(name);
//
//	}
//	
//	@GetMapping("/searchbyrole/{name}")
//	@PreAuthorize("hasRole('ADMIN')")
//	public List<User> findByRolesRoleName(@PathVariable String name){
//		return userService.findByRolesName(name);
//	}

	@GetMapping("/users/pageandsort")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "3") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
		List<User> list = userService.getAllUsers(pageNo, pageSize, sortBy);

		return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/users/findbyid/{id}")
	public ResponseEntity<User> getEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException {
		User entity = userService.getUserById(id);

		return new ResponseEntity<User>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/users/update")
	public ResponseEntity<User> createOrUpdateEmployee(User user)
			throws RecordNotFoundException {
		User updated = userService.createOrUpdateUser(user);
		return new ResponseEntity<User>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteUserById(@PathVariable("id") Long id) throws RecordNotFoundException {
		userService.deleteUserById(id);
		return HttpStatus.FORBIDDEN;
	}
}
