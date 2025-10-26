package com.example.demo.domain.mission.repository;

import com.example.demo.domain.mission.dto.MemberMissionResponseDto;
import com.example.demo.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    // 진행중 미션(isComplete = false)
    @Query("""
        SELECT new com.example.demo.domain.mission.dto.MemberMissionResponseDto(
            s.name, m.content, m.point, mm.isComplete
        )
        FROM MemberMission mm
        JOIN mm.mission m
        JOIN m.store s
        WHERE mm.member.id = :memberId
            AND mm.isComplete = false
    """)
    List<MemberMissionResponseDto> findOngoingMissions(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 진행 완료한 미션(isComplete = true)
    @Query("""
        SELECT new com.example.demo.domain.mission.dto.MemberMissionResponseDto(
            s.name, m.content, m.point, mm.isComplete
        )
        FROM MemberMission mm
        JOIN mm.mission m
        JOIN m.store s
        WHERE mm.member.id = :memberId
            AND mm.isComplete = true
    """)
    List<MemberMissionResponseDto> findCompletedMissions(
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}
