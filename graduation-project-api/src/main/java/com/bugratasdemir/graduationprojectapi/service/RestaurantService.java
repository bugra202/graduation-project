package com.bugratasdemir.graduationprojectapi.service;

import com.bugratasdemir.graduationprojectapi.dto.RestaurantDTO;
import com.bugratasdemir.graduationprojectapi.request.RestaurantSaveRequest;
import com.bugratasdemir.graduationprojectapi.request.RestaurantUpdateRequest;

import java.util.List;

public interface RestaurantService {
    RestaurantDTO save(RestaurantSaveRequest request);
    void delete(String id);
    RestaurantDTO update(RestaurantUpdateRequest request);
    List<RestaurantDTO> findAll();
}
