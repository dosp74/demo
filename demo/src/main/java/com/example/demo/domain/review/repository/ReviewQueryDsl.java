package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryDsl {
    List<Review> findReviewsByFilter(Long memberId, String storeName, Integer starGroup);
}
