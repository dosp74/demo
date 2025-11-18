package com.example.demo.domain.store.converter;

import com.example.demo.domain.store.dto.req.StoreReqDTO;
import com.example.demo.domain.store.dto.res.StoreResDTO;
import com.example.demo.domain.store.entity.Local;
import com.example.demo.domain.store.entity.Store;

public class StoreConverter {
    // DTO -> 객체
    public static Store toStore(StoreReqDTO.CreateDTO dto, Local local) {
        return Store.builder()
                .local(local)
                .name(dto.name())
                .bossNumber(dto.bossNumber())
                .address(dto.address())
                .build();
    }

    // 객체 -> DTO
    public static StoreResDTO.CreateDTO toCreateDTO(Store store) {
        return StoreResDTO.CreateDTO.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .build();
    }
}
