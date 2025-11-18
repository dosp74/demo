package com.example.demo.domain.store.service.command;

import com.example.demo.domain.store.converter.StoreConverter;
import com.example.demo.domain.store.dto.req.StoreReqDTO;
import com.example.demo.domain.store.dto.res.StoreResDTO;
import com.example.demo.domain.store.entity.Local;
import com.example.demo.domain.store.entity.Store;
import com.example.demo.domain.store.exception.StoreException;
import com.example.demo.domain.store.exception.code.StoreErrorCode;
import com.example.demo.domain.store.repository.LocalRepository;
import com.example.demo.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {
    private final LocalRepository localRepository;
    private final StoreRepository storeRepository;

    @Override
    public StoreResDTO.CreateDTO createStore(StoreReqDTO.CreateDTO dto) {
        // 지역 존재 여부 검증
        Local local = localRepository.findById(dto.localId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.LOCAL_NOT_FOUND));

        // 가게 이름 중복 검사
        if (storeRepository.existsByName(dto.name())) {
            throw new StoreException(StoreErrorCode.STORE_NAME_DUPLICATED);
        }

        // 사업자 번호 중복 검사
        if (storeRepository.existsByBossNumber(dto.bossNumber())) {
            throw new StoreException(StoreErrorCode.BOSS_NUMBER_DUPLICATED);
        }

        Store store = StoreConverter.toStore(dto, local);
        storeRepository.save(store);

        return StoreConverter.toCreateDTO(store);
    }
}
