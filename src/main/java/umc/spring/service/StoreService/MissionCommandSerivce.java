package umc.spring.service.StoreService;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.mapping.MemberMission;

public interface MissionCommandSerivce {
    MemberMission createMemberMission(Long missionId, Long memberId);
}
