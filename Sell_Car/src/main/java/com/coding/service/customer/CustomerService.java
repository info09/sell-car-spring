package com.coding.service.customer;

import com.coding.dto.BidDTO;
import com.coding.dto.CarDTO;
import com.coding.dto.SearchDTO;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    boolean createCar(CarDTO carDTO) throws IOException;

    List<CarDTO> getCars();

    CarDTO getCarById(Long id);

    void deleteCarById(Long id);

    boolean updateCar(Long id, CarDTO carDTO) throws IOException;

    List<CarDTO> searchCar(SearchDTO searchDTO);

    List<CarDTO> getMyCar();

    boolean bidACar(BidDTO bidDTO);

    List<BidDTO> getBidsByUserId(Long userId);

    List<BidDTO> getBidsByCarId(Long carId);
}
