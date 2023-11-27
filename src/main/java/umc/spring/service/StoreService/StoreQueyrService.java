package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

import java.util.Optional;

public interface StoreQueyrService {
    Optional<Store> findStore(Long id);

    Page<Review> getReviewList(Long StoreId, Integer page); //가게 id와 페이지 수 주면, review 반환

    Page<Review> getMemberReviewList(Long memberId, Integer page);
}
