package com.example.demo.domain.review.dto;

import com.example.demo.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReviewResponseDto {
    private String storeName;
    private Float star;
    private String content;
    private LocalDateTime createdAt;

    public static ReviewResponseDto from(Review review) {
        return new ReviewResponseDto(
                review.getStore().getName(),
                review.getStar(),
                review.getContent(),
                review.getCreatedAt()
        );
    }
}
