package com.security_practical.security_practical.services;

import com.security_practical.security_practical.dtos.SignUpDTO;
import com.security_practical.security_practical.dtos.UserDTO;
import com.security_practical.security_practical.entity.User;
import com.security_practical.security_practical.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplement implements AuthService{

    @Autowired
    private UserRepositories userRepositories;


    @Override
    public UserDTO createUserRegister(SignUpDTO signUpDTO) {
        User user_register = new User();
        user_register.setFirstName(signUpDTO.getFirstName());
        user_register.setPassword(new BCryptPasswordEncoder().encode(signUpDTO.getPassword()));
        user_register.setLastName(signUpDTO.getLastName());
        user_register.setUsername(signUpDTO.getUsername());
        User newUser = userRepositories.save(user_register);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(newUser.getId());
        userDTO.setUsername(newUser.getUsername());
        userDTO.setUsername(newUser.getUsername());

        return userDTO; // trả về gì -> response register trả về cái đó

    }
}
