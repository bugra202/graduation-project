package com.bugratasdemir.graduationproject.dto;

import com.bugratasdemir.graduationproject.enums.GenderState;
import com.bugratasdemir.graduationproject.enums.UserState;

import java.time.LocalDate;

public record UserDTO(Long id,
                      String name,
                      String surname,
                      LocalDate birthDate,
                      String email,
                      double latitude,
                      double longitude,
                      GenderState gender,
                      UserState status) {
}
