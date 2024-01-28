package com.api.domain.user.controller;

import com.api.common.api.Api;
import com.api.domain.token.Controller.model.TokenResponse;
import com.api.domain.user.business.UserBusiness;
import com.api.domain.user.controller.model.UserLoginRequest;
import com.api.domain.user.controller.model.UserRegisterRequest;
import com.api.domain.user.controller.model.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/user")
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    @PostMapping("/register")
    public Api<UserResponse> register(@Valid @RequestBody Api<UserRegisterRequest> request) {

        var response = userBusiness.register(request.getBody());
        return Api.OK(response);
    }

    @PostMapping("/login")
    public Api<TokenResponse> request(@Valid @RequestBody Api<UserLoginRequest> request){

        var response = userBusiness.login(request.getBody());
        return Api.OK(response);

    }
}
