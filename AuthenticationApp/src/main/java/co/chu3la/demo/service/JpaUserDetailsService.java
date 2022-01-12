package co.chu3la.demo.service;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.chu3la.demo.entities.CustomUserDetails;
import co.chu3la.demo.entities.User;
import co.chu3la.demo.repository.UserRepository;


@Service
public class JpaUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Supplier<UsernameNotFoundException> s = 
				() -> new UsernameNotFoundException("Problem during authentication.");
				
		User user = userRepository.findUserByUsername(username).orElseThrow(s);		
		
		return new CustomUserDetails(user);
				
	}
	
	
	public void save(User u) {
		userRepository.save(u);
	}

}
