package com.example.demo.domain.mission.service.query;

import com.example.demo.domain.mission.dto.res.MissionResDTO;

public interface MissionQueryService {
    MissionResDTO.MissionPreViewListDTO getMissions(Long storeId, Integer page);
}
