package com.bugratasdemir.graduationprojectapi.request;

import lombok.Builder;
import java.util.List;

@Builder
public record SuggestedRequest(double latitude,
                               double longitude,
                               List<Object[]> restaurantRateAndId) {
}
