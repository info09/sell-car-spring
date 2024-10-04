package com.coding.entity;

import com.coding.dto.BidDTO;
import com.coding.enums.BidStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Car car;

    private BidStatus bidStatus;

    public BidDTO getBidDto() {
        return BidDTO.builder()
                .id(id)
                .price(price)
                .userId(user.getId())
                .carId(car.getId())
                .bidStatus(bidStatus)
                .email(user.getEmail())
                .userName(user.getName())
                .carBrand(car.getBrand())
                .carName(car.getName())
                .sellerName(car.getUser().getName())
                .build();
    }
}
