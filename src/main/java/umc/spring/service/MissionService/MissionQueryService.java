package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.mapping.MemberMission;

public interface MissionQueryService {
    Page<MemberMission> getMemberMissionList(Long memberId, Integer page);
}
