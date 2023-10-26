package com.spring.register.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.register.domain.Users;
import com.spring.register.dto.AddUserRequest;
import com.spring.register.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Long save(AddUserRequest dto) {
		
		return userRepository.save(Users.builder()
				.email(dto.getEmail())
				.password(bCryptPasswordEncoder.encode(dto.getPassword()))
				.build()).getId();
	}
}
