package com.example.demo.domain.review.dto.res;

import lombok.Builder;

public class ReviewResDTO {
    @Builder
    public record CreateDTO(
            Long reviewId,
            Long storeId,
            Float star,
            String content
    ) {}
}
