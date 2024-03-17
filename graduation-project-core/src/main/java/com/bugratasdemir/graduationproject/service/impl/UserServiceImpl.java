package com.bugratasdemir.graduationproject.service.impl;

import com.bugratasdemir.graduationproject.client.RecommentApi;
import com.bugratasdemir.graduationproject.dto.RecommentDTO;
import com.bugratasdemir.graduationproject.dto.UserDTO;
import com.bugratasdemir.graduationproject.entity.User;
import com.bugratasdemir.graduationproject.errormessage.UserErrorMessage;
import com.bugratasdemir.graduationproject.exceptions.ItemNotFoundException;
import com.bugratasdemir.graduationproject.general.RestResponse;
import com.bugratasdemir.graduationproject.mapper.RecommentMapper;
import com.bugratasdemir.graduationproject.mapper.UserMapper;
import com.bugratasdemir.graduationproject.repository.CommentRepository;
import com.bugratasdemir.graduationproject.repository.UserRepository;
import com.bugratasdemir.graduationproject.request.SuggestedRequest;
import com.bugratasdemir.graduationproject.request.UserSaveRequest;
import com.bugratasdemir.graduationproject.request.UserUpdateRequest;
import com.bugratasdemir.graduationproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RecommentApi recommentApi;

    private final CommentRepository commentRepository;

    @Override
    public UserDTO save(UserSaveRequest request) {

            User user = UserMapper.INSTANCE.convertToUser(request);
            user = userRepository.save(user);

            log.info("User registered.");
            return UserMapper.INSTANCE.convertToUserDTO(user);
    }
    @Override
    public UserDTO update(Long id,UserUpdateRequest request) {

        User user = userRepository.findById(id).orElseThrow(()-> new ItemNotFoundException(UserErrorMessage.USER_NOT_FOUND));
        UserMapper.INSTANCE.updateUserFields(user,request);
        userRepository.save(user);

        log.info("User updated.");
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("{}. User deleted ",id);
    }
    @Override
    public List<RecommentDTO> suggestRestaurant(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ItemNotFoundException(UserErrorMessage.USER_NOT_FOUND));

        SuggestedRequest suggestedRequest = prepareSuggestedRequest(user.getId());

        ResponseEntity<RestResponse<List<RecommentDTO>>> recommentDTO = recommentApi.recomments(suggestedRequest);

        log.info("Recommended restaurants were brought.");

        return RecommentMapper.INSTANCE.convertToRecommentDTOs(recommentDTO);
    }

    private SuggestedRequest prepareSuggestedRequest(Long userId){

        Optional<User> user =  userRepository.findById(userId);

        List<Object[]> averageRateByRestaurantId = commentRepository.findAverageRateByRestaurantId();

        return  SuggestedRequest.builder()
                    .latitude(user.get().getLatitude())
                    .longitude(user.get().getLongitude())
                    .restaurantRateAndId(averageRateByRestaurantId).build();
    }
}
