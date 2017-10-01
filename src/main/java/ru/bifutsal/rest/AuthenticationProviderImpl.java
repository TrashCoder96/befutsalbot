package ru.bifutsal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itimofeev on 01.10.2017.
 */

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

	@Value("${admin.login}")
	private String login;

	@Value("${admin.password}")
	private String password;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!login.equals(authentication.getName())) {
			throw new BadCredentialsException("Username not found.");
		}
		if (!password.equals(authentication.getCredentials().toString())) {
			throw new BadCredentialsException("Wrong password.");
		}
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return new UsernamePasswordAuthenticationToken("admin", authentication.getCredentials().toString(), roles);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}

