package com.viheakode.api.controller;

import com.viheakode.api.dto.request.LoginRequest;
import com.viheakode.api.service.serviceImp.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@Valid @RequestBody LoginRequest request){
        String token = authenticationService.authenticate(request);
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("token", token);
        return ResponseEntity.ok().body(objectMap);
    }
}
