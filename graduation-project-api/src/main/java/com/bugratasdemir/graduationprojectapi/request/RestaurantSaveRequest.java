package com.bugratasdemir.graduationprojectapi.request;

import lombok.Builder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Builder
public record RestaurantSaveRequest(@NotNull @Positive String id,
                                    @NotBlank @Size(min = 2,message = "Must be not null")String name,
                                    @NotNull double latitude,
                                    @NotNull double longitude) {
}
