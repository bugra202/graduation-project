package com.bugratasdemir.graduationproject.request;

import com.bugratasdemir.graduationproject.enums.RateState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CommentUpdateContentAndScoreRequest(@NotBlank @Size(min = 2,message = "Must be not null")String content,
                                                  @NotNull RateState rate) {
}
