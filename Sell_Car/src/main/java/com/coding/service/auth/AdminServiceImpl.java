package com.coding.service.auth;

import com.coding.entity.User;
import com.coding.enums.UserRole;
import com.coding.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl  {
    private final UserRepository userRepository;

    @PostConstruct
    public void createAnAccountAdmin(){
        Optional<User> optionalUserAdmin = userRepository.findByUserRole(UserRole.ADMIN);
        if(optionalUserAdmin.isEmpty()){
            User user = User.builder()
                    .name("Admin")
                    .email("admin@gmail.com")
                    .password(new BCryptPasswordEncoder().encode("admin"))
                    .userRole(UserRole.ADMIN)
                    .build();
            userRepository.save(user);
            log.info("Admin account created");
        }else{
            log.info("Admin account already exists");
        }
    }
}
