package com.bugratasdemir.graduationproject.service.impl;

import com.bugratasdemir.graduationproject.dto.CommentDTO;
import com.bugratasdemir.graduationproject.entity.Comment;
import com.bugratasdemir.graduationproject.entity.User;
import com.bugratasdemir.graduationproject.faker.CommentFaker;
import com.bugratasdemir.graduationproject.faker.UserFaker;
import com.bugratasdemir.graduationproject.repository.CommentRepository;
import com.bugratasdemir.graduationproject.repository.UserRepository;
import com.bugratasdemir.graduationproject.request.CommentSaveRequest;
import com.bugratasdemir.graduationproject.request.CommentUpdateContentAndScoreRequest;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private UserRepository userRepository;
    private final CommentFaker commentFaker = new CommentFaker(Faker.instance());
    private final UserFaker userFaker = new UserFaker(Faker.instance());
    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void shouldValidSave() {
        //given
        User user = userFaker.createUser();
        Comment comment = commentFaker.createComment(user);
        CommentSaveRequest commentSaveRequest = commentFaker.createCommentSaveRequest(user);

        //when
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        //Act
        CommentDTO commentDTO = commentService.save(commentSaveRequest);

        // Assert
        assertNotNull(commentDTO);
        assertEquals(comment.getId(),commentDTO.id());

        // Verify that userReviewRepository.save is called
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void shouldValidUpdateTextAndScore() {
        //given
        User user = userFaker.createUser();
        Comment comment = commentFaker.createComment(user);
        CommentUpdateContentAndScoreRequest commentUpdateContentAndScoreRequest = commentFaker.createCommentUpdateRequest();

        //when
        when(commentRepository.findById(any())).thenReturn(Optional.of(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        //Act
        CommentDTO commentDTO = commentService.updateTextAndScore(comment.getId(),commentUpdateContentAndScoreRequest);

        //assert
        assertNotNull(commentDTO);
        assertEquals(commentUpdateContentAndScoreRequest.content(),commentDTO.content());
        assertEquals(commentUpdateContentAndScoreRequest.rate(),commentDTO.rate());
    }

    @Test
    void shouldValidDelete() {
        //given
        User user = userFaker.createUser();
        Comment comment = commentFaker.createComment(user);
        Long commentId = comment.getId();

        //act
        commentService.delete(commentId);

        //assert
        assertEquals(comment.getId(),commentId);

        // Verify that userRepository's deleteById method is called once with userId argument
        verify(commentRepository, times(1)).deleteById(commentId);
    }
}