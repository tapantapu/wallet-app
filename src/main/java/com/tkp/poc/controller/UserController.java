package com.tkp.poc.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tkp.poc.entity.User;
import com.tkp.poc.entity.Wallet;
import com.tkp.poc.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@PostMapping
	public User createUser(@Valid @RequestBody User user) {
		Wallet wallet = new Wallet();
		wallet.setAmount(new BigDecimal(0));
		user.setWallet(wallet);
		return userRepository.save(user);
	}
}
