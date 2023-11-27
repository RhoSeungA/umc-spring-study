package umc.spring.web.dto;

import lombok.*;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMemberMissionResultDTO{
        Long missionId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionDTO{
        private Long id;

        private Integer reward;

        private LocalDate deadline;

        private String missionSpec;

        private String store_name;

        private Region store_region;

        private String status;

    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionPreViewListDTO{
        List<MissionResponseDTO.MemberMissionDTO> MissionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateMemberMissionResultDTO{
        Long missionId;
        LocalDateTime updateAt;
    }
}
