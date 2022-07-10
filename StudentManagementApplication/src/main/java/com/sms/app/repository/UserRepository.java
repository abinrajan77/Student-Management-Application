package com.sms.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sms.app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	public void deleteById(Long id);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query("select u from User u join Role r where r.name = :name")
	List<User> findAllByRolename(@Param("name")String name);
//
//
//	List<User> findByRolesName(String name);
	
	@Query("SELECT u from User u WHERE "+ "u.username LIKE CONCAT('%',:query, '%')")
	List<User> searchUser(String query);
}
