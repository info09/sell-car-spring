package com.coding.controller;

import com.coding.dto.CarDTO;
import com.coding.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
@CrossOrigin("*")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/car")
    public ResponseEntity<?> addCar(@ModelAttribute CarDTO carDTO) throws IOException {
        var result =  customerService.createCar(carDTO);
        return result ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @ModelAttribute CarDTO carDTO) throws IOException {
        var result =  customerService.updateCar(id, carDTO);
        return result ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getCars() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCars());
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCarById(id));
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<?> deleteCarById(@PathVariable Long id) {
        customerService.deleteCarById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
