package com.spring.register.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.register.domain.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	// email로 사용자 정보 가져오기
	Optional<Users> findByEmail(String email);
}
