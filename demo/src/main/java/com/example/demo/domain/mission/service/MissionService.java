package com.example.demo.domain.mission.service;

import com.example.demo.domain.mission.dto.MissionHomeResponseDto;
import com.example.demo.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {
    private final MissionRepository missionRepository;

    public List<MissionHomeResponseDto> getAvailableMissions(Long localId, int page, int size) {
        return missionRepository.findAvailableMissionsByLocal(
                localId,
                LocalDate.now(),
                PageRequest.of(page, size)
        );
    }
}
