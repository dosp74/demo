package com.example.demo.domain.review.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.review.dto.ReviewResponseDto;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.repository.ReviewRepository;
import com.example.demo.domain.store.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public void createReview(Member member, Store store, Float star, String content) {
        Review review = Review.builder()
                .member(member)
                .store(store)
                .star(star)
                .content(content)
                .build();

        reviewRepository.save(review);
    }

    public List<ReviewResponseDto> getMyReviews(Long memberId, String storeName, Integer starGroup) {
        List<Review> reviews = reviewRepository.findReviewsByFilter(memberId, storeName, starGroup);

        return reviews.stream()
                .map(ReviewResponseDto::from)
                .toList();
    }
}
