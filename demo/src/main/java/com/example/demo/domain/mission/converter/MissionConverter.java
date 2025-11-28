package com.example.demo.domain.mission.converter;

import com.example.demo.domain.mission.dto.req.MissionReqDTO;
import com.example.demo.domain.mission.dto.res.MissionResDTO;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.store.entity.Store;
import org.springframework.data.domain.Page;

public class MissionConverter {
    // DTO -> 객체
    public static Mission toMission(MissionReqDTO.CreateDTO dto, Store store) {
        return Mission.builder()
                .store(store)
                .content(dto.content())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    // 객체 -> DTO
    public static MissionResDTO.CreateDTO toCreateDTO(Mission mission) {
        return MissionResDTO.CreateDTO.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .content(mission.getContent())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResDTO.MissionPreViewListDTO toMissionPreViewListDTO(
            Page<Mission> result
    ) {
        return MissionResDTO.MissionPreViewListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toMissionPreViewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.MissionPreViewDTO toMissionPreViewDTO(Mission mission) {
        return MissionResDTO.MissionPreViewDTO.builder()
                .missionId(mission.getId())
                .content(mission.getContent())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }
}
