package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {
    public static Review toReview(StoreRequestDTO.ReveiwDTO request){
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .build();
    }

    public static StoreResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review){
        return StoreResponseDTO.CreateReviewResultDTO.builder()
                .createdAt(LocalDateTime.now()).reviewId(review.getId()).build();

    }

    public static Store toStore(StoreRequestDTO.StoreDTO request) {
        return Store.builder().name(request.getName()).address(request.getAddress()).build();
    }

    public static StoreResponseDTO.CreateStoreResultDTO toCreateStoreResultDTO(Store store){
        return StoreResponseDTO.CreateStoreResultDTO.builder()
                .createdAt(LocalDateTime.now()).storeId(store.getId()).build();

    }


}
