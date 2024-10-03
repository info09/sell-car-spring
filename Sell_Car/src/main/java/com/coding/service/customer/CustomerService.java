package com.coding.service.customer;

import com.coding.dto.CarDTO;

import java.io.IOException;

public interface CustomerService {
    boolean createCar(CarDTO carDTO) throws IOException;
}
