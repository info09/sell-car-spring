package com.coding.controller;

import com.coding.dto.BidDTO;
import com.coding.dto.CarDTO;
import com.coding.dto.SearchDTO;
import com.coding.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PutMapping("/car/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @ModelAttribute CarDTO carDTO) throws IOException {
        var result = adminService.updateCar(id, carDTO);
        return result ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/car/search")
    public ResponseEntity<List<CarDTO>> searchCar(@RequestBody SearchDTO searchDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.searchCar(searchDTO));
    }

    @GetMapping("/car/bids")
    public ResponseEntity<List<BidDTO>> getBids() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getBids());
    }
}
