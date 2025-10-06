package com.example.demo.domain.member.entity;

import com.example.demo.domain.member.entity.mapping.MemberPreferFood;
import com.example.demo.domain.member.enums.FoodName;
import com.example.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Food extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodName name;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberPreferFood> memberPreferFoods = new ArrayList<>();
}
