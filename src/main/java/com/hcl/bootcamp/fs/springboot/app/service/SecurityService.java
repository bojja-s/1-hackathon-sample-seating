package com.hcl.bootcamp.fs.springboot.app.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
