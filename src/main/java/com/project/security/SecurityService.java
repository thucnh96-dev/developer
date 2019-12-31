package com.project.security;

public interface SecurityService {
    String findloggedUsername();
    void autologin(String username,String password);
}
