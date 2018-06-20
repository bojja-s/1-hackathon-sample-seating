package com.hcl.bootcamp.fs.springboot.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService{
	
    @Autowired
    private UserDetailsService userDetailsService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String findLoggedInUsername() {
		if (logger.isInfoEnabled()) {
			logger.info("[START] SecurityServiceImpl findLoggedInUsername");
		}		
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }
		if (logger.isInfoEnabled()) {
			logger.info("[END] SecurityServiceImpl findLoggedInUsername");
		}
        return null;
    }

    @Override
    public void autologin(String username, String password) {
		if (logger.isInfoEnabled()) {
			logger.info("[START] SecurityServiceImpl autologin");
		}		
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

       // authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", username));
        }
		if (logger.isInfoEnabled()) {
			logger.info("[END] SecurityServiceImpl autologin");
		}		
    }
}
