package com.example.demo.domain.member.entity;

import com.example.demo.domain.member.entity.mapping.MemberPreferFood;
import com.example.demo.domain.member.entity.mapping.MemberTerm;
import com.example.demo.domain.member.enums.Address;
import com.example.demo.domain.member.enums.Gender;
import com.example.demo.domain.member.enums.SocialType;
import com.example.demo.domain.member.enums.Status;
import com.example.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Member extends BaseEntity {
    @Column(nullable = false, unique = true, length = 30)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Address address;

    @Column(name = "detail_address", length = 100)
    private String detailAddress;

    @Column(unique = true, length = 100)
    private String email;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer point = 0;

    @Column(name = "social_uid")
    private String socialUid;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type")
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "inactive_date")
    private LocalDateTime inactiveDate;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberTerm> memberTerms = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberPreferFood> memberPreferFoods = new ArrayList<>();
}
