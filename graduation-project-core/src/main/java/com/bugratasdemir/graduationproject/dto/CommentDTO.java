package com.bugratasdemir.graduationproject.dto;

import com.bugratasdemir.graduationproject.enums.RateState;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentDTO(Long id,
                         String content,
                         Long userId,
                         LocalDateTime commentDate,
                         RateState rate
                         ){}
