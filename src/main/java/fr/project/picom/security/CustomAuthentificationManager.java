package fr.project.picom.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
@Primary
public class CustomAuthentificationManager implements AuthenticationManager {

	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;
	
	public CustomAuthentificationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		super();
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);

		return authProvider;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return authenticationProvider().authenticate(authentication); 		
	}
	
	

}
