package com.bugratasdemir.graduationproject.request;

import lombok.Builder;

@Builder
public record UserLocationRequest(Long userId,
                                  Double latitude,
                                  Double longitude) {
}
