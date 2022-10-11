package com.does.config.auth.dto;

import com.does.biz.domain.Role;
import com.does.biz.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;
	private String provider;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture, String provider) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
		this.provider = provider;
	}

	// OAuth2User(Default) 에서 반환하는 사용자 정보는 Map 에 담겨있기 때문에 값 하나하나를 변환해야한다.
	public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
		System.out.println("attributes = " + attributes);
		if ("naver".equalsIgnoreCase(registrationId)){
			return ofNaver("id", attributes, registrationId);
		}
		if ("kakao".equalsIgnoreCase(registrationId)) {
			return ofKakao("id", attributes, registrationId);
		}
		return ofGoogle(userNameAttributeName, attributes, registrationId);
	}

	private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes, String registrationId) {
		return OAuthAttributes.builder()
				.name((String) attributes.get("name"))
				.email((String) attributes.get("email"))
				.picture((String) attributes.get("picture"))
				.provider(registrationId)
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}

	private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes, String registrationId) {
		Map<String, Object> response = (Map<String, Object>) attributes.get("response");

		return OAuthAttributes.builder()
				.name((String) response.get("name"))
				.email((String) response.get("email"))
				.picture((String) response.get("profile_image"))
				.provider(registrationId)
				.attributes(response)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}

	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes, String registrationId) {
		Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
		Map<String, Object> profile = (Map<String, Object>) response.get("profile");

		return OAuthAttributes.builder()
				.name((String) profile.get("nickname"))
				.email((String) response.get("email"))
				.picture((String) profile.get("profile_image_url"))
				.provider(registrationId)
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}

	public User toUser() {
		return User.builder()
				.name(name)
				.email(email)
				.picture(picture)
				.role(Role.USER)
				.provider(provider)
				.build();
	}
}
