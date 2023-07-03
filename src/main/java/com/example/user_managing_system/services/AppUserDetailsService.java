package com.example.user_managing_system.services;

import com.example.user_managing_system.exceptions.UserNotFoundException;
import com.example.user_managing_system.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(AppUserDetailsService.class);

    @Autowired
    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        return userRepository.findByEmail(username.toLowerCase()).orElseThrow(() -> {
            logger.info("user for username {} not found", username);
            return new UserNotFoundException(username);
        });
    }
}
