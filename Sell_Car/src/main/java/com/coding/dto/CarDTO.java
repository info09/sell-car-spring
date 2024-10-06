package com.coding.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Builder
public class CarDTO {
    private Long id;
    private String name;
    private String brand;
    private String type;
    private String transmission;
    private String color;
    private String year;
    private String modelYear;
    private Boolean sold;
    private String description;
    private MultipartFile img;
    private Long userId;
    private Long price;
    private byte[] returnedImg;
}
