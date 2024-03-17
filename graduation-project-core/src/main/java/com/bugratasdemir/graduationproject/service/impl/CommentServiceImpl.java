package com.bugratasdemir.graduationproject.service.impl;

import com.bugratasdemir.graduationproject.dto.CommentDTO;
import com.bugratasdemir.graduationproject.entity.Comment;
import com.bugratasdemir.graduationproject.entity.User;
import com.bugratasdemir.graduationproject.errormessage.CommentErrorMessage;
import com.bugratasdemir.graduationproject.errormessage.UserErrorMessage;
import com.bugratasdemir.graduationproject.exceptions.ItemNotFoundException;
import com.bugratasdemir.graduationproject.mapper.CommentMapper;
import com.bugratasdemir.graduationproject.repository.CommentRepository;
import com.bugratasdemir.graduationproject.repository.UserRepository;
import com.bugratasdemir.graduationproject.request.CommentSaveRequest;
import com.bugratasdemir.graduationproject.request.CommentUpdateContentAndScoreRequest;
import com.bugratasdemir.graduationproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    @Override
    public CommentDTO save(CommentSaveRequest request) {

        Comment comment = CommentMapper.INSTANCE.convertToComment(request);
        User user = userRepository.findById(request.userId()).orElseThrow(()-> new ItemNotFoundException(UserErrorMessage.USER_NOT_FOUND));
        comment.setUser(user);
        comment = commentRepository.save(comment);

        log.info("Comment registered.");
        return CommentMapper.INSTANCE.convertToCommentDTO(comment);
    }
    @Override
    public CommentDTO updateTextAndScore(Long id, CommentUpdateContentAndScoreRequest request) {

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(CommentErrorMessage.COMMENT_NOT_FOUND));
        comment.setContent(request.content());
        comment.setRate(request.rate());
        comment = commentRepository.save(comment);

        log.info("Comment updated.");
        return CommentMapper.INSTANCE.convertToCommentDTO(comment);
    }
    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
        log.info("{}. Comment deleted ",id);
    }
}