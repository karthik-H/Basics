package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserDetails;
import com.example.demo.service.UserDetailsService;

@RestController()
@RequestMapping("/user")
public class UserDetailsController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@GetMapping("/")
	public List<UserDetails> getAllUsers()
	{
		return userDetailsService.getAllUsers();
	}
	@PostMapping("/")
	public Long addUser(@RequestBody UserDetails userDetails) {
		return userDetailsService.addUser(userDetails);
	}
	
}
