package com.bugratasdemir.graduationproject.request;

import com.bugratasdemir.graduationproject.enums.RateState;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentSaveRequest(@NotBlank @Size(min = 2,message = "Must be not null")String content,
                                 @NotNull @Positive Long userId,
                                 @NotNull @Positive Long restaurantId,
                                 @NotNull @Past(message = "Date of comment must be in the past tense.")LocalDateTime commentDate,
                                 @NotNull RateState rate) {
}
