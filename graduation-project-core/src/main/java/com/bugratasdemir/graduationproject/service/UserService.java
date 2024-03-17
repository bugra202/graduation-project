package com.bugratasdemir.graduationproject.service;

import com.bugratasdemir.graduationproject.dto.RecommentDTO;
import com.bugratasdemir.graduationproject.dto.UserDTO;
import com.bugratasdemir.graduationproject.request.UserSaveRequest;
import com.bugratasdemir.graduationproject.request.UserUpdateRequest;

import java.util.List;

public interface UserService {
    UserDTO save(UserSaveRequest  request);
    void delete(Long id);
    UserDTO update(Long id,UserUpdateRequest request);
    List<RecommentDTO> suggestRestaurant(Long userId);

}
