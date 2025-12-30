package boot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurity {
	
	// Security Filter
	
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
		httpSecurity.csrf(c -> c.disable())
					.authorizeHttpRequests(req -> req
							.requestMatchers("/register")
							.permitAll()
							.requestMatchers("/msg")
							.hasRole("ADMIN")
							.anyRequest()
							.authenticated())
					.formLogin(f -> f.successForwardUrl("/greet")); // by default POST method
		return httpSecurity.build();
	}
}
