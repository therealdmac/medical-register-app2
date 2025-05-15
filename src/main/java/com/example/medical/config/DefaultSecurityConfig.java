package com.example.medical.config;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@Configuration
@Profile("!auth0")
public class DefaultSecurityConfig {

	/*@Bean
	public SecurityFilterChain formLoginSecurityFilterChain(HttpSecurity http) throws Exception {


		http
			.authorizeHttpRequests(authz -> authz
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/doctor/**").hasRole("DOCTOR")
				.requestMatchers("/nurse/**").hasRole("NURSE")
				.requestMatchers("/", "/login", "/login.xhtml", "/error", "/favicon.ico", "/webjars/**").permitAll()
				.anyRequest().authenticated())
				.formLogin(login -> login
						.loginPage("/login.xhtml")
						.defaultSuccessUrl("/dashboard.xhtml", true)
						.permitAll()
				)
				.logout(Customizer.withDefaults())
			.exceptionHandling(eh -> eh.accessDeniedPage("/access-denied"));
		return http.build();
	}*/

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) {
		try {
			http.csrf((csrf) -> csrf.disable());
			http.authorizeHttpRequests((authorize) -> authorize
							.requestMatchers("/admin/**").hasRole("ADMIN")
							.requestMatchers("/doctor/**").hasRole("DOCTOR")
							.requestMatchers("/nurse/**").hasRole("NURSE")
							//.requestMatchers("/").permitAll()
							.requestMatchers("/oauth2/**").permitAll()
							.requestMatchers("/**.faces").permitAll()
							.requestMatchers("/jakarta.faces.resource/**").permitAll()
							.anyRequest().authenticated())
//					.oauth2ResourceServer((oauth2) -> oauth2
//							.jwt(Customizer.withDefaults())
//					)
					.oauth2Login(oauth2 -> {
						oauth2.loginPage("/login.faces")
							.successHandler((request, response, authentication) -> {
								response.sendRedirect("/dashboard.faces");
							});
					})

//					.formLogin((formLogin) -> formLogin.loginPage("/login.faces")
//							.permitAll()
//							.failureUrl("/login.faces?error=true")
//							.defaultSuccessUrl("/dashboard.faces"))
//					.logout((logout) -> logout.logoutSuccessUrl("/login.faces").deleteCookies("JSESSIONID"))
					.exceptionHandling(eh -> eh.accessDeniedPage("/access-denied"));;
			return http.build();
		}
		catch (Exception ex) {
			throw new BeanCreationException("Wrong spring security configuration", ex);
		}
	}

//	@Bean
//	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
//		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
//	}

	@Bean
	public UserDetailsService users() {
		return new InMemoryUserDetailsManager(
			User.withUsername("alice").password("{noop}password").roles("ADMIN").build(),
			User.withUsername("bob").password("{noop}password").roles("DOCTOR").build(),
			User.withUsername("claire").password("{noop}password").roles("NURSE").build()
		);
	}
}
