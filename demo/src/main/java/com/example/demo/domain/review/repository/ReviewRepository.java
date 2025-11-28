package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
    Page<Review> findAllByMember_Id(Long memberId, PageRequest pageRequest);
}
