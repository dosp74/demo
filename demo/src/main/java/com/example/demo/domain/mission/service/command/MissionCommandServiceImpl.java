package com.example.demo.domain.mission.service.command;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.mission.converter.MissionConverter;
import com.example.demo.domain.mission.dto.req.MissionReqDTO;
import com.example.demo.domain.mission.dto.res.MissionResDTO;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.mapping.MemberMission;
import com.example.demo.domain.mission.exception.MissionException;
import com.example.demo.domain.mission.exception.code.MissionErrorCode;
import com.example.demo.domain.mission.repository.MemberMissionRepository;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.domain.store.entity.Store;
import com.example.demo.domain.store.exception.StoreException;
import com.example.demo.domain.store.exception.code.StoreErrorCode;
import com.example.demo.domain.store.repository.StoreRepository;
import com.example.demo.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MissionResDTO.CreateDTO createMission(Long storeId, MissionReqDTO.CreateDTO dto) {
        // Store 존재 여부 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(dto, store);
        missionRepository.save(mission);

        return MissionConverter.toCreateDTO(mission);
    }

    @Override
    public MissionResDTO.MemberMissionChallengeDTO challengeMission(Member member, Long missionId) {
        // Mission 검증
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        // 이미 도전한 미션인지 확인
        memberMissionRepository.findByMemberAndMission(member, mission)
                .ifPresent(mm -> {
                    throw new MissionException(MissionErrorCode.MISSION_ALREADY_CHALLENGED);
                });

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .isComplete(false)
                .build();

        memberMissionRepository.save(memberMission);

        return MissionResDTO.MemberMissionChallengeDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(mission.getId())
                .isComplete(memberMission.getIsComplete())
                .build();
    }
}
