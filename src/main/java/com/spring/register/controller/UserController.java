package com.spring.register.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.register.dto.AddUserRequest;
import com.spring.register.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;

	@PostMapping("/user")
	public String signup(@Valid AddUserRequest request, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "signup";
		}
		
		if (!request.getPassword().equals(request.getPasswordCheck())) {
			bindingResult.rejectValue("passwordCheck", "passwordInCorrect",
					"2개 패스워드가 일치하지않아욥!");
			 
			return "signup";
		}
		try {
			userService.save(request);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자네욥!");
			return "signup";
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "signup";
		}
		
		return "redirect:/login";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup(AddUserRequest addUserRequest) {
		return "signup";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response,
				SecurityContextHolder.getContext().getAuthentication());
		
		return "redirect:/login";
	}
}
