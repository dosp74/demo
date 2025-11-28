package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.dto.req.ReviewReqDTO;
import com.example.demo.domain.review.dto.res.ReviewResDTO;
import com.example.demo.domain.review.exception.code.ReviewSuccessCode;
import com.example.demo.domain.review.service.command.ReviewCommandService;
import com.example.demo.domain.review.service.query.ReviewQueryService;
import com.example.demo.global.annotation.ValidPage;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewControllerDocs {
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping("/api/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.CreateDTO dto
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, reviewCommandService.createReview(storeId, dto));
    }

    // 가게의 리뷰 목록 조회
//    @Operation( // API 설명 작성
//            summary = "가게의 리뷰 목록 조회 API By 마크 (개발 중)", // API 제목
//            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다." // API 설명
//    )
//    @ApiResponses({ // 코드와 응답 메시지 작성(@ApiResponse 묶음)
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
//    })
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;

        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }

    @Operation(
            summary = "내가 작성한 리뷰 목록 조회 API By 제이",
            description = "사용자가 작성한 리뷰 목록을 페이지네이션으로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "page 형식 오류, 실패")
    })
    @GetMapping("/api/members/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviews(
            @PathVariable Long memberId,
            @ValidPage @RequestParam String page // null, 0, 음수 검증을 위해 String 자료형 사용
    ) {
        int pageNumber = Integer.parseInt(page);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewQueryService.findMyReviews(memberId, pageNumber));
    }
}
