package com.bugratasdemir.graduationprojectapi.controller;

import com.bugratasdemir.graduationprojectapi.dto.RecommentDTO;
import com.bugratasdemir.graduationprojectapi.general.RestResponse;
import com.bugratasdemir.graduationprojectapi.request.SuggestedRequest;
import com.bugratasdemir.graduationprojectapi.service.RecommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/recomments")
@Tag(name = "Recomment Controller", description = "Recomment Management")
public class RecommentController {

    private final RecommentService recommentService;

    @PostMapping
    @Operation(summary = "Recommended Restaurants",description = "Since requests are made to this API from within the application, no documentation has been added.")
    public ResponseEntity<RestResponse<List<RecommentDTO>>> recommentRestaurant(@RequestBody SuggestedRequest suggestedRequest){
        List<RecommentDTO> recommentDTOS = recommentService.recommentRestaurant(suggestedRequest);
        return ResponseEntity.ok(RestResponse.of(recommentDTOS));

    }
}
