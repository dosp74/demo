package com.example.demo.domain.mission.service.query;

import com.example.demo.domain.mission.dto.MemberMissionResponseDto;

import java.util.List;

public interface MemberMissionQueryService {
    List<MemberMissionResponseDto> findOngoingMissions(Long memberId, Integer page);
}
