package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder().member(member).mission(mission).status(MissionStatus.CHALLENGING).build();
    }

    public static MissionResponseDTO.CreateMemberMissionResultDTO toCreateMemberMissionResultDTO(MemberMission memberMission){
        return MissionResponseDTO.CreateMemberMissionResultDTO.builder().missionId(memberMission.getId()).createdAt(memberMission.getCreatedAt()).build();
    }

    public static MissionResponseDTO.MemberMissionPreViewListDTO toMemberMissionPreViewListDTO(Page<MemberMission> memberMissions){
        List<MissionResponseDTO.MemberMissionDTO> memberMissionDTOList = memberMissions.stream().map(MissionConverter::toMemberMissionDTO).collect(Collectors.toList());
        return MissionResponseDTO.MemberMissionPreViewListDTO.builder().isLast(memberMissions.isLast())
                .isFirst(memberMissions.isFirst())
                .totalPage(memberMissions.getTotalPages())
                .totalElements(memberMissions.getTotalElements())
                .listSize(memberMissionDTOList.size())
                .MissionList(memberMissionDTOList)
                .build();
    }

    //MemberMission 하나를 FindMemberMissionDTO로
    public static MissionResponseDTO.MemberMissionDTO toMemberMissionDTO(MemberMission memberMission){
        Mission mission = memberMission.getMission();
        return MissionResponseDTO.MemberMissionDTO.builder().id(memberMission.getId()).reward(mission.getReward())
                .deadline(mission.getDeadline()).missionSpec(mission.getMissionSpec()).store_name(mission.getStore().getName())
                .store_region(mission.getStore().getRegion()).build();
    }


}
