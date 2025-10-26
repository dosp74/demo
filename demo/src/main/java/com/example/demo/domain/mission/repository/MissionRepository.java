package com.example.demo.domain.mission.repository;

import com.example.demo.domain.mission.dto.MissionHomeResponseDto;
import com.example.demo.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("""
        SELECT new com.example.demo.domain.mission.dto.MissionHomeResponseDto(
            l.name, s.name, m.content, m.point, m.deadline
        )
        FROM Mission m
        JOIN m.store s
        JOIN s.local l
        WHERE l.id = :localId
            AND m.deadline >= :today
    """)
    List<MissionHomeResponseDto> findAvailableMissionsByLocal(
            @Param("localId") Long localId,
            @Param("today") LocalDate today,
            Pageable pageable
    );
}
