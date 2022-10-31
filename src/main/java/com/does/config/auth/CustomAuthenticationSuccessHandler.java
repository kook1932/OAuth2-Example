package com.does.config.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final RequestCache requestCache = new HttpSessionRequestCache();
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		String uri = "/";
		String prevUrl = (String) request.getSession().getAttribute("prevUrl"); // 로그인 페이지 이동 시 세션에 저장한 이전 경로 값 추출
		if (prevUrl != null && !prevUrl.isEmpty()) {
			request.getSession().removeAttribute("prevUrl");                    // 세션에 값이 있다면 추출하고 세션에서는 값을 제거
		}

		// 로그인 페이지 강제 이동시 Spring Security 가 저장한 request 정보를 추출
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null) {
			// savedRequest 객체가 있다는 것은 강제로 로그인 페이지에 접속한 경우이며, savedRequest 에 저장된 이전 경로값으로 이동한다.
			uri = savedRequest.getRedirectUrl();
		} else if (prevUrl != null && !prevUrl.isEmpty()) {
			// savedRequest 객체가 없다는 것은 사용자가 로그인 페이지로 이동한 경우이며 세션에 저장한 이전 경로값으로 이동한다.
			uri = prevUrl;
		}

		redirectStrategy.sendRedirect(request, response, uri);
	}
}
