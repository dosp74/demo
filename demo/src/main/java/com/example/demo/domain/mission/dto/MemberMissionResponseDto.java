package com.example.demo.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberMissionResponseDto {
    private String storeName;
    private String content;
    private Integer point;
    private Boolean isComplete;
}
