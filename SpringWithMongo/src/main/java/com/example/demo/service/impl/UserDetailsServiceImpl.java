package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserDetails;
import com.example.demo.repo.UserDetailsRepo;
import com.example.demo.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserDetailsRepo userDetailsRepo;
	
	public List<UserDetails> getAllUsers() {
		List<UserDetails> userDetails = new ArrayList<>();
		userDetailsRepo.findAll().forEach(userDetails::add);
		return userDetails;
	}

	@Override
	public Long addUser(UserDetails userDetails) {
		userDetails.setStatus("Active");
		UserDetails userSavedData = userDetailsRepo.save(userDetails);
		return userSavedData.getId();
	}

	@Override
	public UserDetails getUser(Long userId) {
		return userDetailsRepo.findFirstById(userId);
	}

	@Override
	public void deleteUser(Long userId) {
		UserDetails userDetails = getUser(userId);
		userDetails.setStatus("Inactive");
		userDetailsRepo.save(userDetails);
	}

	@Override
	public Map<String,Object> updateUserDetail(UserDetails userDetails) {
		Map<String,Object> returnVal = new HashMap<String, Object>();
		if(userDetails == null) {
			returnVal.put("Error", "provide User Data");
		}else {
			UserDetails dbUserDetails = getUser(userDetails.getId());
			if(dbUserDetails == null) {
				returnVal.put("Error", "user does not exists");
			}else {
				final String email = userDetails.getEmail();
				final String firstName = userDetails.getFirstName();
				final String lastName = userDetails.getLastName();
				final String password = userDetails.getPassword();
				
				if(email != dbUserDetails.getEmail() && email != null) {
					dbUserDetails.setEmail(email);
				}
				if(firstName != dbUserDetails.getFirstName() && firstName != null) {
					dbUserDetails.setFirstName(firstName);
				}
				if(lastName != dbUserDetails.getLastName() && lastName != null) {
					dbUserDetails.setLastName(lastName);
				}
				if(password != dbUserDetails.getPassword() && password != null) {
					dbUserDetails.setPassword(password);
				}
				
				userDetailsRepo.save(dbUserDetails);
				returnVal.put("Info", "User data updated successfully");
			}
		}
		return returnVal;
	}

	
}
