package com.example.demo.domain.store.dto.res;

import lombok.Builder;

public class StoreResDTO {
    @Builder
    public record CreateDTO(
            Long storeId,
            String storeName
    ) {}
}
