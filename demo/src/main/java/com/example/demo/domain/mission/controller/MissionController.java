package com.example.demo.domain.mission.controller;

import com.example.demo.domain.mission.dto.req.MissionReqDTO;
import com.example.demo.domain.mission.dto.res.MissionResDTO;
import com.example.demo.domain.mission.service.command.MissionCommandService;
import com.example.demo.domain.mission.service.query.MissionQueryService;
import com.example.demo.global.annotation.ValidPage;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class MissionController {
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.CreateDTO> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateDTO dto
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, missionCommandService.createMission(storeId, dto));
    }

    // 특정 가게의 미션 목록 조회
    @Operation(
            summary = "특정 가게의 미션 목록 조회 API By 제이",
            description = "특정 가게의 미션 목록을 페이지네이션으로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "page 형식 오류, 실패")
    })
    @GetMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.MissionPreViewListDTO> getMissions(
            @PathVariable Long storeId,
            @ValidPage @RequestParam String page
    ) {
        int pageNumber = Integer.parseInt(page);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionQueryService.getMissions(storeId, pageNumber));
    }
}
