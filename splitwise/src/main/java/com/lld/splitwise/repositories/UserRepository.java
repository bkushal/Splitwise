package com.lld.splitwise.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lld.splitwise.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findById(Long aLong);
	
	Optional<User> findByPhone(String phone);
	
	User save(User user);
	

}
