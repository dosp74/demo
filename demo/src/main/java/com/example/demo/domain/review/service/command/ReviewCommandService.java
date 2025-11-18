package com.example.demo.domain.review.service.command;

import com.example.demo.domain.review.dto.req.ReviewReqDTO;
import com.example.demo.domain.review.dto.res.ReviewResDTO;

public interface ReviewCommandService {
    ReviewResDTO.CreateDTO createReview(Long storeId, ReviewReqDTO.CreateDTO dto);
}
