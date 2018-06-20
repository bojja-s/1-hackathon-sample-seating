package com.hcl.bootcamp.fs.springboot.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hcl.bootcamp.fs.springboot.app.jpa.UserRepository;
import com.hcl.bootcamp.fs.springboot.app.model.Role;
import com.hcl.bootcamp.fs.springboot.app.model.User;

/**
 * @author seethayya.n
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (logger.isInfoEnabled()) {
			logger.info("[START] UserDetailsServiceImpl loadUserByUsername username " + username);
		}		
		try {
			final User user = userRepository.findByUserName(username);
			logger.info("User:" +user.getUserName());
			logger.info("Pwd:" + user.getPassword());
			logger.info("Pwd:" + user.getRoles());
			//logger.info("Pwd:" + user.getEnable());
			
			if (user == null) {
				if (logger.isInfoEnabled()) {
					logger.info(" No user found with username: " + username);
				}				
				throw new UsernameNotFoundException("No user found with username: " + username);
			}
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
					true, true, true, true, getAuthorities(user.getRoles()));
		} catch (final Exception e) {
			logger.error(e.getMessage(), e);
			throw new UsernameNotFoundException("No user found with username: " + username);
		}
	}
	
	private final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
		List<String> rolesList = (roles == null ? Collections.emptyList()
				: roles.stream().map(r -> r.getName()).collect(Collectors.toList()));
		return getGrantedAuthorities(rolesList);
	}

	private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
		if (logger.isInfoEnabled()) {
			logger.info("[START] UserDetailsServiceImpl getGrantedAuthorities");
		}		
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (final String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		if (logger.isInfoEnabled()) {
			logger.info("[END] UserDetailsServiceImpl getGrantedAuthorities");
		}			
		return authorities;
	}

}

