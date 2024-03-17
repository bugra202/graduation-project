package com.bugratasdemir.graduationprojectapi.service.impl;

import com.bugratasdemir.graduationprojectapi.dto.RestaurantDTO;
import com.bugratasdemir.graduationprojectapi.entity.Restaurant;
import com.bugratasdemir.graduationprojectapi.errormessage.RestaurantErrorMessage;
import com.bugratasdemir.graduationprojectapi.exceptions.ItemNotFoundException;
import com.bugratasdemir.graduationprojectapi.mapper.RestaurantMapper;
import com.bugratasdemir.graduationprojectapi.repository.RestaurantRepository;
import com.bugratasdemir.graduationprojectapi.request.RestaurantSaveRequest;
import com.bugratasdemir.graduationprojectapi.request.RestaurantUpdateRequest;
import com.bugratasdemir.graduationprojectapi.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;
    @Override
    public RestaurantDTO save(RestaurantSaveRequest request) {
        Restaurant restaurant = RestaurantMapper.INSTANCE.convertToRestaurant(request);

        repository.save(restaurant);

        log.info("Restaurant registered.");
        return RestaurantMapper.INSTANCE.convertToRestaurantDTO(restaurant);
    }
    @Override
    public RestaurantDTO update(RestaurantUpdateRequest request) {

        Restaurant restaurant = repository.findById(request.id()).orElseThrow(()-> new ItemNotFoundException(RestaurantErrorMessage.RESTAURANT_NOT_FOUND));
        RestaurantMapper.INSTANCE.updateRestaurantFields(restaurant,request);
        repository.save(restaurant);

        log.info("Restaurant updated.");
        return RestaurantMapper.INSTANCE.convertToRestaurantDTO(restaurant);
    }
    @Override
    public void delete(String id) {
        repository.deleteById(id);
        log.info("{}. Restaurant deleted ",id);
    }
    @Override
    public List<RestaurantDTO> findAll() {
        Iterable<Restaurant> restaurants = repository.findAll();

        List<Restaurant> restaurantList = new ArrayList<>();
        restaurants.forEach(restaurantList::add);

        log.info("All restaurants were brought.");
        return RestaurantMapper.INSTANCE.convertToRestaurantDTOs(restaurantList);
    }
}
