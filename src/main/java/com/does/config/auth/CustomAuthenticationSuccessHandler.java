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
		String prevUrl = (String) request.getSession().getAttribute("prevUrl");
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest != null) {
			uri = savedRequest.getRedirectUrl();
		} else if (prevUrl != null && !prevUrl.isEmpty()){
			uri = prevUrl;
		}

		redirectStrategy.sendRedirect(request, response, uri);
	}
}
