package com.coding.service.admin;

import com.coding.dto.CarDTO;
import com.coding.dto.SearchDTO;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    List<CarDTO> getCars();
    CarDTO getCarById(Long id);
    void deleteCarById(Long id);

    boolean updateCar(Long id, CarDTO carDTO) throws IOException;

    List<CarDTO> searchCar(SearchDTO searchDTO);
}
