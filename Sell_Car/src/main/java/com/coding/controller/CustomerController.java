package com.coding.controller;

import com.coding.dto.AnalyticsDTO;
import com.coding.dto.BidDTO;
import com.coding.dto.CarDTO;
import com.coding.dto.SearchDTO;
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
        var result = customerService.createCar(carDTO);
        return result ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @ModelAttribute CarDTO carDTO) throws IOException {
        var result = customerService.updateCar(id, carDTO);
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

    @GetMapping("/car/search")
    public ResponseEntity<List<CarDTO>> searchCar(@RequestBody SearchDTO searchDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.searchCar(searchDTO));
    }

    @GetMapping("/my-car/{userId}")
    public ResponseEntity<List<CarDTO>> getMyCar(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getMyCar(userId));
    }

    @PostMapping("/car/bid")
    public ResponseEntity<?> bidACar(@RequestBody BidDTO bidDTO) {
        var result = customerService.bidACar(bidDTO);
        return result ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/car/bid/{userId}")
    public ResponseEntity<List<BidDTO>> getBidsByUserId(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getBidsByUserId(userId));
    }

    @GetMapping("/car/{carId}/bid")
    public ResponseEntity<List<BidDTO>> getBidsByCarId(@PathVariable Long carId) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getBidsByCarId(carId));
    }

    @GetMapping("/car/bid/{bidId}/{status}")
    public ResponseEntity<?> changeBidStatus(@PathVariable Long bidId, @PathVariable String status) {
        var result = customerService.changeBidStatus(bidId, status);
        return result ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/car/analytics/{userId}")
    public ResponseEntity<AnalyticsDTO> getAnalyticsByUserId(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAnalytics(userId));
    }
}
