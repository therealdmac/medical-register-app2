//package com.example.medical.common;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.enterprise.context.RequestScoped;
//import jakarta.inject.Named;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.primefaces.model.menu.DefaultMenuItem;
//import org.primefaces.model.menu.DefaultMenuModel;
//import org.primefaces.model.menu.MenuModel;
//
//@Named("menuBean")
//@RequestScoped
//public class DynamicMenuBean {
//
//	private MenuModel model;
//
//	@PostConstruct
//	public void init() {
//		model = new DefaultMenuModel();
//
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String role = auth != null && auth.getAuthorities().stream().findFirst().isPresent()
//			? auth.getAuthorities().iterator().next().getAuthority()
//			: "";
//
//		if (role.equals(AppConstants.ROLE_ADMIN)) {
//			model.getElements().add(DefaultMenuItem.builder().value("Admin Home").url("/admin-home.xhtml").icon("pi pi-home").build());
//			model.getElements().add(DefaultMenuItem.builder().value("View Records").url("/records").icon("pi pi-list").build());
//			model.getElements().add(DefaultMenuItem.builder().value("Audit Log").url("/audit-log").icon("pi pi-history").build());
//		} else if (role.equals(AppConstants.ROLE_DOCTOR)) {
//			model.getElements().add(DefaultMenuItem.builder().value("Doctor Home").url("/doctor-home.xhtml").icon("pi pi-home").build());
//			model.getElements().add(DefaultMenuItem.builder().value("View Records").url("/records").icon("pi pi-list").build());
//		} else if (role.equals(AppConstants.ROLE_NURSE)) {
//			model.getElements().add(DefaultMenuItem.builder().value("Nurse Home").url("/nurse-home.xhtml").icon("pi pi-home").build());
//			model.getElements().add(DefaultMenuItem.builder().value("View Records").url("/records").icon("pi pi-list").build());
//		}
//
//		model.getElements().add(DefaultMenuItem.builder().value("Logout").url("/logout").icon("pi pi-sign-out").build());
//	}
//
//	public MenuModel getModel() {
//		return model;
//	}
//}
