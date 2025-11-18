package com.example.demo.domain.store.controller;

import com.example.demo.domain.store.dto.req.StoreReqDTO;
import com.example.demo.domain.store.dto.res.StoreResDTO;
import com.example.demo.domain.store.service.command.StoreCommandService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreCommandService storeCommandService;

    @PostMapping
    public ApiResponse<StoreResDTO.CreateDTO> createStore(@RequestBody @Valid StoreReqDTO.CreateDTO dto) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, storeCommandService.createStore(dto));
    }
}
