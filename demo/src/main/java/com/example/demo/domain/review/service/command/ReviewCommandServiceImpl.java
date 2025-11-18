package com.example.demo.domain.review.service.command;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.exception.MemberException;
import com.example.demo.domain.member.exception.code.MemberErrorCode;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.review.converter.ReviewConverter;
import com.example.demo.domain.review.dto.req.ReviewReqDTO;
import com.example.demo.domain.review.dto.res.ReviewResDTO;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.repository.ReviewRepository;
import com.example.demo.domain.store.entity.Store;
import com.example.demo.domain.store.exception.StoreException;
import com.example.demo.domain.store.exception.code.StoreErrorCode;
import com.example.demo.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResDTO.CreateDTO createReview(Long storeId, ReviewReqDTO.CreateDTO dto) {
        // Store 검증
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.LOCAL_NOT_FOUND));

        // Member 검증
        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Review review = ReviewConverter.toReview(dto, member, store);
        reviewRepository.save(review);

        return ReviewConverter.toCreateDTO(review);
    }
}
