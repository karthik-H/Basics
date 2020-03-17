package com.example.demo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.UserDetails;

public interface UserDetailsRepo extends MongoRepository<UserDetails, Long>{

	UserDetails findFirstById(Long userId);
	
	UserDetails findFirstIdByEmail(String email);
	
	List<UserDetails> findByEmail(String email);
}
