package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements  StoreQueyrService{

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private  final MemberRepository memberRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page) {
        Store store = storeRepository.findById(StoreId).get();

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));//10은 사이즈!
        return StorePage;
    }

    @Override
    public Page<Review> getMemberReviewList(Long memberId, Integer page) {
        Member member= memberRepository.findById(memberId).get();
        Page<Review> reviews = reviewRepository.findAllByMember(member,PageRequest.of(page, 10));
        return reviews;

    }
}
