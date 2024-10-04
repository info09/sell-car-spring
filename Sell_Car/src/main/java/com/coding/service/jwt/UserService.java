package com.coding.service.jwt;

import com.coding.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    UserDTO getUserByEmail(String email);
}
