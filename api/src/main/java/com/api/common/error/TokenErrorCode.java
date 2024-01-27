package com.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * User의 경우 2000번대를 사용
 */
@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeIfs{

    INVALID_TOKEN(400, 2000, "유효하지 않은 토큰"),

    EXPIRED_TOKEN(400, 2001, "만료된 토큰"),

    TOKEN_EXCEPTION(400, 2002, "알 수 없는 토큰 에러");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
