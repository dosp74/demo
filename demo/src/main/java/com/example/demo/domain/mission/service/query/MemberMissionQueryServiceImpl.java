package com.example.demo.domain.mission.service.query;

import com.example.demo.domain.mission.dto.MemberMissionResponseDto;
import com.example.demo.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public List<MemberMissionResponseDto> findOngoingMissions(Long memberId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        return memberMissionRepository.findOngoingMissions(memberId, pageRequest);
    }
}
