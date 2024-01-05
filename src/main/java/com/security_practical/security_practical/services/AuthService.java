package com.security_practical.security_practical.services;

import com.security_practical.security_practical.dtos.SignUpDTO;
import com.security_practical.security_practical.dtos.UserDTO;

public interface AuthService {

    UserDTO createUserRegister(SignUpDTO signUpDTO);

}
