package com.wep.iftube.seguranca;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.wep.iftube.repositories.CanalRepository;
@Component
public class CustomUserDetailsService implements UserDetailsService {

	private CanalRepository users;
	
	public CustomUserDetailsService(CanalRepository users) {
        this.users = users;
    }
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.users.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }

}
