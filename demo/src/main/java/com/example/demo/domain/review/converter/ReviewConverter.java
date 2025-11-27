package com.example.demo.domain.review.converter;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.review.dto.req.ReviewReqDTO;
import com.example.demo.domain.review.dto.res.ReviewResDTO;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class ReviewConverter {
    // DTO -> 객체
    public static Review toReview(ReviewReqDTO.CreateDTO dto, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .star(dto.star())
                .content(dto.content())
                .build();
    }

    // 객체 -> DTO
    public static ReviewResDTO.CreateDTO toCreateDTO(Review review) {
        return ReviewResDTO.CreateDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .star(review.getStar())
                .content(review.getContent())
                .build();
    }

    // result -> DTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ) {
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(Review review) {
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getStar())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
