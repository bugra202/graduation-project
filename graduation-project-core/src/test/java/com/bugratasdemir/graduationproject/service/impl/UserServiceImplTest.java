package com.bugratasdemir.graduationproject.service.impl;

import com.bugratasdemir.graduationproject.client.RecommentApi;
import com.bugratasdemir.graduationproject.dto.RecommentDTO;
import com.bugratasdemir.graduationproject.dto.UserDTO;
import com.bugratasdemir.graduationproject.entity.User;
import com.bugratasdemir.graduationproject.faker.UserFaker;
import com.bugratasdemir.graduationproject.general.RestResponse;
import com.bugratasdemir.graduationproject.repository.CommentRepository;
import com.bugratasdemir.graduationproject.repository.UserRepository;
import com.bugratasdemir.graduationproject.request.SuggestedRequest;
import com.bugratasdemir.graduationproject.request.UserSaveRequest;
import com.bugratasdemir.graduationproject.request.UserUpdateRequest;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private RecommentApi recommentApi;
    private final UserFaker userFaker = new UserFaker(Faker.instance());
    @InjectMocks
    private UserServiceImpl userService;
    @Test
    void shouldValidSave() {
        //given
        User user = userFaker.createUser();
        UserSaveRequest userSaveRequest = userFaker.createUserSaveRequest();

        //when
        when(userRepository.save(any(User.class))).thenReturn(user);

        //Act
        UserDTO userDTO = userService.save(userSaveRequest);

        // Assert
        assertNotNull(userDTO);
        assertEquals(user.getId(),userDTO.id());

        // Verify that userReviewRepository.save is called
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldValidUpdate() {
        //given
        User user = userFaker.createUser();
        UserUpdateRequest userUpdateRequest = userFaker.createUserUpdateRequest(user);

        //when
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        //Act
        UserDTO userDTO = userService.update(user.getId(),userUpdateRequest);

        //assert
        assertNotNull(userDTO);
        assertEquals(user.getId(),userDTO.id());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldValidDelete() {
        //given
        User user = userFaker.createUser();
        Long userId = user.getId();

        //act
        userService.delete(userId);

        //assert
        assertEquals(user.getId(),userId);

        // Verify that userRepository's deleteById method is called once with userId argument
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void shouldValidSuggestRestaurant() {

        //given
        User user = userFaker.createUser();
        Long userId = user.getId();
        SuggestedRequest suggestedRequest = userFaker.createSuggestedRequest(user);
        List<Object[]> averageRateByRestaurantId = suggestedRequest.restaurantRateAndId();
        List<RecommentDTO> recommentDTO = userFaker.generateRecommentDTOList(3);
        ResponseEntity<RestResponse<List<RecommentDTO>>> mockRecommentDTO = new ResponseEntity<>(new RestResponse<>(recommentDTO,true),HttpStatus.OK);

        //when
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(commentRepository.findAverageRateByRestaurantId()).thenReturn(averageRateByRestaurantId);
        when(recommentApi.recomments(suggestedRequest)).thenReturn(mockRecommentDTO);

        //act
        List<RecommentDTO> result = userService.suggestRestaurant(userId);

        //assert
        assertNotNull(result);

        // Verify that recommentApi's recomments method is called once with suggestedRequest argument
        verify(userRepository, times(2)).findById(userId);
        verify(commentRepository, times(1)).findAverageRateByRestaurantId();
        verify(recommentApi, times(1)).recomments(any());
    }
}