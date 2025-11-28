package com.example.demo.domain.mission.controller;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.mission.dto.MemberMissionResponseDto;
import com.example.demo.domain.mission.dto.res.MissionResDTO;
import com.example.demo.domain.mission.service.command.MissionCommandService;
import com.example.demo.domain.mission.service.query.MemberMissionQueryService;
import com.example.demo.global.annotation.ValidPage;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MemberMissionController {
    private final MissionCommandService missionCommandService;
    private final MemberRepository memberRepository; // 사용자 임시 조회용
    private final MemberMissionQueryService memberMissionQueryService;

    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionResDTO.MemberMissionChallengeDTO> challengeMission(
            @PathVariable Long missionId
    ) {
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("해당 사용자가 존재하지 않습니다."));

        MissionResDTO.MemberMissionChallengeDTO response = missionCommandService.challengeMission(member, missionId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 내가 진행중인 미션 목록 조회
    @Operation(
            summary = "내가 진행 중인 미션 목록 조회 API By 제이",
            description = "사용자가 진행 중인 미션 목록을 페이지네이션으로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "page 형식 오류, 실패")
    })
    @GetMapping("/{memberId}/ongoing")
    public ApiResponse<List<MemberMissionResponseDto>> getOngoingMissions(
            @PathVariable Long memberId,
            @ValidPage @RequestParam String page
    ) {
        int pageNumber = Integer.parseInt(page);

        List<MemberMissionResponseDto> response = memberMissionQueryService.findOngoingMissions(memberId, pageNumber);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}
