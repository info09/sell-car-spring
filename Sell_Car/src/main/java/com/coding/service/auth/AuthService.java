package com.coding.service.auth;

import com.coding.dto.SignupRequest;
import com.coding.dto.UserDTO;

public interface AuthService {
    UserDTO signup(SignupRequest signupRequest);
    Boolean hasUserWithEmail(String email);
}
