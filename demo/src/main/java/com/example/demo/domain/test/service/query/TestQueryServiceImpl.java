package com.example.demo.domain.test.service.query;

import com.example.demo.domain.test.exception.TestException;
import com.example.demo.domain.test.exception.code.TestErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestQueryServiceImpl implements TestQueryService {
    @Override
    public void checkFlag(Long flag) {
        if (flag == 1L) {
            throw new TestException(TestErrorCode.TEST_EXCEPTION);
        }
    }
}
