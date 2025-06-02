package com.foodify.foodify.Impl;

import com.foodify.foodify.Entity.UserEntity;
import com.foodify.foodify.IO.UserRequest;
import com.foodify.foodify.IO.UserResponse;
import com.foodify.foodify.Repository.UserRepository;
import com.foodify.foodify.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse registerUser(UserRequest request) {
        UserEntity newUser=convertToEntity(request);
        newUser=userRepository.save(newUser);
        return convertToResponse(newUser);
    }
    private UserEntity convertToEntity(UserRequest request){
          return UserEntity.builder()
                  .email(request.getEmail())
                  .password(passwordEncoder.encode(request.getPassword()))
                  .name(request.getName())
                  .build();
    }
    private UserResponse convertToResponse(UserEntity registeredUser){
         return UserResponse.builder()
                .id(registeredUser.getId())
                .name(registeredUser.getName())
                .email(registeredUser.getEmail())
                .build();
    }
}
