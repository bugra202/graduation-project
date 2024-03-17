package com.bugratasdemir.graduationprojectapi.dto;

public record RecommentDTO(String id,
                           String name,
                           double latitude,
                           double longitude,
                           double rate) {
}
