package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements  MissionQueryService{
    private final  MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    @Override
    public Page<MemberMission> getMemberMissionList(Long memberId, Integer page) {
        Member member=memberRepository.findById(memberId).get();

        Page<MemberMission> memberMissions =memberMissionRepository.findAllByMemberAndStatus(member,MissionStatus.CHALLENGING,PageRequest.of(page, 10));
        return memberMissions;
    }
}
