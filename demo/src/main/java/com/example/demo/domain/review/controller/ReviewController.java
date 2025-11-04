package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.dto.ReviewResponseDto;
import com.example.demo.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/test")
    public List<ReviewResponseDto> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer starGroup
    ) {
        return reviewService.getMyReviews(memberId, storeName, starGroup);
    }
}
