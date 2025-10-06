package com.example.demo.domain.member.entity;

import com.example.demo.domain.member.entity.mapping.MemberTerm;
import com.example.demo.domain.member.enums.TermName;
import com.example.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "term")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Term extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TermName name;

    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberTerm> memberTerms = new ArrayList<>();
}
