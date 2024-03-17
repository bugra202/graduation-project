package com.bugratasdemir.graduationprojectapi.service;

import com.bugratasdemir.graduationprojectapi.dto.RecommentDTO;
import com.bugratasdemir.graduationprojectapi.request.SuggestedRequest;

import java.util.List;

public interface RecommentService{
    List<RecommentDTO> recommentRestaurant(SuggestedRequest suggestedRequest);
}
