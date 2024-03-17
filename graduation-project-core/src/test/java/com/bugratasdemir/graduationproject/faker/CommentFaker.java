package com.bugratasdemir.graduationproject.faker;

import com.bugratasdemir.graduationproject.entity.Comment;
import com.bugratasdemir.graduationproject.entity.User;
import com.bugratasdemir.graduationproject.enums.RateState;
import com.bugratasdemir.graduationproject.request.CommentSaveRequest;
import com.bugratasdemir.graduationproject.request.CommentUpdateContentAndScoreRequest;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RequiredArgsConstructor
public class CommentFaker {

    private final Faker faker;
    public Comment createComment(User user) {
        Comment comment = new Comment();
        comment.setId(generateRandomId());
        comment.setRestaurantId(generateRandomId());
        comment.setUser(user);
        comment.setContent(generateRandomText());
        comment.setCommentDate(generateRandomDate());
        comment.setRate(RateState.FIVE);
        return comment;
    }
    public CommentSaveRequest createCommentSaveRequest(User user){
        return CommentSaveRequest.builder()
                .rate(RateState.FIVE)
                .commentDate(generateRandomDate())
                .restaurantId(generateRandomId())
                .content(generateRandomText())
                .userId(user.getId()).build();
    }
    public CommentUpdateContentAndScoreRequest createCommentUpdateRequest(){
        return CommentUpdateContentAndScoreRequest.builder()
                .content(generateRandomText())
                .rate(RateState.ONE).build();

    }

    private Long generateRandomId() {
        return faker.number().randomNumber();
    }
    private LocalDateTime generateRandomDate(){
        Date date = new Date();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay();
    }
    private String generateRandomText(){
        return faker.lorem().paragraph();
    }
}
