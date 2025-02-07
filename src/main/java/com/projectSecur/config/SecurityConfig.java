package com.projectSecur.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

	 @Bean
	    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
	        http.cors(withDefaults())
	            .csrf().disable()
	            .authorizeHttpRequests(authorize -> {
	                authorize.requestMatchers("/service/user/login").permitAll();
	                authorize.requestMatchers("/service/user/register").permitAll();
	                authorize.requestMatchers("/service/user/hello").permitAll(); // Assurez-vous que cette route est permise
	                authorize.requestMatchers("/service/upload/file").permitAll();
	                authorize.anyRequest().authenticated();
	            })
	            .httpBasic(withDefaults())
	            .sessionManagement(session -> session
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            );

	        return http.build();
	    }
	 
	 
	 	@Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        final CorsConfiguration config = new CorsConfiguration();

	        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	        config.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
	        config.setAllowCredentials(true);
	        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", config);

	        return source;
	    }
}
