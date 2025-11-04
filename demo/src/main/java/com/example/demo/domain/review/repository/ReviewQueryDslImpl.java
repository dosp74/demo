package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.entity.QReview;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.store.entity.QStore;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> findReviewsByFilter(Long memberId, String storeName, Integer starGroup) {
        QReview review = QReview.review;
        QStore store = QStore.store;

        // 기본 쿼리: 내가 작성한 리뷰
        var query = queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin()
                .where(review.member.id.eq(memberId));

        // 가게별
        if (storeName != null && !storeName.isEmpty()) {
            query.where(store.name.eq(storeName));
        }

        // 별점별
        if (starGroup != null) {
            double minStar = starGroup;
            double maxStar = starGroup + 0.9;
            query.where(review.star.between(minStar, maxStar));
        }

        // 최신순으로 정렬
        return query
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}
