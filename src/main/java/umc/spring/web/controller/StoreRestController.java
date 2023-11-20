package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.mapping.MemberMission;
import umc.spring.service.StoreService.MissionCommandSerivce;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import javax.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private  final MissionCommandSerivce missionCommandSerivce;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(@RequestBody @Valid
                                                                                StoreRequestDTO.ReveiwDTO request,
                                                                             @ExistStore  @PathVariable(name = "storeId") Long storeId,
                                                                            @ExistMember @RequestParam(name = "memberId") Long memberId){
        Review review = storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));// review를 resultDTO로!
    }
    @PostMapping
    public ApiResponse<StoreResponseDTO.CreateStoreResultDTO> createStore(@RequestBody @Valid StoreRequestDTO.StoreDTO request,
                                                                           @RequestParam(name = "regionId") Long regionId){
        Store store = storeCommandService.createStore(regionId,request);
        return ApiResponse.onSuccess(StoreConverter.toCreateStoreResultDTO(store));
    }

    //가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
    @PostMapping("/mission/member")
    public ApiResponse<MissionResponseDTO.CreateMemberMissionResultDTO> createMemberMission
    (@RequestParam(name = "missionId") Long missionId
            ,@RequestParam(name = "memberId") Long memberId){
        MemberMission mission = missionCommandSerivce.createMemberMission(missionId,memberId);
        return ApiResponse.onSuccess(MissionConverter.toCreateMemberMissionResultDTO(mission));
    }




}