package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.UserDetails;

@Service
public interface UserDetailsService {
	

	List<UserDetails> getAllUsers();
	
	Long addUser(UserDetails userDetails);
	
	UserDetails getUser(Long userId);
	
	void deleteUser(Long userId);
	
	Map<String,Object> updateUserDetail(UserDetails userDetails);
	
}
