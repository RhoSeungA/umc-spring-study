package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class MissionConverter {
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder().member(member).mission(mission).status(MissionStatus.CHALLENGING).build();
    }

    public static MissionResponseDTO.CreateMemberMissionResultDTO toCreateMemberMissionResultDTO(MemberMission memberMission){
        return MissionResponseDTO.CreateMemberMissionResultDTO.builder().missionId(memberMission.getId()).createdAt(memberMission.getCreatedAt()).build();

    }
}
