package com.example.user_managing_system.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundException extends UsernameNotFoundException {
    public UserNotFoundException(String username) {
        super("Not found for username: " + username);
    }
}
