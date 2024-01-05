package com.security_practical.security_practical.controller;

// ======= Xác thực người dùng

import com.security_practical.security_practical.dtos.AuthenticationRequest;
import com.security_practical.security_practical.dtos.AuthenticationResponseDTO;
import com.security_practical.security_practical.services.JwtService.UserDetailServiceImplement;
import com.security_practical.security_practical.utils.Jwt_create;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthenticationController {

    @Autowired
    private Jwt_create jwtCreate;

    // xác thực ngươời dùng
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImplement userDetailServiceImplement;

    @PostMapping("/login")
    public AuthenticationResponseDTO create_Token_Authentication(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse http_response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new BadCredentialsException("inccoret username or password");
        }
        catch (DisabledException disabledException) {
            http_response.sendError(HttpServletResponse.SC_NOT_FOUND, "user is not database");
            return null;
        }
        final UserDetails userDetails = userDetailServiceImplement.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt =jwtCreate.generateToken(userDetails.getUsername());
        System.out.println("token" + jwt);
        return new AuthenticationResponseDTO(jwt);

    }

}
