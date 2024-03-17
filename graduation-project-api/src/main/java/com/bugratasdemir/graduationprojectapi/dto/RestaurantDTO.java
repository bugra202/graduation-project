package com.bugratasdemir.graduationprojectapi.dto;

public record RestaurantDTO(String id,
                            String name,
                            double latitude,
                            double longitude,
                            double rate) {
}
