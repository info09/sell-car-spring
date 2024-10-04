package com.coding.dto;

import com.coding.enums.BidStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BidDTO {
    private Long id;
    private Long price;
    private Long userId;
    private Long carId;
    private BidStatus bidStatus;

    private String userName;
    private String carName;
    private String carBrand;
    private String email;
    private String sellerName;
}
