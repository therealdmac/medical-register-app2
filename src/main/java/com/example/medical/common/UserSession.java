package com.example.medical.common;

//@Named("userSession")
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class UserSession implements Serializable {
//
//	private String selectedRole;
//
//	public String getSelectedRole() {
//		return selectedRole;
//	}
//
//	public void setSelectedRole(String selectedRole) {
//		this.selectedRole = selectedRole;
//	}
//}

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.Map;

@Named
//@SessionScoped
@Getter
@Setter
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession implements Serializable {
	private String username;
	private String role;

	@PostConstruct
	public void init() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof OAuth2User user) {
			Map<String, Object> attributes = user.getAttributes();
			this.username = (String) attributes.getOrDefault("login", "unknown");
			// Set default role; in production, determine from DB or claim
			this.role = "Doctor"; // or Admin/Nurse
		}
	}

	public boolean canView() {
		return true;
	}

	public boolean canEdit() {
		return true;
		//return role.equals("Admin") || role.equals("Doctor");
	}

	public boolean canDelete() {
		return true;
		//return role.equals("Admin");
	}

	public boolean canViewAudit() {
		return true;
		//return role.equals("Admin");
	}

	public String logout() {
		SecurityContextHolder.clearContext();
		return "/login.xhtml?faces-redirect=true";
	}
}
