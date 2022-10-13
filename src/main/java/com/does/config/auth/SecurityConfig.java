package com.does.config.auth;

import com.does.biz.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity                                                  // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2UserService customOAuth2UserService;
	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.headers().frameOptions().disable()                 // h2-console 를 사용하기 위한 옵션
				.and()
					.authorizeRequests()                            // URL 별 권한 관리를 설정하는 옵션의 시작점. 이 메서드가 선언되어야 antMatchers 옵션을 사용할 수 있음
					.antMatchers("/", "/user/login","/assets/**", "/h2-console/**", "/view").permitAll()           // 해당 URL 은 모두 허용
					.antMatchers("/api/v1/**", "/myPage/**").hasRole(Role.USER.name())      // 해당 URL 은 USER 권한이 있어야 함
					.anyRequest().authenticated()                                                      // 이외 나머지 URL 은 모두 인증 정보가 있어야 함(로그인)
				.and()
					.logout()
						.logoutSuccessUrl("/")                      // 로그아웃 후 이동할 URL
				.and()
					.oauth2Login()                                  // oauth2 로그인 기능에 대한 여러 설정의 진입점
						.loginPage("/user/login")                   // Spring Security 가 제공하는 기본 로그인 화면이 아닌 구현한 페이지를 노출하도록 설정
						.successHandler(customAuthenticationSuccessHandler)     // 로그인 인증에 성공 후 동작하는 핸들러 구현체를 등록
						.userInfoEndpoint()                         // oauth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
							.userService(customOAuth2UserService);  // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
																	// 리소스 서버에서 사용자 정보를 가져온 후 추가로 진행하고자 하는 기능을 명시
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
				.ignoring()
				.antMatchers("/favicon.ico", "/error");  // HttpSecurity 설정 적용 전, 앞 단에서 먼저 적용됨
	}
}
