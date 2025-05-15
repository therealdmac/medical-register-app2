package com.example.medical.common;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.HashMap;
import java.util.Map;

@Named("navConfig")
@ApplicationScoped
public class NavigationConfig {

	private final Map<String, String> roleHomePages = new HashMap<>();

	public NavigationConfig() {
		roleHomePages.put(AppConstants.ROLE_ADMIN, "/admin-home.xhtml");
		roleHomePages.put(AppConstants.ROLE_DOCTOR, "/doctor-home.xhtml");
		roleHomePages.put(AppConstants.ROLE_NURSE, "/nurse-home.xhtml");
	}

	public String getHomePageForRole(String role) {
		return roleHomePages.getOrDefault(role, "/home.xhtml");
	}
}
