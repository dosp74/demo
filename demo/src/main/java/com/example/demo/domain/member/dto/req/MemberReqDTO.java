package com.example.demo.domain.member.dto.req;

import com.example.demo.domain.member.enums.Address;
import com.example.demo.domain.member.enums.Gender;
import com.example.demo.global.annotation.ExistFoods;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO(
            @NotBlank
            String name,
            @Email
            String email,
            @NotBlank
            String password,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            Address address,
            @NotNull
            String detailAddress,
            // @ExistFoods
            List<Long> preferCategory
    ) {}
}
