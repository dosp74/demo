package com.example.demo.domain.mission.service.query;

import com.example.demo.domain.mission.converter.MissionConverter;
import com.example.demo.domain.mission.dto.res.MissionResDTO;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.domain.store.entity.Store;
import com.example.demo.domain.store.exception.StoreException;
import com.example.demo.domain.store.exception.code.StoreErrorCode;
import com.example.demo.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public MissionResDTO.MissionPreViewListDTO getMissions(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);

        return MissionConverter.toMissionPreViewListDTO(result);
    }
}
