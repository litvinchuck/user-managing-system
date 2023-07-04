package com.example.user_managing_system.services;

import com.example.user_managing_system.dto.UserSignUp;
import com.example.user_managing_system.dto.UserUpdate;
import com.example.user_managing_system.exceptions.UserNotFoundException;
import com.example.user_managing_system.models.Role;
import com.example.user_managing_system.models.User;
import com.example.user_managing_system.repositories.UserRepository;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TypeMap<UserSignUp, User> userTypeMap;
    private static final Logger logger = LoggerFactory.getLogger(AppUserDetailsService.class);

    @Autowired
    public AppUserDetailsService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 TypeMap<UserSignUp, User> userTypeMap) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userTypeMap = userTypeMap;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        return userRepository.findByEmail(username.toLowerCase()).orElseThrow(() -> {
            logger.info("user for username {} not found", username);
            return new UserNotFoundException(username);
        });
    }

    public boolean userExistsByUsername(String username) {
        return userRepository.existsByEmail(username);
    }

    public void signUp(UserSignUp userRequest) {
        User user = userTypeMap.map(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.grantAuthority(Role.ROLE_USER);
        userRepository.save(user);
        logger.info("user {} signed up", user);
    }

    public void updateUser(User user, UserUpdate userUpdate) {
        user.setName(userUpdate.getName());
        user.setEmail(userUpdate.getEmail());
        userRepository.save(user);
        logger.info("user {} was updated", user);
    }
}
