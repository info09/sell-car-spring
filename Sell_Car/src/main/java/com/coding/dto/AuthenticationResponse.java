package com.coding.dto;

import com.coding.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;
    private Long userId;
    private UserRole userRole;
}
