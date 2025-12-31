package boot.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurity {

	// Security filter chain
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
		httpSecurity.csrf(c -> c.disable())
					.authorizeHttpRequests(req -> req
							.requestMatchers("/cust/greet", "/cust/register", "/cust/login")
							.permitAll()
							.anyRequest()
							.authenticated());
		
		return httpSecurity.build();
	}

	// Authentication Manager
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
		return configuration.getAuthenticationManager();
	}

	// Password Encoder
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
