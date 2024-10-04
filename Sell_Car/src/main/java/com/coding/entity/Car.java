package com.coding.entity;

import com.coding.dto.CarDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private String type;
    private String transmission;
    private String color;
    private String year;
    private Boolean sold;
    @Lob
    @Column(columnDefinition = "longblob")
    private String description;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public CarDTO getCarDto() {
        return CarDTO.builder()
                .id(id)
                .name(name)
                .brand(brand)
                .type(type)
                .transmission(transmission)
                .color(color)
                .year(year)
                .sold(sold)
                .description(description)
                .price(price)
                .userId(user.getId())
                .returnedImg(img)
                .build();
    }
}
