package com.example.demo.domain.mission.controller;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.mission.dto.res.MissionResDTO;
import com.example.demo.domain.mission.service.command.MissionCommandService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MemberMissionController {
    private final MissionCommandService missionCommandService;
    private final MemberRepository memberRepository; // 사용자 임시 조회용

    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionResDTO.MemberMissionChallengeDTO> challengeMission(
            @PathVariable Long missionId
    ) {
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("해당 사용자가 존재하지 않습니다."));

        MissionResDTO.MemberMissionChallengeDTO response = missionCommandService.challengeMission(member, missionId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}
