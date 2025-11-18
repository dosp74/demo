package com.example.demo.domain.mission.service.command;

import com.example.demo.domain.mission.converter.MissionConverter;
import com.example.demo.domain.mission.dto.req.MissionReqDTO;
import com.example.demo.domain.mission.dto.res.MissionResDTO;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.domain.store.entity.Store;
import com.example.demo.domain.store.exception.StoreException;
import com.example.demo.domain.store.exception.code.StoreErrorCode;
import com.example.demo.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public MissionResDTO.CreateDTO createMission(Long storeId, MissionReqDTO.CreateDTO dto) {
        // Store 존재 여부 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(dto, store);
        missionRepository.save(mission);

        return MissionConverter.toCreateDTO(mission);
    }
}
