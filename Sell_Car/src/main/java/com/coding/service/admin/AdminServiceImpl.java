package com.coding.service.admin;

import com.coding.dto.CarDTO;
import com.coding.dto.SearchDTO;
import com.coding.entity.Car;
import com.coding.repository.CarRepository;
import com.coding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;

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
        if (optionalCar.isPresent()) {
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

    @Override
    public List<CarDTO> searchCar(SearchDTO searchDTO) {
        Car car = Car.builder()
                .type(searchDTO.getType())
                .color(searchDTO.getColor())
                .name(searchDTO.getName())
                .brand(searchDTO.getBrand())
                .year(searchDTO.getYear())
                .transmission(searchDTO.getTransmission())
                .build();

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("brand", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("transmission", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("year", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Car> example = Example.of(car, exampleMatcher);
        return carRepository.findAll(example).stream().map(Car::getCarDto).collect(Collectors.toList());
    }
}
