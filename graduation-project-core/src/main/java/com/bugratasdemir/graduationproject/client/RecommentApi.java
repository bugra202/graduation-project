package com.bugratasdemir.graduationproject.client;

import com.bugratasdemir.graduationproject.dto.RecommentDTO;
import com.bugratasdemir.graduationproject.general.RestResponse;
import com.bugratasdemir.graduationproject.request.SuggestedRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@FeignClient(name = "recomment", url = "${baseURL}")
public interface RecommentApi {

    @PostMapping
    ResponseEntity<RestResponse<List<RecommentDTO>>> recomments(@RequestBody SuggestedRequest suggestedRequest);
}
