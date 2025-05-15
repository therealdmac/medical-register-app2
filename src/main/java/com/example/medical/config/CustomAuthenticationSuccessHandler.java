package com.example.medical.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.example.medical.common.UserSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Collections;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final UserSession userSession;

	public CustomAuthenticationSuccessHandler(UserSession userSession) {
		this.userSession = userSession;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
										Authentication authentication)
			throws IOException, ServletException {

		// Inject selected role into context

		String role = userSession.getRole();
		if (role != null) {
			// Replace authorities with selected role
			Authentication originalAuth = SecurityContextHolder.getContext().getAuthentication();
			SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role);
			Authentication newAuth = new UsernamePasswordAuthenticationToken(
					originalAuth.getPrincipal(), originalAuth.getCredentials(), Collections.singleton(grantedAuthority));
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}

		// Redirect based on role
		switch (role) {
			case "ADMIN" -> response.sendRedirect("/admin/home");
			case "DOCTOR" -> response.sendRedirect("/doctor/home");
			case "NURSE" -> response.sendRedirect("/nurse/home");
			default -> response.sendRedirect("/home");
		}
	}
}
