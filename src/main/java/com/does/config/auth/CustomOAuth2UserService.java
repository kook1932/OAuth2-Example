package com.does.config.auth;

import com.does.biz.dao.UserDAO;
import com.does.biz.domain.User;
import com.does.config.auth.dto.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final UserDAO userDAO;
	private final HttpSession httpSession;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		// Default OAuth2UserService 객체 생성
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

		// default service 에서 userRequest 값을 받아 default OAuth2User 정보를 조회
		OAuth2User oAuth2User = delegate.loadUser(userRequest);

		// 현재 로그인 진행 중인 서비스를 구분하는 코드(google, naver, kakao ...)
		String registrationId = userRequest.getClientRegistration().getRegistrationId();

		// OAuth2 로그인 진행 시 키가 되는 필드값. Primary Key 와 같은 의미
		// 여러 개의 oauth 2 로그인을 진행할때 구분하기 위한 값
		// 구글 : sub, 네이버,카카오 : 지원하지 않기 때문에 OAuthAttributes 클래스에서 "id" 라는 값으로 명시해 준다
		String userNameAttributeName = userRequest
				.getClientRegistration()
				.getProviderDetails()
				.getUserInfoEndpoint()
				.getUserNameAttributeName();

		// OAuth2UserService 를 통해 가져온 OAuth2User 의 attribute 를 담을 객체를 생성
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

		// user 정보를 저장 or 수정
		User user = saveOrUpdate(attributes);

		// 세션에 사용자 정보를 담음
		httpSession.setAttribute("user", user);
		httpSession.setAttribute("userId", attributes.getId());

		return new DefaultOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
				attributes.getAttributes(),
				attributes.getNameAttributeKey());
	}

	private User saveOrUpdate(OAuthAttributes attributes) {
		User user = userDAO
				.findByEmail(attributes.getEmail(), attributes.getProvider())   // Email, Provider 가 일치하는 계정이 있으면 DB 에서 객체를 매핑하고
				.orElse(attributes.toUser());                                   // 일치하는 계정이 없으면 OAuthAttributes 에서 User 객체를 생성한다

		if (user.getSeq() > 0)  userDAO.update(user);
		else                    userDAO.save(user);
		if (user.getProvider().equalsIgnoreCase("kakao")) user.setId(attributes.getId());

		return user;
	}
}
