package com.example.demo.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDate;

public class MissionResDTO {
    @Builder
    public record CreateDTO(
            Long missionId,
            Long storeId,
            String content,
            Integer point,
            LocalDate deadline
    ) {}
}
