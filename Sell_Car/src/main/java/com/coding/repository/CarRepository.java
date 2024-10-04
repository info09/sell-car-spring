package com.coding.repository;

import com.coding.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByUserId(Long id);

    Long countByUserId(Long userId);

    Long countByUserIdAndSoldTrue(Long userId);
}
