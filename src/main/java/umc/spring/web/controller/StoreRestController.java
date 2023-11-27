package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.data.domain.Page;
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
import umc.spring.service.StoreService.StoreQueyrService;
import umc.spring.validation.annotation.CheckPage;
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
    private  final StoreQueyrService storeQueyrService;

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

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,@RequestParam Integer page){
        Page<Review> result=storeQueyrService.getReviewList(storeId,page);
        return  ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(result));
    }


    // 내가 작성한 리뷰 목록
    @GetMapping("/mission/member/{memberId}")
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getMemberReviewList(@ExistMember @PathVariable(name = "memberId") Long memberId, @CheckPage @RequestParam Integer page){
        Page<Review> result = storeQueyrService.getMemberReviewList(memberId,page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(result));
    }

    //2. 내가 진행중인 미션 목록

    //3. 진행중인 미션 진행 완료로 바꾸기



}