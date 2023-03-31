package com.does.biz.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor
public class User implements Serializable {

	private long seq;
	private String name;
	private String email;
	private String picture;
	private Role role;
	private String provider;
	private String id;

	@Builder
	public User(String name, String email, String picture, Role role, String provider, String id) {
		this.name = name;
		this.email = email;
		this.picture = picture;
		this.role = role;
		this.provider = provider;
		this.id = id;
	}

	public String getRoleKey() {
		return role.getKey();
	}
}
