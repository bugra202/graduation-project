package com.bugratasdemir.graduationprojectapi.mapper;

import com.bugratasdemir.graduationprojectapi.dto.RestaurantDTO;
import com.bugratasdemir.graduationprojectapi.entity.Restaurant;
import com.bugratasdemir.graduationprojectapi.request.RestaurantSaveRequest;
import com.bugratasdemir.graduationprojectapi.request.RestaurantUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);
    Restaurant convertToRestaurant(RestaurantSaveRequest request);
    RestaurantDTO convertToRestaurantDTO(Restaurant restaurant);
    @Mapping(target = "id", ignore = true)
    void updateRestaurantFields(@MappingTarget Restaurant restaurant, RestaurantUpdateRequest request);
    List<RestaurantDTO> convertToRestaurantDTOs(List<Restaurant> restaurantList);
}
