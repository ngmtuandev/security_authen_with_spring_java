package com.security_practical.security_practical.controller;

import com.security_practical.security_practical.dtos.SignUpDTO;
import com.security_practical.security_practical.dtos.UserDTO;
import com.security_practical.security_practical.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    @Autowired
    private AuthService authService;

//    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDTO) {
//        UserDTO createNewUser = authService
//    }

    @PostMapping("/register")
    public ResponseEntity<?> registerController(@RequestBody SignUpDTO signUpDTO) {
        UserDTO newUserRegisted = authService.createUserRegister(signUpDTO);
        if (newUserRegisted == null) {
            return new ResponseEntity<>("User is not created, try again later", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newUserRegisted, HttpStatus.CREATED);
    }


}
