package com.spring.register.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	// 소셜 로그인
	@Column(name = "provider")
	private String provider;
	
	@Column(name = "provider_id")
	private String provider_id;
	
	@Column(name = "social_id")
	private String social_id;

}
