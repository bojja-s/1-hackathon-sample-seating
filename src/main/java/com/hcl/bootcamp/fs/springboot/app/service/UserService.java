package com.hcl.bootcamp.fs.springboot.app.service;

import com.hcl.bootcamp.fs.springboot.app.model.User;

public interface UserService {
    void save(User user);

    User findByEmail(String username);
}
