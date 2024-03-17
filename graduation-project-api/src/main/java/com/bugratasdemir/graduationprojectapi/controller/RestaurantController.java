package com.bugratasdemir.graduationprojectapi.controller;

import com.bugratasdemir.graduationprojectapi.dto.RestaurantDTO;
import com.bugratasdemir.graduationprojectapi.general.RestResponse;
import com.bugratasdemir.graduationprojectapi.request.RestaurantSaveRequest;
import com.bugratasdemir.graduationprojectapi.request.RestaurantUpdateRequest;
import com.bugratasdemir.graduationprojectapi.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/restaurants")
@Tag(name = "Restaurant Controller", description = "Restaurant Management")
public class RestaurantController {

    private final RestaurantService service;
    @PostMapping
    @Operation(
            description = "Creates new restaurant",
            summary = "Create",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurant",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = RestaurantSaveRequest.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new restaurant",
                                                    summary = "Restaurant",
                                                    description = "Complete request with all available " +
                                                            "fields for Restaurant",
                                                    value = "{\n" +
                                                            "    \"id\": \"10\",\n" +
                                                            "    \"name\": \"test\",\n" +
                                                            "    \"latitude\": \"75\",\n" +
                                                            "    \"longitude\": \"80\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    public ResponseEntity<RestResponse<RestaurantDTO>> save(@Valid @RequestBody RestaurantSaveRequest request){
        RestaurantDTO restaurantDTO = service.save(request);
        return ResponseEntity.ok(RestResponse.of(restaurantDTO));
    }
    @GetMapping
    @Operation(summary = "Get all restaurants")
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> findAll(){
        List<RestaurantDTO> restaurantDTOS = service.findAll();
        return ResponseEntity.ok(RestResponse.of(restaurantDTOS));
    }
    @PutMapping("/{debugUserId}")
    @Operation(
            description = "Update restaurant",
            summary = "Update",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurant",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = RestaurantUpdateRequest.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "update restaurant",
                                                    summary = "Restaurant",
                                                    description = "Complete request with all available " +
                                                            "fields for restaurant",
                                                    value = "{\n" +
                                                            "    \"id\": \"15\",\n" +
                                                            "    \"name\": \"test\",\n" +
                                                            "    \"latitude\": \"80\",\n" +
                                                            "    \"longitude\": \"85\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    public ResponseEntity<RestResponse<RestaurantDTO>> update(@Positive @PathVariable Long debugUserId, @Valid @RequestBody RestaurantUpdateRequest request){
        RestaurantDTO restaurantDTO = service.update(request);
        return ResponseEntity.ok(RestResponse.of(restaurantDTO));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete", description = "Delete by restaurant")
    public void delete(@Positive @NotNull @PathVariable String id){
        service.delete(id);
    }
}