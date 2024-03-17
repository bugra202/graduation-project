package com.bugratasdemir.graduationproject.request;

import lombok.Builder;

import java.util.List;
@Builder
public record SuggestedRequest(double latitude,
                               double longitude,
                               List<Object[]> restaurantRateAndId) {
}
