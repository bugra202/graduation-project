package com.bugratasdemir.graduationproject.controller;

import com.bugratasdemir.graduationproject.dto.RecommentDTO;
import com.bugratasdemir.graduationproject.dto.UserDTO;
import com.bugratasdemir.graduationproject.general.RestResponse;
import com.bugratasdemir.graduationproject.request.UserSaveRequest;
import com.bugratasdemir.graduationproject.request.UserUpdateRequest;
import com.bugratasdemir.graduationproject.service.UserService;
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
import java.util.List;


@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/users")
@Tag(name = "User Controller", description = "User Management")
public class UserController {

    private final UserService userService;
    @PostMapping
    @Operation(
            description = "Creates new user",
            summary = "Create",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UserSaveRequest.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new user",
                                                    summary = "User",
                                                    description = "Complete request with all available " +
                                                            "fields for user",
                                                    value = "{\n" +
                                                            "    \"name\": \"test\",\n" +
                                                            "    \"surname\": \"test\",\n" +
                                                            "    \"email\": \"test@gmail.com\",\n" +
                                                            "    \"birthDate\": \"2000-05-16\",\n" +
                                                            "    \"latitude\": \"152\",\n" +
                                                            "    \"longitude\": \"355\",\n" +
                                                            "    \"status\": \"ACTIVE\",\n" +
                                                            "    \"gender\": \"MALE\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    public ResponseEntity<RestResponse<UserDTO>> save(@Valid @RequestBody UserSaveRequest  request){
        UserDTO userDTO = userService.save(request);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }
    @PutMapping("/{debugUserId}")
    @Operation(
            description = "Update user",
            summary = "Update",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UserUpdateRequest.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "update user",
                                                    summary = "User",
                                                    description = "Complete request with all available " +
                                                            "fields for user",
                                                    value = "{\n" +
                                                            "    \"id\": \"43\",\n" +
                                                            "    \"name\": \"test\",\n" +
                                                            "    \"surname\": \"test\",\n" +
                                                            "    \"birthDate\": \"2019-04-20\",\n" +
                                                            "    \"email\": \"test@gmail.com\",\n" +
                                                            "    \"latitude\": \"37.7749\",\n" +
                                                            "    \"longitude\": \"-122.4194\",\n" +
                                                            "    \"gender\": \"MALE\",\n" +
                                                            "    \"status\" : \"ACTIVE\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    public ResponseEntity<RestResponse<UserDTO>> update(@PathVariable @NotNull Long debugUserId, @Valid @RequestBody UserUpdateRequest request){
        UserDTO userDTO = userService.update(debugUserId,request);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }
    @GetMapping("/restaurants/{userId}")
    @Operation(summary = "Recommend Restaurant",description = "It recommends three restaurants to the user.")
    public ResponseEntity<RestResponse<List<RecommentDTO>>> restaurants(@PathVariable @Positive Long userId){
        List<RecommentDTO> recommentDTOS = userService.suggestRestaurant(userId);
        return ResponseEntity.ok(RestResponse.of(recommentDTOS));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete",description = "Deletes user")
    public void delete(@PathVariable @NotNull @Positive Long id){
        userService.delete(id);
    }
}