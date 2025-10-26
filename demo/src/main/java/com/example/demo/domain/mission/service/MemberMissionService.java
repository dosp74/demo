package com.example.demo.domain.mission.service;

import com.example.demo.domain.mission.dto.MemberMissionResponseDto;
import com.example.demo.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionService {
    private final MemberMissionRepository memberMissionRepository;

    public List<MemberMissionResponseDto> getOngoingMissions(Long memberId, int page, int size) {
        return memberMissionRepository.findOngoingMissions(memberId, PageRequest.of(page, size));
    }

    public List<MemberMissionResponseDto> getCompletedMissions(Long memberId, int page, int size) {
        return memberMissionRepository.findCompletedMissions(memberId, PageRequest.of(page, size));
    }
}
