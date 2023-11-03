package com.spring.register.oauth;

import java.util.Map;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KakaoUserInfo implements OAuth2UserInfo{
	private Map<String, Object> attributes;
	
	@Override
	public String getProviderId() {
		return attributes.get("id").toString();
	}
	
	@Override
	public String getProvider() {
		return "kakao";
	}
	
	@Override
	public String getEmail() {
		return (String)((Map) attributes.get("kakao_account")).get("email");
	}
	
	@Override
	public String getName() {
		return (String) ((Map) attributes.get("properties")).get("nickname");
	}
}
