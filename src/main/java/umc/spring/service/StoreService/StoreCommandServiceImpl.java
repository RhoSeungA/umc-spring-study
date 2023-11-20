package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{

    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    @Override
    public Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReveiwDTO request) {
        Review review = StoreConverter.toReview(request); //requestDTO를 review로 바꿔줌
        review.setStore(storeRepository.findById(storeId).get());
        review.setMember(memberRepository.findById(memberId).get());
        return reviewRepository.save(review);

    }

    @Override
    public Store createStore(Long regionId, StoreRequestDTO.StoreDTO request) {
        Store store= StoreConverter.toStore(request);
        store.setRegion(regionRepository.findById(regionId).get());
        return storeRepository.save(store);
    }
}
