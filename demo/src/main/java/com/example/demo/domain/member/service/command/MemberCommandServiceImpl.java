package com.example.demo.domain.member.service.command;

import com.example.demo.domain.member.converter.MemberConverter;
import com.example.demo.domain.member.dto.req.MemberReqDTO;
import com.example.demo.domain.member.dto.res.MemberResDTO;
import com.example.demo.domain.member.entity.Food;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.mapping.MemberPreferFood;
import com.example.demo.domain.member.exception.FoodException;
import com.example.demo.domain.member.exception.code.FoodErrorCode;
import com.example.demo.domain.member.repository.FoodRepository;
import com.example.demo.domain.member.repository.MemberPreferFoodRepository;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.global.auth.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
    private final MemberPreferFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    @Transactional
    public MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    ) {
        // 솔트된 비밀번호 생성
        String salt = passwordEncoder.encode(dto.password());

        // 사용자 생성
        Member member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);

        // DB 적용
        memberRepository.save(member);

        List<Long> preferCategory = dto.preferCategory();

        if (preferCategory != null && !preferCategory.isEmpty()) {
            List<Food> foods = foodRepository.findAllById(preferCategory);

            if (!foods.isEmpty()) {
                List<MemberPreferFood> memberPreferFoods = foods.stream()
                        .map(food -> MemberPreferFood.builder()
                                .member(member)
                                .food(food)
                                .build())
                        .toList();

                memberFoodRepository.saveAll(memberPreferFoods);
            }
        }

//        // 선호 음식 존재 여부 확인
//        if (dto.preferCategory().size() > 1) {
//            List<MemberPreferFood> memberFoodList = new ArrayList<>();
//
//            // 선호 음식 ID별 조회
//            for (Long id : dto.preferCategory()) {
//                // 음식 존재 여부 검증
//                Food food = foodRepository.findById(id)
//                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));
//
//                // MemberPreferFood 엔티티 생성 (Converter 사용해야 함)
//                MemberPreferFood memberFood = MemberPreferFood.builder()
//                        .member(member)
//                        .food(food)
//                        .build();
//
//                // 사용자 - 음식(선호 음식) 추가
//                memberFoodList.add(memberFood);
//            }
//
//            // 모든 선호 음식 추가: DB 적용
//            memberFoodRepository.saveAll(memberFoodList);
//        }

        // 응답 DTO 생성
        return MemberConverter.toJoinDTO(member);
    }
}
