package com.example.demo.domain.mission.service.command;

import com.example.demo.domain.mission.dto.req.MissionReqDTO;
import com.example.demo.domain.mission.dto.res.MissionResDTO;

public interface MissionCommandService {
    MissionResDTO.CreateDTO createMission(Long storeId, MissionReqDTO.CreateDTO dto);
}
