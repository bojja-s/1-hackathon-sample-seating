package com.hcl.bootcamp.fs.springboot.app.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.bootcamp.fs.springboot.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String username);
}
