package com.spring.register.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
	@Email
	@NotEmpty(message = "이메일을 입력하세욥")
	private String email;
	
	@NotEmpty(message = "비밀번호를 입력하세욥")
	private String password;
	
	@NotEmpty(message = "비밀번호 확인을 입력하세욥")
	private String passwordCheck;
}
