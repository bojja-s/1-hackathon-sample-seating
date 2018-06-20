package com.hcl.bootcamp.fs.springboot.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hcl.bootcamp.fs.springboot.app.jpa.RoleRepository;
import com.hcl.bootcamp.fs.springboot.app.jpa.UserRepository;
import com.hcl.bootcamp.fs.springboot.app.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(User user) {
		if (logger.isInfoEnabled()) {
			logger.info("[START] UserServiceImpl save");
			logger.info("[START] UserServiceImpl save user.getPassword() " + user.getPassword());
			logger.info("[START] UserServiceImpl save passwordEncoder.encode(user.getPassword()) " + passwordEncoder.encode(user.getPassword()));
		}		
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
		if (logger.isInfoEnabled()) {
			logger.info("[END] UserServiceImpl save");
		}			
    }

    @Override
    public User findByUserName(String username) {
		if (logger.isInfoEnabled()) {
			logger.info("[START] UserServiceImpl findByUserName [" + username + "]");
		}			
        return userRepository.findByUserName(username);
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	
 
}
