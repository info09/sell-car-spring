package com.coding.service.customer;

import com.coding.dto.AnalyticsDTO;
import com.coding.dto.BidDTO;
import com.coding.dto.CarDTO;
import com.coding.dto.SearchDTO;
import com.coding.entity.Bid;
import com.coding.entity.Car;
import com.coding.entity.User;
import com.coding.enums.BidStatus;
import com.coding.repository.BidRepository;
import com.coding.repository.CarRepository;
import com.coding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final BidRepository bidRepository;

    @Override
    public boolean createCar(CarDTO carDTO) throws IOException {
        Optional<User> optionalUser = userRepository.findById(carDTO.getUserId());
        if (optionalUser.isPresent()) {
            Car car = Car.builder()
                    .name(carDTO.getName())
                    .brand(carDTO.getBrand())
                    .type(carDTO.getType())
                    .transmission(carDTO.getTransmission())
                    .color(carDTO.getColor())
                    .year(carDTO.getModelYear())
                    .sold(false)
                    .description(carDTO.getDescription())
                    .img(carDTO.getImg().getBytes())
                    .price(carDTO.getPrice())
                    .user(optionalUser.get()).build();
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

    @Override
    public List<CarDTO> getMyCar(Long userId) {
        return carRepository.findAllByUserId(userId).stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public boolean bidACar(BidDTO bidDTO) {
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        var optionalUser = userRepository.findByEmail(userName);
        if (optionalUser.isPresent()) {
            Optional<Car> optionalCar = carRepository.findById(bidDTO.getCarId());
            if (optionalCar.isPresent()) {
                Bid bid = Bid.builder()
                        .user(optionalUser.get())
                        .car(optionalCar.get())
                        .price(bidDTO.getPrice())
                        .bidStatus(BidStatus.PENDING)
                        .build();
                bidRepository.save(bid);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<BidDTO> getBidsByUserId(Long userId) {
        return bidRepository.findAllByUserId(userId).stream().map(Bid::getBidDto).collect(Collectors.toList());
    }

    @Override
    public List<BidDTO> getBidsByCarId(Long carId) {
        return bidRepository.findAllByCarId(carId).stream().map(Bid::getBidDto).collect(Collectors.toList());
    }

    @Override
    public boolean changeBidStatus(Long id, String status) {
        Optional<Bid> optionalBid = bidRepository.findById(id);
        if (optionalBid.isPresent()) {
            Bid exsitingBid = optionalBid.get();
            if (Boolean.TRUE.equals(exsitingBid.getCar().getSold())) {
                return false;
            }
            if (Objects.equals(status, BidStatus.APPROVED.name())) {
                exsitingBid.setBidStatus(BidStatus.APPROVED);
            } else {
                exsitingBid.setBidStatus(BidStatus.REJECTED);
            }
            bidRepository.save(exsitingBid);
            return true;
        }
        return false;
    }

    @Override
    public AnalyticsDTO getAnalytics(Long userId) {
        AnalyticsDTO analyticsDTO = new AnalyticsDTO();
        analyticsDTO.setTotalCars(carRepository.countByUserId(userId));
        analyticsDTO.setTotalSoldCars(carRepository.countByUserIdAndSoldTrue(userId));
        return analyticsDTO;
    }
}
