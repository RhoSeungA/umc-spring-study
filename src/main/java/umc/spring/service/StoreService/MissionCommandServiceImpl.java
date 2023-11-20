package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandSerivce{

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    @Override
    public MemberMission createMemberMission(Long missionId, Long memberId) {
        Mission mission=missionRepository.findById(missionId).get();
        Member member = memberRepository.findById(memberId).get();

        MemberMission memberMission =
                MemberMission.builder().mission(mission).member(member).status(MissionStatus.CHALLENGING).build();
        return memberMissionRepository.save(memberMission);
    }
}
