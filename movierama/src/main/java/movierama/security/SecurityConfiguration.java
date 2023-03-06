package movierama.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import movierama.service.CustomAuthenticationProvider;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

	private AuthenticationProvider authenticationProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests()
				.antMatchers("/", "/signup**", "/login**", "/style**", "/movies**", "/incrementlikes**","/insertion**", "/callback/", "/webjars/**",
						"/css**","style.css", "/error**", "/resources/**", "/static/**", "/templates/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.loginProcessingUrl("/login").defaultSuccessUrl("/movies").failureUrl("/login?message=error")
				.usernameParameter("username").passwordParameter("password").and().logout().logoutUrl("/perform_logout")
				.logoutSuccessUrl("/login?message=logout");

		return http.build();
	}

	@Bean
	public AuthenticationProvider authProvider() {
		if (authenticationProvider == null) {
			authenticationProvider = new CustomAuthenticationProvider();
		}
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(authenticationProvider);
		return authenticationManagerBuilder.build();
	}
}
