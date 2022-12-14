package fr.project.picom.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import fr.project.picom.handler.CustomAuthentificationFailureHandler;
import fr.project.picom.handler.CustomAuthentificationSuccessHandler;
import fr.project.picom.handler.CustomLogoutSuccessHandler;
import fr.project.picom.security.CustomAuthentificationManager;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().and()
				.authenticationManager(new CustomAuthentificationManager(userDetailsService, passwordEncoder))
				.formLogin().successHandler(authenticationSuccessHandler())
				.failureHandler(authenticationFailureHandler()).loginProcessingUrl("/login").and().logout()
				.logoutSuccessHandler(logoutSuccessHandler()).and()
				// Pour la console H2 (à ne pas utiliser en prod)
				.headers().frameOptions().disable();

		return http.build();
	}

	@Bean
	AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthentificationFailureHandler();
	}

	@Bean
	AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomAuthentificationSuccessHandler();
	}

	@Bean
	LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}

}
