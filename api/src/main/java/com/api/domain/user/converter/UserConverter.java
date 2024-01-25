package com.api.domain.user.converter;

import com.api.common.annotiation.Converter;
import com.api.common.error.ErrorCode;
import com.api.common.exception.ApiException;
import com.api.domain.user.controller.model.UserRegisterRequest;
import com.api.domain.user.controller.model.UserResponse;
import com.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest request) {

         return Optional.ofNullable(request)
                .map(it -> UserEntity.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .address(request.getAddress())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, " UserRegisterRequest Null"));
    }

    public UserResponse toResponse(UserEntity userEntity) {

        return Optional.ofNullable(userEntity)
                .map(it -> UserResponse.builder()
                       .id(userEntity.getId())
                       .name(userEntity.getName())
                       .password(userEntity.getPassword())
                       .status(userEntity.getStatus())
                       .email(userEntity.getEmail())
                       .address(userEntity.getAddress())
                       .registeredAt(userEntity.getRegisteredAt())
                       .lastLoginAt(userEntity.getLastLoginAt())
                       .unregisteredAt(userEntity.getUnregisteredAt())
                       .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity Null"));
    }
}
