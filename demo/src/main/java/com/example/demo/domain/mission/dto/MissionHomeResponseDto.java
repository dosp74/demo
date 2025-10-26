package com.example.demo.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MissionHomeResponseDto {
    private String localName;
    private String storeName;
    private String content;
    private Integer point;
    private LocalDate deadline;
}
