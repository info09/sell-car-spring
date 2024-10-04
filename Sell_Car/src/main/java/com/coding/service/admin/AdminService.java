package com.coding.service.admin;

import com.coding.dto.CarDTO;

import java.util.List;

public interface AdminService {
    List<CarDTO> getCars();
    CarDTO getCarById(Long id);
    void deleteCarById(Long id);
}
