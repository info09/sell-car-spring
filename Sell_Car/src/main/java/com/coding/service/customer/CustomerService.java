package com.coding.service.customer;

import com.coding.dto.CarDTO;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    boolean createCar(CarDTO carDTO) throws IOException;

    List<CarDTO> getCars();
}
