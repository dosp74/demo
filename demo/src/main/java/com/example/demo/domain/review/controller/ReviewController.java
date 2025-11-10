package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.dto.ReviewResponseDto;
import com.example.demo.domain.review.service.ReviewService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.GeneralSuccessCode;
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
    public ApiResponse<List<ReviewResponseDto>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer starGroup
    ) {
        List<ReviewResponseDto> reviews = reviewService.getMyReviews(memberId, storeName, starGroup);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviews
        );
    }
}
