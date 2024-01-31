package com.api.domain.store.controller.model;

import com.db.store.enums.StoreCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRegisterRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    private StoreCategory storeCategory;


    @NotBlank
    private String thumbnailUrl;

    @NotNull
    private BigDecimal minimumAmount;

    @NotBlank
    private BigDecimal minimumDeliveryAmount;

    @NotBlank
    private String phoneNumber;

}
