package com.example.demo.global.exception.code;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PageErrorCode implements BaseErrorCode {
    PAGE_INVALID(HttpStatus.BAD_REQUEST,
            "PAGE400_1",
            "page는 1 이상의 정수여야 합니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
