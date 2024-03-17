package com.bugratasdemir.graduationproject.request;

import com.bugratasdemir.graduationproject.enums.GenderState;
import com.bugratasdemir.graduationproject.enums.UserState;
import jakarta.validation.constraints.*;
import lombok.Builder;
import java.time.LocalDate;

@Builder
public record UserUpdateRequest(@NotNull @Positive Long id,
                                @NotBlank @Size(min = 2,message = "Must be not null")String name,
                                @NotBlank @Size(min = 2,message = "Must be not null")String surname,
                                @NotNull @Past(message = "Date of birth must be in the past tense.")LocalDate birthDate,
                                @Email String email,
                                @NotNull double latitude,
                                @NotNull double longitude,
                                @NotNull GenderState gender,
                                @NotNull UserState status) {
}
