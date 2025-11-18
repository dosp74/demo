package com.example.demo.domain.store.exception.code;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {
    LOCAL_NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE404_1",
            "해당 지역을 찾을 수 없습니다."),
    STORE_NAME_DUPLICATED(HttpStatus.BAD_REQUEST,
            "STORE400_1",
            "이미 존재하는 가게 이름입니다."),
    BOSS_NUMBER_DUPLICATED(HttpStatus.BAD_REQUEST,
            "STORE400_2",
            "이미 등록된 사업자 번호입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
