package com.example.demo.domain.mission.controller;

import com.example.demo.domain.mission.dto.req.MissionReqDTO;
import com.example.demo.domain.mission.dto.res.MissionResDTO;
import com.example.demo.domain.mission.service.command.MissionCommandService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class MissionController {
    private final MissionCommandService missionCommandService;

    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.CreateDTO> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateDTO dto
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, missionCommandService.createMission(storeId, dto));
    }
}
