package com.spring.register.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.spring.register.domain.PrincipalDetails;
import com.spring.register.domain.Users;
import com.spring.register.oauth.GoogleUserInfo;
import com.spring.register.oauth.KakaoUserInfo;
import com.spring.register.oauth.NaverUserInfo;
import com.spring.register.oauth.OAuth2UserInfo;
import com.spring.register.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest)  throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.info("getAttributes : {}", oAuth2User.getAttributes());
		
		OAuth2UserInfo oAuth2UserInfo = null;
		
		String provider = userRequest.getClientRegistration().getRegistrationId();
		
		if (provider.equals("google")) {
			log.info("구글 로그인 요청");
			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
		} else if (provider.equals("naver")) {
			log.info("네이버 로그인 요청");
			oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
			System.out.println((Map)oAuth2User.getAttributes().get("response"));
		} else if (provider.equals("kakao") ) {
			log.info("카카오 로그인 요청");
			oAuth2UserInfo = new KakaoUserInfo((Map)oAuth2User.getAttributes());
			System.out.println((Map)oAuth2User.getAttributes());
		}
	
		String provider_id = oAuth2UserInfo.getProviderId();
		String email = oAuth2UserInfo.getEmail();
		String social_id =provider + "_" + provider_id;
		System.out.println(email);
		
		Optional<Users> optionalUsers = userRepository.findByEmail(email);
		Users users = null;
		
		if (optionalUsers.isEmpty()) {
			users = Users.builder()
					.email(email)
					.provider(provider)
					.provider_id(provider_id)
					.social_id(social_id)
					.build();
			userRepository.save(users);
		} else {
			users = optionalUsers.get();
		}
		
		return new PrincipalDetails(users, oAuth2User.getAttributes());
	}
}
