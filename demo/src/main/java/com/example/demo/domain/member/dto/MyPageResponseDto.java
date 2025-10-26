package com.example.demo.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPageResponseDto {
    private String name;
    private String email;
    private String phoneNumberStatus;
    private Integer point;
}
