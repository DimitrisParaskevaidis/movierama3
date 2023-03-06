package movierama.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import movierama.repository.UserRepository;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private UserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (authentication == null) {
			return null;
		}
		String name = authentication.getName();
		if (authentication.getCredentials() == null) {
			return null;
		}
		String password = authentication.getCredentials().toString();
		movierama.entity.User userObject = userRepository.findByName(name);
		if (userObject == null || !password.equals(userObject.getPassword())) {
			throw new UsernameNotFoundException("User Not Found or Incorrect password", null);
		}
		if (name.equals(userObject.getName()) && password.equals(userObject.getPassword())) {
			Collection<? extends GrantedAuthority> authorities = Collections
					.singleton(new SimpleGrantedAuthority("ROLE_USER"));
			Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
			return auth;
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}