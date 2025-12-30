package boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurity {
	
	// In Memory Authentication and Authorization 
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails publicUser = User.builder()
								.username("Vishnu")
								.password(encoder().encode("Vishnu@123"))
								.roles("PUBLIC")
								.build();
		
		UserDetails user = User.builder()
							.username("test")
							.password(encoder().encode("test@123"))
							.roles("USER")
							.build();
		
		UserDetails admin = User.builder()
							.username("Admin")
							.password(encoder().encode("admin@123"))
							.roles("ADMIN")
							.build();
		
		return new InMemoryUserDetailsManager(publicUser, user, admin);
	}

	// Filter Chain
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) {
		
		http.csrf(c -> c.disable())
			.authorizeHttpRequests(req -> req
					.requestMatchers("/public/**")
					.permitAll()
					.requestMatchers("/user/**")
					.hasAnyRole("USER", "ADMIN")
					.requestMatchers("/admin/**")
					.hasRole("ADMIN")
					.anyRequest()
					.authenticated()
					)
			.formLogin(Customizer.withDefaults())
			.logout(Customizer.withDefaults());
			
		return http.build();
	}
	
	// Password Encoder
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
