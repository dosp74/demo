package com.example.demo.global.validator;

import com.example.demo.global.annotation.ValidPage;
import com.example.demo.global.exception.PageException;
import com.example.demo.global.exception.code.PageErrorCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageValidator implements ConstraintValidator<ValidPage, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            throw new PageException(PageErrorCode.PAGE_INVALID);
        }

        try {
            int page = Integer.parseInt(value);

            if (page < 1) {
                throw new PageException(PageErrorCode.PAGE_INVALID);
            }
        } catch (NumberFormatException e) {
            throw new PageException(PageErrorCode.PAGE_INVALID);
        }

        return true;
    }
}
