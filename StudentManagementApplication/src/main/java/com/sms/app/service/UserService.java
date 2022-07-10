package com.sms.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sms.app.entity.User;
import com.sms.app.exception.RecordNotFoundException;
import com.sms.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User findByUsername(String username) {
		Optional<User> result = userRepository.findByUsername(username);

		User user = null;

		if (result.isPresent()) {
			user = result.get();
		} else {
			// we didn't find the student
			throw new RuntimeException("Did not find user - " + username);
		}

		return user;
	}
	
	public List<User> searchUser(String query){
		List<User> users= userRepository.searchUser(query);
		return users;
	}

	public List<User> findAllByRolename(String name) {
		return userRepository.findAllByRolename(name);

	}
//
//	public List<User> findByRolesName(String name) {
//		return userRepository.findByRolesName(name);
//	}

	public List<User> getAllUsers(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<User> pagedResult = userRepository.findAll(paging);

		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<User>();
		}
	}

	public User getUserById(Long id) throws RecordNotFoundException {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			return user.get();
		} else {
			throw new RecordNotFoundException("No user record exist for given id");
		}
	}

	public User createOrUpdateUser(User entity) throws RecordNotFoundException {
		Optional<User> user = userRepository.findById(entity.getId());

		if (user.isPresent()) {
			User newEntity = user.get();
			newEntity.setEmail(entity.getEmail());
			newEntity.setUsername(entity.getUsername());
			newEntity.setPassword(entity.getPassword());
			newEntity.setRoles(entity.getRoles());

			newEntity = userRepository.save(newEntity);

			return newEntity;
		} else {
			entity = userRepository.save(entity);

			return entity;
		}
	}

	public void deleteUserById(Long id) throws RecordNotFoundException {
		userRepository.deleteById(id);
	}

}
