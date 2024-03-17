package com.bugratasdemir.graduationprojectapi.service.impl;

import com.bugratasdemir.graduationprojectapi.dto.RecommentDTO;
import com.bugratasdemir.graduationprojectapi.entity.Restaurant;
import com.bugratasdemir.graduationprojectapi.repository.RestaurantRepository;
import com.bugratasdemir.graduationprojectapi.request.SuggestedRequest;
import com.bugratasdemir.graduationprojectapi.service.RecommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class RecommentServiceImpl implements RecommentService {

    private final RestaurantRepository restaurantRepository;
    private static final double EARTH_RADIUS = 6371;
    @Override
    public List<RecommentDTO> recommentRestaurant(SuggestedRequest suggestedRequest) {

        List<Restaurant> restaurantList = restaurantList(suggestedRequest.restaurantRateAndId());

        List<RecommentDTO> allRestaurants = recommentDTOList(restaurantList);

        List<RecommentDTO> recommentDTOS = sortAndFilterRestaurants(allRestaurants, suggestedRequest.latitude(), suggestedRequest.longitude());

        return recommentDTOS;
    }
    private List<Restaurant> restaurantList(List<Object[]> restaurantRateAndIdList){

        List<Restaurant> restaurants = restaurantRateAndIdList.stream()
                .map(arr -> restaurantRepository.findById(String.valueOf(((Number) arr[0]).longValue())).orElse(null))
                .filter(Objects::nonNull)
                .toList();

        IntStream.range(0, Math.min(restaurants.size(), restaurantRateAndIdList.size()))
                .forEach(i -> restaurants.get(i).setRate((Double) restaurantRateAndIdList.get(i)[1]));

        return restaurants;
    }

    private List<RecommentDTO> recommentDTOList(List<Restaurant> restaurantList){
        return restaurantList.stream()
                .map(restaurant -> new RecommentDTO(
                        restaurant.getId(),
                        restaurant.getName(),
                        restaurant.getLatitude(),
                        restaurant.getLongitude(),
                        restaurant.getRate()
                ))
                .toList();
    }
    private List<RecommentDTO> sortAndFilterRestaurants(List<RecommentDTO> restaurants, double userLatitude, double userLongitude) {
        double maxDistance = 10.0;
        List<RecommentDTO> filteredRestaurants = restaurants.stream()
                .filter(restaurant -> isWithinDistance(userLatitude, userLongitude, restaurant, maxDistance))
                .sorted(Comparator.comparingDouble(restaurant ->
                        0.7 * restaurant.rate() + 0.3 * calculateDistance(userLatitude, userLongitude, restaurant.latitude(), restaurant.longitude())))
                .toList();

        int numberOfRecommendations = 3;
        return filteredRestaurants.stream()
                .limit(Math.min(numberOfRecommendations, filteredRestaurants.size()))
                .collect(Collectors.toList());
    }
    private boolean isWithinDistance(double userLatitude, double userLongitude, RecommentDTO restaurant, double maxDistance) {
        double distance = calculateDistance(userLatitude, userLongitude, restaurant.latitude(), restaurant.longitude());
        return distance <= maxDistance;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;

        return distance;
    }

}
