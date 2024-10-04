package com.coding.service.admin;

import com.coding.dto.CarDTO;
import com.coding.entity.Car;
import com.coding.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final CarRepository carRepository;
    @Override
    public List<CarDTO> getCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public CarDTO getCarById(Long id) {
        var car = carRepository.findById(id);
        return car.map(Car::getCarDto).orElse(null);
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public boolean updateCar(Long id, CarDTO carDTO) throws IOException {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()){
            Car car = optionalCar.get();
            car.setName(carDTO.getName());
            car.setBrand(carDTO.getBrand());
            car.setType(carDTO.getType());
            car.setTransmission(carDTO.getTransmission());
            car.setColor(carDTO.getColor());
            car.setYear(carDTO.getYear());
            car.setDescription(carDTO.getDescription());
            if (carDTO.getImg() != null)
                car.setImg(carDTO.getImg().getBytes());
            car.setPrice(carDTO.getPrice());
            carRepository.save(car);
            return true;
        }
        return false;
    }
}
