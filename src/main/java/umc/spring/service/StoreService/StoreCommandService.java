package umc.spring.service.StoreService;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReveiwDTO request);

    Store createStore(Long regionId, StoreRequestDTO.StoreDTO request);
}
