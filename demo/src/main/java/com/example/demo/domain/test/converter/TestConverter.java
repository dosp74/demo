package com.example.demo.domain.test.converter;

import com.example.demo.domain.test.dto.res.TestResDTO;

public class TestConverter {
    // 객체 -> DTO
    public static TestResDTO.Testing toTestingDTO(
            String testing
    ) {
        // 빌더 패턴으로 DTO를 조립
        return TestResDTO.Testing.builder()
                .testString(testing)
                .build();
    }

    // 객체 -> DTO
    public static TestResDTO.Exception toExceptionDTO(
            String testing
    ) {
        return TestResDTO.Exception.builder()
                .testString(testing)
                .build();
    }
}
