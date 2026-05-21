package com.viheakode.api.service.serviceImp;

import com.viheakode.api.dto.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final MoyJwtService moyJwtService;

    public String authenticate(LoginRequest request){

        String username = request.getUsername().toUpperCase();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    username,
                    request.getPassword()
                )
        );
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        System.out.println(userDetails.getAuthorities());
        return moyJwtService.generateToken(userDetails);
    }
}
