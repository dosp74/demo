package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.dto.req.ReviewReqDTO;
import com.example.demo.domain.review.dto.res.ReviewResDTO;
import com.example.demo.domain.review.service.command.ReviewCommandService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.CreateDTO dto
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, reviewCommandService.createReview(storeId, dto));
    }
}
