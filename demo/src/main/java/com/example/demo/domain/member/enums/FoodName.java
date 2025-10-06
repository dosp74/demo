package com.example.demo.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FoodName {
    KOREAN("한식"),
    JAPANESE("일식"),
    CHINESE("중식"),
    WESTERN("양식"),
    CHICKEN("치킨"),
    SNACK("분식"),
    BBQ("고기구이"),
    LUNCHBOX("도시락"),
    NIGHTFOOD("야식"),
    FASTFOOD("패스트푸드"),
    DESSERT("디저트"),
    ASIANFOOD("아시안푸드");

    private final String displayName;
}
