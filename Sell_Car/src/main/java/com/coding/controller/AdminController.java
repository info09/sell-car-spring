package com.coding.controller;

import com.coding.dto.CarDTO;
import com.coding.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getCars() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getCars());
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getCarById(id));
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<?> deleteCarById(@PathVariable Long id) {
        adminService.deleteCarById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
