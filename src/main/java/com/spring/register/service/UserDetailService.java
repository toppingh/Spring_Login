package com.spring.register.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.register.domain.PrincipalDetails;
import com.spring.register.domain.Users;
import com.spring.register.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = userRepository.findByEmail(email)
				.orElseThrow(()-> {
					return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다!");
				});
		return new PrincipalDetails(user, null);
	}

}