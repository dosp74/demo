package com.example.demo.domain.test.dto.res;

import lombok.Builder;
import lombok.Getter;

public class TestResDTO {
    /*
    DTO들은 큰 묶음으로 클래스를 만들고, 내부적으로 static 클래스를 만드는 것이 좋다.
    DTO 자체는 수많은 곳에서 사용이 될 수 있기 때문에 static class로 만들면 매번 class 파일을 만들 필요도 없고, 범용적으로 DTO를 사용할 수 있다.
    */
    @Builder
    @Getter
    public static class Testing {
        private String testString;
    }

    @Builder
    @Getter
    public static class Exception {
        private String testString;
    }
}
