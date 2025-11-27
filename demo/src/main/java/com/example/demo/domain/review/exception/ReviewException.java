package com.example.demo.domain.review.exception;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import com.example.demo.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
