package com.example.user_managing_system.config;

import com.example.user_managing_system.dto.UserSignUp;
import com.example.user_managing_system.models.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public TypeMap<UserSignUp, User> getUserTypeMap() {
        Provider<User> userProvider = p -> User.builder().build();
        TypeMap<UserSignUp, User> userTypeMap = getModelMapper().createTypeMap(UserSignUp.class, User.class);
        userTypeMap.setProvider(userProvider);

        return userTypeMap;
    }
}
