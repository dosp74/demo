package com.example.demo.domain.store.service.command;

import com.example.demo.domain.store.dto.req.StoreReqDTO;
import com.example.demo.domain.store.dto.res.StoreResDTO;

public interface StoreCommandService {
    StoreResDTO.CreateDTO createStore(StoreReqDTO.CreateDTO dto);
}
