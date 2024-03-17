package com.bugratasdemir.graduationproject.controller;

import com.bugratasdemir.graduationproject.dto.CommentDTO;
import com.bugratasdemir.graduationproject.general.RestResponse;
import com.bugratasdemir.graduationproject.request.CommentSaveRequest;
import com.bugratasdemir.graduationproject.request.CommentUpdateContentAndScoreRequest;
import com.bugratasdemir.graduationproject.request.UserSaveRequest;
import com.bugratasdemir.graduationproject.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@Tag(name = "Comment Controller", description = "Comment Management")
public class CommentController {

    private final CommentService commentService;
    @PostMapping
    @Operation(
            description = "Creates new comment",
            summary = "Create",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Comment",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CommentSaveRequest.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new comment",
                                                    summary = "Comment",
                                                    description = "Complete request with all available " +
                                                            "fields for comment",
                                                    value = "{\n" +
                                                            "    \"content\": \"test\",\n" +
                                                            "    \"userId\": \"25\",\n" +
                                                            "    \"restaurantId\": \"4\",\n" +
                                                            "    \"commentDate\": \"2016-12-31T07:59:00.000Z\",\n" +
                                                            "    \"rate\": \"FIVE\"\n" +
                                                            "\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    public ResponseEntity<RestResponse<CommentDTO>> save(@Valid @RequestBody CommentSaveRequest request){
        CommentDTO commentDTO = commentService.save(request);
        return ResponseEntity.ok(RestResponse.of(commentDTO));
    }
    @PatchMapping("/{debugCommentId}/contentAndScore")
    @Operation(
            description = "Update content and score",
            summary = "Update Content And Score",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Comment",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CommentUpdateContentAndScoreRequest.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "update content and score",
                                                    summary = "Comment",
                                                    description = "Complete request with all available " +
                                                            "fields for comment",
                                                    value = "{\n" +
                                                            "    \"content\": \"test\",\n" +
                                                            "    \"rate\":\"FIVE\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    public ResponseEntity<RestResponse<CommentDTO>> editContentAndScore(@PathVariable @Positive Long debugCommentId,@Valid @RequestBody CommentUpdateContentAndScoreRequest request){
        CommentDTO commentDTO = commentService.updateTextAndScore(debugCommentId,request);
        return ResponseEntity.ok(RestResponse.of(commentDTO));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete", description = "Delete by comment")
    public void delete(@PathVariable @NotNull @Positive Long id){
        commentService.delete(id);
    }
}
