package com.example.demo.domain.review.dto.req;

import jakarta.validation.constraints.*;

public class ReviewReqDTO {
    public record CreateDTO(
            @NotNull Long memberId,

            @NotNull
            @DecimalMin(value = "0.0")
            @DecimalMax(value = "5.0")
            Float star,

            @NotBlank
            String content
    ) {}
}
