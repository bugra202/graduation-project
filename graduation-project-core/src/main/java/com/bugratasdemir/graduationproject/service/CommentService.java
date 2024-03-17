package com.bugratasdemir.graduationproject.service;

import com.bugratasdemir.graduationproject.dto.CommentDTO;
import com.bugratasdemir.graduationproject.request.CommentSaveRequest;
import com.bugratasdemir.graduationproject.request.CommentUpdateContentAndScoreRequest;

public interface CommentService {
    CommentDTO save(CommentSaveRequest request);
    void delete(Long id);
    CommentDTO updateTextAndScore(Long id,CommentUpdateContentAndScoreRequest request);


}
