package com.example.demo.global.exception;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import com.example.demo.global.apiPayload.exception.GeneralException;

public class PageException extends GeneralException {
    public PageException(BaseErrorCode code) {
        super(code);
    }
}
