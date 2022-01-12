package co.chu3la.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.chu3la.demo.entities.CustomUserDetails;


@Service
public class AuthenticationProviderService implements AuthenticationProvider {
	
	@Autowired
	private JpaUserDetailsService jpaUserDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	/*
	@Autowired
	private SCryptPasswordEncoder sCryptPasswordEncoder;
	*/
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		 CustomUserDetails user = (CustomUserDetails) jpaUserDetailsService.loadUserByUsername(username);
		/*
		switch(user.getUser().getAlgorithm()) {
		case BCRYPT:
			return checkPassword(user, password, bCryptPasswordEncoder);
		case SCRYPT:
			return checkPassword(user, password, sCryptPasswordEncoder);
		
		default:
			break;
			
		}
		*/
		
		 return checkPassword(user, password, bCryptPasswordEncoder);
		
	}

	private Authentication checkPassword(CustomUserDetails user, String RawPassword,
			PasswordEncoder encoder) {
		if(encoder.matches(RawPassword, user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(
					user.getUsername(),
					user.getPassword(),
					user.getAuthorities()
					);
		}else {
			throw new BadCredentialsException("Bad credentials");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication);
	}

}
