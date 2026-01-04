package boot.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurity {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AppFilter appFilter;

	// Security filter chain
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
		httpSecurity.csrf(c -> c.disable())
					.authorizeHttpRequests(req -> req
							.requestMatchers("/cust/register", "/cust/login")
							.permitAll()
							.anyRequest()
							.authenticated())
							.sessionManagement(session -> session
							.sessionCreationPolicy(SessionCreationPolicy.STATELESS))				// add protocol stateless
							.authenticationProvider(getProvider())									// add auth provider
							.addFilterBefore(appFilter,UsernamePasswordAuthenticationFilter.class); // App Filter
		
		return httpSecurity.build();
	}
	
	
	// Authentication Provider
	@Bean
	public AuthenticationProvider getProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(encoder());
		return provider;
	}
	
	// Authentication Manager
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}

	// Password Encoder
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
