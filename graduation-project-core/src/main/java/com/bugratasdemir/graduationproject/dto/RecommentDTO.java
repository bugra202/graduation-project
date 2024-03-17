package com.bugratasdemir.graduationproject.dto;

import lombok.Builder;

@Builder
public record RecommentDTO(String id,
                           String name,
                           double latitude,
                           double longitude,
                           double rate) {
}
