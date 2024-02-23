package com.example.springtrivial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
public class ConfigSecurity {
	@Bean
	public UserDetailsManager userDetailsManager(DataSource datasource) {
		
		return new JdbcUserDetailsManager(datasource);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests( configure ->{
			configure
				.requestMatchers(HttpMethod.POST, "/rh-app/preguntas").hasRole("admin")
				.requestMatchers(HttpMethod.PUT, "/rh-app/preguntas/**").hasRole("admin")
				.requestMatchers(HttpMethod.DELETE, "/rh-app/preguntas/**").hasRole("admin")
				.requestMatchers(HttpMethod.GET, "/rh-app/preguntas").hasAnyRole("jugador", "admin")
				.requestMatchers(HttpMethod.GET, "/rh-app/preguntas/**").hasAnyRole("jugador", "admin")
				.requestMatchers(HttpMethod.GET, "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").hasAnyRole("jugador", "admin");

		});
		
		http.httpBasic(Customizer.withDefaults());
		
		http.csrf( csrf -> csrf.disable());
		
		return http.build();
		
	}
}