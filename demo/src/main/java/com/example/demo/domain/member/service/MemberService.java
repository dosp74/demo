package com.example.demo.domain.member.service;

import com.example.demo.domain.member.dto.MyPageResponseDto;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MyPageResponseDto getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));

        String phoneNumberStatus =
                (member.getPhoneNumber() == null) ? "미인증" : member.getPhoneNumber();

        return new MyPageResponseDto(
                member.getName(),
                member.getEmail(),
                phoneNumberStatus,
                member.getPoint()
        );
    }
}
