package com.spring.register.domain;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class PrincipalDetails implements UserDetails, OAuth2User{
	private Users users;
	private Map<String, Object> attributes;
	
	
	public PrincipalDetails(Users users, Map<String, Object> attributes) {
		this.users = users;
		this.attributes = attributes;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	@Override
	public String getName() {
		return null;
	}
	
	@Override
	// 사용자가 갖고있는 권한 목록 반환, 사용자 외에 권한이 없어서 user 반환!
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("user"));
	}
	
	@Override
	public String getPassword() { // 비밀번호 반환, 암호화해서 저장!
		return users.getPassword();
	}
	
	@Override
	public String getUsername() { // 사용자 식별 가능한 사용자 이름반환, 반드시 unique 해야함!
		return users.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() { // 계정 만료여부 확인, 만료x면 true반환!
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() { // 계정 잠금여부 확인, 잠금x면 true반환!
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() { // 비번 만료여부 확인, 만료x면 true반환!
		return true;
	}
	
	@Override
	public boolean isEnabled() { // 계정 사용가능 여부 확인, 사용가능이면 true반환!
		return true;
	}
}
