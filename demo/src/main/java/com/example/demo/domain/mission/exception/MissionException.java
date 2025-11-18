package com.example.demo.domain.mission.exception;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import com.example.demo.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(BaseErrorCode code) {
        super(code);
    }
}