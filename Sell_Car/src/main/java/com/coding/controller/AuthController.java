package com.coding.controller;

import com.coding.dto.AuthenticationRequest;
import com.coding.dto.AuthenticationResponse;
import com.coding.dto.SignupRequest;
import com.coding.repository.UserRepository;
import com.coding.service.auth.AuthService;
import com.coding.service.jwt.UserService;
import com.coding.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JWTUtils jwtUtils;
    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        if(Boolean.TRUE.equals(authService.hasUserWithEmail(signupRequest.getEmail()))){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already exists");
        }

        var userDto = authService.signup(signupRequest);
        if(userDto == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
        final var userDetails = userService.userDetailsService().loadUserByUsername(request.getEmail());
        var optionalUser = userRepository.findFirstByEmail(request.getEmail());
        var token = jwtUtils.generateToken(userDetails);
        AuthenticationResponse response = new AuthenticationResponse();
        if(optionalUser.isPresent()){
            response.setJwt(token);
            response.setUserRole(optionalUser.get().getUserRole());
            response.setUserId(optionalUser.get().getId());
        }

        return response;
    }
}
