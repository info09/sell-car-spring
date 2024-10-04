package com.coding.service.customer;

import com.coding.dto.CarDTO;
import com.coding.entity.Car;
import com.coding.entity.User;
import com.coding.repository.CarRepository;
import com.coding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final UserRepository  userRepository;
    private final CarRepository  carRepository;
    @Override
    public boolean createCar(CarDTO carDTO) throws IOException {
        Optional<User> optionalUser = userRepository.findById( carDTO.getUserId());
        if (optionalUser.isPresent()){
            Car car = Car.builder()
                    .name(carDTO.getName())
                    .brand( carDTO.getBrand())
                    .type( carDTO.getType())
                    .transmission( carDTO.getTransmission())
                    .color( carDTO.getColor())
                    .year( carDTO.getYear())
                    .sold( false)
                    .description( carDTO.getDescription())
                    .img( carDTO.getImg().getBytes())
                    .price( carDTO.getPrice())
                    .user( optionalUser.get()).build();
            carRepository.save(car);
            return true;
        }
        return false;
    }

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
}
