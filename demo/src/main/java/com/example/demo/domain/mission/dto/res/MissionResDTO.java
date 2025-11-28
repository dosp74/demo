package com.example.demo.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {
    @Builder
    public record CreateDTO(
            Long missionId,
            Long storeId,
            String content,
            Integer point,
            LocalDate deadline
    ) {}

    @Builder
    public record MemberMissionChallengeDTO(
            Long memberMissionId,
            Long missionId,
            Boolean isComplete
    ) {}

    @Builder
    public record MissionPreViewListDTO(
            List<MissionPreViewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MissionPreViewDTO(
            Long missionId,
            String content,
            Integer point,
            LocalDate deadline
    ) {}
}
