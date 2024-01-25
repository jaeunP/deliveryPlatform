package com.api.domain.user.business;

import com.api.common.annotiation.Business;
import com.api.domain.user.controller.model.UserRegisterRequest;
import com.api.domain.user.controller.model.UserResponse;
import com.api.domain.user.converter.UserConverter;
import com.api.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;

    /**
     * 사용자에 대한 가입 처리 로직 (request -> entity > save -> response)
     */
    public UserResponse register(UserRegisterRequest request) {

        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);

        return response;

//        return Optional.ofNullable(request)
//                .map(userConverter::toEntity)
//                .map(userService::register)
//                .map(userConverter::toResponse)
//                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "request null"));
    }
}
