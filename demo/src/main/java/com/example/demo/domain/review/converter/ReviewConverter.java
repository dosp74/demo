package com.example.demo.domain.review.converter;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.review.dto.req.ReviewReqDTO;
import com.example.demo.domain.review.dto.res.ReviewResDTO;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.store.entity.Store;

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
}
