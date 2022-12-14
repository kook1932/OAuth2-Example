package com.does.config;

import com.does.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final LoginUserArgumentResolver loginUserArgumentResolver;

	// HandlerMethodArgumentResolver 는 항상 WebMvcConfigurer 의 addArgumentResolvers() 통해 추가해야 한다.
	// 다른 MethodArgumentResolver 가 필요하다면 같은 방식으로 추가해 주면 된다.
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(loginUserArgumentResolver);
	}
}
