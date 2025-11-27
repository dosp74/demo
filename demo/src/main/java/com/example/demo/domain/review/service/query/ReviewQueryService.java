package com.example.demo.domain.review.service.query;

import com.example.demo.domain.review.dto.res.ReviewResDTO;

public interface ReviewQueryService {
    ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page);
}
