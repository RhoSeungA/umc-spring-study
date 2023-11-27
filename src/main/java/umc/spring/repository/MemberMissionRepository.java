package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.mapping.MemberMission;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission,Long> {
    Page<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus status, PageRequest pageRequest);

}
