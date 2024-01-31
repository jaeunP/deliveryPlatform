package com.api.domain.store.converter;

import com.api.common.annotiation.Converter;
import com.api.common.error.ErrorCode;
import com.api.common.exception.ApiException;
import com.api.domain.store.controller.model.StoreRegisterRequest;
import com.api.domain.store.controller.model.StoreResponse;
import com.db.store.StoreEntity;

import java.util.Optional;

@Converter
public class StoreConverter {

    public StoreEntity toEntity(StoreRegisterRequest request) {

        return StoreEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .category(request.getStoreCategory())
                .minimumAmount(request.getMinimumAmount())
                .minimumDeliveryAmount(request.getMinimumDeliveryAmount())
                .thumbnailUrl(request.getThumbnailUrl())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    public StoreResponse toResponse(StoreEntity entity){
        return Optional.ofNullable(entity)
                .map(it ->{
                    return StoreResponse.builder()
                            .id(entity.getId())
                            .name(entity.getName())
                            .status(entity.getStatus())
                            .category(entity.getCategory())
                            .address(entity.getAddress())
                            .minimumAmount(entity.getMinimumAmount())
                            .minimumDeliveryAmount(entity.getMinimumDeliveryAmount())
                            .thumbnailUrl(entity.getThumbnailUrl())
                            .phoneNumber(entity.getPhoneNumber())
                            .star(entity.getStar())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
