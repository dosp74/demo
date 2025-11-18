package com.example.demo.domain.mission.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MissionReqDTO {
    public record CreateDTO(
            @NotBlank String content,
            @NotNull Integer point,
            @NotNull LocalDate deadline
    ) {}
}
