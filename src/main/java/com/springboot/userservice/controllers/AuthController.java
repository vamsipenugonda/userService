package com.springboot.userservice.controllers;

import com.springboot.userservice.dto.*;
import com.springboot.userservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/sign_up")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto request) {
        SignUpResponseDto response = new SignUpResponseDto();
        try {
            if (authService.signUp(request.getEmail(), request.getPassword())) {
                response.setRequestStatus(RequestStatus.SUCCESS);
            } else {
                response.setRequestStatus(RequestStatus.FAILURE);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setRequestStatus(RequestStatus.FAILURE);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        try {
            String token = authService.login(request.getEmail(), request.getPassword());
            LoginResponseDto loginDto = new LoginResponseDto();
            loginDto.setRequestStatus(RequestStatus.SUCCESS);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("AUTH_TOKEN", token);

            ResponseEntity<LoginResponseDto> response = new ResponseEntity<>(
                    loginDto, headers , HttpStatus.OK
            );
            return response;
        } catch (Exception e) {
            LoginResponseDto loginDto = new LoginResponseDto();
            loginDto.setRequestStatus(RequestStatus.FAILURE);
            ResponseEntity<LoginResponseDto> response = new ResponseEntity<>(
                    loginDto, null , HttpStatus.BAD_REQUEST
            );
            return response;
        }
    }

    @GetMapping("/validate")
    public boolean validate(@RequestParam("token") String token) {
        return authService.validate(token);
    }

}
