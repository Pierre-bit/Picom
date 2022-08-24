package fr.project.picom.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import fr.project.picom.security.CustomAuthentificationManager;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()

        .authenticationManager(new CustomAuthentificationManager(userDetailsService, passwordEncoder))

        // Pour la console H2 (à ne pas utiliser en prod)
        .headers().frameOptions().disable();
        
       return http.build();
    }
    
    
}
