package com.example.demo.domain.store.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StoreReqDTO {
    // private final 필드, 생성자, getter, toString, equals, hashCode가 전부 자동 생성된다.
    public record CreateDTO(
            @NotNull Long localId,
            @NotBlank String name,
            @NotNull Long bossNumber,
            @NotBlank String address
    ) {}
}
