/**
 * 
 */
package com.tkp.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkp.poc.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserNameAndPassword(String username, String password);
	User findByUserName(String username);
}
