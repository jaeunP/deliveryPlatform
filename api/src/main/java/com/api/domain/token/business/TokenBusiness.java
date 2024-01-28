package com.api.domain.token.business;

import com.api.common.annotiation.Business;
import com.api.common.error.ErrorCode;
import com.api.common.exception.ApiException;
import com.api.domain.token.Controller.model.TokenResponse;
import com.api.domain.token.converter.TokenConverter;
import com.api.domain.token.service.TokenService;
import com.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class TokenBusiness {

    private final TokenService tokenService;
    private final TokenConverter tokenConverter;


    /**
     * 1. user entity -> user Id 추출
     * 2. access, refresh token 발행
     * 3. converter 로 token response 변경
     */
    public TokenResponse issueToken(UserEntity userEntity) {

        return Optional.ofNullable(userEntity.getId())
                .map(userId ->{
                    var accessToken = tokenService.issueAccessToken(userId);
                    var refreshToken = tokenService.issueRefreshToken(userId);

                    return tokenConverter.tokenResponse(accessToken, refreshToken);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
