package co.edu.icesi.awc.back.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	@Autowired
	public MyCustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserApp> users = userRepository.findByUsername(username);
		UserApp userApp = null;
		
		if(!users.isEmpty())
			userApp = users.get(0);
		
		if (userApp != null) {
			User.UserBuilder builder = User.withUsername(username);
			builder.password(userApp.getPassword());
			builder.roles(userApp.getType().toString().toUpperCase());
			return builder.build();
		}
		else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}